# 사용 방법

## 컴파일 ( gcc -o 파일이름 파일이름.c )

gcc -o login login.c
gcc -o customer_menu customer_menu.c
gcc -o server_menu server_menu.c
gcc -o rserver rserver.c
gcc -o rclient rclient.c

### 실행순서 (서버 - 관리자)

1. ./login
2. 이름입력, 유형 1 입력, 비밀번호 2314 입력
3. 주식 정보 키오스크에서 1 입력 후 서버 생성
  
#### 실행순서 (클라이언트 - 구매자)

1. ./login
2. 이름입력, 유형 0 입력, 초기 자본 입력
3. 구매&판매 메뉴에서 1 입력 후 구매 진행

##### 파일 설명
login.c  - 로그인 화면
customer_menu.c  - 구매&판매 메뉴
server_menu.c  - 서버 메뉴
rserver.c - 서버
rclient.c - 클라이언트