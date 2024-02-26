#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>

void main(int argc, char *argv[])
{
    int c;
	char* argv1[] = {"rclient",argv[1],argv[2],NULL};
	char* argv2[] = {"login",argv[1],argv[2],NULL};
    while (1)
    {   
		printf("-------------------------------------------\n");
		printf("\n");
        printf("\t      구매&판매 메뉴 \n");
		printf("\n");
		printf("-------------------------------------------\n");
		printf("\n");
        printf("\t 1. 시세확인 및 구매&판매 \n");
		printf("\t 2.      뒤로가기 \n");
		printf("\t 3.       종료 \n");
		printf("\n");
		printf("-------------------------------------------\n");
		printf("\n");
		printf("         원하는 메뉴를 선택하세요 : ");
        scanf("%d", &c);
		printf("\n");
		printf("-------------------------------------------\n");
		sleep(1);
		

        switch(c) {
            case 1:
				execvp("./rclient", argv1);
				fprintf(stderr,"failed 1\n");
				exit(1);
            case 2:
				system("clear");
				execvp("./login", argv2);
				fprintf(stderr,"failed 1\n");
				exit(1);
			case 3:
				exit(1);
        }
    }
}