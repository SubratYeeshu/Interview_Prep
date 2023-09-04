## Delete Node in a BST

## Problem statement

Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

    Search for a node to remove.
    If the node is found, delete the node.


## Approach 1 : Implementation

- Time complexity : O(N)
- Space complexity : O(LogN)

```cpp
/*

    -> We have to point the parent to the appropriate node for deletion
    -> If the node we want to delete have only right tree then point the parent pointer to its next node  : CASE 1
    -> If the node we want to delete have only left tree then point the parent pointer to its next node  : CASE 2
    -> If we have both the subtree for the key, the adjust its right child to the correct position : CASE 3

*/
TreeNode *findLastRight(TreeNode *root){
    if(root -> right == NULL)return root;
    return findLastRight(root -> right);
}

TreeNode *helper(TreeNode *root){
    // CASE 1 : Only Left Subtree
    if(root -> right == NULL)return root -> left;
    // CASE 2 : Only Right Subtree
    else if(root -> left == NULL)return root -> right;
    // Both NULL Leaf Node
    else if(root -> left == NULL && root -> right == NULL)return NULL;  // Automatically Handeled using case 1 and 2
    // CASE 3 : Both Subtree
    else{
        TreeNode *rightChild = root -> right;
        TreeNode *lastRight = findLastRight(root -> left);
        lastRight -> right = rightChild;  // Adjusted the left tree, now point the pointer to its next
        return root -> left;
    }
}

TreeNode* deleteNode(TreeNode* root, int key) {
    if(!root)return NULL;
    
    if(root -> val == key)return helper(root);
    
    TreeNode *curr = root;
    
    while(curr != NULL){
        if(curr -> val > key){
            // Left subtree
            if(curr -> left && curr -> left -> val == key){
                // Parent of the key found
                curr -> left = helper(curr -> left);
                break;
            }else curr = curr -> left;
        }
        else{
            // Left subtree
            if(curr -> right && curr -> right -> val == key){
                // Parent of the key found
                curr -> right = helper(curr -> right);
                break;
            }else curr = curr -> right;
        }
    }
    
    return root;
}
```