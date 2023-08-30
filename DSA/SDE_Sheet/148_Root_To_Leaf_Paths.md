# Root to Leaf Paths / Binary Tree Paths

## Problem statement 

Given the root of a binary tree, return all root-to-leaf paths in any order. A leaf is a node with no children.

## Approach 1 : Recursion

Time complexity : O(N)
Space complexity : O(N)

```cpp
void solve(TreeNode *root, string psf, vector<string> &res){
    if(!root)return;
    
    psf += to_string(root -> val);
    
    if(!root -> left && !root -> right){  // Leaf node
        res.push_back(psf);
        return;
    }

    psf += "->"; 
    solve(root -> left, psf, res);
    solve(root -> right, psf, res);

}

vector<string> binaryTreePaths(TreeNode* root) {
    vector<string> res;
    
    solve(root, "", res);
    
    return res;
}
```