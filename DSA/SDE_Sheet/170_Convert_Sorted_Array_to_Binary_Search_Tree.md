# Convert Sorted Array to Binary Search Tree

## Problem statement

Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.

## Approach 1 : Implementation

- Time complexity : O(N)
- Space complexity : O(LogN)

```cpp
TreeNode *buildTree(int is, int ie, vector<int> &inorder){
    if(is > ie)return NULL;
    
    int rootIdx = (is + ie) / 2; 
    TreeNode *root = new TreeNode(inorder[rootIdx]);
    
    root -> left = buildTree(is, rootIdx - 1, inorder);
    root -> right = buildTree(rootIdx + 1, ie, inorder);
    
    return root;
}

TreeNode* sortedArrayToBST(vector<int>& nums) {
    int n = nums.size(), is = 0, ie = nums.size() - 1;
    return buildTree(is, ie, nums);
}
```