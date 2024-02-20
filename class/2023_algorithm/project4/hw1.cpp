#include <iostream>
#include <string>
#include <vector>
#include <cmath>
 
using namespace std;
 
void basicRabinKarp(const string& A, const string& P, int d) {
    int n = A.length();
    int m = P.length();
    int p = 0;  
    long long a_1 = 0;
 
    for (int i = 0; i < m; i++) {
        p = d * p + P[i] - 'a';
        a_1 = d * a_1 + A[i] - 'a';
    }
 
    int count = 0; 
    vector<int> positions;  
    if (p == a_1) {
        count++;
        positions.push_back(1);
    }
    cout << a_1 << " ";
 
    int pow_d = 1;
    for (int i = 0; i < m - 1; i++) {
        pow_d *= d;
    }
 
    for (int i = 1; i <= n - m; i++) {
        cout << a_1 << " ";
        a_1 = d * (a_1 - (A[i - 1] - 'a') * pow_d) + A[i + m - 1] - 'a';
 
        if (p == a_1) {
            count++;
            positions.push_back(i + 1);
        }
    }
 
 
    cout << count;
}
 
int main() {
    string A, P;
    cin >> A >> P;
 
    basicRabinKarp(A, P, 10);
 
    return 0;
}