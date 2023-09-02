# Min distance between two given nodes of a Binary Tree

## Problem statement 

Given a binary tree and two node values your task is to find the minimum distance between them. The given two nodes are guaranteed to be in the binary tree and nodes are numbered from 1 to N. Please Note that a and b are not always leaf node.

## Approach 1 : LCA (Storing Path)

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

## Approach 2 : LCA (Without Storing Path)

- Time complexity : O(N)
- Space complexity : O(LogN)

```cpp
Node* LCA(Node *root, int node1, int node2){
    if(!root)return NULL;
    if(root -> data == node1 || root -> data == node2)return root;
    
    Node *fromLeft = LCA(root -> left, node1, node2);
    Node *fromRight = LCA(root -> right, node1, node2);
    
    if(fromLeft != NULL && fromRight != NULL)return root;
    if(fromLeft == NULL)return fromRight;
    else return fromLeft;
}

int findDistance(Node *root, int tg, int level){
    if(!root)return -1;
    
    if(root -> data == tg)return level;
    
    int leftDistance = findDistance(root -> left, tg, level + 1);
    int rightDistance = findDistance(root -> right, tg, level + 1);
    
    if(leftDistance != -1)return leftDistance;
    return rightDistance;
}

int findDist(Node* root, int a, int b) {
    Node *lcaNode = LCA(root, a, b);

    if(!lcaNode)return 0;
    
    int d1 = findDistance(lcaNode, a, 0);
    int d2 = findDistance(lcaNode, b, 0);
    
    return d1 + d2;   
}
```