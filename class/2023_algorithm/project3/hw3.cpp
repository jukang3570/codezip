#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int minTimeToSchool(const vector<vector<int> >& map) {
    int n = map.size();
    int m = map[0].size();

    vector<vector<int> > dp(n, vector<int>(m, 0));

    dp[0][0] = map[0][0];
    for (int j = 1; j < m; ++j) {
        dp[0][j] = dp[0][j - 1] + map[0][j];
    }

    for (int i = 1; i < n; ++i) {
        dp[i][0] = dp[i - 1][0] + map[i][0];
    }

    for (int i = 1; i < n; ++i) {
        for (int j = 1; j < m; ++j) {
            dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + map[i][j];
        }
    }

    return dp[n - 1][m - 1];
}

int main() {
    int n, m;
    cin >> n >> m;

    vector<vector<int> > map(n, vector<int>(m));
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            cin >> map[i][j];
        }
    }

    int result = minTimeToSchool(map);
    cout << result << endl;

    return 0;
}
