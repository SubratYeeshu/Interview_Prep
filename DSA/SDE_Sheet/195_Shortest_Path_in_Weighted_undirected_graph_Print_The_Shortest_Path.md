# Shortest Path in Weighted undirected graph (Print the shortest path)

## Problem statement

You are given a weighted undirected graph having n vertices numbered from 1 to n and m edges describing there are edges between a to b with some weight, find the shortest path between the vertex 1 and the vertex n and if path does not exist then return a list consisting of only -1.

## Approach 1 : Dijkstra

- Time complexity : O(ELogV)  
- Auxiliary space : O(N)

```cpp
vector<int> shortestPath(int n, int m, vector<vector<int>> &edges){
    vector<pair<int, int>> adj[n + 1];
    for (auto it : edges){
        adj[it[0]].push_back({it[1], it[2]});
        adj[it[1]].push_back({it[0], it[2]});
    }
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int,int>>> pq;

    vector<int> dist(n + 1, 1e9), parent(n + 1);
    for (int i = 1; i <= n; i++)parent[i] = i;
    dist[1] = 0;
    pq.push({0, 1});
    
    while (!pq.empty()){
        auto it = pq.top();
        pq.pop();
        int node = it.second;
        int dis = it.first;

        for (auto it : adj[node]){
            int adjNode = it.first;
            int edW = it.second;
            if (dis + edW < dist[adjNode]){
                dist[adjNode] = dis + edW;
                pq.push({dis + edW, adjNode});
                parent[adjNode] = node;
            }
        }
    }

    if (dist[n] == 1e9)return {-1};

    vector<int> path;
    int node = n;

    while (parent[node] != node){
        path.push_back(node);
        node = parent[node];
    }
    path.push_back(1);
    reverse(path.begin(), path.end());
    return path;
}
```