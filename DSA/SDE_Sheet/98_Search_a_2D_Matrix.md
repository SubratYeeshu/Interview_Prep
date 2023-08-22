# Search a 2D Matrix

## Problem statement

- You are given an m x n integer matrix matrix with the following two properties:
Each row is sorted in non-decreasing order. The first integer of each row is greater than the last integer of the previous row. 
- Given an integer target, return true if target is in matrix or false otherwise. You must write a solution in O(log(m * n)) time complexity.

## Approach 1 : Linear Search 

Time complexity : O(N \* M) 
Space complexity : O(1)

```cpp
bool searchMatrix(vector<vector<int>>& matrix, int target) {
    int n = matrix.size(), m = matrix[0].size();
    for(int i = 0 ; i < n ; i++){
        for(int j = 0 ; j < m ; j++){
            if(matrix[i][j] == target)return true;
        }
    }
    return false;
}
```

## Approach 2 : Condtitional traversal

Time complexity : O(N + M) 
Space complexity : O(1)

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

## Approach 3 : Binary Search 

Time complexity : O(Log(N\*M)) 
Space complexity : O(1)

```cpp
bool searchMatrix(vector<vector<int>>& matrix, int target) {
    int n = matrix.size(), m = matrix[0].size();
    
    int low = 0, high = n * m - 1;
    
    while(low <= high){
        int mid = (low + high) / 2;
        
        int row = mid / m, col = mid % m;
        
        if(matrix[row][col] == target)return true;
        else if(matrix[row][col] > target)high = mid - 1;
        else low = mid + 1;
    }
    
    return false;
}
```