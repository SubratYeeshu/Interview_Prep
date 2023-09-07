# Cheapest Flights Within K Stops

## Problem statement

There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.

You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.

## Approach 1 : 

- Time complexity : O(E) E : No. of Flights
- Space complexity : O(E)

```cpp
/*
    
    -> We can not apply dijkstra based on distance directly because there can be a node which from which correct answer can be generated with < k stops but are not able to use that node because it may be relaxed by some other node
    -> We will apply dijkstra based on number of stops
    -> We will use a queue instead of priority queue because stops will be only increased by one at each step
        so using pq will involve the logarithmic time of insertion and queue will automatically ensures the smallest.
    
*/
int findCheapestPrice(int n, vector<vector<int>>& flights, int src, int dst, int k) {
    // Creating Graph
    vector<pair<int, int>> adj[n];
    for(auto it : flights){
        int u = it[0];
        int v = it[1];
        int w = it[2];
        adj[u].push_back({v, w});
    }
    
    // Initital configuration of dijkstra
    vector<int> dist (n, 1e9);
    dist[src] = 0;
    queue<pair<int, pair<int, int>>> q;
    int steps = 0;
    q.push({0, {src, 0}});
    
    // Applying modified dijkstra    
    while(!q.empty()){
        auto it = q.front();
        int steps = it.first;
        int node = it.second.first;
        int currCost = it.second.second;
        q.pop();
        
        if(steps > k)continue;
        
        // Exploring the childs
        for(auto child : adj[node]){
            int adjjNode = child.first;
            int adjNodeWeight = child.second;
            
            // Relaxing the nodes
            if(steps <= k && currCost + adjNodeWeight < dist[adjjNode]){
                dist[adjjNode] = currCost + adjNodeWeight;
                q.push({steps + 1, {adjjNode, currCost + adjNodeWeight}});
            }
            
        }
    }
    if(dist[dst] == 1e9)return -1;
    return dist[dst];
}
```