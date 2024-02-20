#include <iostream>
#include <vector>
#include <set>
#include <limits>

using namespace std;

const int INF = numeric_limits<int>::max();

int dijkstra(vector<vector<pair<int, int>>> &graph, int start, int end, vector<int> &prev)
{
    int n = graph.size();
    set<pair<int, int>> pq;
    vector<int> distance(n, INF);

    distance[start] = 0;
    pq.insert(make_pair(0, start));

    while (!pq.empty())
    {
        int u = pq.begin()->second;
        pq.erase(pq.begin());

        for (vector<pair<int, int>>::const_iterator it = graph[u].begin(); it != graph[u].end(); ++it)
        {
            int v = it->first;
            int w = it->second;

            if (distance[u] + w < distance[v])
            {
                pq.erase(make_pair(distance[v], v));
                distance[v] = distance[u] + w;
                prev[v] = u;
                pq.insert(make_pair(distance[v], v));
            }
        }
    }

    return distance[end];
}

int main()
{
    int n, m;
    cin >> n >> m;

    vector<vector<pair<int, int>>> graph(n + 1);
    vector<int> prev(n + 1, 0);

    for (int i = 0; i < m; ++i)
    {
        int x, y, w;
        cin >> x >> y >> w;
        graph[x].push_back(make_pair(y, w));
    }

    int start, end;
    cin >> start >> end;

    int shortestDistance = dijkstra(graph, start, end, prev);

    for (int i = 1; i <= n; ++i)
    {
        cout << prev[i] << " ";
    }

    if (shortestDistance == INF)
    {
        cout << "Impossible" << endl;
    }
    else
    {
        cout << shortestDistance << endl;
    }

    return 0;
}
