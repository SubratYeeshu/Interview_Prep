# Diagonal Traversal of Binary Tree

## Problem statement

Given a Binary Tree, print the diagonal traversal of the binary tree.
- Consider lines of slope -1 passing between nodes. Given a Binary Tree, print all diagonal elements in a binary tree belonging to same line.
- If the diagonal element are present in two different subtress then left subtree diagonal element should be taken first and then right subtree.

## Approach 1 : 

- Time complexity : O(N)
- Space complexity : O(N)

```cpp
vector<int> diagonal(Node *root){
    if(!root)return {};
    vector<int> ans;
    queue<Node*> q;
    q.push(root);
   
    while(!q.empty()){
        int size = q.size();
        Node *curr = q.front();
        q.pop();
       
        while(curr != NULL){
            ans.push_back(curr -> data);
            if(curr -> left)q.push(curr -> left);
            curr = curr -> right;
        }
    }
    return ans;
}
```