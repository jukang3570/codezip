#include <iostream>
#include <vector>
#include <set>
#include <climits>

using namespace std;

const int INF = INT_MAX;

class Edge {
public:
    int destination;
    int weight;

    Edge(int dest, int w) : destination(dest), weight(w) {}
};

class Graph {
public:
    int numNodes;
    vector<vector<Edge> > adjList;

    Graph(int n) : numNodes(n), adjList(n + 1) {}

    void addEdge(int u, int v, int w) {
        adjList[u].emplace_back(v, w);
        adjList[v].emplace_back(u, w);
    }
};

int extractMin(set<pair<int, int> >& Q, vector<int>& d, vector<int>& extractedVertices) {
    auto it = Q.begin();
    int minVertex = it->second;
    extractedVertices.push_back(minVertex);
    Q.erase(it);
    return minVertex;
}

void primMST(const Graph& graph, int startNode) {
    set<pair<int, int> > Q;
    vector<int> d(graph.numNodes + 1, INF); 
    vector<int> tree(graph.numNodes + 1, -1);
    vector<int> extractedVertices;

    d[startNode] = 0;
    for (int u = 1; u <= graph.numNodes; ++u) {
        Q.insert(make_pair(d[u], u));
    }

    while (!Q.empty()) {
        int u = extractMin(Q, d, extractedVertices);

        for (const Edge& edge : graph.adjList[u]) {
            int v = edge.destination;
            int w = edge.weight;

            if (Q.count(make_pair(d[v], v)) && w < d[v]) {
                Q.erase(make_pair(d[v], v));
                d[v] = w;
                tree[v] = u;
                Q.insert(make_pair(d[v], v));
            }
        }
    }

    for (int i : extractedVertices) {
        cout << i << " ";
    }
    int totalWeight = 0;
    for (int i = 1; i <= graph.numNodes; ++i) {
        if (i != startNode) {
            totalWeight += d[i];
        }
    }
    cout << totalWeight << endl;
}

int main() {
    int n, m;
    cin >> n >> m;

    Graph graph(n);

    for (int i = 0; i < m; ++i) {
        int x, y, w;
        cin >> x >> y >> w;
        graph.addEdge(x, y, w);
    }

    int startNode = 1; 
    primMST(graph, startNode);

    return 0;
}
