# Lowest Common Ancestor of a Binary Search Tree

## Problem statement 

Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

## Approach 1 : DFS

- Time complexity : O(N)
- Space complexity : O(LogN)

```cpp
TreeNode* LCA(TreeNode *root, TreeNode *node1, TreeNode *node2){
    if(!root)return NULL;
    if(root -> val == node1 -> val || root -> val == node2 -> val)return root;
    
    TreeNode *fromLeft = LCA(root -> left, node1, node2);
    TreeNode *fromRight = LCA(root -> right, node1, node2);
    
    if(fromLeft != NULL && fromRight != NULL)return root;
    if(fromLeft == NULL)return fromRight;
    else return fromLeft;
}
```

## Approach 2 : DFS (Optimal)

- Time complexity : O(LogN)
- Space complexity : O(LogN)

```cpp
TreeNode* LCA(TreeNode *root, TreeNode *p, TreeNode *q){
    if ((root -> val > p -> val) && (root -> val > q -> val)) {
        return lowestCommonAncestor(root -> left, p, q);
    }
    if ((root -> val < p -> val) && (root -> val < q -> val)) {
        return lowestCommonAncestor(root -> right, p, q);
    }
    return root;
}
```