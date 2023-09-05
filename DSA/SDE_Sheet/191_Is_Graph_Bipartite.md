# Is Graph Bipartite

## Problem statement

There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1. You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to. More formally, for each v in graph[u], there is an undirected edge between node u and node v. The graph has the following properties:

- There are no self-edges (graph[u] does not contain u).
- There are no parallel edges (graph[u] does not contain duplicate values).
- If v is in graph[u], then u is in graph[v] (the graph is undirected).
- The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.

A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the graph connects a node in set A and a node in set B.

Return true if and only if it is bipartite.

## Approach 1 : DFS

- Time complexity : O(N) 
- Space complexity : O(N) 

```cpp
bool dfs(int node, vector<int> adj[], vector<int> &color, int c){
    color[node] = c;
    
    for(auto child : adj[node]){
        if(color[child] == -1){
            if(dfs(child, adj, color, !c) == false)return false;
        }else{
            if(color[child] == c)return false;
        }
    }
    
    return true;
}

bool isBipartite(vector<vector<int>>& graph) {
    int n = graph.size();
    vector<int> adj[n];
    vector<int> color (n, -1);
    
    // Building the graph
    for(int i = 0 ; i < n ; i++){
        for(int j = 0 ; j < graph[i].size() ; j++){
            adj[i].push_back(graph[i][j]);
            adj[graph[i][j]].push_back(i);
        }
    }
    
    // Painting every component of the graph
    bool canColor = true;
    for(int i = 0 ; i < n ; i++){
        if(color[i] == -1){
            canColor = canColor && dfs(i, adj, color, 0);
        }
    }
    return canColor;
}
```

## Approach 1 : DFS

- Time complexity : O(N) 
- Space complexity : O(N) 

```cpp
bool bfs(int node, vector<int>adj[], vector<int> &color, int col){
    queue<int> q;
    q.push(node);
    color[0] = col;
    while(!q.empty()){
        int node = q.front();
        q.pop();
        for(auto child : adj[node]){
            if(color[child] == -1){
                color[child] = !color[node];
                q.push(child);
            }
            if(color[child] == color[node])return false;
        }
    }
    return true;
}

bool isBipartite(vector<vector<int>>& graph) {
    int n = graph.size();
    vector<int> adj[n];
    vector<int> color (n, -1);
    
    // Building the graph
    for(int i = 0 ; i < n ; i++){
        for(int j = 0 ; j < graph[i].size() ; j++){
            adj[i].push_back(graph[i][j]);
            adj[graph[i][j]].push_back(i);
        }
    }
    
    // Painting every component of the graph
    bool canColor = true;
    for(int i = 0 ; i < n ; i++){
        if(color[i] == -1){
            canColor = canColor && bfs(i, adj, color, 0);
        }
    }
    return canColor;
}
```