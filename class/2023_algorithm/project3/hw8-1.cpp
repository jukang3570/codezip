#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct Edge {
    int u, v, weight;

    bool operator<(const Edge& other) const {
        return weight < other.weight;
    }
};

struct Subset {
    int parent, rank;
};

int find(Subset subsets[], int i) {
    if (subsets[i].parent != i)
        subsets[i].parent = find(subsets, subsets[i].parent);
    return subsets[i].parent;
}

void unionSets(Subset subsets[], int x, int y) {
    int xroot = find(subsets, x);
    int yroot = find(subsets, y);

    if (subsets[xroot].rank < subsets[yroot].rank)
        subsets[xroot].parent = yroot;
    else if (subsets[xroot].rank > subsets[yroot].rank)
        subsets[yroot].parent = xroot;
    else {
        subsets[yroot].parent = xroot;
        subsets[xroot].rank++;
    }
}

void kruskal(vector<Edge>& edges, int n) {
    vector<Edge> result;

    Subset* subsets = new Subset[n + 1];

    for (int i = 1; i <= n; i++) {
        subsets[i].parent = i;
        subsets[i].rank = 0;
    }

    sort(edges.begin(), edges.end());

    int totalWeight = 0;

    for (int i = 0; i < edges.size(); i++) {
        int u = edges[i].u;
        int v = edges[i].v;

        int setU = find(subsets, u);
        int setV = find(subsets, v);

        if (setU != setV) {
            result.push_back(edges[i]);
            totalWeight += edges[i].weight;
            unionSets(subsets, setU, setV);
        }
    }

    for (const Edge& edge : result) {
        cout << edge.weight << " ";
    }
    cout << totalWeight << endl;

    delete[] subsets;
}

int main() {
    int n, m;
    cin >> n >> m;

    vector<Edge> edges;

    for (int i = 0; i < m; i++) {
        Edge edge;
        cin >> edge.u >> edge.v >> edge.weight;
        edges.push_back(edge);
    }

    kruskal(edges, n);

    return 0;
}
