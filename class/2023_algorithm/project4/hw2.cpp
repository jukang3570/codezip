#include <iostream>
#include <vector>
#include <string>

using namespace std;

void computeJump(const string& pattern, vector<int>& jump) {
    int m = pattern.length();
    for (int i = 0; i < 256; ++i) {
        jump[i] = m;
    }
    for (int i = 0; i < m - 1; ++i) {
        jump[pattern[i]] = m - 1 - i;
    }
}

void BoyerMooreHorspool(const string& text, const string& pattern, int& count, int& patternCount) {
    int n = text.length();
    int m = pattern.length();
    
    vector<int> jump(256);
    computeJump(pattern, jump);
    
    int i = 0;
    count = 0;
    patternCount = 0;

    while (i <= n - m) {
        int j = m - 1;
        int k = i + m - 1;

        while (j >= 0 && pattern[j] == text[k]) {
            count ++;
            --j;
            --k;
        }

        if (j == -1) {
            ++patternCount;

        }

        i += jump[text[i + m - 1]];
    }
}

int main() {
    string text, pattern;
    cin >> text >> pattern;

    int count, patternCount;
    BoyerMooreHorspool(text, pattern, count, patternCount);

    cout << count << " " << patternCount << endl;

    return 0;
}
