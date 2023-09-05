# Flatten Binary Tree to Linked List

## Problem statement 

Given the root of a binary tree, flatten the tree into a "linked list":
- The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
- The "linked list" should be in the same order as a pre-order traversal of the binary tree.

## Approach 1 : DFS (Post order)

- Time complexity : O(N)
- Space complexity : O(LogN)

```cpp
TreeNode *prev = NULL;
    void flatten(TreeNode* root) {
    if(!root)return;
    
    flatten(root -> right);
    flatten(root -> left);
    
    root -> right = prev;
    root -> left = NULL;
    prev = root;
}
```

## Approach 2 : Stack 

- Time complexity : O(N)
- Space complexity : O(LogN)

```cpp
void flatten(TreeNode* root) {
    if(!root) return; 
    stack<TreeNode*> st; 
    st.push(root);
    
    while(!st.empty()) {
        TreeNode* cur = st.top(); 
        st.pop(); 
        
        if(cur -> right != NULL)st.push(cur -> right); 
        if(cur -> left != NULL)st.push(cur -> left); 
        if(!st.empty())cur -> right = st.top(); 
        cur -> left = NULL;
    }
}
```

## Approach 2 : Implementation

- Time complexity : O(N)
- Space complexity : O(1)

```cpp
void flatten(TreeNode* root) {
    TreeNode *curr = root;
    
    while(curr != NULL){
        if(curr -> left){
            TreeNode *temp = curr -> left;
            
            // Linkage / Absorb the nodes in between
            while(temp -> right != NULL)temp = temp -> right;
            temp -> right = curr -> right;
            curr -> right = curr -> left;
            curr -> left = NULL;
        }
        
        curr = curr -> right;
    }
}
```