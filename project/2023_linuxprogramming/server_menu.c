#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>

int main(int argc, char *argv)
{
    int c;
	char* argv1[] = {"rserver",NULL};
	char* argv2[] = {"login",NULL};
	
    system("clear");
	printf("-------------------------------------------\n");
	printf("\n");
    printf("\t   주식 정보 키오스크\n\n");
	printf("-------------------------------------------\n");
	printf("\n");
    printf("\t   1.    생성 \t\n");
    printf("\t   2.   뒤로가기 \t\n");
	printf("\t   3.    종료 \t\n");
	printf("\n");
	printf("-------------------------------------------\n");
	printf("\n");
	printf("\t원하는 메뉴를 선택하세요 : ");
    scanf("%d", &c);
	printf("\n");
	printf("-------------------------------------------\n");
	sleep(1);
    switch(c){
        case 1 :
			system("clear");
			execvp("./rserver", argv1);
			fprintf(stderr,"failed 1\n");
			exit(1);
		
        case 2 :
			system("clear");
			execvp("./login", argv2);
			fprintf(stderr,"failed 1\n");
			exit(1);	
		
		case 3 :
			exit(1);
    }

}