# Recover BST

## Problem statement

You are given the root of a binary search tree (BST), where the values of exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.

## Approach 1 : Brute 

- Time complexity : O(N)
- Space complexity : O(N)

```cpp
void inorder(TreeNode *root, vector<TreeNode*> &res){
    if(!root)return;
    
    inorder(root -> left, res);
    res.push_back(root);
    inorder(root -> right, res);
}

void recoverTree(TreeNode* root) {
    vector<TreeNode*> v;
    inorder(root, v);
    
    TreeNode *a = NULL, *b = NULL, *m = NULL;
    
    for(int i = 1 ; i < v.size() ; i++){
        if(v[i] -> val < v[i - 1] -> val){
            if(a == NULL){
                a = v[i - 1];
                m = v[i];
            }else{
                b = v[i];
            }
        }
    }
    
    // Think Graphically
    if(b == NULL){
        // Adjacent swaps
        int temp = a -> val;
        a -> val = m -> val;
        m -> val = temp;
    }else{
        // Large number came infront
        int temp = a -> val;
        a -> val = b -> val;
        b -> val = temp;
    }
}
```

## Approach 2 : Recursive (Inorder)

- Time complexity : O(N)
- Space complexity : O(LogN)

```cpp
TreeNode *a = NULL, *b = NULL, *m = NULL, *prev = NULL;
void solve(TreeNode *root){
    if(!root)return;
    
    solve(root -> left);
    
    // Inorder Part
    if(prev != NULL && prev -> val > root -> val){
        if(a == NULL){
            a = prev;
            m = root;
        }else{
            b = root;
        }
    }
    
    prev = root;
    
    solve(root -> right);
    
}
void recoverTree(TreeNode* root) {
    solve(root);
    
    // Think Graphically
    if(b == NULL){
        // Adjacent swaps
        int temp = a -> val;
        a -> val = m -> val;
        m -> val = temp;
    }else{
        // Large number came infront
        int temp = a -> val;
        a -> val = b -> val;
        b -> val = temp;
    }
}
```

## Approach 3 : Morris Traversal 

- Time complexity : O(N)
- Space complexity : O(1)

```cpp
TreeNode* a = NULL;
TreeNode* b = NULL;
TreeNode* m = NULL;
TreeNode* prev = NULL;
    
void helper(TreeNode* curr) {
    if (curr == NULL) return;
    while(curr != NULL) {
        if (curr -> left == NULL) {
            if (prev != NULL && curr -> val < prev -> val) {  
                if (a == NULL) {
                    a = prev; 
                    m = curr;
                }
                else {
                    b = curr;
                }
            }

            prev = curr; 
            curr = curr -> right;
        }
        else {
            TreeNode *temp = curr->left;
            while(temp -> right != NULL && temp -> right != curr) {
                temp  =  temp -> right;
            }
            
            if (temp -> right == NULL) {
                temp -> right = curr;
                curr = curr -> left;
            }
            else {
                
                temp -> right = NULL;
                if (prev != NULL && curr -> val < prev -> val) {   
                    if (a == NULL) {
                        a = prev; 
                        m = curr;
                    }
                    else {
                        b = curr;
                    }
                }
                prev = curr; 
                
                curr = curr -> right;
            }
        }
    } 
}

void recoverTree(TreeNode* root) {
    helper(root);
    
    if(b == NULL){
        // Adjacent swaps
        int temp = a -> val;
        a -> val = m -> val;
        m -> val = temp;
    }else{
        // Large number came infront
        int temp = a -> val;
        a -> val = b -> val;
        b -> val = temp;
    }
}
```