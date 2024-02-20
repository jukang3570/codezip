#include <iostream>
#include <vector>
#include <algorithm>

int lcs_length(const std::string& X, const std::string& Y) {
    int m = X.length();
    int n = Y.length();

    std::vector<std::vector<int> > dp(m + 1, std::vector<int>(n + 1, 0));

    for (int i = 1; i <= m; ++i) {
        for (int j = 1; j <= n; ++j) {
            if (X[i - 1] == Y[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
            } else {
                dp[i][j] = std::max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }

    return dp[m][n];
}

int main() {
    std::string a, b;
    std::cin >> a >> b;

    int result = lcs_length(a, b);
    std::cout << result << std::endl;

    return 0;
}
