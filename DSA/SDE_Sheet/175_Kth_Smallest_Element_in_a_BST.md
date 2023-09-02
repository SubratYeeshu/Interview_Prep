# Kth Smallest Element in a BST

## Problem statement 

Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.

## Approach 1 : Inorder Sorted

- Time complexity : O(N)
- Space complexity : O(N)

```cpp
void inOrderTraversal(TreeNode* root, vector<int> &v){
    if(root == NULL)    return;
    
    //left, root, right 
    inOrderTraversal(root->left, v);
    v.push_back(root->val);
    inOrderTraversal(root->right, v);      
}
int kthSmallest(TreeNode* root, int k) {
    vector<int> v; 
    inOrderTraversal(root, v);
    return v[k-1];
}
```

## Approach 2 : Inorder

- Time complexity : O(N)
- Space complexity : O(LogN)

```cpp
int ans = 0;
    void dfsInorder(TreeNode* root, int &k) {
    if (!root) return;
    dfsInorder(root->left, k);  // First call for kth largest
    if (--k == 0){
        ans = root->val;
        return;
    } 
    dfsInorder(root->right, k);
}  

int kthSmallest(TreeNode* root, int k) {
    dfsInorder(root, k);
    return ans;
}
```