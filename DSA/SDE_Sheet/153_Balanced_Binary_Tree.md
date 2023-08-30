# Balanced Binary Tree

## Problem statement

Given a binary tree, determine if it is height-balanced.
-  Difference between heights of left and right subtree is at max 1

## Approach 1 : Brute 

Time complexity : O(N^2)
Space complexity : O(LogN)

```cpp
int height(TreeNode* root){
    if(!root)return 0;
    
    int lh = height(root -> left);
    int rh = height(root -> right);
    
    return 1 + max(lh, rh);
}

bool solve(TreeNode *root){
    if(!root)return true;
    
    int lh = height(root -> left);
    int rh = height(root -> right);
    
    bool a = solve(root -> left);
    bool b = solve(root -> right);
    
    if(abs(lh - rh) > 1)return false;
    
    if(a && b)return true;
    return false;
}

bool isBalanced(TreeNode* root) {
    return solve(root);
}
```

## Approach 2 : DFS (Optimal - Height Function Modification)

Time complexity : O(N)
Space complexity : O(LogN)

```cpp
bool ans = true;
int solve(TreeNode* root){
    if(!root)return 0;
    
    int lh = solve(root -> left);
    int rh = solve(root -> right);
    
    if(abs(lh - rh) > 1)ans = false;
    
    return 1 + max(lh, rh);
}

bool isBalanced(TreeNode* root) {
    solve(root);
    return ans;
}
```

## Approach 3 : DFS (Optimal - Return Pair)

Time complexity : O(N)
Space complexity : O(N)

```cpp
class balancedNode{
    public:
    int height;
    bool isBalanced;
};
class Solution {
public:
    balancedNode *solve(TreeNode *root){
        if(!root){
            balancedNode *temp = new balancedNode();
            temp -> height = 0;
            temp -> isBalanced = 1;
            return temp;
        }
        
        balancedNode *leftInfo = solve(root -> left);
        balancedNode *rightInfo = solve(root -> right);
        
        balancedNode *temp = new balancedNode();
        temp -> height = 1 + max(leftInfo -> height, rightInfo -> height);
        temp -> isBalanced = leftInfo -> isBalanced && rightInfo -> isBalanced  // Left balance, right balanced itself balanced
            && abs(leftInfo -> height - rightInfo -> height) <= 1;
        
        return temp;
    }

    bool isBalanced(TreeNode* root) {
        return solve(root) -> isBalanced;
    }
};
```