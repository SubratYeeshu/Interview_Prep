# Top View of Binary Tree

## Problem statement

Given below is a binary tree. The task is to print the top view of binary tree. Top view of a binary tree is the set of nodes visible when the tree is viewed from the top. For the given below tree

## Approach 1 : Recursive

- Time complexity : O(N)
- Space complexity : O(N)

```cpp
/*

    -> At every horizontal level there will be nodes at different levels
    -> Less level is prioritized
    -> Horizontal distance, Level, Node

*/
map<int, pair<int, int>> mp; 
void solve(Node *root, int hd, int level){
    if(!root)return;
    
    if(mp.count(hd) == 0 || mp[hd].first > level)mp[hd] = {level, root -> data};
    
    solve(root -> left, hd - 1, level + 1);
    solve(root -> right, hd + 1, level + 1);
}

vector<int> topView(Node *root){
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
// At every horizontal level pick up the upper most level nodes
vector<int> topView(Node *root){
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
            
            if(!mp[hd])mp[hd] = curr -> data;
            if(curr -> left)q.push({curr -> left, hd - 1});
            if(curr -> right)q.push({curr -> right, hd + 1});
        }
        
    }
    for(auto it: mp)res.push_back(it.second);
    return res;
}
```