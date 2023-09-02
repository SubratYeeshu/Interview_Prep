## Maximum Sum BST / Largest BST

## Variant 1 : Maximum Sum BST

## Problem statement

Given a binary tree root, return the maximum sum of all keys of any sub-tree which is also a Binary Search Tree (BST).

Assume a BST is defined as follows:

- The left subtree of a node contains only nodes with keys less than the node's key.
- The right subtree of a node contains only nodes with keys greater than the node's key.
- Both the left and right subtrees must also be binary search trees.

## Approach 1 : Brute

- Time complexity : O(N^2)
- Space complexity : O(LogN), h is logn, for skewed tree (n)

```cpp
/*

    -> Go on every node
    -> Check whether it is a valid BST or not
    -> If its a valid BST, calculate its sum and maximize it

*/
bool solve(TreeNode *root, long lowerBound, long upperBound){
    if(!root)return true;

    if(root -> val >= upperBound || root -> val <= lowerBound)return false;

    bool fromLeft = solve(root -> left, lowerBound, root -> val);
    bool fromRight = solve(root -> right, root -> val, upperBound);

    return fromLeft && fromRight;
}
bool isValidBST(TreeNode* root) {
    return solve(root, LONG_MIN, LONG_MAX);
}

int calculateSum(TreeNode *root){
    if(!root)return 0;
    
    return root -> val + calculateSum(root -> left) + calculateSum(root -> right);
}


void help(TreeNode *root, int &maxSum){
    if(!root)return;
    
    help(root -> left, maxSum);
    if(isValidBST(root))maxSum = max(maxSum, calculateSum(root));
    help(root -> right, maxSum);
}


int maxSumBST(TreeNode* root) {
    int maxSum = INT_MIN;
    help(root, maxSum);
    return max(0, maxSum);
}
```

## Approach 2 : Optimal (returnInfo)

## Note : We can solve any question of BT and BST using this returnInfo concept

- Time complexity : O(N)
- Space complexity : O(LogN)

```cpp
class Solution {
public:
    class BSTInfo{
        public:
        int subtreeSum;
        int mini;
        int maxi;
        bool isBST;
    };
    
    int maxisum = INT_MIN;
    BSTInfo* solve(TreeNode *root){
        if(!root){
            BSTInfo *temp = new BSTInfo();
            temp -> subtreeSum = 0;
            temp -> maxi = INT_MIN;
            temp -> mini = INT_MAX;
            temp -> isBST = true;
            return temp;
        }
        
        BSTInfo *leftInfo = solve(root -> left);
        BSTInfo *rightInfo = solve(root -> right);
        
        int currNodeData = root -> val;
        BSTInfo* toReturnInfo = new BSTInfo();
        toReturnInfo -> isBST = leftInfo -> isBST && rightInfo -> isBST 
            && currNodeData > leftInfo -> maxi && currNodeData < rightInfo -> mini;
        
        if(toReturnInfo -> isBST){
            toReturnInfo -> subtreeSum = leftInfo -> subtreeSum + rightInfo -> subtreeSum + root -> val;
            maxisum = max(maxisum, toReturnInfo -> subtreeSum);
        }else{
            toReturnInfo -> subtreeSum = 0;
        }
        
        toReturnInfo -> maxi = max({leftInfo -> maxi, rightInfo -> maxi, currNodeData});
        toReturnInfo -> mini = min({leftInfo -> mini, rightInfo -> mini, currNodeData});
        
        return toReturnInfo;
    }
  
    
    int maxSumBST(TreeNode* root) {
        BSTInfo *myNode = solve(root);
        return max(0, maxisum);
    }
};
```

## Variant 2 : Largest BST

## Problem statement

Given a binary tree. Find the size of its largest subtree that is a Binary Search Tree.

## Approach 1 : Optimal (returnInfo)

- Time complexity : O(N)
- Space complexity : O(LogN)

```cpp
class Solution{
    public:
    class BSTInfo{
        public:
        int subtreeSum;
        int mini;
        int maxi;
        int countOfNode;
        bool isBST;
    };
    
    int maxisum = INT_MIN;
    int maxinodes = INT_MIN;
    BSTInfo* solve(Node *root){
        if(!root){
            BSTInfo *temp = new BSTInfo();
            temp -> subtreeSum = 0;
            temp -> maxi = INT_MIN;
            temp -> mini = INT_MAX;
            temp -> isBST = true;
            temp -> countOfNode = 0;
            return temp;
        }
        
        BSTInfo *leftInfo = solve(root -> left);
        BSTInfo *rightInfo = solve(root -> right);
        
        int currNodeData = root -> data;
        BSTInfo* toReturnInfo = new BSTInfo();
        toReturnInfo -> isBST = leftInfo -> isBST && rightInfo -> isBST 
            && currNodeData > leftInfo -> maxi && currNodeData < rightInfo -> mini;
        
        
        if(toReturnInfo -> isBST){
            toReturnInfo -> subtreeSum = leftInfo -> subtreeSum + rightInfo -> subtreeSum + root -> data;
            toReturnInfo -> countOfNode = leftInfo -> countOfNode + rightInfo -> countOfNode + 1;
            maxisum = max(maxisum, toReturnInfo -> subtreeSum);
            maxinodes = max(maxinodes, toReturnInfo -> countOfNode);
        }else{
            toReturnInfo -> subtreeSum = 0;
            toReturnInfo -> countOfNode = 0;
        }
        
        toReturnInfo -> maxi = max({leftInfo -> maxi, rightInfo -> maxi, currNodeData});
        toReturnInfo -> mini = min({leftInfo -> mini, rightInfo -> mini, currNodeData});
        
        return toReturnInfo;
    }
    
    int largestBst(Node *root){
    	BSTInfo *myNode = solve(root);
        // return max(0, maxisum);
        return max(0, maxinodes);
    }
};
```