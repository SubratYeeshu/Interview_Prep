# Max Width of Binary Tree

## Problem statement

Given the root of a binary tree, return the maximum width of the given tree.
- The maximum width of a tree is the maximum width among all levels. The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level are also counted into the length calculation. 

It is guaranteed that the answer will in the range of a 32-bit signed integer.

## Approach 1 : Level Order Traversal

Time complexity : O(N)
Space complexity : O(N)

```cpp
/*

    -> The width of binary tree is not equal to 2^h, think of a skew tree
    -> To find width of the binary tree we have to use level order traversal 
    -> Using level order traversal we can store the indexes of childs
    -> For 0 indexed tree the childs are at 2*i + 1 and 2*i + 2, for 1 based 2*i and 2*i + 1
    
*/
int widthOfBinaryTree(TreeNode* root) {
    long long maxi = INT_MIN;
    queue<pair<TreeNode*, long long>> q;
    q.push({root, 0});
    
    while(!q.empty()){
        int size = q.size();
        maxi = max(maxi, q.back().second - q.front().second + 1);  // We will get the while traversing through the last level
        
        for(int i = 0 ; i < size ; i++){
            TreeNode *curr = q.front().first;
            int parentIndex = q.front().second;
            q.pop();
            
            if(curr -> left)q.push({curr -> left, 1LL * 2 * parentIndex + 1});
            if(curr -> right)q.push({curr -> right, 1LL * 2 * parentIndex + 2});    
        }
    }
    
    return (int)maxi;
}
```