# Children Sum Parent Property

## Problem statement 

Given a Binary Tree. Check whether all of its nodes have the value equal to the sum of their child nodes.

## Approach 1 : DFS 

- Time complexity : O(N)
- Space complexity : O(LogN)

```cpp
bool solve(Node *root){
    if(!root)return true;
    if(!root -> right && !root -> left)return true;
    
    int sum = 0;
    
    if(root -> left)sum += root -> left -> data;
    if(root -> right)sum += root -> right -> data;
    
    if(sum != root -> data)return false;
    
    return solve(root -> left) && solve(root -> right);
}
```

## Approach 2 : DFS + (Minor changes)

- Time complexity : O(N)
- Space complexity : O(LogN)

```cpp
bool solve(Node *root){
    if(!root)return true;
    
    int sum = 0;
    
    if(root -> left)sum += root -> left -> data;
    if(root -> right)sum += root -> right -> data;
    
    if(sum != root -> data && (root -> left || root -> right))return false;
    
    return solve(root -> left) && solve(root -> right);
}
```

## Variation : Sum of subtrees same as parent node

- Time complexity : O(N)
- Space complexity : O(LogN)

```cpp
bool flag = true;
    int solve(Node *root){
    if(!root)return 0;
    
    int leftSum = solve(root -> left);
    int rightSum = solve(root -> right);
    
    if(leftSum + rightSum != root -> data && (root -> left || root -> right))flag = false;
    
    return leftSum + rightSum + root -> data;
}
```