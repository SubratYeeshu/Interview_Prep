# Construct Binary Tree from Inorder and Postorder Traversal

## Problem statement

Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.

## Approach 1 : Implementation

- Time complexity : O(N)
- Space complexity : O(N)

```cpp
TreeNode *buildTree(int is, int ie, int ps, int pe, unordered_map<int, int> &mp, vector<int> &inorder, vector<int> &postorder){
    if(is > ie || ps > pe)return NULL;
    
    TreeNode *root = new TreeNode(postorder[pe]);
    
    int idx = mp[postorder[pe]];
    int numsLeft = idx - is;
    
    root -> left = buildTree(is, idx - 1, ps, ps + numsLeft - 1, mp, inorder, postorder);
    root -> right = buildTree(idx + 1, ie, ps + numsLeft, pe - 1, mp, inorder, postorder);
    
    return root;
}

TreeNode* buildTree(vector<int>& inorder, vector<int>& postorder) {
    int n = inorder.size();
    unordered_map<int, int> mp;
    for(int i = 0 ; i < n ; i++)mp[inorder[i]] = i;
    int is = 0, ie = n - 1, ps = 0, pe = n - 1;  // Short Variable Naming
    return buildTree(is, ie, ps, pe, mp, inorder, postorder );        
}
```