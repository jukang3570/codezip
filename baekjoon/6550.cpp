#include <iostream>

using namespace std;

int main()
{
    string s; //문자열 s 선언
    string t; //문자열 t 선언
    while (cin >> s >> t) //문제에선 문자열을 계속 입력받기 때문에 무한반복으로 s와 t를 입력받는다.
    {
        int check = 0; //부분문자열이 맞는지 확인 0이면 false 1 이면 true
        int j = 0; //부분 문자열 s 위치를 저장하기 위한 변수

        for (int i = 0; i < t.length(); i++) //문자열 s는 t의 일부이기 때문에 t의 전체를 문자열 비교한다.
        {
            if (s[j] == t[i]) //문자열 위치가 같은경우 j++ 을 하여 다음 문자 검사
            {
                j++;
            }
            if (j == s.length()) //j의 값 즉 부분무자열 s의 위치가 모두 도달한경우 check 값을 true로 변환
            {
                check = 1;
            }
        }

        if (check == 1)
        {
            cout << "Yes" << endl; //부분문자열일경우 Yes 출력
        }
        else
        {
            cout << "No" << endl; //부분무자열이 아닌경우 No 출력
        }
    }
    return 0;
}