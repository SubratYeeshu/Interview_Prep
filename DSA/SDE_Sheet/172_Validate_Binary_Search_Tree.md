# Validate Binary Search Tree

## Problem statement

Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

- The left subtree of a node contains only nodes with keys less than the node's key.
- The right subtree of a node contains only nodes with keys greater than the node's key.
- Both the left and right subtrees must also be binary search trees.

## Approach 1 : Inorder Sorted

- Time complexity : O(N)
- Space complexity : O(N)

```cpp
void solve(TreeNode *root, vector<int> &num){
    if(!root)return;
    
    solve(root -> left, num);
    num.push_back(root -> val);
    solve(root -> right, num);
}

bool isValidBST(TreeNode* root) {
    vector<int> inorder;
    solve(root, inorder);
    
    for(int i = 1 ; i < inorder.size() ; i++)
        if(inorder[i] <= inorder[i - 1])
            return false;
    
    return true;
}
```

## Approach 2 : Lower and Upper Limit of Node

- Time complexity : O(N)
- Space complexity : O(LogN)

```cpp
bool solve(TreeNode *root, long lowerBound, long upperBound){
    if(!root)return true;
    
    if(root -> val >= upperBound || root -> val <= lowerBound)return false;
    
    bool fromLeft = solve(root -> left, lowerBound, root -> val);
    bool fromRight = solve(root -> right, root -> val, upperBound);
    
    return fromLeft && fromRight;
}
bool isValidBST(TreeNode* root) {
    return solve(root, LONG_MIN, LONG_MAX);
}
```

## Approach 3 : Return Info

- Time complexity : O(N)
- Space complexity : O(LogN)

```cpp
BSTInfo* solve(TreeNode* root){
    if(!root){
        BSTInfo* temp = new BSTInfo();
        temp -> mini = LONG_MAX;
        temp -> maxi = LONG_MIN;
        temp -> isBST = true;
        return temp;
    }
    
    BSTInfo* leftInfo = solve(root->left);
    BSTInfo* rightInfo =  solve(root->right);
    
    long long currNodeData = root -> val;
    BSTInfo* toReturnInfo = new BSTInfo();
    toReturnInfo -> isBST = leftInfo -> isBST && rightInfo -> isBST 
        && currNodeData > leftInfo -> maxi && currNodeData < rightInfo -> mini;
    toReturnInfo -> mini = min(leftInfo -> mini, min(rightInfo -> mini, currNodeData));
    toReturnInfo -> maxi = max(leftInfo -> maxi, max(rightInfo -> maxi, currNodeData));
    
    return toReturnInfo;
}

bool isValidBST(TreeNode* root) {
    return solve(root) -> isBST;
}
```
