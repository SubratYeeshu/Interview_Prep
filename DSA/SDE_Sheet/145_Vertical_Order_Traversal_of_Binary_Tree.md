# Vertical Order Traversal of Binary Tree

## Problem statement

Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
- For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).
- The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index starting from the leftmost column and ending on the rightmost column. There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values. 

Return the vertical order traversal of the binary tree.

## Approach 1 : DFS

- Time complexity : O(N)
- Space complexity : O(N)

```cpp
/*
        
    -> At every horizontal distance there will be multiple nodes at different vertical levels
    -> If on same horizontal distance and same level there are multiple nodes choose the one with larger value
    -> Assign level and horizontal for each node

*/
map<int, multiset<pair<int, int>>> mp;
void solve(TreeNode* root, int level, int hd){
    if(!root)return;
    
    mp[hd].insert({level, root -> val});
    solve(root -> left, level + 1, hd - 1);
    solve(root -> right, level + 1, hd + 1);
}
vector<vector<int>> verticalTraversal(TreeNode* root) {
    vector<vector<int>> res;
    solve(root, 0, 0);
    
    for(auto i : mp){
        vector<int> level;
        for(auto j : i.second){
            level.push_back(j.second);
        }
        res.push_back(level);
    }
    
    return res;
}
```

## Approach 2 : BFS

- Time complexity : O(N)
- Space complexity : O(N)

```cpp
vector<vector<int>> verticalTraversal(TreeNode* root) {
    map<int, multiset<pair<int, int>>> mp;
    vector<vector<int>> res;
    
    queue<pair<TreeNode*, pair<int, int>>> q;
    q.push({root, {0, 0}});
    while(!q.empty()){
        int size = q.size();
        
        for(int i = 0 ; i < size ; i++){
            TreeNode *curr = q.front().first;
            int hl = q.front().second.first;
            int level = q.front().second.second;
            q.pop();
            
            mp[hl].insert({level, curr -> val});
            if(curr -> left)q.push({curr -> left, {hl - 1, level + 1}});
            if(curr -> right)q.push({curr -> right, {hl + 1, level + 1}});
        }
    }
    
    for(auto i : mp){
        vector<int> level;
        for(auto j : i.second){
            level.push_back(j.second);
        }
        res.push_back(level);
    }
    
    return res;
}
```

## Approach 2.2 : BFS 

- Time complexity : O(N)
- Space complexity : O(N)

```cpp
vector<vector<int>> verticalTraversal(TreeNode* root) {
    // HL, VL, Multiple node
    map<int, map<int, multiset<int>>> mp;
    vector<vector<int>> res;
    
    queue<pair<TreeNode*, pair<int, int>>> q;
    q.push({root, {0, 0}});
    while(!q.empty()){
        int size = q.size();
        
        for(int i = 0 ; i < size ; i++){
            TreeNode *curr = q.front().first;
            int hl = q.front().second.first;
            int level = q.front().second.second;
            q.pop();
            
            mp[hl][level].insert(curr -> val);
            if(curr -> left)q.push({curr -> left, {hl - 1, level + 1}});
            if(curr -> right)q.push({curr -> right, {hl + 1, level + 1}});
        }
    }
    
    for(auto i : mp){  // HL
        vector<int> level;
        for(auto j : i.second){  // For every VL in each HL (j)
            multiset<int> ms = j.second;
            for(auto val : ms)level.push_back(val);
        }
        res.push_back(level);
    }
    
    return res;
}
```
