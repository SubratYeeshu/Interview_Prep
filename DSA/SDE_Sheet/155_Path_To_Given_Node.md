# Path To Given Node

Print the path from root to node

## Problem statement

Time complexity : O(N)
Space complexity : O(LogN)

```cpp
bool dfs(TreeNode *root, int target, vector<int> &path){
    if(!root)return false;
    
    path.push_back(root -> val);
    
    if(root -> val == target){
        return true;
    }
    
    bool fromLeft = dfs(root -> left, target, path);
    bool fromRight = dfs(root -> right, target, path);
    
    if(fromLeft || fromRight)return true;
    
    path.pop_back();
    
    return false;
}
```