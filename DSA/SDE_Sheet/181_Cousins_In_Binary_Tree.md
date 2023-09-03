# Cousins In Binary Tree

## Problem statement

Given the root of a binary tree with unique values and the values of two different nodes of the tree x and y, return true if the nodes corresponding to the values x and y in the tree are cousins, or false otherwise.

Two nodes of a binary tree are cousins if they have the same depth with different parents.

Note that in a binary tree, the root node is at the depth 0, and children of each depth k node are at the depth k + 1.

## Approach 1 : Maintain Information For Each Node

- Time complexity : O(N)
- Space complexity : O(N)

```cpp
// Mapping : Node Value, Depth, Parent
bool isCousins(TreeNode* root, int x, int y) {
    unordered_map<int, pair<int, int>> m;
    helper(root, 0, -1, m);
    return m[x].first == m[y].first && m[x].second != m[y].second;
}
void helper(TreeNode* node, int depth, int parent, unordered_map<int, pair<int, int>>& m) {
    if (!node) return;
    m[node->val] = {depth, parent};
    helper(node->left, depth + 1, node->val, m);
    helper(node->right, depth + 1, node->val, m);
}
```

## Approach 2 : Same Depth + Not Same Parent

- Time complexity : O(2N)
- Space complexity : O(LogN)

```cpp
TreeNode* LCA(TreeNode *root, int node1, int node2){
    if(!root)return NULL;
    if(root -> val == node1 || root -> val == node2)return root;

    TreeNode *fromLeft = LCA(root -> left, node1, node2);
    TreeNode *fromRight = LCA(root -> right, node1, node2);

    if(fromLeft != NULL && fromRight != NULL)return root;
    if(fromLeft == NULL)return fromRight;
    else return fromLeft;
}

int findDistance(TreeNode *root, int tg, int level){
    if(!root)return -1;

    if(root -> val == tg)return level;

    int leftDistance = findDistance(root -> left, tg, level + 1);
    int rightDistance = findDistance(root -> right, tg, level + 1);

    if(leftDistance != -1)return leftDistance;
    return rightDistance;
}


bool isCousins(TreeNode* root, int x, int y) {
    TreeNode *lca = LCA(root, x, y);
    if(lca != NULL && lca -> left && lca -> right)
        if(lca -> left -> val == x && lca -> right -> val == y || 
            lca -> left -> val == y && lca -> right -> val == x)return false;
    
    
    cout << lca -> val;
    // Distance from LCA
    int d1 = findDistance(lca, x, 0);
    int d2 = findDistance(lca, y, 0);
    
    return d1 == d2;
}
```

## Approach 2 : Maintain Information

- Time complexity : O(N)
- Space complexity : O(LogN)

```cpp
/*

    -> Maintain 4 vairables
    -> 2 Information for each of the two nodes 
    -> Their depth and their parentif(root->val==x || root->val==y) return false;

*/
int xdepth, ydepth, xparent, yparent;
void depth(TreeNode *root, int x, int y, int par, int level){
    if(!root)return;
    
    if(root -> val == x){
        xdepth = level;
        xparent = par;
        return;
    }
    
    if(root -> val == y){
        ydepth = level;
        yparent = par;
        return;
    }
    
    depth(root -> left, x, y, root -> val, level + 1);
    depth(root -> right, x, y, root -> val, level + 1);
    
}

bool isCousins(TreeNode* root, int x, int y) {
    depth(root, x, y, 0, 0);

    if(xdepth == ydepth && xparent != yparent)return true;
    return false;
}
```