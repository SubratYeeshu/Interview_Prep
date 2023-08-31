# Symmetric Tree

## Problem statement

Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).

## Approach 1 : Implementation

- Time complexity : O(N)
- Space complexity : O(LogN)

```cpp
bool solve(TreeNode *root1, TreeNode *root2){
    if(!root1 && !root2)return true;
    if(!root1 && root2 != NULL)return false;
    if(!root2 && root1 != NULL)return false;
    if(root1 -> val != root2 -> val)return false;
    
    return solve(root1 -> left, root2 -> right) && solve(root1 -> right, root2 -> left);
}

bool isSymmetric(TreeNode* root) {
    if(!root)return true;
    return solve(root -> left, root -> right);
}
```