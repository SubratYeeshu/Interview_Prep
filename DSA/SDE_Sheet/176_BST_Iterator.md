# Binary Search Tree Iterator

## Problem statement

Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):

- BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor. The pointer should be initialized to a non-existent number smaller than any element in the BST. 

- boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.

- int next() Moves the pointer to the right, then returns the number at the pointer.

Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return the smallest element in the BST.

You may assume that next() calls will always be valid. That is, there will be at least a next number in the in-order traversal when next() is called.

## Approach 1 : Storing the inorder

- Time complexity : O(1), next(), hasNext()
- Space complexity : O(N)

```cpp
class BSTIterator {
private: 
    vector<int> inorder;
    int i, size;
public:
    void solve(TreeNode *root, vector<int> &res){
        if(!root)return;
        
        solve(root -> left, res);
        inorder.push_back(root -> val);
        solve(root -> right, res);
    }
    
    BSTIterator(TreeNode* root) {
        solve(root, inorder);
        this -> size = inorder.size();
        this -> i = 0;
    }
    
    int next() {
        return inorder[i++];
    }
    
    bool hasNext() {
        return i < size ? true : false;
    }
};
```

## Approach 2 : Stack

- Time complexity : O(1), next(), hasNext()
- Space complexity : O(Height)

```cpp
// For smaller push temp -> left, move root = root -> right
class BSTIterator {
private: 
    stack<TreeNode*> st;
public:
    void push(TreeNode *root){
        while(root)st.push(root), root = root -> left;
    }
    
    BSTIterator(TreeNode* root) {
        push(root);
    }
    
    int next() {
        TreeNode *temp = st.top();
        st.pop();
        push(temp -> right);
        return temp -> val;
    }
    
    bool hasNext() {
        return !st.empty();
    }
};
```