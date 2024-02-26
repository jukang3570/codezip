#include <stdio.h>
#include <signal.h>
#include <sys/types.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <sys/un.h>
#include <signal.h>
#include <fcntl.h>
#include "server.h"
#include "customer.h"

#define DEFAULT_PROTOCOL 0
#define MAXLINE 100
char* argv1[] = {};

void setAlarm()
{
	system("clear");
	printf("시간이 초과되어 주식창 초기 화면으로 돌아갑니다...\n");
	sleep(2);
	system("clear");
	execvp("./login", argv1);
}

int main(int argc, char* argv[])
{
	// 알람 설정 : 일정 시간(3분) 이후 menu로 돌아감
	signal(SIGALRM, setAlarm);
	alarm(120); // 알람 명령이 추가 될때 마다 알람 시간 초기화
	int zero = 0;
	int clientfd, result, idx, n, ch, num, s, cs;
	double wallet;
	char c, c3;
	char inmsg[MAXLINE], outmsg[MAXLINE];
	struct customer* customer;
	struct server server;
	struct sockaddr_un serverUNIXaddr;

	char* argv1[] = { "login", argv[1], argv[2], NULL };
	clientfd = socket(AF_UNIX, SOCK_STREAM, DEFAULT_PROTOCOL);
	serverUNIXaddr.sun_family = AF_UNIX;
	strcpy(serverUNIXaddr.sun_path, "server");

	do
	{ /* 연결 요청 */
		result = connect(clientfd, &serverUNIXaddr, sizeof(serverUNIXaddr));
		if (result == -1)
			sleep(1);
	} while (result == -1);

	wallet = atof(argv[2]); // 지갑 금액 실수화
	read(clientfd, &n, sizeof(int));
	customer = (struct customer*)malloc(n * sizeof(struct customer));
	customer->wallet = wallet;

	while (1)
	{
		system("clear");
		printf("----------------------------------------------------------\n");
		printf("\t\t     서버주식현황 \t\n");
		printf("----------------------------------------------------------\n\n");
		for (int i = 0; i < n; i++)
		{
			read(clientfd, &server, sizeof(struct server));
			printf("[ID]: %d\t 주식이름:%2s\t [가격]:%.1lf\t [개수]:%d\n", server.id, server.name, server.cost, server.count);
		}
		printf("\n----------------------------------------------------------\n");
		printf("\n\t\t현재 자산 : %.1lf \n", customer->wallet);
		printf("\n");
		printf("----------------------------------------------------------\n");
		printf("\t\t     보유주식현황 \t\n");
		printf("----------------------------------------------------------\n");
		printf("[ID]\t[주식이름]\t[가격]\t[개수]\n");
		for (int i = 0; i < n; i++)
		{
			if (customer[i].id != NULL) {
				printf("  %d\t    %s\t        %.1lf   %d\n", customer[i].id, customer[i].name, customer[i].cost, customer[i].count);
			}
		}
		printf("----------------------------------------------------------\n");
		printf("\n\t   (1 - 구매) (2 - 판매) : ");
		scanf("%d", &ch);
		while(ch != 1 && ch != 2) {
			printf("잘못 입력하셨습니다. 다시 입력해주세요 \n");
			printf("\n\t   (1 - 구매) (2 - 판매) : ");
			scanf(" %d", &ch);
			
		}
		write(clientfd, &ch, sizeof(int)); // 구매판매결정변수 ch 전송
		printf("\n----------------------------------------------------------\n");
		while (1)
		{
			if (ch == 1)
			{
				printf("몇번 주식을 구매하시겠습니까 : ");
				scanf("%d", &s);
				if (s > n)
				{
					printf("잘못된 ID 입니다. 다시 입력해주세요. \n");
					continue;
				}
				write(clientfd, &s, sizeof(int)); // 주식id 전송
				read(clientfd, &server, sizeof(struct server));
				printf(" %s  주식을 몇주 구매하시겠습니까 : ", server.name);
				scanf("%d", &cs);
				if (cs > server.count)
				{
					write(clientfd, &zero, sizeof(int));
					printf("개수를 잘못 입력하셨습니다. 구매 초기화면으로 들어갑니다. \n");
					sleep(1);
					break;
				}
				else
				{
					if (customer->wallet < (server.cost * cs)) {	// 돈 부족 / 거래 실패
						write(clientfd, &zero, sizeof(int));
						printf(" 금액이 부족합니다. 구매 초기화면으로 들어갑니다. \n");
						sleep(1);
						break;
					}
					write(clientfd, &cs, sizeof(int)); // 주식 개수 전송
					printf("  %s  주식을 %d 개 구매완료 하셨습니다. \n", server.name, cs);
					customer[s - 1].id = server.id;
					strcpy(customer[s - 1].name, server.name);
					customer[s - 1].cost = server.cost;
					customer[s - 1].count += cs;
					customer->wallet -= server.cost * cs;
					sleep(1);
					system("clear");
					printf("----------------------------------------------------------\n\n");
					printf("주식창 초기화면으로 돌아갑니다...\n\n시세를 불러오는 중이니 잠시만 기다려주십시요. \n\n");
					printf("----------------------------------------------------------\n");
					sleep(1);
					break;
				}
			}
			else if (ch == 2)
			{
				while (1)
				{
					printf("판매할 주식 id를 입력해 주세요 \n");
					scanf("%d", &s);
					if (customer[s - 1].id == NULL)
					{
						printf("잘못 입력하셨습니다\n");
						printf("처음으로 돌아갑니다\n");
						sleep(1);
						continue;
					}
					write(clientfd, &s, sizeof(int));
					read(clientfd, &server, sizeof(struct server));
					printf("%s 주식을 몇주 판매하시겠습니까? ", server.name);
					scanf(" %d", &cs);
					if (customer[s - 1].count < cs || cs < 0)
					{
						write(clientfd, &zero, sizeof(int));
						printf("개수를 잘못 입력하셨습니다. \n 처음으로 돌아갑니다. ");
						sleep(1);
						break;
					}
					write(clientfd, &cs, sizeof(int));
					printf(" %s 주식을 %d 개 판매완료 하셨습니다. \n", server.name, cs);
					customer[s - 1].count -= cs;
					customer->wallet += server.cost * cs;
					if (customer[s - 1].count == 0)
						customer[s - 1].id = NULL;
					sleep(1);
					system("clear");
					printf("주식창 초기화면으로 돌아갑니다...\n 시세를 불러오는 중이니 잠시만 기다려주십시요. \n");
					sleep(1);
					break;
				}
				break;
			}
			
		}
	}

	close(clientfd);
	exit(0);
}
