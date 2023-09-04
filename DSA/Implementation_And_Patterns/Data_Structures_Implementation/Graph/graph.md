# Graph

## Two types of representation are possible

- Adjacency Matrix
- Adjacency List

Adjacency Matrix:
- Space Complexity: O(|V|^2)
- Adding a Vertex: O(|V|^2)
- Adding an Edge: O(1)
- Removing a Vertex: O(|V|^2)
- Removing an Edge: O(1)
- Querying for an Edge: O(1)

Adjacency List:
- Space Complexity: O(|V| + |E|)
- Adding a Vertex: O(1)
- Adding an Edge: O(1)
- Removing a Vertex: O(|V| + |E|)
- Removing an Edge: O(|E|)
- Querying for an Edge: O(|V|)

## Code snippet

```cpp
int main() {
    int n = 7, m = 8;

    // Method 1: Adjacency Matrix
    int adjMatrix[n + 1][n + 1] = {};

    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;

        adjMatrix[u][v] = 1;
        adjMatrix[v][u] = 1;
    }

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            cout << adjMatrix[i][j] << " ";
        }
        cout << endl;
    }
    cout << endl;
    // Method 2: Adjacency List (Non-Weighted)
    vector<int> adjList[n + 1];
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        adjList[u].push_back(v);
        adjList[v].push_back(u);
    }

    for (int i = 1; i <= n; i++) {
        cout << i << " : ";
        for (auto j : adjList[i]) {
            cout << j << ", ";
        }
        cout << endl;
    }
    cout << endl;

    // Method 3: Adjacency List (Weighted)
    vector<pair<int, int>> adjListWeighted[n + 1];
    for (int i = 0; i < m; i++) {
        int u, v, w;
        cin >> u >> v >> w;
        adjListWeighted[u].push_back({v, w});
        adjListWeighted[v].push_back({u, w});
    }

    for (int i = 1; i <= n; i++) {
        cout << i << " : ";
        for (auto j : adjListWeighted[i]) {
            cout << "{" << j.first << ", " << j.second << "}, ";
        }
        cout << endl;
    }

    return 0;
}
```