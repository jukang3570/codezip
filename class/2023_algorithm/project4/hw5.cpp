#include <iostream>
#include <string>
#include <cmath>

using namespace std;

int mod(long long x, long long y) {
    return (((x % y) + y) % y);
}

void RabinKarp(const string& A, const string& P, int d, int q) {
    int n = A.length();
    int m = P.length();
    int p = 0, b = 0;
    int count = 0;

    for (int i = 0; i < m; ++i) {
        p = mod((d * p + P[i]- 'a'), q);
        b = mod((d * b + A[i]- 'a'), q);
    }

    int h = mod(pow(d, m - 1), q);

    for (int i = 0; i <= n - m; ++i) {
        cout << b << " ";

        if (i != 0) {
            b = mod((d * (b - h * (A[i - 1] - 'a')) + (A[i + m - 1] - 'a')), q);
        }

        if (p == b) {
            bool match =true;
            for (int j = 0; j < m; ++j) {
                if (P[j] != A[i + j]) {
                    match = false;
                    break;
                } else {
                    match =true;
                }
            }
            if (match) {
                count++;
            }
        }
    }

    cout << count;
}

int main() {
    string A, P;
    cin >> A >> P;

    // Assuming d = 26 and q = 113
    int d = 26;
    int q = 113;

    RabinKarp(A, P, d, q);

    return 0;
}
