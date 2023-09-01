# Postorder Traversal

## Problem statement

Given the root of a binary tree, return the preorder traversal of its nodes' values.

## Approach 1 : Recursive

- Time complexity : O(N)
- Space complexity : O(LogN) 

```cpp
void postorder(TreeNode *root, vector<int> &res){
    if(!root)return;

    postorder(root -> left, res);
    postorder(root -> right, res);
    res.push_back(root -> val);
}

vector<int> postorderTraversal(TreeNode* root) {
    vector<int> res;
    postorder(root, res);
    return res;
}
```

## Approach 2 : Iterative (Two - Stack)

- Time complexity : O(N)
- Space complexity : O(N) 

```cpp
vector<int> postorderTraversal(TreeNode* root) {
    vector<int> ans;
    if(root == NULL)return ans;
    stack<TreeNode*> s1, s2;

    s1.push(root);
    while(!s1.empty()){
        TreeNode *curr = s1.top();
        s1.pop();
        s2.push(curr);

        if(curr->left != NULL)s1.push(curr->left);
        if(curr->right != NULL)s1.push(curr->right);
    }

    while(!s2.empty()){
        ans.push_back(s2.top()->val);
        s2.pop();
    }
    return ans;
}
```

## Approach 3 : Iterative (One - Stack)

- Time complexity : O(N)
- Space complexity : O(N)

```cpp
vector<int> postorderTraversal(TreeNode* root) {
    if(!root)return {};
    stack<TreeNode*> st;
    vector<int> res;
    TreeNode *curr = root;
        
    while(curr != NULL || !st.empty()){
        // Go as left as possible
        if(curr != NULL){
            st.push(curr);
            curr = curr -> left;
        }else{
            // No left node possible
            TreeNode *temp = st.top() -> right;
            if(temp == NULL){
                temp = st.top();
                st.pop();
                res.push_back(temp -> val);
                
                while(!st.empty() && temp == st.top() -> right){
                    temp = st.top();
                    st.pop();
                    res.push_back(temp -> val);
                }
            }else{
                curr = temp;
            }
        }
    }
    
    return res;
}
```