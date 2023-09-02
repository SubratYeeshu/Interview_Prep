# Two Sum in BST (Two Sum - IV)

## Problem statement

Given the root of a binary search tree and an integer k, return true if there exist two elements in the BST such that their sum is equal to k, or false otherwise.

## Approach 1 : Storing the inorder

- Time complexity : O(N) 
- Space complexity : O(N)

```cpp
void solve(TreeNode *root, vector<int> &v){
    if(!root)return;
    
    solve(root -> left, v);
    v.push_back(root -> val);
    solve(root -> right, v);
}

bool findTarget(TreeNode* root, int k) {
    vector<int> v;
    solve(root, v);

    int i = 0, j = v.size() - 1;
    
    while(i < j){
        int sum = v[i] + v[j];
        
        if(sum == k)return true;
        
        if(sum < k)i++;
        else j--;
    }
    
    return false;
}
```

## Approach 2 : Using BST Iterator

- Time complexity : O(N) 
- Space complexity : O(2\*Height)

```cpp
class BSTIterator {
private: 
    stack<TreeNode*> st;
    bool flag;
public:
    void push(TreeNode *root, bool flag){
        if(flag)while(root)st.push(root), root = root -> left;
        else while(root)st.push(root), root = root -> right;
    }
    
    BSTIterator(TreeNode* root, bool isInc){
        this -> flag = isInc;
        push(root, flag);
    }
    
    int next() {
        TreeNode *temp = st.top();
        st.pop();
        if(flag)push(temp -> right, flag);
        else push(temp -> left, flag);
        return temp -> val;
    }
    
    bool hasNext() {
        return !st.empty();
    }
};

class Solution {
public:
    bool findTarget(TreeNode* root, int k) {
        bool flag = true;  // true -> inc, false -> dec
        BSTIterator *inc = new BSTIterator(root, flag);
        BSTIterator *dec = new BSTIterator(root, !flag);
        
        int i = inc -> next();  // Pointer on inc side
        int j = dec -> next();  // Pointer on dec side
        
        while(i < j){
            int sum = i + j;
            if(sum == k)return true;
            
            if(sum > k)i = inc -> next();
            else j = dec -> next();
        }
        
        return false;
    }
};
```