#include <iostream>
#include <vector>
using namespace std;

void countingSort(vector<int>& array, vector<int>& array_B, int length) {
    int max = array[0];
    int min = array[0];

    for(int i=1; i<=length; i++) {
        if(array[i] > max)
            max = array[i];
        if(array[i] < min)
            min = array[i];
    }

    int k = max - min + 1;

    vector<int>array_C(k);

    for(int i=0;i<k; i++){
        array_C[i] = 0;
    }
    for(int j=0; j<length;j++) {
        array_C[array[j] - min]++;
    }
    for(int i= 1; i< k ; i++ )
    {
        array_C[i] = array_C[i] + array_C[i-1];
    }

     for (int i = 0; i <k; i++)
        cout << array_C[i] << " ";

    for(int j = length -1; j >= 0; j--){
        array_B[array_C[array[j]]] = array[j];
        array_C[array[j]]--;
    }
}

int main() {
    int length;
    cin >> length;

    vector<int> array(length);
    for (int i = 0; i < length; ++i) {
        cin >> array[i];
    }
    vector<int> array_B(length);
    countingSort(array, array_B, length);

    return 0;
}