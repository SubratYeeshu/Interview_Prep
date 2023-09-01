# All Nodes Distance K in Binary Tree

## Problem statement

Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values of all nodes that have a distance k from the target node.

You can return the answer in any order.

## Approach 1 : Level Order Traversal (Radial Traversal)

- Time complexity : O(N)
- Space complexity : O(N)

```cpp
void findParent(TreeNode* root, unordered_map<TreeNode*, TreeNode*>& par){
    queue<TreeNode*>q;
    q.push(root);
    
    while(!q.empty()){
        int size = q.size();
        for(int i = 0 ; i < size ; i++){
            TreeNode* node = q.front(); q.pop();
            
            if(node -> left){
                par[node -> left] = node;
                q.push(node -> left);
            }
            if(node -> right){
                par[node -> right] = node;
                q.push(node -> right);
            }
        }
    }
}

vector<int> distanceK(TreeNode* root, TreeNode* target, int k) {
    // Create a graph like structure for raidal traversal
    unordered_map<TreeNode*, TreeNode*> par;
    findParent(root, par);
    
    // Radial Level Order - Just like graph traversal 3 direction traversal, graph can have infinite so we can use 3 loop in case of graph
    unordered_map<TreeNode*, bool> vis;
    queue<TreeNode*>q;
    q.push(target);
    vis[target] = true;
    
    // Declaration
    vector<vector<int>>radialTraversal;
    vector<int> res;
    int currLevel = 0;
    
    while(!q.empty()){
        int size = q.size();
        currLevel++;
        vector<int> radialLevel;
        for(int i = 0 ; i < size ; i++){
            TreeNode* currNode = q.front(); q.pop();
            
            // Three Directions possible
            if(currNode -> left && !vis[currNode -> left]){
                q.push(currNode -> left);
                vis[currNode -> left] = 1;
            }
            
            if(currNode -> right && !vis[currNode -> right]){
                q.push(currNode -> right);
                vis[currNode -> right] = 1;
            }
            
            if(par[currNode] && !vis[par[currNode]]){
                q.push(par[currNode]);
                vis[par[currNode]] = 1;
            }
            
            radialLevel.push_back(currNode -> val);
        }
        radialTraversal.push_back(radialLevel);
    }
    
    return k < radialTraversal.size() ? radialTraversal[k] : res;
}
```