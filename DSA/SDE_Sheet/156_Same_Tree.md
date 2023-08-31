# Same Tree

## Problem statement

Given the roots of two binary trees p and q, write a function to check if they are the same or not. Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

## Approach 1 : DFS

- Time complexity : O(N)
- Space complexity : O(LogN)

```cpp
bool solve(TreeNode *root1, TreeNode *root2){
    if(!root1 && !root2)return true;
    if(root1 == NULL && root2 != NULL)return false;
    if(root2 == NULL && root1 != NULL)return false;
    if(root1 -> val != root2 -> val)return false;
    
    return solve(root1 -> left, root2 -> left) && solve(root1 -> right, root2 -> right);
}

bool isSameTree(TreeNode* p, TreeNode* q) {
    return solve(p, q);
}
```