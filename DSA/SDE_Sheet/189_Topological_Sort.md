# Topological sort

## Problem statement

Given a Directed Acyclic Graph (DAG) with V vertices and E edges, Find any Topological Sorting of that Graph.

## Approach 1 : DFS

- Time complexity : O(N + E) ~ Total N nodes, 2E neighbours undirected
- Space complexity : O(N)

```cpp
/*

	-> The main idea behind topological sorting is to create a linear ordering of the vertices such that for any directed edge from vertex u to vertex v, u comes before v in the ordering. This ordering is known as a topological order.
    -> Only DAG
    -> For intution check for all the edges and compare it with topo order
	
*/
void dfs(int node, vector<int> adj[], vector<int> &vis, stack<int> &st){
    vis[node] = 1;
    
    for(auto child : adj[node]){
        if(!vis[child]){
            dfs(child, adj, vis, st);
        }
    }
    
    // Every node has been processed / visited after this node
    st.push(node);
}

vector<int> topoSort(int V, vector<int> adj[]){
    vector<int> vis (V + 1, 0), res;
    stack<int> st;
    
    for(int i = 0 ; i < V ; i++){
        if(!vis[i])
            dfs(i, adj, vis, st);
    }
    
    while(!st.empty()){
        res.push_back(st.top());
        st.pop();
    }
    
    return res;
}
```

## Approach 2 : BFS (Kahn's Algorithm)

- Time complexity : O(V + E)
- Space complexity : O(N)

```cpp
vector<int> topoSort(int V, vector<int> adj[]){
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
    
    return res;
}
```