# Binary Tree Zigzag Level Order Traversal

## Problem statement

Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).

## Approach 1 : Level order traversal

Time complexity : O(N)
Space complexity : O(N)

```cpp
vector<vector<int>> zigzagLevelOrder(TreeNode* root) {
    if(!root)return {};
    vector<vector<int>> levels;
    queue<TreeNode*> q;
    q.push(root);
    bool flag = true;
    
    while(!q.empty()){
        int size = q.size();
        vector<int> level (size);
        for(int i = 0 ; i < size ; i++){
            TreeNode *curr = q.front();
            q.pop();
            
            int idx = flag ? i : size - i - 1;
            level[idx] = curr -> val;
            
            if(curr -> left)q.push(curr -> left);
            if(curr -> right)q.push(curr -> right);
        }
        levels.push_back(level);
        flag = !flag;
    }       
    
    return levels;
}
```

## Approach 1.2 Level order traverasl 

Time complexity : O(N^2)
Space complexity : O(N)

```cpp
vector<vector<int>> zigzagLevelOrder(TreeNode* root) {
    vector<vector<int>> ans;
    if(root == nullptr) return ans;
    queue<TreeNode* > q;
    q.push(root);
    int count = 0;
    while(!q.empty()){
        int size = q.size();
        vector<int> level;
        for(int i = 0;i < size; i++){
            TreeNode * a = q.front();
            q.pop();
            if(a->left) q.push(a->left);
            if(a->right) q.push(a->right);
            level.push_back(a->val);
            
        }
        if(count &1){
                reverse(level.begin(), level.end());
                ans.push_back(level);
                count++;
            }
            else{
                ans.push_back(level);
                count++;
            }
    }
    return ans;
}
```