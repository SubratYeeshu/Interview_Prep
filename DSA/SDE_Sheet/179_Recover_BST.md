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