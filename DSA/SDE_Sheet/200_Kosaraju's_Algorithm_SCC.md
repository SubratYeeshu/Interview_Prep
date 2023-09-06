# Kosaraju's Algorithm (Strongly Connected Components)

## Problem statement

Given a Directed Graph with V vertices (Numbered from 0 to V-1) and E edges, Find the number of strongly connected components in the graph.

## Approach 1 : Kosaraju's Algorithm

- Time complexity : O(V + E)  
- Auxiliary space : O(2V)

```cpp
/*

    -> Strongly connected components are the one in which any node can be reached from any node
    -> Kosaraju's algorithm is used to find the strongly connected components
    -> In this algorithm we have to do two DFS
    -> First DFS to store the nodes in order of their insertion
    -> Then we reverse each edge
    -> Now using the stack we take nodes one by one and find the strongly connected component
    -> Reversing the edges ensures the components are not reachable at once when a dfs is called
    -> Time Complexity : O(V + E)
    -> We store the elements in stack because if we start a dfs from starting node it will visit everyone in one go, and we will not be able to find the SCC
    
*/

void dfs(int node, vector<int> adj[], vector<int> &vis, stack <int> &time_of_ins){
    vis[node] = 1;
    
    for(auto child : adj[node]){
        if(!vis[child]){
            dfs(child, adj, vis, time_of_ins);
        }
    }
    time_of_ins.push(node);
}
void dfs2(int node, vector<int> adj[], vector<int> &vis, vector<int> &ds){
    vis[node] = 1;
    
    for(auto child : adj[node]){
        if(!vis[child]){
            dfs2(child, adj, vis, ds);
        }
    }
    ds.push_back(node);
}


int kosaraju(int V, vector<vector<int>>& adj){
    // Declarations
    int no_of_scc = 0;
    vector<vector<int>> scc;
    vector<int> ds;
    vector<int> vis (V, 0), vis2(V, 0);
    vector<int> adj1 [V];
    vector<int> adj2[V];
    stack <int> time_of_ins;
    
    // Creating the adjacency list
    for(int i = 0 ; i < V ; i++){
        for(auto child : adj[i]){
            adj1[i].push_back(child);
        }
    }
    
    // DFS 1
    for(int i = 0 ; i < V; i++){
        if(!vis[i]){
            dfs(i, adj1, vis, time_of_ins);
        }
    }
    
    // Reversing the edges 
    for(int i = 0 ; i < V ; i++){
        for(auto child : adj[i]){
            adj2[child].push_back(i);
        }
    }
    
    // DFS 2
    while(!time_of_ins.empty()){
        int node = time_of_ins.top();
        time_of_ins.pop();
        if(!vis2[node]){
            no_of_scc++;
            dfs2(node, adj2, vis2, ds);
            scc.push_back(ds);
            ds.clear();
        }
    }
    
    // Printing the strongly connected elements
    // for(auto i : scc){
    //     for(auto j : i){
    //         cout << j << " ";
    //     }
    //     cout << endl;
    // }
    
    return no_of_scc;
}
```