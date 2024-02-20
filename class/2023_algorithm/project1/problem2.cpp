#include <iostream>
#include <vector>

using namespace std;
void insertionSort(vector<int>& array) {
    int length = array.size();
    int cnt = 0; // 이동횟수 계산 변수
    for (int i = 1; i < length; i++) {
        int key = array[i]; // 현재 비교값 백업
        int state = i -1; //키값을 비교할 위치

        while(state >= 0 && array[state] > key) {
            cnt++; //삽입 횟수 적립
            array[state + 1] = array[state];

            state--;
        }
        cnt++; //오른쪽으로 이동횟수 적립
        array[state+1] = key;
        
    }
    cout << cnt<<endl;
}

int main(){
    int length;
    cin >> length;
    vector<int> array(length);

    for(int  i = 0; i<length; i++){
        cin >> array[i];
    }

    insertionSort(array);


}