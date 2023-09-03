# Serialize and Deserialize Binary Tree

## Problem statement

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

## Approach 1 : Implementation

- Time complexity : O(N)
- Space complexity : O(N)

```cpp
/*
    
    -> We will simply do a level order traversal while serializing and deserilizing the tree
    -> When building a tree we have must have to know whether its left child is null or right
    -> So we have to push in both the child if they are null

*/
string serialize(TreeNode* root) {
    if(!root)return "";
    
    string data;
    queue<TreeNode*> q;
    q.push(root);
    
    while(!q.empty()){
        int size = q.size();
        
        for(int i = 0 ; i < size ; i++){
            TreeNode *curr = q.front();
            q.pop();
            
            if(curr){
                q.push(curr -> left);
                q.push(curr -> right);
                data += to_string(curr -> val) + ",";
            }else data += "#,";
            
        }
    }
    
    return data;
}

TreeNode* deserialize(string data) {
    if(data.size() == 0)return NULL;
    cout << data;
    stringstream ss(data);
    string temp = "";
    getline(ss, temp, ',');
    
    queue<TreeNode*> q;
    TreeNode *root = new TreeNode(stoi(temp));
    q.push(root);
    
    while(!q.empty()){
        int size = q.size();
        
        for(int i = 0 ; i < size ; i++){
            TreeNode *curr = q.front();
            q.pop();
            
            getline(ss, temp, ',');
            if(temp == "#"){
                curr -> left = NULL;
            }else{
                TreeNode *myNode = new TreeNode(stoi(temp));
                curr -> left = myNode;
                q.push(myNode);
            }
            
            getline(ss, temp, ',');
            if(temp == "#"){
                curr -> right = NULL;
            }else{
                TreeNode *myNode = new TreeNode(stoi(temp));
                curr -> right = myNode;
                q.push(myNode);
            }
        }
    }
    
    return root;
}
```