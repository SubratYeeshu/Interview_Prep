# Level Order Traversal 

## Problem statement

Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).

## Approach 1 : Level order (Multi Line - Level Wise)

Time complexity : O(N)
Space complexity : O(N)

```cpp
vector<vector<int>> levelOrder(TreeNode* root) {
    if(!root)return {};
    vector<vector<int>> levels;
    queue<TreeNode*> q;
    q.push(root);
    
    while(!q.empty()){
        int size = q.size();
        vector<int> level;
        
        for(int i = 0 ; i < size ; i++){
            TreeNode *curr = q.front();
            q.pop();
            
            cout << curr -> val << " ";
            
            level.push_back(curr -> val);
            if(curr -> left)q.push(curr -> left);
            if(curr -> right)q.push(curr -> right);
        }
        cout << endl;
        
        levels.push_back(level);
    }
    
    return levels;
}
```

## Approach 2 : Level order (Single Line - Level Wise)

Time complexity : O(N)
Space complexity : O(N)

```cpp
vector<vector<int>> levelOrder(TreeNode* root) {
    if(!root)return {};
    vector<vector<int>> levels;
    queue<TreeNode*> q;
    q.push(root);
    
    while(!q.empty()){
        int size = q.size();
        vector<int> level;
        
        TreeNode *curr = q.front();
        q.pop();

        cout << curr -> val << " ";

        if(curr -> left)q.push(curr -> left);
        if(curr -> right)q.push(curr -> right);
    }
    
    return levels;
}
```