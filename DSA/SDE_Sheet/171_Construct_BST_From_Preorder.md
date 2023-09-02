# Construct BST (Unique) from Preorder

## Problem statement

Given an array of integers preorder, which represents the preorder traversal of a BST (i.e., binary search tree), construct the tree and return its root.

It is guaranteed that there is always possible to find a binary search tree with the given requirements for the given test cases.

A binary search tree is a binary tree where for every node, any descendant of Node.left has a value strictly less than Node.val, and any descendant of Node.right has a value strictly greater than Node.val.

A preorder traversal of a binary tree displays the value of the node first, then traverses Node.left, then traverses Node.right.

## Approach 1 : Insertion of Nodes

- Time complexity : O(NLogN) ~ N^2 Skewed Tree
- Space complexity : O(LogN)

```cpp
TreeNode* insertIntoBST(TreeNode *&root, int data) {
    if (!root) {
        root = new TreeNode(data);
        return root;
    }

    if (data > root -> val) {
        root -> right = insertIntoBST(root -> right, data);
    } else {
        root -> left = insertIntoBST(root -> left, data);
    }

    return root;
}

TreeNode* bstFromPreorder(vector<int>& preorder) {
    TreeNode *root = NULL;
    
    for(int i = 0 ; i < preorder.size() ; i++){
        insertIntoBST(root, preorder[i]);
    }
    
    return root;
}
```

## Approach 2 : Generating the inorder than applying Binary Tree Construction concept

- Time complexity : O(NLogN + N)
- Space complexity : O(LogN)

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

TreeNode* bstFromPreorder(vector<int>& preorder) {
    int n = preorder.size();
    int is = 0, ie = n - 1, ps = 0, pe = n - 1;
    
    vector<int> inorder = preorder;
    sort(inorder.begin(), inorder.end());
    unordered_map<int, int> mp;
    for(int i = 0 ; i < n ; i++)mp[inorder[i]] = i;
    
    return buildTree(is, ie, ps, pe, mp, preorder, inorder);
}
```

## Approach 3 : Optimal

- Time complexity : O(N)
- Space complexity : O(LogN)

```cpp
TreeNode* buildTree(int &index, int lowerBound, int upperBound, vector<int> &preorder){
    if(index >= preorder.size())return NULL;
    if(preorder[index] > upperBound || preorder[index] < lowerBound)return NULL;
    
    TreeNode *root = new TreeNode(preorder[index++]);
    
    root -> left = buildTree(index, lowerBound, root -> val, preorder);
    root -> right = buildTree(index, root -> val, upperBound, preorder);
    
    return root;
}

TreeNode* bstFromPreorder(vector<int>& preorder) {
    int index = 0;
    return buildTree(index, INT_MIN, INT_MAX, preorder);
}
```