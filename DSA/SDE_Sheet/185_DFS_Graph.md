# DFS of Graph

## Problem statement

Given a connected undirected graph. Perform a Depth First Traversal of the graph.

## Approach 1 : DFS

- Time complexity : O(N)  
- Auxiliary space : O(N)

```cpp
void dfs(int node, vector<int> adj[], vector<int> &vis, vector<int> &res){
    vis[node] = 1;
    res.push_back(node);
    
    for(auto child : adj[node]){
        if(!vis[child])
            dfs(child, adj, vis, res);
    }
}

vector<int> dfsOfGraph(int V, vector<int> adj[]) {
    vector<int> res;
    vector<int> vis (V, 0);
    
    dfs(0, adj, vis, res);
    
    return res;
}
```
