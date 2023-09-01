# Preorder Traversal

## Problem statement

Given the root of a binary tree, return the preorder traversal of its nodes' values.

## Approach 1 : Recursive

- Time complexity : O(N)
- Space complexity : O(LogN) 

```cpp
void preorder(TreeNode *root, vector<int> &res){
    if(!root)return;

    res.push_back(root -> val);
    preorder(root -> left, res);
    preorder(root -> right, res);
}

vector<int> preorderTraversal(TreeNode* root) {
    vector<int> res;
    preorder(root, res);
    return res;
}
```

## Approach 2 : Iterative (Stack)

- Time complexity : O(N)
- Space complexity : O(N) 

```cpp
vector<int> preorderTraversal(TreeNode* root) {
    if(!root)return {};
    vector<int> res;
    stack<TreeNode*> st;
    st.push(root);
    while(!st.empty()){
        root = st.top();
        st.pop();
        
        res.push_back(root -> val);
        if(root -> right)st.push(root -> right);  // LIFO behaviour
        if(root -> left)st.push(root -> left);
    }
    
    return res;
}
```