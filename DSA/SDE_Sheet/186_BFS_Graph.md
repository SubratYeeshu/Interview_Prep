# DFS of Graph

## Problem statement

Given a connected undirected graph. Perform a Depth First Traversal of the graph.

## Approach 1 : Level order (Single Line - Level Wise)

- Time complexity : O(N)  
- Auxiliary space : O(N)

```cpp
/*

			0
		  /   \
		-      -
	   /        \
      1          2 

*/
vector<int> bfsOfGraph(int V, vector<int> adj[]) {
    vector<int> res;
    
    vector<int> vis (V, 0);
    queue<int> q;
    q.push(0);
    vis[0] = 1;
    
    while(!q.empty()){
        int size = q.size();
        
        int node = q.front();
        q.pop();
        res.push_back(node);
        
        
        // For binary tree we dont need to use a loop as there are only two childs
        for(auto child : adj[node]){
            if(!vis[child]){
                q.push(child);
                vis[child] = 1;
            }
        }
    }
    
    return res;
}
```

## Approach 2 : Level order (Multi Line - Level Wise)

- Time complexity : O(N)  
- Auxiliary space : O(N)

```cpp
/*

			0
		  /   \
		--------
	   /        \
      1          2 

      We use three loop to remove a parameter from the queue in most of the cases (like level and time)

*/
vector<int> bfsOfGraph(int V, vector<int> adj[]) {
    vector<int> res;
    
    vector<int> vis (V, 0);
    queue<int> q;
    q.push(0);
    vis[0] = 1;
    
    while(!q.empty()){
        int size = q.size();
        
        for(int i = 0 ; i < size ; i++){
            int node = q.front();
            q.pop();
            res.push_back(node);
            
            for(auto child : adj[node]){
                if(!vis[child]){
                    q.push(child);
                    vis[child] = 1;
                }
            }
        }
    }
    
    return res;
}
```
