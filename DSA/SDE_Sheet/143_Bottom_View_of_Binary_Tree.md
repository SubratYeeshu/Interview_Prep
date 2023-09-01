# Bottom View of Binary Tree

## Problem statement

Given a binary tree, print the bottom view from left to right.
A node is included in bottom view if it can be seen when we look at the tree from bottom.

## Approach 1 : Recursive

- Time complexity : O(N)
- Space complexity : O(N)

```cpp
map<int, pair<int, int>> mp; 
void solve(Node *root, int hd, int level){
    if(!root)return;
    
    if(mp.count(hd) == 0 || mp[hd].first <= level)mp[hd] = {level, root -> data};  // If on same level choose the
    
    solve(root -> left, hd - 1, level + 1);
    solve(root -> right, hd + 1, level + 1);
}
vector <int> bottomView(Node *root) {
    vector<int> res;
    solve(root, 0, 0);
    for(auto i : mp)res.push_back(i.second.second);
    return res;
}
```

## Approach 2 : Level order

- Time complexity : O(N)
- Space complexity : O(N)

```cpp
// At every horizontal level pick up the lower most level nodes
vector <int> bottomView(Node *root) {
    if(!root)return {};
    vector<int>res;

    queue<pair<Node*, int>>q;
    q.push({root, 0});
    map<int, int>mp;
    
    while(!q.empty()){
        int size = q.size();
        
        for(int i = 0 ; i < size ; i++){
            Node *curr = q.front().first;
            int hd = q.front().second;
            q.pop();
            
            mp[hd] = curr -> data;
            if(curr -> left)q.push({curr -> left, hd - 1});
            if(curr -> right)q.push({curr -> right, hd + 1});
        }
        
    }
    for(auto it: mp)res.push_back(it.second);
    return res;
}
```