# Burn Tree

## Problem statement

Given a binary tree and a node data called target. Find the minimum time required to burn the complete binary tree if the target is set on fire. It is known that in 1 second all nodes connected to a given node get burned. That is its left child, right child, and parent.

Note: The tree contains unique values.

## Approach 1 : Level Order Traversal (Radial Traversal)

- Time complexity : O(N)
- Space complexity : O(N)

```cpp
Node* markParents(Node* root, unordered_map<Node*, Node*> &parent_track, Node* res, int target){
    queue<Node*> queue;
    queue.push(root);
    while(!queue.empty()) { 
        Node* current = queue.front(); 
        queue.pop();
        if(current -> data == target)res = current;
        if(current->left) {
            parent_track[current->left] = current;
            queue.push(current->left);
        }
        if(current->right) {
            parent_track[current->right] = current;
            queue.push(current->right);
        }
    }
    return res;
}

int burn(Node* target,  unordered_map<Node*, Node*> &parent_track){    
    queue<Node*> q;
    q.push(target);
    unordered_map<Node*, int> vis;
    vis[target] = 1;
    int maxi = 0;
    while(!q.empty()){
        int size = q.size();
        int fl = 0;
        // Iterate on every node in queue for childs
        for(int i = 0 ; i < size ; i++){
            auto node = q.front();
            q.pop();
            if(node -> left && !vis[node->left]){
                fl = 1;
                vis[node->left] = 1;
                q.push(node -> left);
            }
            if(node -> right && !vis[node -> right]){
                fl = 1;
                vis[node -> right] = 1;
                q.push(node -> right);
            }
            if(parent_track[node] && !vis[parent_track[node]])
            {
                fl = 1;
                vis[parent_track[node]] = 1;
                q.push(parent_track[node]);
            }
        }
        if(fl) maxi++;
    }
    return maxi;
}

int minTime(Node* root, int target){
    // Marking parents and finding target node address
    unordered_map<Node*, Node*> parent;
    Node* res = markParents(root, parent, res, target);
    return burn(res, parent);
}
```