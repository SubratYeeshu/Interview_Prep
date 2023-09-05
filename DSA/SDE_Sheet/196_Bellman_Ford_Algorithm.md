# Bellman Ford

## Problem statement

Given a weighted, directed and connected graph of V vertices and E edges, Find the shortest distance of all the vertex's from the source vertex S.
Note: If the Graph contains a negative cycle then return an array consisting of only -1.

## Approach 1 : Bellman Ford Algorithm

- Time complexity : O(V\*E)  
- Auxiliary space : O(V)

```cpp
/*  
    
    -> Relax the edges n - 1 times where n is the number of vertex
    -> To proove that relaxing all edges n - 1 times fetches the distance vector in graph with negative edges
        do a dry run on simple graph 1 -> 2 -> 3 -> 4 
    -> If a node is reachable relax the node on each iteration
    -> And just in case if on the nth iteration any node can be relaxed than it means a negative cycle is present
        return -1 or floydwarshall
    -> Only for DAG
    
*/
vector<int> bellman_ford(int V, vector<vector<int>>& edges, int S) {
    vector<int> dist(V, 1e8);
    dist[S] = 0;
    for(int i = 1 ; i <= V - 1 ; i++){
        for(auto it : edges){
            int u = it[0];
            int v = it[1];
            int w = it[2];
            
            // Reachable and can be relaxed 1 condition save some iterations
            if(dist[u] != 1e8 && w + dist[u] < dist[v]){
                dist[v] = w + dist[u];
            }
        }
    }
    
    for(auto it : edges){
        int u = it[0];
        int v = it[1];
        int w = it[2];
        
        // Reachable and can be relaxed
        if(dist[v] != 1e8 && w + dist[u] < dist[v]){
            return {-1};
        }
    }
    return dist;
}
```