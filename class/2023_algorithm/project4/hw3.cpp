#include <iostream>
#include <vector>
#include <string>

using namespace std;

void preprocessing(const string& pattern, vector<int>& pi) {
    int m = pattern.length();
    pi.resize(m);

    pi[0] = 0;
    int j = 0;

    for (int i = 1; i < m; ++i) {
        while (j > 0 && pattern[i] != pattern[j]) {
            j = pi[j - 1];
        }

        if (pattern[i] == pattern[j]) {
            ++j;
        }

        pi[i] = j;
    }
}

void KMP(const string& text, const string& pattern) {
    vector<int> pi;
    preprocessing(pattern, pi);

    int n = text.length();
    int m = pattern.length();

    int count = 0;
    int i = 0, j = 0;

    bool found = false;

    while (i < n) {
        count++;
        if (j == 0 || text[i] == pattern[j]) {
            ++i;
            ++j;

        } else {
            j = pi[j - 1];
        }
        if (j == m) {
            cout << count << endl;
            j = pi[j - 1];
            found = true;
        }
    }

    if (!found) {
        cout << "fail" << endl;
    }
}

int main() {
    string text, pattern;
    cin >> text >> pattern;

    KMP(text, pattern);

    return 0;
}
