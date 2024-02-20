#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>

using namespace std;

void dfs(vector<vector<int> >& graph, vector<bool>& visited, int start) {
    stack<int> s;
    s.push(start);

    while (!s.empty()) {
        int current = s.top();
        s.pop();

        if (!visited[current]) {
            cout << current + 1 << " ";
            visited[current] = true;
        }

        // 정렬을 통해 가장 작은 값부터 방문하도록 함
        sort(graph[current].begin(), graph[current].end(), greater<int>());

        for (size_t i = 0; i < graph[current].size(); ++i) {
            int neighbor = graph[current][i];
            if (!visited[neighbor]) {
                s.push(neighbor);
            }
        }
    }
}

int main() {
    int n, m;
    cin >> n >> m;

    vector<vector<int> > graph(n + 1);
    vector<bool> visited(n + 1, false);

    for (int i = 0; i < m; ++i) {
        int x, y;
        cin >> x >> y;
        graph[x - 1].push_back(y - 1);
        graph[y - 1].push_back(x - 1);  // Assuming the graph is undirected
    }

    int start;
    cin >> start;

    dfs(graph, visited, start - 1);

    cout << endl;

    return 0;
}
