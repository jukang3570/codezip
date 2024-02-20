#include <iostream>
#include <vector>

using namespace std;

const int MAX_N = 100;

int n, m, k;
vector<int> adj[MAX_N + 1]; 
int color[MAX_N + 1]; 

bool valid(int i, int c) {
    for (int j = 0; j < adj[i].size(); ++j) {
        int neighbor = adj[i][j];
        if (color[neighbor] == c) {
            return false;
        }
    }
    return true;
}

bool k_coloring(int i, int c) {
    cout << "(" << i << ", " << c << ")" << endl;

    if (valid(i, c)) {
        color[i] = c;
        if (i == n) {
            return true;
        } else {
            for (int d = 1; d <= k; ++d) {
                if (k_coloring(i + 1, d)) {
                    return true;
                }
            }
        }
        color[i] = 0; 
        return false;
    } else {
        return false;
    }
}

int main() {
    cin >> n >> m >> k;
    for (int i = 0; i < m; ++i) {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    bool isPossible = false;
    for (int d = 1; d <= k; ++d) {
        if (k_coloring(1, d)) {
            isPossible = true;
            break;
        }
    }

    if (isPossible) {
        cout << "possible" << endl;
    } else {
        cout << "impossible" << endl;
    }

    return 0;
}
