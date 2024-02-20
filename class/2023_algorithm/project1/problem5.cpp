#include <iostream>
#include <vector>
using namespace std;
int cnt = 0;
int tmp[100000];
void merge(vector<int> &array, int p, int q, int r)
{
    int i = p;
    int j = q+1;
    int t =p;

    while (i <= q && j <= r) {
        if(array[i] < array[j]) {
            tmp[t++] = array[i++];
        }
        else {
            tmp[t++] = array[j++];
        }
        cnt++;
    }
    if(i <= q) {
        while(i<= q) {
            tmp[t++] = array[i++];
        }
    }
    else if(j <= r) {
        while(j <= r) {
            tmp[t++] = array[j++];
        }
    }

    for(int k=p;k <= r; k++) {
        array[k] = tmp[k];
    }
}

void MergeSort(vector<int> &array, int p, int r)
{
    if (p < r) {
        int q = (p + r) / 2;
        MergeSort(array, p, q);
        MergeSort(array, q + 1, r);
        merge(array, p, q, r);
    }
}

int main()
{
    int length;
    cin >> length;
    vector<int> array(length);

    for (int i = 0; i < length; i++)
    {
        cin >> array[i];
    }

    MergeSort(array, 0, length -1);
    cout << cnt;

    return 0;
}
