#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    int m;
    cin >> m;

    vector<vector<int> > board(3, vector<int>(m));
    vector<vector<int> > dp(3, vector<int>(m, 0));

    // 입력 받기
    for (int i = 0; i < 3; ++i) {
        for (int j = 0; j < m; ++j) {
            cin >> board[i][j];
        }
    }

    // 초기값 설정
    dp[0][0] = board[0][0];
    dp[1][0] = board[1][0];
    dp[2][0] = board[2][0];

    // 동적 계획법을 사용하여 최대값 찾기
    for (int j = 1; j < m; ++j) {
        for (int i = 0; i < 3; ++i) {
            // 가로로 이동하는 경우
            dp[i][j] = max(dp[i][j], dp[i][j - 1] + board[i][j]);

            // 대각선으로 이동하는 경우
            if (i - 1 >= 0) {
                dp[i][j] = max(dp[i][j], dp[i - 1][j - 1] + board[i][j]);
            }

            // 세로로 이동하는 경우
            if (i + 1 < 3) {
                dp[i][j] = max(dp[i][j], dp[i + 1][j - 1] + board[i][j]);
            }
        }
    }

    // 마지막 열의 최대값 찾기
    int result = max(max(dp[0][m - 1], dp[1][m - 1]), dp[2][m - 1]);

    cout << result << endl;

    return 0;
}
