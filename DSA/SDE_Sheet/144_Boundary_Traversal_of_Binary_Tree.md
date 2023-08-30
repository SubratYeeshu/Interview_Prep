# Boundary Traversal of Binary Tree

## Problem statement

Given a Binary Tree, find its Boundary Traversal. The traversal should be in the following order: 
- Left boundary nodes: defined as the path from the root to the left-most node ie- the leaf node you could reach when you always travel preferring the left subtree over the right subtree. 
- Leaf nodes: All the leaf nodes except for the ones that are part of left or right boundary.
- Reverse right boundary nodes: defined as the path from the right-most node to the root. The right-most node is the leaf node you could reach when you always travel preferring the right subtree over the left subtree. Exclude the root from this as it was already included in the traversal of left boundary nodes.
Note: If the root doesn't have a left subtree or right subtree, then the root itself is the left or right boundary.

## Approach 1.1 : Implementation

Time complexity : O(N)
Space complexity : O(N)

```cpp
/*

    -> If leftView exists then only take it else do not move
    -> If rightView exists then only take it else do not move
    -> First push the root
    -> Then push the left view except the root and the leaf
    -> Then push the reverse of right view except the root and the leaf

*/
bool IsLeafNode(Node *root){
    return !root -> left && !root -> right;
}
void leftView(Node *root,vector<int> &ans){
    if(!root) return;
    
    if(!IsLeafNode(root))ans.push_back(root -> data);
    
    if(root -> left)leftView(root -> left, ans);
    else if(root -> right)leftView(root -> right, ans);
}
void leaf(Node *root, vector<int> &ans){
    if(!root) return;
    
    leaf(root -> left, ans);
    leaf(root -> right, ans);
    
    if(IsLeafNode(root))ans.push_back(root -> data);
}
void rightView(Node *root, vector<int> &ans){
    if(!root) return;
    
    if(root -> right)rightView(root -> right, ans);
    else if(root -> left)rightView(root -> left, ans);
    
    if(!IsLeafNode(root))ans.push_back(root -> data);
}
vector <int> boundary(Node *root){
    vector<int> ans;
    if(!root) return ans;
    
    ans.push_back(root -> data);
    
    leftView(root -> left, ans);
    
    if(!IsLeafNode(root))leaf(root,ans);
    
    rightView(root -> right, ans);
    
    return ans;
}
```

## Approach 1.2 : Implementation

Time complexity : O(N)
Space complexity : O(N)

```cpp
inline bool isLeaf(Node *root){
    return !root -> left && !root -> right;
}

void addLeftBoundary(Node *root, vector <int> &res) {
    Node *cur = root -> left;
    while(cur) {
        if(!isLeaf(cur))res.push_back(cur -> data);
        
        if(cur -> left)cur = cur -> left;
        else cur = cur -> right;
    }
}
void addRightBoundary(Node *root, vector <int> &res) {
    Node *cur = root -> right;
    vector<int> tmp;
    while(cur){
        if(!isLeaf(cur))tmp.push_back(cur -> data);
        if(cur -> right)cur = cur -> right;
        else cur = cur -> left;
    }
    for (int i = tmp.size() - 1; i >= 0; --i) {
        res.push_back(tmp[i]);
    }
}

void addLeaves(Node *root, vector <int> &res) {
    if(isLeaf(root)){
        res.push_back(root -> data);
        return;
    }
    if(root -> left)addLeaves(root -> left, res);
    if(root -> right)addLeaves(root -> right, res);
}


vector <int> boundary(Node *root){
    vector<int> res;
    if(!root)return res;
    if(!isLeaf(root))res.push_back(root -> data);
    
    addLeftBoundary(root, res);
    addLeaves(root, res);
    addRightBoundary(root,res);
    
    return res;
}
```