#include <iostream>
#include <vector>
#include <queue>
#include <cmath>
#include <iomanip>

using namespace std;

struct Point {
    double f;
    int x, y;

    Point(double _f, int _x, int _y) : f(_f), x(_x), y(_y) {}
};

struct ComparePoints {
    bool operator()(const Point& p1, const Point& p2) {
        return p1.f > p2.f || (p1.f == p2.f && (p1.x > p2.x || (p1.x == p2.x && p1.y > p2.y)));
    }
};

double euclideanDist(const Point& a, const Point& b) {
    double x = a.x - b.x;
    double y = a.y - b.y;
    x = x < 0.0 ? -x : x;
    y = y < 0.0 ? -y : y;
    return sqrt(x * x + y * y);
}

double heuristic(const Point& current, const Point& goal) {
    return euclideanDist(current, goal);
}

void printPath(const vector<vector<int> >& board, const vector<vector<Point> >& cameFrom, const Point& start, const Point& goal) {
    vector<Point> path;
    Point current = goal;

    while (!(current.x == start.x && current.y == start.y)) {
        path.push_back(current);
        current = cameFrom[current.x][current.y];
    }

    path.push_back(start);

    for (int i = path.size() - 1; i >= 0; --i) {
        cout << "(" << path[i].x + 1 << ", " << path[i].y + 1 << ")";
        if (i > 0) cout << " -> ";
    }
    cout << endl;
}

double aStar(const vector<vector<int> >& board, const Point& start, const Point& goal) {
    int n = board.size();
    int m = board[0].size();
    
    vector<vector<double> > g(n, vector<double>(m, -1.0));
    vector<vector<Point> > cameFrom(n, vector<Point>(m, Point(-1.0, -1, -1)));
    
    priority_queue<Point, vector<Point>, ComparePoints> openSet;
    
    g[start.x][start.y] = 0.0;
    openSet.push(start);

    while (!openSet.empty()) {
        Point current = openSet.top();
        openSet.pop();

        if (current.x == goal.x && current.y == goal.y) {
            printPath(board, cameFrom, start, goal);
            return g[current.x][current.y];
        }

        const int dx[] = {-1, -1, -1, 0, 0, 1, 1, 1};
        const int dy[] = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; ++i) {
            int newX = current.x + dx[i];
            int newY = current.y + dy[i];

            if (newX >= 0 && newX < n && newY >= 0 && newY < m && board[newX][newY] == 0) {
                double tentative_g = g[current.x][current.y] + euclideanDist(current, Point(0.0, newX, newY));

                if (g[newX][newY] == -1.0 || tentative_g < g[newX][newY]) {
                    g[newX][newY] = tentative_g;
                    cameFrom[newX][newY] = current;

                    double f = tentative_g + heuristic(Point(0.0, newX, newY), goal);
                    openSet.push(Point(f, newX, newY));
                }
            }
        }
    }

    cout << "-1" << endl;
    return -1.0;
}

int main() {
    int n, m;
    cin >> n >> m;

    vector<vector<int> > board(n, vector<int>(m));
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            cin >> board[i][j];
        }
    }

    int x1, y1, x2, y2;
    cin >> x1 >> y1 >> x2 >> y2;

    Point start(0.0, x1 - 1, y1 - 1);
    Point goal(0.0, x2 - 1, y2 - 1);

    double shortestPath = aStar(board, start, goal);
    if (shortestPath != -1.0) {
        cout << fixed << setprecision(2) << shortestPath << endl;
    }

    return 0;
}
