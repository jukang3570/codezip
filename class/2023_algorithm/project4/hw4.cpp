#include <iostream>
#include <string>

using namespace std;

void naiveMatching(const string& A, const string& P, int& comparisons, int& matches) {
    int n = A.length();
    int m = P.length();

    comparisons = 0;
    matches = 0;

    for (int i = 0; i <= n - m; ++i) {
        bool isMatch = true;

        for (int j = 0; j < m; ++j) {
            comparisons++;

            if (P[j] != A[i + j]) {
                isMatch = false;
                break;
            }
        }

        if (isMatch) {
            matches++;
        }
    }
}

int main() {
    string A, P;
    cin >> A >> P;

    int comparisons, matches;
    naiveMatching(A, P, comparisons, matches);

    cout << comparisons << " " << matches << endl;

    return 0;
}
