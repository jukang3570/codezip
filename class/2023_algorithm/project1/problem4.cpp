#include <iostream>
#include <vector>
using namespace std;
int cnt = 0;

void heapify(vector<int>& array, int length, int state) {
    int max = 0; // 가장 큰 값 (root)의 위치
    int left = 2 * state + 1; // 왼쪽
    int right = 2 * state + 2; // 오른쪽

    if (right < length) {
        if(array[left] > array[right]) {
            max = left;
        }
        else
            max = right;
    } // 왼쪽 자식이 더 큰 경우

    else if (left < length) {
        max= left;
    }
    else return;

    if (array[max] > array[state]) {
        swap(array[state], array[max]);
        cnt++;
        heapify(array, length, max);
    }
}

void maxheap(vector<int>& array, int length) {
    for (int i = length / 2 - 1; i >= 0; i--) {
        heapify(array, length, i);
    }
}

void HeapSort(vector<int>& array) {
    int length = array.size();

    maxheap(array, length);
    cnt =0;

    for (int i = length - 1; i > 0; i--) {
        swap(array[0], array[i]);
        heapify(array, i, 0);
    }

    cout << cnt << endl;
}

int main() {
    int length;
    cin >> length;
    vector<int> array(length);

    for (int i = 0; i < length; i++) {
        cin >> array[i];
    }

    HeapSort(array);

    for (int i=0; i<length;i++) {
        cout << array[i] << " ";
    }

    return 0;
}
