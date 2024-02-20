#include <iostream>
#include <vector>
using namespace std;
void SelectionSort(vector<int>& array) {
    int length = array.size();
    int cnt = 0; // 이동횟수 계산 변수
    for(int i=length-1; i > 0; i--)
    {
        int state = 0; // swap 위치
        int max = array[0];
        for(int j = 1; j <= i; j++)
        {
            if(array[j] > max) {
                state = j;
                max = array[j];
                cnt++; //비교 회수를 센다
            }
        }
        array[state] = array[i]; //swap
        array[i] = max; //swap
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

    SelectionSort(array);


}