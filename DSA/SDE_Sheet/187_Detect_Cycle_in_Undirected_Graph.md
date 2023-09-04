# Detect Cycle in Undirected Graph

## Problem statement

Given an undirected graph with V vertices and E edges, check whether it contains any cycle or not. Graph is in the form of adjacency list where adj[i] contains all the nodes ith node is having edge with.

## Approach 1 : BFS

- Time complexity : O(N + 2E) ~ Total N nodes, 2E neighbours undirected
- Space complexity : O(N)

```cpp
bool isCycleBFS(int start, int V, vector<int> adj[]){
    // Child node is visited and not parent
    vector<int> vis(V, 0);
    int parent = -1;
    
    queue<pair<int, int>> q;
    q.push({start, -1});
    
    vis[start] = 1;
    
    while(!q.empty()){
        int size = q.size();
        
        for(int i = 0 ; i < size ; i++){  // We can comment this line
            int node = q.front().first;
            int parent = q.front().second;
            q.pop();

            for(auto child : adj[node]){
                if(!vis[child]){
                    q.push({child, node});
                    vis[child] = 1;
                }else if(vis[child] && parent != child)return true;
            }
        }  
    }
    
    return false;
}

bool isCycle(int V, vector<int> adj[]) {
    for(int i = 0 ; i < V ; i++){
        if(isCycleBFS(i, V, adj))return true;
    }
    
    return false;
}
```

## Approach 1 : DFS

- Time complexity : O(N + 2E) ~ Total N nodes, 2E neighbours undirected
- Space complexity : O(N)

```cpp
bool isCycleDFS(int node, int parent, vector<int> adj[], vector<int> &vis){
    vis[node] = 1;
    
    for(auto child : adj[node]){
        if(!vis[child]){
            if(isCycleDFS(child, node, adj, vis))return true;
        }else if(vis[child] && child != parent)return true;
    }
    
    return false;
}

bool isCycle(int V, vector<int> adj[]) {
    vector<int> vis (V + 1, 0);
    for(int i = 0 ; i < V ; i++){
        if(!vis[i]){
            if(isCycleDFS(i, -1, adj, vis))return true;
        }
    }
    return false;
}
```