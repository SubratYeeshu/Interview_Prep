# Floyd Warshall Algorithm

## Problem statement

The problem is to find the shortest distances between every pair of vertices in a given edge-weighted directed graph. The graph is represented as an adjacency matrix of size n*n. Matrix[i][j] denotes the weight of the edge from i to j. If Matrix[i][j]=-1, it means there is no edge from i to j. Do it in-place.

## Approach 1 : 

- Time complexity : O(V^3)  
- Auxiliary space : O(V^2)

```cpp
/*
  
    -> Multisource shortest path
    -> This algorithm also finds out the negative cycle 
    -> For(i = 0 -> n)if(cost[i][i] < 0)negative cycle exists

*/
void shortest_distance(vector<vector<int>>&matrix){
    int n = matrix.size();
    for(int i = 0 ; i < n ; i++){
        for(int j = 0 ; j < n ; j++){
            if(matrix[i][j] == -1)matrix[i][j] = 1e9;
        }
    }
    
    for(int k = 0 ; k < n ; k++){
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                matrix[i][j] = min(matrix[i][j], matrix[i][k] + matrix[k][j]);
            }
        }
    }
    
    for(int i = 0 ; i < n ; i++){
        for(int j = 0 ; j < n ; j++){
            if(matrix[i][j] == 1e9)matrix[i][j] = -1;
        }
    };
}
```