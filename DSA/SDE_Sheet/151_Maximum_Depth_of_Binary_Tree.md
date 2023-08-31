# Maximum Depth of Binary Tree

## Problem statement

Given the root of a binary tree, return its maximum depth. A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

## Approach 1 : DFS

- Time complexity : O(N)
- Space complexity : O(LogN)

```cpp
int solve(TreeNode *root){
    if(!root)return 0;
    
    int leftHeight = solve(root -> left);
    int rightHeight = solve(root -> right);
    
    return 1 + max(leftHeight, rightHeight);
}

int maxDepth(TreeNode* root) {
    return solve(root);
}
```