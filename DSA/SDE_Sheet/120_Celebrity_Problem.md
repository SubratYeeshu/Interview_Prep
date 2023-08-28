# The Celebrity Problem

## Problem statement

A celebrity is a person who is known to all but does not know anyone at a party. If you go to a party of N people, find if there is a celebrity in the party or not. A square NxN matrix M[][] is used to represent people at the party such that if an element of row i and column j  is set to 1 it means ith person knows jth person. Here M[i][i] will always be 0.
Note: Follow 0 based indexing. 
- Follow Up: Can you optimize it to O(N)

## Approach 1 : Grpah

Time complexity : O(1) 
Space complexity : O(1)

```cpp
int celebrity(vector<vector<int> >& M, int n){
    vector<int> adj[n];
    
    for(int i = 0 ; i < M.size() ; i++){
        for(int j = 0 ; j < M[0].size() ; j++){
            if(M[i][j] == 1){
                adj[i].push_back(j);
            }
        }
    }
    
    vector<int> indeg (n, 0), outdeg (n, 0);
    for(int i = 0 ; i < n ; i++){
        for(auto j : adj[i]){
            outdeg[i]++;
            indeg[j]++;
        }
    }
    
    for(int i = 0 ; i < n ; i++)
        if(indeg[i] == n - 1 && outdeg[i] == 0)
            return i;
            
    return -1;
    }
```