# Invert Binary Tree / Mirror Tree

## Problem statement

Given a Binary Tree, convert it into its mirror / Invert the tree.

## Approach 1 : Post Order Traversal

- Time complexity : O(N)
- Space complexity : O(LogN)

```cpp
void solve(Node *root){
    if(!root)return;
    
    solve(root -> left);
    solve(root -> right);
    
    // On post order of a node swap the childrens
    swap(root -> left, root -> right);
    
    // Node *temp = root -> left;
    // root -> left = root -> right;
    // root -> right = temp;
}
```