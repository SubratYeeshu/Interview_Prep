# Detect Cycle in Directed Graph

## Problem statement

Given a Directed Graph with V vertices (Numbered from 0 to V-1) and E edges, check whether it contains any cycle or not.

## Approach 1 : DFS

- Time complexity : O(N + E) ~ Total N nodes, 2E neighbours undirected
- Space complexity : O(2N)

```cpp
bool isCycleDFS(int node, vector<int> adj[], vector<int> &vis, vector<int> &pathVis){
    vis[node] = 1;
    pathVis[node] = 1;
    
    for(auto child : adj[node]){
        if(!vis[child]){
            if(isCycleDFS(child, adj, vis, pathVis))return true;
        }else if(vis[child] && pathVis[child])return true;
    }
    
    pathVis[node] = 0;
    return false;
}

bool isCyclic(int V, vector<int> adj[]) {
    vector<int> vis (V + 1, 0), pathVis(V + 1, 0);
    
    for(int i = 0 ; i < V ; i++){
        if(!vis[i]){
            if(isCycleDFS(i, adj, vis, pathVis))return true;
        }
    }
    
    return false;
}
```