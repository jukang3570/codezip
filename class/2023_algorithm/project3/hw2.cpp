#include <iostream>
#include <vector>
#include <algorithm>
 
#define HEIGHT 3
#define WIDTH 100
#define TRUE 1
#define FALSE 0
#define PATTERN_COUNT 4
 
using namespace std;
 
vector<vector<int> > g_Table(HEIGHT, vector<int>(WIDTH));
 
vector<vector<int> > g_DT(WIDTH, vector<int>(PATTERN_COUNT + 1, -1));
 
 
int PebbleDT(void);
 
int Together(int curPattern, int nextPattern);
 
int PatternPoint(int i, int curPattern);
 
int main() {
    int m;
    cin >> m;
 
    for (int i = 0; i < HEIGHT; ++i) {
        for (int j = 0; j < m; ++j) {
            cin >> g_Table[i][j];
        }
    }
 
    int maxSum = PebbleDT();
 
    cout << maxSum << endl;
 
    return 0;
}
 
int PebbleDT() {
    int curPattern;
    int prevPattern;
    int high = 0;
    int value;
    int i;
 
    for (curPattern = 1; curPattern <= PATTERN_COUNT; curPattern++) {
        g_DT[0][curPattern] = PatternPoint(0, curPattern);
    }
 
    for (i = 1; i < g_Table[0].size(); i++) {
        for (curPattern = 1; curPattern <= PATTERN_COUNT; curPattern++) {
            high = 0;  // 수정된 부분: high 값을 각 열마다 초기화해주어야 합니다.
 
            for (prevPattern = 1; prevPattern <= PATTERN_COUNT; prevPattern++) {
                if (Together(curPattern, prevPattern) == TRUE) {
                    value = g_DT[i - 1][prevPattern] + PatternPoint(i, curPattern);
 
                    if (value > high)
                        high = value;
                }
            }
            g_DT[i][curPattern] = high;
        }
    }
 
    int maxPoint = g_DT[g_Table[0].size() - 1][1];
    for (int col = 2; col <= PATTERN_COUNT; col++) {
        if (g_DT[g_Table[0].size() - 1][col] > maxPoint)
            maxPoint = g_DT[g_Table[0].size() - 1][col];  // 수정된 부분: 누적 합이 아닌 최대값을 저장해야 합니다.
    }
 
    return maxPoint;
}
 
int Together(int curPattern, int nextPattern) {
    if (curPattern == nextPattern)
        return FALSE;
 
    if (curPattern == 4) {
        if (nextPattern == 2)
            return TRUE;
    } else if (curPattern == 3) {
        if (nextPattern == 1 || nextPattern == 2)
            return TRUE;
    } else if (curPattern == 2) {
        if (nextPattern != 2)
            return TRUE;
    } else if (curPattern == 1) {
        if (nextPattern == 3 || nextPattern == 2)
            return TRUE;
    }
 
    return FALSE;
}
 
int PatternPoint(int i, int curPattern) {
    if (curPattern == 1)
        return g_Table[0][i];
 
    else if (curPattern == 2)
        return g_Table[1][i];
 
    else if (curPattern == 3)
        return g_Table[2][i];
 
    else if (curPattern == 4)
        return g_Table[0][i] + g_Table[2][i];
 
    return 0;
}
