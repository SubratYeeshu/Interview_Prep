# Alien Dictonary

## Problem statement

Given a sorted dictionary of an alien language having N words and k starting alphabets of standard dictionary. Find the order of characters in the alien language.
Note: Many orders may be possible for a particular test case, thus you may return any valid order and output will be 1 if the order of string returned by the function is correct else 0 denoting incorrect string returned.

## Approach 1 : Topo Sort

- Time complexity : O(V + E)
- Space complexity : O(N)

```cpp
/*

    -> There are two cases in which the dictonary can not be formed
        -> Cyclic dependency 
        -> Larger string before smaller and everything is matching
        
    -> No topo sort possible

*/
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
    
// Find differentiating factor between characters and find the topo order
string findOrder(string dict[], int N, int K) {
    vector<int> adj[K];
    
    // Creating adjacency list
    for(int i = 0 ; i < N - 1 ; i++){
        string s1 = dict[i];
        string s2 = dict[i + 1];
        
        for(int j = 0 ; j < min(s1.size(), s2.size()) ; j++){
            if(s1[j] != s2[j]){
                adj[s1[j] - 'a'].push_back(s2[j] - 'a');
                break;
            }
        }
    }
    
    vector<int> topo = topoSort(K, adj);
    
    string res = "";
    
    for(int i = 0 ; i < topo.size() ; i++)res += char(topo[i] + 'a');
    
    return res;
}
```