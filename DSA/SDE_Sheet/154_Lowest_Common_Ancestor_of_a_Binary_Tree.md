# Lowest Common Ancestor of a Binary Tree

## Problem statement

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree. According to the definition of LCA on Wikipedia: 
- “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

## Approach 1 : DFS (Brute - Root to node path)

- Time complexity : O(N)
- Space complexity : O(N)

```cpp
bool rootToNode(TreeNode *root, TreeNode *target, vector<int> &path){
    if(!root)return false;

    path.push_back(root -> val);

    if(root -> val == target -> val){
        return true;
    }

    bool fromLeft = rootToNode(root -> left, target, path);
    bool fromRight = rootToNode(root -> right, target, path);

    if(fromLeft || fromRight)return true;

    path.pop_back();

    return false;
}

TreeNode* find(TreeNode *root, int x){
    if(!root)return NULL;
    if(root -> val == x)return root;
    
    TreeNode *fromLeft = find(root -> left, x);
    TreeNode *fromRight = find(root -> right, x);
    
    if(fromLeft == NULL)return fromRight;
    else return fromLeft;
}

TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {
    vector<int> path1, path2;
    bool temp1 = rootToNode(root, p, path1);
    bool temp2 = rootToNode(root, q, path2);
    
    if(!temp1 || !temp2)return NULL;
    
    int x = INT_MIN;
    for(int i = 0 ; i < min(path1.size(), path2.size()) ; i++){
        if(path1[i] == path2[i])continue;
        else {
            x = path1[i - 1];
            break;
        }
    }
    
    if(x == INT_MIN)x = path1.size() > path2.size() ? path2[path2.size() - 1] : path1[path1.size() - 1];
    

    return find(root, x);
}
```

## Approach 2 : DFS (Optimal)

- Time complexity : O(N)
- Space complexity : O(LogN)

```cpp
/*
    
    -> We can define LCA as the node from which if we ask the left subtree and the right subtree and both of them 
    -> Simultaneously returns a true then we call that node to be LCA of node1 and node2

*/
TreeNode* LCA(TreeNode *root, TreeNode *node1, TreeNode *node2){
    if(!root)return NULL;
    if(root -> val == node1 -> val || root -> val == node2 -> val)return root;
    
    TreeNode *fromLeft = LCA(root -> left, node1, node2);
    TreeNode *fromRight = LCA(root -> right, node1, node2);
    
    if(fromLeft != NULL && fromRight != NULL)return root;
    if(fromLeft == NULL)return fromRight;
    else return fromLeft;
}

TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {
    return LCA(root, p, q);
}
```