#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include "login.h"
#define MAX 40

int main(int argc, char *argv[])
{
	// 사용할 변수 정의
	int idx = 0; 
	int  flag, res, password;
	char name[MAX],init_m[MAX];
	char* argv1[] = {"customer_menu",name,init_m,NULL};
	char* argv2[] = {"server_menu",NULL};
	
	// 초기 선택 화면
	while(1){
		printf("-------------------------------------------\n");
		printf("\n");
		printf("\t 사용자 이름을 입력해주세요.\n");
		printf("\n");
		printf("-------------------------------------------\n");
		printf("\n");
		printf("\t    이름 : ");
		scanf("%s", name);
		printf("\n");
		printf("-------------------------------------------\n");
		printf("\n");
		printf("\t구매&판매 - 0, 관리자 - 1\n\n");
		printf("\t유형 선택을 해주세요 : ");
		scanf("%d", &flag);
		printf("\n");
		printf("-------------------------------------------\n");
		
		if (flag == 0){
			printf("\n");
			printf("      초기 자본을 입력하세요 : ");
			scanf(" %s",init_m);
			printf("\n");
			printf("-------------------------------------------\n");
			sleep(1);
		}
		
		// 유형에 따라 화면 전환 
		if(flag == 0)
		{
			system("clear");
			execvp("./customer_menu", argv1);
			fprintf(stderr,"failed\n");
			exit(1);
		}
		else if(flag == 1){
			printf("\n");
			printf("\t비밀번호를 입력해주세요 : ");
			scanf(" %d",&password);
			printf("\n");
			if ( password == 2314){
				system("clear");
				execvp("./server_menu", argv2);
				fprintf(stderr,"failed\n");
				exit(1);
			}
			else
				printf("-------------------------------------------\n");
				printf("\n");
				printf("\t 비밀번호가 틀렸습니다.\n");
				printf("\n");
				printf("-------------------------------------------\n");
				sleep(2);
				system("clear");
				continue;
		}
		else {
			system("clear");
			printf("-------------------------------------------\n");
			printf("\n");
			printf("\t   잘못 입력하셨습니다.\n");
			printf("\n");
			printf("-------------------------------------------\n");
			sleep(1);
			exit(1);
		}
	}
	return 0;
}