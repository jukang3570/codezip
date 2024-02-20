#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct Edge
{
    int u, v, weight;
};

struct DSU
{
    vector<int> parent, rank;

    DSU(int n)
    {
        parent.resize(n);
        rank.resize(n, 0);
        for (int i = 0; i < n; ++i)
        {
            parent[i] = i;
        }
    }

    int find(int x)
    {
        if (parent[x] != x)
        {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    void unionSets(int x, int y)
    {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY)
        {
            if (rank[rootX] < rank[rootY])
            {
                parent[rootX] = rootY;
            }
            else if (rank[rootX] > rank[rootY])
            {
                parent[rootY] = rootX;
            }
            else
            {
                parent[rootX] = rootY;
                rank[rootY]++;
            }
        }
    }
};

bool compareEdges(const Edge &a, const Edge &b)
{
    return a.weight < b.weight;
}

vector<int> kruskal(vector<Edge> &edges, int n, int &totalWeight)
{
    DSU dsu(n);
    vector<int> result;

    sort(edges.begin(), edges.end(), compareEdges);

    for (vector<Edge>::iterator it = edges.begin(); it != edges.end(); ++it)
    {
        int u = it->u;
        int v = it->v;
        int weight = it->weight;

        if (dsu.find(u) != dsu.find(v))
        {
            result.push_back(weight);
            totalWeight += weight;
            dsu.unionSets(u, v);
        }
    }

    return result;
}

int main()
{
    int n, m;
    cin >> n >> m;

    vector<Edge> edges(m);
    for (int i = 0; i < m; ++i)
    {
        cin >> edges[i].u >> edges[i].v >> edges[i].weight;
    }

    int totalWeight = 0;
    vector<int> mstWeights = kruskal(edges, n, totalWeight);

    for (int i = 0; i < mstWeights.size(); ++i)
    {
        cout << mstWeights[i] << " ";
    }

    cout << totalWeight << endl;

    return 0;
}
