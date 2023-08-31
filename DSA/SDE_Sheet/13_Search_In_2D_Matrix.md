# Search in 2D matrix

## Problem statement
You are given an m x n integer matrix matrix with the following two properties:
- Each row is sorted in non-decreasing order.
- The first integer of each row is greater than the last integer of the previous row.

Given an integer target, return true if target is in matrix or false otherwise.
You must write a solution in O(log(m * n)) time complexity.


## Approach 1 : Using Binary Search

- Time complexity :  O(N + logM)
- Space complexity : O(1)

```cpp
bool binary_search(vector<int> temp, int target){
    int low = 0, high = temp.size() - 1;
    
    while(low <= high){
        int mid = (low + high) / 2;
        if(temp[mid] == target)return true;
        else if(temp[mid] < target)low = mid + 1;
        else high = mid - 1;
    }
    
    return false;
}

bool searchMatrix(vector<vector<int>>& matrix, int target) {
    int n = matrix.size(), m = matrix[0].size(), row = -1;
    bool res = false;
    
    for(int i = 0 ; i < n ; i++)
        if(target <= matrix[i][m - 1] && 
            target >= matrix[i][0])row = i;
    
    if(row != -1){
        vector<int> temp = matrix[row];
        res = binary_search(temp, target);
    }
    
    return res;
}
```

## Approach 2 : Using simple traversal

- Time complexity : O(N)  
- Space complexity : O(1)

```cpp
bool searchMatrix(vector<vector<int>>& matrix, int target) {
    int n = matrix.size(), m = matrix[0].size();
    int i = 0, j = m - 1;
    while(i < n && j >= 0){
        if(matrix[i][j] == target)return true;
        else if(matrix[i][j] < target)i++;
        else j--;
    }
    return false;
}
```