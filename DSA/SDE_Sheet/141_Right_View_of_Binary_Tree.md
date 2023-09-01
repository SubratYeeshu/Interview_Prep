# Right View of Binary Tree

## Problem statement

Given a Binary Tree, return Left view of it. Right view of a Binary Tree is set of nodes visible when tree is visited from Right side. The task is to complete the function rightSideView(), which accepts root of the tree as argument.

## Approach 1 : DFS

- Time complexity : O(N)
- Space complexity : O(LogN)

```cpp
void solve(TreeNode *root, vector<int> &res, int level){
    if(!root)return;
    
    if(level == res.size())res.push_back(root -> val);
    solve(root -> right, res, level + 1);
    solve(root -> left, res, level + 1);
}
vector<int> rightSideView(TreeNode* root) {
    vector<int> res;
    solve(root, res, 0);
    return res;
}
```

## Approach 2 : Level order

- Time complexity : O(N)
- Space complexity : O(N)

```cpp
vector<int> rightSideView(TreeNode* root) {
    if(!root)return {};
    vector<int> res;
    queue<TreeNode*> q;
    q.push(root);

    while(!q.empty()){
        int size = q.size();
        for(int i = 0 ; i < size ; i++){
            TreeNode *curr = q.front();
            q.pop();
            if(i == size - 1)res.push_back(curr -> val);
            if(curr -> left)q.push(curr -> left);
            if(curr -> right)q.push(curr -> right);
        }
    }
    return res;
}
```