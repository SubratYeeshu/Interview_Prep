# Min distance between two given nodes of a Binary Tree

## Problem statement 

Given a binary tree and two node values your task is to find the minimum distance between them. The given two nodes are guaranteed to be in the binary tree and nodes are numbered from 1 to N. Please Note that a and b are not always leaf node.

## Approach 1 : Store LCA Path

- Time complexity : O(N)
- Space complexity : O(N)

```cpp
bool rootToNode(Node *root, vector<int> &path, int tg){
    if(!root)return false;
    
    path.push_back(root -> data);
    
    if(root -> data == tg){
        return true;
    }
    
    bool fromLeft = rootToNode(root -> left, path, tg);
    bool fromRight = rootToNode(root -> right, path, tg);
    
    if(fromLeft || fromRight)return true;
    
    path.pop_back();
    
    return false;
}

int findDist(Node* root, int a, int b) {
    vector<int> path1, path2;
    bool n1 = rootToNode(root, path1, a);
    bool n2 = rootToNode(root, path2, b);
    
    if(!n1 || !n2)return false;
    
    int n = path1.size(), m = path2.size(), j = 0;
    for(int i = 0 ; i < min(n, m) ; i++){
        if(path1[i] == path2[i])j = i;
    }
    
    return m + n - 2 * j - 2;
}
```