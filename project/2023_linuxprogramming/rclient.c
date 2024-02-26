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
	printf("�ð��� �ʰ��Ǿ� �ֽ�â �ʱ� ȭ������ ���ư��ϴ�...\n");
	sleep(2);
	system("clear");
	execvp("./login", argv1);
}

int main(int argc, char* argv[])
{
	// �˶� ���� : ���� �ð�(3��) ���� menu�� ���ư�
	signal(SIGALRM, setAlarm);
	alarm(120); // �˶� ����� �߰� �ɶ� ���� �˶� �ð� �ʱ�ȭ
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
	{ /* ���� ��û */
		result = connect(clientfd, &serverUNIXaddr, sizeof(serverUNIXaddr));
		if (result == -1)
			sleep(1);
	} while (result == -1);

	wallet = atof(argv[2]); // ���� �ݾ� �Ǽ�ȭ
	read(clientfd, &n, sizeof(int));
	customer = (struct customer*)malloc(n * sizeof(struct customer));
	customer->wallet = wallet;

	while (1)
	{
		system("clear");
		printf("----------------------------------------------------------\n");
		printf("\t\t     �����ֽ���Ȳ \t\n");
		printf("----------------------------------------------------------\n\n");
		for (int i = 0; i < n; i++)
		{
			read(clientfd, &server, sizeof(struct server));
			printf("[ID]: %d\t �ֽ��̸�:%2s\t [����]:%.1lf\t [����]:%d\n", server.id, server.name, server.cost, server.count);
		}
		printf("\n----------------------------------------------------------\n");
		printf("\n\t\t���� �ڻ� : %.1lf \n", customer->wallet);
		printf("\n");
		printf("----------------------------------------------------------\n");
		printf("\t\t     �����ֽ���Ȳ \t\n");
		printf("----------------------------------------------------------\n");
		printf("[ID]\t[�ֽ��̸�]\t[����]\t[����]\n");
		for (int i = 0; i < n; i++)
		{
			if (customer[i].id != NULL) {
				printf("  %d\t    %s\t        %.1lf   %d\n", customer[i].id, customer[i].name, customer[i].cost, customer[i].count);
			}
		}
		printf("----------------------------------------------------------\n");
		printf("\n\t   (1 - ����) (2 - �Ǹ�) : ");
		scanf("%d", &ch);
		while(ch != 1 && ch != 2) {
			printf("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ��� \n");
			printf("\n\t   (1 - ����) (2 - �Ǹ�) : ");
			scanf(" %d", &ch);
			
		}
		write(clientfd, &ch, sizeof(int)); // �����ǸŰ������� ch ����
		printf("\n----------------------------------------------------------\n");
		while (1)
		{
			if (ch == 1)
			{
				printf("��� �ֽ��� �����Ͻðڽ��ϱ� : ");
				scanf("%d", &s);
				if (s > n)
				{
					printf("�߸��� ID �Դϴ�. �ٽ� �Է����ּ���. \n");
					continue;
				}
				write(clientfd, &s, sizeof(int)); // �ֽ�id ����
				read(clientfd, &server, sizeof(struct server));
				printf(" %s  �ֽ��� ���� �����Ͻðڽ��ϱ� : ", server.name);
				scanf("%d", &cs);
				if (cs > server.count)
				{
					write(clientfd, &zero, sizeof(int));
					printf("������ �߸� �Է��ϼ̽��ϴ�. ���� �ʱ�ȭ������ ���ϴ�. \n");
					sleep(1);
					break;
				}
				else
				{
					if (customer->wallet < (server.cost * cs)) {	// �� ���� / �ŷ� ����
						write(clientfd, &zero, sizeof(int));
						printf(" �ݾ��� �����մϴ�. ���� �ʱ�ȭ������ ���ϴ�. \n");
						sleep(1);
						break;
					}
					write(clientfd, &cs, sizeof(int)); // �ֽ� ���� ����
					printf("  %s  �ֽ��� %d �� ���ſϷ� �ϼ̽��ϴ�. \n", server.name, cs);
					customer[s - 1].id = server.id;
					strcpy(customer[s - 1].name, server.name);
					customer[s - 1].cost = server.cost;
					customer[s - 1].count += cs;
					customer->wallet -= server.cost * cs;
					sleep(1);
					system("clear");
					printf("----------------------------------------------------------\n\n");
					printf("�ֽ�â �ʱ�ȭ������ ���ư��ϴ�...\n\n�ü��� �ҷ����� ���̴� ��ø� ��ٷ��ֽʽÿ�. \n\n");
					printf("----------------------------------------------------------\n");
					sleep(1);
					break;
				}
			}
			else if (ch == 2)
			{
				while (1)
				{
					printf("�Ǹ��� �ֽ� id�� �Է��� �ּ��� \n");
					scanf("%d", &s);
					if (customer[s - 1].id == NULL)
					{
						printf("�߸� �Է��ϼ̽��ϴ�\n");
						printf("ó������ ���ư��ϴ�\n");
						sleep(1);
						continue;
					}
					write(clientfd, &s, sizeof(int));
					read(clientfd, &server, sizeof(struct server));
					printf("%s �ֽ��� ���� �Ǹ��Ͻðڽ��ϱ�? ", server.name);
					scanf(" %d", &cs);
					if (customer[s - 1].count < cs || cs < 0)
					{
						write(clientfd, &zero, sizeof(int));
						printf("������ �߸� �Է��ϼ̽��ϴ�. \n ó������ ���ư��ϴ�. ");
						sleep(1);
						break;
					}
					write(clientfd, &cs, sizeof(int));
					printf(" %s �ֽ��� %d �� �ǸſϷ� �ϼ̽��ϴ�. \n", server.name, cs);
					customer[s - 1].count -= cs;
					customer->wallet += server.cost * cs;
					if (customer[s - 1].count == 0)
						customer[s - 1].id = NULL;
					sleep(1);
					system("clear");
					printf("�ֽ�â �ʱ�ȭ������ ���ư��ϴ�...\n �ü��� �ҷ����� ���̴� ��ø� ��ٷ��ֽʽÿ�. \n");
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
