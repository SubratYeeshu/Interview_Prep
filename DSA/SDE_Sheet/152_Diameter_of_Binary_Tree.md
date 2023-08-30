# Diameter of Binary Tree

## Problem statement

Given the root of a binary tree, return the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root. The length of a path between two nodes is represented by the number of edges between them.

## Approach 1 : DFS (Brute)

Time complexity : O(N^2)
Space complexity : O(LogN)

```cpp
int height(TreeNode *root){
    if(!root)return 0;

    int leftHeight = height(root -> left);
    int rightHeight = height(root -> right);

    return 1 + max(leftHeight, rightHeight);
}

int solve(TreeNode *root){
    if(!root)return 0;
    
    int diameterIncludingRoot = height(root -> left) + height(root -> right);
    int leftDiameter = solve(root -> left);
    int rightDiameter = solve(root -> right);
    
    return max({diameterIncludingRoot, leftDiameter, rightDiameter});
}

int diameterOfBinaryTree(TreeNode* root) {
    return solve(root);
}
```

## Approach 2.1 : DFS (Optimal - Return Pair)

Time complexity : O(N)
Space complexity : O(N)

```cpp
pair<int, int> solve(TreeNode *root){
    if(!root)return {0, 0};
    
    pair<int, int> leftInfo = solve(root -> left);
    pair<int, int> rightInfo = solve(root -> right);
    
    int leftHeight = leftInfo.first;
    int rightHeight = rightInfo.first;
    
    int leftDiameter = leftInfo.second;
    int rightDiameter = rightInfo.second;
    
    pair<int, int> toReturnInfo;
    toReturnInfo.first = 1 + max(leftHeight, rightHeight);
    toReturnInfo.second = max({leftDiameter, rightDiameter, leftHeight + rightHeight});
    
    return toReturnInfo;
}

int diameterOfBinaryTree(TreeNode* root) {
    return solve(root).second;
}
```

## Approach 2.2 : DFS (Optimal - Height Function Modification)

Time complexity : O(N)
Space complexity : O(LogN)

```cpp
int maxi = INT_MIN;
    int solve(TreeNode *root){
    if(!root)return 0;
    
    int leftHeight = solve(root -> left);
    int rightHeight = solve(root -> right);
    
    maxi = max(maxi, leftHeight + rightHeight);  // Each node maximization
    
    return 1 + max(leftHeight, rightHeight);
}

int diameterOfBinaryTree(TreeNode* root) {
    solve(root);
    return maxi;
}
```
 