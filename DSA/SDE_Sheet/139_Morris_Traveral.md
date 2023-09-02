# Morris Traversal

## Problem statement 

Find the inorder and preorder traversal of a binary tree without using extra space

## Variation 1 : Morris Traversal (Inorder)

- Time complexity : O(N)
- Space complexity : O(1) 

```cpp
vector<int> inorderTraversal(TreeNode* root) {
    vector<int> res;
    TreeNode *curr = root;
    
    while(curr != NULL){
        if(curr -> left == NULL){
            // Inorder / Preorder Processing Part
            res.push_back(curr -> val);
            curr = curr -> right;
            
        }else{
            // Thread creation
            TreeNode *temp = curr -> left;
            while(temp -> right != NULL && temp -> right != curr)temp = temp -> right;  // Rightmost node on the left subtree
    
            if(temp -> right == NULL){
                // No thread present
                temp -> right = curr;
                // res.push_back(curr -> val);  // Pre order
                curr = curr -> left;
            }else{
                // Loop thread is present
                temp -> right = NULL;
                
                // Inorder Processing Part
                res.push_back(curr -> val);  // Inorder
                
                curr = curr -> right;
            }
        }
    }
    
    return res;
}
```

## Variation 2 : Morris Traversal (Preorder)

- Time complexity : O(N)
- Space complexity : O(1) 

```cpp
vector<int> preorderTraversal(TreeNode* root) {
    vector<int> res;
    TreeNode *curr = root;

    while(curr != NULL){
        if(curr -> left == NULL){
            // Inorder / Preorder Processing Part
            res.push_back(curr -> val);
            curr = curr -> right;
        }else{
            // Thread creation
            TreeNode *temp = curr -> left;
            while(temp -> right != NULL && temp -> right != curr)temp = temp -> right;  // Rightmost node on the left subtree

            if(temp -> right == NULL){
                // No thread present
                temp -> right = curr;

                // Prorder Processing Part
                res.push_back(curr -> val);  // Pre order
                
                curr = curr -> left;
            }else{
                // Loop thread is present
                temp -> right = NULL;
                // res.push_back(curr -> val);  // Inorder
                curr = curr -> right;
            }
        }
    }
    return res;
}
```