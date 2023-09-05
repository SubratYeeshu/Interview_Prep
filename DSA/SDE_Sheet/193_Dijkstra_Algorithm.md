# Dijkstra Algorithm (Single Source Shortest Path in Weighted Graph)

## Problem statement

Given a weighted, undirected and connected graph of V vertices and an adjacency list adj where adj[i] is a list of lists containing two integers where the first integer of each list j denotes there is edge between i and j , second integers corresponds to the weight of that  edge . You are given the source vertex S and You to Find the shortest distance of all the vertex's from the source vertex S. You have to return a list of integers denoting shortest distance between each node and Source vertex S.

## Note : Dijkstra never works for negative weight / negative cycle infinite loop

## Approach 1 : Priority Queue

- Time complexity : O(ELogV)  
- Auxiliary space : O(N)

```cpp
vector <int> dijkstra(int V, vector<vector<int>> adj[], int S){
    vector<int> dist (V, INT_MAX);
    
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    pq.push({0, S});
    dist[S] = 0;
    
    while(!pq.empty()){
        int sourceNode = pq.top().second;
        int sourceDistance = pq.top().first;
        pq.pop();
        
        for(auto child : adj[sourceNode]){
            int childNode = child[0];
            int childWeight = child[1];
            
            if(sourceDistance + childWeight <= dist[childNode]){
                dist[childNode] = sourceDistance + childWeight;
                pq.push({dist[childNode], childNode});
            }
        }
        
    }
    return dist;
}
```

## Approach 1 : Set - Erase already existing paths

- Time complexity : O(ELogV)  
- Auxiliary space : O(N)

```cpp
// Invest logn for erasing to save some iterations in future
vector <int> dijkstra(int V, vector<vector<int>> adj[], int S){
    vector<int> dist (V, INT_MAX);
    
    set<pair<int, int>> st;
    st.insert({0, S});
    dist[S] = 0;
    
    while(!st.empty()){
        auto it = *(st.begin());
        int sourceNode = it.second;
        int sourceDistance = it.first;
        st.erase(it);   
        
        for(auto child : adj[sourceNode]){
            int childNode = child[0];
            int childWeight = child[1];
            
            if(sourceDistance + childWeight <= dist[childNode]){
                // Erase if exists
                if(dist[childNode] != INT_MAX){
                    // Already reached by someone with greater distance
                    // Delete the entry because its useless to use to reach any other node via greater distance
                    st.erase({dist[childNode], childNode});
                }
                
                dist[childNode] = sourceDistance + childWeight;
                st.insert({dist[childNode], childNode});
            }
        }
        
    }
    return dist;
}
```
