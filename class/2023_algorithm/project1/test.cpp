#include <iostream>
#include <vector>
using namespace std;

void countingSort(vector<int> &A, vector<int> &B, int n)
{
    int max_val = A[0], min_val = A[0];
    for (int i = 1; i < n; ++i)
    {
        if (A[i] > max_val)
            max_val = A[i];
        if (A[i] < min_val)
            min_val = A[i];
    }

    int k = max_val - min_val + 1;
    vector<int> C(k, 0);

    for (int j = 0; j < n; ++j)
        C[A[j] - min_val]++;

    for (int i = 1; i < k; ++i)
        C[i] += C[i - 1];

    for (int i = 0; i < C.size(); ++i)
        cout << C[i] << " ";

    for (int j = n - 1; j > 0; --j)
    {
        B[C[A[j] - min_val]] = C[A[j]];
        C[A[j] - min_val]--;
    }
}

int main()
{
    int n;
    cin >> n;

    vector<int> A(n);
    for (int i = 0; i < n; ++i)
        cin >> A[i];

    vector<int> B(n);
    countingSort(A, B, n);

    return 0;
}
