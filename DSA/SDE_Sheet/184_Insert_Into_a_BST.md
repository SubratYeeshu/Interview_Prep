# Insert Into a BST

## Problem statement 

You are given the root node of a binary search tree (BST) and a value to insert into the tree. Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.

Notice that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.

## Approach 1 : Implementation

- Time complexity : O(N)
- Space complexity : O(1)

```cpp
/*
    
    -> Insert at Leaf

*/
TreeNode* insertIntoBST(TreeNode* root, int val) {
    if(!root)return new TreeNode(val);  
    TreeNode *curr = root;
    
    while(true){
        if(curr -> val > val){
            // Move left
            if(curr -> left)curr = curr -> left;
            else {
                curr -> left = new TreeNode(val);
                break;
            }
        }else{
            // Move right
            if(curr -> right)curr = curr -> right;
            else {
                curr -> right = new TreeNode(val);
                break;
            }
        }
    }
    
    return root;
}
```