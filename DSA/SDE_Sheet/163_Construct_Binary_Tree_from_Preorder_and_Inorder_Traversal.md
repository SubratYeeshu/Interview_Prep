# Construct Binary Tree from Preorder and Inorder Traversal

## Problem statement

Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.

## Approach 1.1 : Implementation

- Time complexity : O(N)
- Space complexity : O(N)

```cpp
/*

    -> Root Left Right
    -> preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
    ->             ^                       <--^------->
    -> 3 Root1, Left of Root1 (3) : 3, Right of Root1 (3) : 15, 20, 7

*/

TreeNode *buildTree(int preStartIndex, int preEndIndex, int inorderStartIndex, int inorderEndIndex, 
                    unordered_map<int, int> &mp, vector<int> &preorder, vector<int> &inorder){
    if(preStartIndex > preEndIndex || inorderStartIndex > inorderEndIndex)return NULL;
    
    TreeNode *root = new TreeNode(preorder[preStartIndex]);  // Declaring root
    
    int indexOfRootInInorder = mp[preorder[preStartIndex]];  // To the left if this index lies the left subtree and vice versa
    int numsInLeftSubtree = indexOfRootInInorder - inorderStartIndex;  // Number of elements in left subtree
    
                    
    root -> left = buildTree(preStartIndex + 1, preStartIndex + numsInLeftSubtree,
                                inorderStartIndex, indexOfRootInInorder - 1, mp, preorder, inorder);
    
    root -> right = buildTree(preStartIndex + numsInLeftSubtree + 1, preEndIndex,
                                indexOfRootInInorder + 1, inorderEndIndex, mp, preorder, inorder);
    
    return root;
}

TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
    int n = preorder.size();
    unordered_map<int, int> mp;
    for(int i = 0 ; i < n ; i++)mp[inorder[i]] = i;
    int preStartIndex = 0, preEndIndex = n - 1, inorderStartIndex = 0, inorderEndIndex = n - 1; 
    
    TreeNode *root = buildTree(preStartIndex, preEndIndex, inorderStartIndex, inorderEndIndex, mp, preorder, inorder);
    return root;
}
```

## Approach 1.2 : Short Variable Naming (More Readable)

- Time complexity : O(N)
- Space complexity : O(N)

```cpp
TreeNode *buildTree(int is, int ie, int ps, int pe, unordered_map<int, int> &mp, vector<int> &preorder, vector<int> &inorder){
    if(ps > pe || is > ie)return NULL;
    
    TreeNode *root = new TreeNode(preorder[ps]);
    
    int idx = mp[preorder[ps]];
    int numsLeft = idx - is;
    
    root -> left = buildTree(is, idx - 1, ps + 1, ps + numsLeft, mp, preorder, inorder);
    root -> right = buildTree(idx + 1, ie, ps + numsLeft + 1, pe, mp, preorder, inorder);
    
    return root;
}

TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
    int n = inorder.size();
    unordered_map<int, int> mp;
    for(int i = 0 ; i < n ; i++)mp[inorder[i]] = i;
    int is = 0, ie = n - 1, ps = 0, pe = n - 1;
    return buildTree(is, ie, ps, pe, mp, preorder, inorder);
}
```