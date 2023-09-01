# Count Complete Tree Nodes

## Problem statement

Given the root of a complete binary tree, return the number of the nodes in the tree.

According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Design an algorithm that runs in less than O(n) time complexity.

## Approach 1 : Brute

- Time complexity : O(N)
- Space complexity : O(N)

```cpp
int solve(TreeNode *root){
    if(!root)return 0;

    int fromLeft = solve(root -> left);
    int fromRight = solve(root -> right);
            
    return 1 + fromLeft + fromRight;
}

int countNodes(TreeNode* root) {
    return solve(root);
}
```

## Approach 2 : Optimal 

- Time complexity : O(LogN^2) : Number of times Binary Search (logN times) each takes logN time
- Space complexity : O(N)

```cpp
/*

    -> Number of nodes in perfect binary tree if if level is l -> 2^level - 1
    -> Number of nodes at any level if level is l -> 2^level - 1
    -> Level is 1 based

*/
pair<int, int> leftAndRightDepth(TreeNode *root){
    if(!root)return {0, 0};
    
    pair<int, int> depth;
    int hl = 0;
    TreeNode *curr = root;
    while(curr != NULL){
        curr = curr -> left;
        hl++;
    }
    
    int hr = 0;
    curr = root;
    while(curr != NULL){
        curr = curr -> right;
        hr++;
    }
    
    depth.first = hl;
    depth.second = hr;
    
    return depth;
}

int solve(TreeNode *root){
    if(!root)return 0;
    
    pair<int, int> p = leftAndRightDepth(root);
    int l = p.first, r = p.second;
    if(r == l)return pow(2, l) - 1;
    
    int fromLeft = solve(root -> left);
    int fromRight = solve(root -> right);
    
    return 1 + fromLeft + fromRight;
}

int countNodes(TreeNode* root) {
    return solve(root);
}
```