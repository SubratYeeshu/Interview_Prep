# Populate Next Pointer

## Problem statement

You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

## Approach 1 : Level Order Travesal

- Time complexity : O(N)
- Space complexity : O(N)

```cpp
// This also works on non complete binary tree (Populate next pointer - 2)
Node* connect(Node* root) {
    if(!root)return root;
    queue<Node*> q;
    q.push(root);
    
    while(!q.empty()){
        int size = q.size();
        
        for(int i = 0 ; i < size ; i++){
            Node *curr = q.front();
            q.pop();
            
            if(size - i > 1)curr -> next = q.front();
            // if(!q.empty())curr -> next = q.front(); // wrong
            
            if(curr -> left)q.push(curr -> left);
            if(curr -> right)q.push(curr -> right);
        }
    }
    
    return root;
}
```

## Approach 2 : Implementation

- Time complexity : O(N)
- Space complexity : O(1)

```cpp
// Works only on CBT
Node* connect(Node* root) {
    if(!root)return root;
    
    Node *curr = root;
    
    while(curr -> left != NULL){
        Node *temp = curr;
        while(temp != NULL){  // Iterate in each level to connect the pointers
            temp -> left -> next = temp -> right;
            temp -> right -> next = (temp -> next) ? temp -> next -> left : NULL;
            temp = temp -> next;
        }
        curr = curr -> left;
    }
    
    return root;
}
```

## Approach 2 : Recursive

- Time complexity : O(N)
- Space complexity : O(LogN)

```cpp
// Works only on CBT
void connectRecur(Node *&root){
    if(!root)return;

    if(root -> left)root -> left-> next = root -> right;
    if(root -> right)root -> right -> next = (root -> next) ? root -> next -> left : NULL;

    connectRecur(root -> left);
    connectRecur(root -> right);
}
Node* connect(Node* root) {
    if(!root)return root;
    
    connectRecur(root);
    
    return root;
}
```