#include <iostream>
int BubbleCount(int array[], int length);
using namespace std;

int main()
{
    int length;
    int cnt = 0;
    cin >> length; // 배열 크기 입력

    int *array = new int[length]; //동적배열할당

    for (int i = 0; i < length; i++)
        cin >> array[i];

    cnt = BubbleCount(array, length);

    cout << cnt;

    delete[] array;
    return 0;
}

int BubbleCount(int array[], int length) { //Bubblesort 정렬 및 연산 횟수 반환
    int cnt = 0;
    int tmp;
    for(int i = length; i > 1 ; i--) {
        for (int j = 0 ; j < i - 1; j++) {
            if(array[j] > array[j+1])
            {
                tmp = array[j];
                array[j] = array[j+1];
                array[j+1] = tmp;
                cnt++;
            }
        }
    }
    return cnt;
}