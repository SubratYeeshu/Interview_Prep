# Inorder Traversal

## Problem statement 

Given the root of a binary tree, return the inorder traversal of its nodes' values.

## Approach 1 : Recursive

- Time complexity : O(N)
- Space complexity : O(LogN) 

```cpp
void inorder(TreeNode *root, vector<int> &res){
    if(!root)return;
    
    inorder(root -> left, res);
    res.push_back(root -> val);
    inorder(root -> right, res);
}

vector<int> inorderTraversal(TreeNode* root) {
    vector<int> res;
    inorder(root, res);
    return res;
}
```

## Approach 2 : Iterative (Stack)

- Time complexity : O(N)
- Space complexity : O(N) 

```cpp
vector<int> inorderTraversal(TreeNode* root) {
    vector<int> res;
    stack<TreeNode*> st;
    TreeNode *curr = root;
    
    while(true){
        if(curr != NULL){
            st.push(curr);
            curr = curr -> left;
        }else{
            if(st.empty())break;
            curr = st.top();
            st.pop();
            res.push_back(curr -> val);
            curr = curr -> right;
        }
    }
    
    return res;
}
```