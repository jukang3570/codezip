#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

void bfs(vector<vector<int> >& graph, int start) {
    int n = graph.size();
    vector<bool> visited(n, false);

    queue<int> q;
    q.push(start);
    visited[start - 1] = true;

    while (!q.empty()) {
        int current = q.front();
        q.pop();
        cout << current << " ";

        sort(graph[current - 1].begin(), graph[current - 1].end());

        for (int i = 0; i < graph[current - 1].size(); ++i) {
            int neighbor = graph[current - 1][i];
            if (!visited[neighbor - 1]) {
                q.push(neighbor);
                visited[neighbor - 1] = true;
            }
        }
    }
}

int main() {
    int n, m;
    cin >> n >> m;

    vector<vector<int> > graph(n);

    for (int i = 0; i < m; ++i) {
        int x, y;
        cin >> x >> y;
        graph[x - 1].push_back(y);
        graph[y - 1].push_back(x);
    }

    int start;
    cin >> start;

    bfs(graph, start);

    return 0;
}
