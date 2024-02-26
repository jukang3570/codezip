#include <stdio.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <sys/un.h>
#include <stdlib.h>
#include <fcntl.h>
#include <sys/mman.h> // mmap 헤더 추가
#include "server.h"

#define DEFAULT_PROTOCOL 0
#define MAXLINE 100
int main(int argc, char *argv[])
{
    int listenfd, connfd, clientlen;
    char inmsg[MAXLINE], outmsg[MAXLINE];
    int idx, i, n, ch, num, s, cs;
    struct sockaddr_un serverUNIXaddr, clientUNIXaddr;
    struct server *server;
    int *shared_n; // 공유 변수 포인터 추가
    struct server *shared_server; // 공유 구조체 포인터 추가

    // 서버 주식 설정
	printf("-------------------------------------------\n");
	printf("\n");
    printf("     설정할 주식 개수를 입력하세요 : ");
    scanf("%d", &n);
	printf("\n");
	printf("-------------------------------------------\n");

    server = (struct server *)malloc(n * sizeof(struct server));
	printf("\n");
    printf("\t %d개의 주식을 설정합니다.\n", n);
	printf("\n");
	printf("-------------------------------------------\n");
	sleep(2);
	system("clear");
    printf("%-4s %-9s %-8s %-4s\n", "[ID]", "[주식이름]", "[가격]", "[개수]");
    for (i = 0; i < n; i++)
    {
        printf("   %d   ", i + 1);
        server[i].id = i + 1;
        scanf("%s %lf %d", server[i].name, &server[i].cost, &server[i].count);
    }

    // 파일 매핑을 통해 공유 변수와 공유 구조체 설정
    shared_n = (int *)mmap(NULL, sizeof(int), PROT_READ | PROT_WRITE, MAP_SHARED | MAP_ANONYMOUS, -1, 0);
    shared_server = (struct server *)mmap(NULL, n * sizeof(struct server), PROT_READ | PROT_WRITE, MAP_SHARED | MAP_ANONYMOUS, -1, 0);
    *shared_n = n;
    memcpy(shared_server, server, n * sizeof(struct server));

    // 서버 설정
    signal(SIGCHLD, SIG_IGN);
    clientlen = sizeof(clientUNIXaddr);

    listenfd = socket(AF_UNIX, SOCK_STREAM, DEFAULT_PROTOCOL);
    serverUNIXaddr.sun_family = AF_UNIX;
    strcpy(serverUNIXaddr.sun_path, "server");
    unlink("server");
    bind(listenfd, &serverUNIXaddr, sizeof(serverUNIXaddr));
    listen(listenfd, 5);

    while (1)
    { /* 소켓 연결 요청 수락 */
        connfd = accept(listenfd, &clientUNIXaddr, &clientlen);
        if (fork() == 0)
        {
            write(connfd, shared_n, sizeof(int));
            while (1)
            {
                for (int i = 0; i < *shared_n; i++)
                {
                    write(connfd, &shared_server[i], sizeof(struct server));
                }
                read(connfd, &ch, sizeof(int)); // 구매판매결정변수 ch 받음
                while (1)
                {
                    if (ch == 1)
                    {
                        read(connfd, &s, sizeof(int)); // 주식id입력받음
                        write(connfd, &shared_server[s - 1], sizeof(struct server));
                        read(connfd, &cs, sizeof(int));
                        shared_server[s - 1].count -= cs;
                        break;
                    }
                    else if(ch == 2)
                    {
                        read(connfd, &s, sizeof(int)); // 주식id입력받음
                        write(connfd, &shared_server[s - 1], sizeof(struct server));
                        read(connfd, &cs, sizeof(int));
                        shared_server[s - 1].count += cs;
                        break;
                    }
                }
                continue;
            }
            close(connfd);
            exit(0);
        }
        else
            close(connfd);
    }
}
