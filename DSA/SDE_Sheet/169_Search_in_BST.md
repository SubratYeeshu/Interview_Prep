# Search in BST

## Problem statement

You are given the root of a binary search tree (BST) and an integer val.

Find the node in the BST that the node's value equals val and return the subtree rooted with that node. If such a node does not exist, return null.

## Approach 1 : Search

- Time complexity : O(N)
- Space complexity : O(LogN)

```cpp
TreeNode *search(TreeNode *root, int val_){
    if(!root)return NULL;
    
    if(root -> val == val_)return root;
    
    if(val_ > root -> val)return search(root -> right, val_);
    else return search(root -> left, val_);
}

TreeNode* searchBST(TreeNode* root, int val){
    return search(root, val);
}
```