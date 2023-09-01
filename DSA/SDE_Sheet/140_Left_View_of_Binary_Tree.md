# Left View of Binary Tree

## Problem statement

Given a Binary Tree, return Left view of it. Left view of a Binary Tree is set of nodes visible when tree is visited from Left side. The task is to complete the function leftView(), which accepts root of the tree as argument.

## Approach 1 : DFS

- Time complexity : O(N)
- Space complexity : O(LogN)

```cpp
void solve(Node *root, vector<int> &res, int level){
    if(!root)return;
    
    if(level == res.size())res.push_back(root -> data);
    solve(root -> left, res, level + 1);
    solve(root -> right, res, level + 1);
}

vector<int> leftView(Node *root){
   vector<int> res;
   solve(root, res, 0);
   return res;
}
```

## Approach 2 : Level Order

- Time complexity : O(N)
- Space complexity : O(N)

```cpp
vector<int> leftView(Node *root){
    if(!root)return {};
    vector<int> res;
    queue<Node*> q;
    q.push(root);
    
    while(!q.empty()){
        int size = q.size();
        for(int i = 0 ; i < size ; i++){
            Node *curr = q.front();
            q.pop();
            if(i == 0)res.push_back(curr -> data);
            if(curr -> left)q.push(curr -> left);
            if(curr -> right)q.push(curr -> right);
        }
    }
    return res;
}
```