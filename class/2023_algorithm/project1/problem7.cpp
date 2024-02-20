#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int getDigit(int num, int digit)
{
    int result = 1;
    for(int i =0; i< digit; i++) {
        result *= 10;
    }

    return (num / result) % 10;
}

void radixSort(vector<int> &array, int n, int t)
{
    queue<int> que[10];

    for (int i = 0; i < t; i++)
    {
        for (int j = 0; j < n; j++)
        {
            int d = getDigit(array[j], i);
            que[d].push(array[j]);
        }

        int p = 0;
        for (int j = 0; j < 10; j++)
        {
            while (!que[j].empty())
            {
                array[p++] = que[j].front();
                que[j].pop();
            }
        }
    }

    for (int j = 0; j < n; j++)
    {
        cout << array[j] << " ";
    }
}

int main()
{
    int length, digit;
    cin >> length >> digit;

    vector<int> array(length);
    for (int i = 0; i < length; i++)
    {
        cin >> array[i];
    }

    radixSort(array, length, digit);

    return 0;
}
