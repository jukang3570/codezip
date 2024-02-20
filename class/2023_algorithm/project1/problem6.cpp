#include <iostream>
#include <vector>
using namespace std;
int cnt = 0;

int partition(vector<int> &array, int p, int r)
{
    int tmp = array[r];
    int i = p-1;
    for(int j = p; j < r; j++) {
        if(array[j] <= tmp) {
            swap(array[++i], array[j]);
            cnt++;
        }
    }
    swap(array[i+1], array[r]);
    cnt++;
    return i+1;
}

void QuickSort(vector<int> &array, int p, int r)
{
    if (p < r) {
        int q = partition(array, p, r);
        QuickSort(array,p,q-1);
        QuickSort(array,q+1,r);
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

    QuickSort(array, 0, length -1);
    cout << cnt << endl;

    return 0;
}
