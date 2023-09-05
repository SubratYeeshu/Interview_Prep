# Detect Cycle in Directed Graph

## Problem statement

Given a Directed Graph with V vertices (Numbered from 0 to V-1) and E edges, check whether it contains any cycle or not.

## Approach 1 : DFS

- Time complexity : O(V + E) ~ Total N nodes, 2E neighbours undirected
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

## Approach 2 : BFS (Kahn's Algorithm)

- Time complexity : O(V + E) ~ Total N nodes, 2E neighbours undirected
- Space complexity : O(N)

```cpp
bool topoSort(int V, vector<int> adj[]){
    vector<int> res, indeg(V, 0);
    queue<int> q;
    
    for(int i = 0 ; i < V ; i++)
        for(auto child : adj[i])
            indeg[child]++;
            
    for(int i = 0 ; i < V ; i++)
        if(indeg[i] == 0)
            q.push(i);
    
    
    while(!q.empty()){
        int node = q.front();
        q.pop();
        
        res.push_back(node);
        
        for(auto child : adj[node]){
            indeg[child]--;
            if(indeg[child] == 0)
                q.push(child);
        }
    }
    
    return res.size() != V;
}

bool isCyclic(int V, vector<int> adj[]) {
    return topoSort(V, adj);
}
```