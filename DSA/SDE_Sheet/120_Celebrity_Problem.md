# The Celebrity Problem

## Problem statement

A celebrity is a person who is known to all but does not know anyone at a party. If you go to a party of N people, find if there is a celebrity in the party or not. A square NxN matrix M[][] is used to represent people at the party such that if an element of row i and column j  is set to 1 it means ith person knows jth person. Here M[i][i] will always be 0.
Note: Follow 0 based indexing. 

- Follow Up: Can you optimize it to O(N)

## Approach 1 : Grpah

- Time complexity : O(N^2) 
- Space complexity : O(N)

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

## Approach 2 : Stack

- Time complexity : O(N) 
- Space complexity : O(N)

```cpp
int celebrity(vector<vector<int> >& M, int n){
    stack<int> st;
    
    for(int i = 0 ; i < n ; i++)st.push(i);
    
    while(st.size() > 1){
        int first = st.top(); st.pop();
        int second = st.top(); st.pop();
        
        if(M[first][second] == 1)st.push(second);
        else st.push(first);
    }
    int cel = st.top();
    
    // Verifying
    int zero = 0, one = 0;
    // Row
    for(int i = 0 ; i < n ; i++)if(M[cel][i] == 0)zero++;
    // Col
    for(int i = 0 ; i < n ; i++)if(M[i][cel] == 1)one++;
    
    if(zero == n && one == n - 1)return cel;
    
    return -1;
}
```

## Approach 3 :

- Time complexity : O(N) 
- Space complexity : O(1)

```cpp
int celebrity(vector<vector<int> >& M, int n){
    int c = 0;
    
    for(int i = 1 ; i < n ; i++){
        if(M[c][i] == 1)
            c = i;
    }
    
    for(int i = 0 ; i < M.size() ; i++){
        if(i != c && (M[c][i] == 1 || M[i][c] == 0))return -1;
    }
    
    return c;
}
```