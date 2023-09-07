# Accounts Merge

## Problem statement

Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

## Approach 1 : DSU

- Time complexity : O(NKlogNK), N : Number of accounts, K : maximum length of an account.
- Space complexity : O(NK)

```cpp
class DisjointSet{
    vector<int> rank, parent, size;
    public: 
    // Constructor
    DisjointSet(int n){
        rank.resize(n + 1, 0);
        parent.resize(n + 1, 0);
        size.resize(n + 1, 0);
        
        // Ensures 1 Based indexing graph also
        for(int i = 0 ; i <= n ; i++){
            parent[i] = i;
            size[i] = 1;
        }
    };

    // Find Operation with path compression
    int find(int node){
        if(parent[node] == node)return node;
        else return parent[node] = find(parent[node]);
    };
    
    // Union operation by rank
    void unionbyrank(int x, int y){
        int ult_parent_x = find(x);
        int ult_parent_y = find(y);
        
        // They already belongs to the same group
        if(ult_parent_x == ult_parent_y)return;
        
        // They belong to different groups, based on ranking the union operation will be broken in three pieces
        if(rank[ult_parent_x] > rank[ult_parent_y]){
            parent[ult_parent_y] = ult_parent_x;
        }else if(rank[ult_parent_x] < rank[ult_parent_y]){
            parent[ult_parent_x] = ult_parent_y;
        }else{
            parent[ult_parent_x] = ult_parent_y;
            rank[ult_parent_y] += 1;
        }
    }
    
    // Union operation by size
    void unionbysize(int x, int y){
        int ult_parent_x = find(x);
        int ult_parent_y = find(y);
        
        // They already belongs to the same group
        if(ult_parent_x == ult_parent_y)return;
        
        // They belong to different groups, based on ranking the union operation will be broken in three pieces
        // x component bigger 
        if(size[ult_parent_x] > size[ult_parent_y]){
            parent[ult_parent_y] = ult_parent_x;
            size[ult_parent_x] += size[ult_parent_y];
        // y component bigger or both same
        }else{
            parent[ult_parent_x] = ult_parent_y;
            size[ult_parent_y] += size[ult_parent_x];
        }
    }
};
class Solution {
public:
    vector<vector<string>> accountsMerge(vector<vector<string>>& accounts) {
        vector<vector<string>>ans;
        int n = accounts.size();
        DisjointSet ds(n);
        unordered_map<string, int> mapMailNode;
        for(int i = 0 ; i < accounts.size() ; i++){
            for(int j = 1 ; j < accounts[i].size() ; j++){
                string mail = accounts[i][j];
                // Mapping already done that means same account merge then
                if(mapMailNode.find(mail) != mapMailNode.end()){
                    ds.unionbyrank(i, mapMailNode[mail]);
                }else{
                    mapMailNode[mail] = i;
                }
            }
        }
        
        //  Now the DSU has been created the ultimate parent of each account will store the mail address
        vector<string> mergedNode[n];
        for(auto it : mapMailNode){
            int node = ds.find(it.second);
            string mail = it.first;
            mergedNode[node].push_back(mail);
        }
        
        for(int i = 0 ; i < n ; i++){
            if(mergedNode[i].size() == 0)continue;
            sort(mergedNode[i].begin(), mergedNode[i].end());
            vector<string>temp;
            temp.push_back(accounts[i][0]);
            for(auto it : mergedNode[i]){
                temp.push_back(it);
            }
            ans.push_back(temp);
        }
        return ans;
    }
};
```