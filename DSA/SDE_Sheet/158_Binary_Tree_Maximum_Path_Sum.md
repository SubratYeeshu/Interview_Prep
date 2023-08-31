# Binary Tree Maximum Path Sum

## Problem statement

A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any non-empty path.

## Approach 1.1 : Implementation (Intutive)

- Time complexity : O(N)
- Space complexity : O(LogN)

```cpp
/*
    
    -> Three cases of sum possible : maximize these three to get maximum path sum
        -> l + r + root -> val : Converge (1)
        -> max(l, r) + root -> val : Taking the root and any one of the good tree (2)
        -> root -> val (3)
    -> Return max of (2) & (3)
        

*/

int maxi = INT_MIN;
int solve(TreeNode *root){
    if(!root)return 0;
    
    int left = solve(root -> left);
    int right = solve(root -> right);
    
    int sumConvergedInSubtree = left + right + root -> val;  // (1)
    int fromLeftfromRightIncludingRoot = max(left, right) + root -> val;  // (2)
    int onlyRoot = root -> val;  // (3)
    
    maxi = max({maxi, sumConvergedInSubtree, fromLeftfromRightIncludingRoot, onlyRoot});  // Maximization
    
    return max(fromLeftfromRightIncludingRoot, onlyRoot);  // Return max (2, 3)
}

int maxPathSum(TreeNode* root) {
    solve(root);        
    return maxi;
}
```

## Approach 1.2

Time complexity : O(N)
Space complexity : O(LogN)

```cpp
int maxi = INT_MIN;
int solve(TreeNode* root) {
    if(!root)return 0;
    int lsubtreesum = max(0, solve(root->left));
    int rightsubtreesum = max(0, solve(root->right));
    maxi = max(maxi, lsubtreesum + rightsubtreesum + root->val);
    return max(lsubtreesum, rightsubtreesum) + root->val;
}

int maxPathSum(TreeNode* root) {
    if(!root)return 0;
    solve(root);
    return maxi;
}
```