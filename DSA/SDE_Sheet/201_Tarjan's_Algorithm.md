# Tarjan's Algorithm (Bridge Detection)

## Problem statement

## Approach 1 : Tarjan's Algorithm

- Time complexity : O(V + E)  
- Auxiliary space : O(V)

```cpp
/*
	
    -> There is no alternate path to the adj node with lesser time means the edge between the node and its adj is 
        a bridge, we are taking par to update low only on the basis adj node or node itself, 
    -> par == child continue means dont take parent for updating low
    -> lowest[adj] > time_of_insertion[node]
    
*/

int timer = 1;
void tarjan(int node, vector<int>adj[], int par, vector<int>& lowest, 
                vector<int>& time_of_insertion, vector<vector<int>>& ans, vector<int>&vis){        
    // Intitially time of insertion and lowest time of insertion will be same
    vis[node] = 1;
    time_of_insertion[node] = lowest[node] = timer; 
    timer++;
    
    for(auto it : adj[node]){
        // This condition for updation of low value only on the basis of adj node not on the parent
        if(it == par)continue;
        if(!vis[it]){
            tarjan(it, adj, node, lowest, time_of_insertion, ans, vis);
            
            // While returning from the dfs of a node updating the lowest
            lowest[node] = min(lowest[node], lowest[it]);
            
            // If there is no other path to the node then the edge is a bridge / criticall connectoin
            if(lowest[it] > time_of_insertion[node])ans.push_back({node, it});
        }else lowest[node] = min(lowest[node], lowest[it]);
    }        
}

vector<vector<int>> criticalConnections(int n, vector<vector<int>>& connections) {
    vector<int>adj[n];
    vector<int>vis(n, 0), lowest(n, 0), time_of_insertion(n, 0);
    vector<vector<int>>ans;
    
    // Creating a graph
    for(int i = 0 ; i < connections.size() ; i++){
        adj[connections[i][0]].push_back(connections[i][1]);
        adj[connections[i][1]].push_back(connections[i][0]);
    }
    
    // Tarjans Alogirthm
    tarjan(0, adj, -1, lowest, time_of_insertion, ans, vis);
    
    return ans;
    
}
```