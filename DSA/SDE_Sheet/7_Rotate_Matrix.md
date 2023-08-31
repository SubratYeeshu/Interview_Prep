# Rotate Matrix

## Problem Statement

You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise). You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

## Approach 1 : Transpose then reverse row (Clockwise)

- Time complexity : O(N^2)  
- Space complexity : O(1)

```cpp
void rotate(vector<vector<int>>& matrix) {
    // Transpose
    for(int i = 0 ; i < matrix.size() ; i++){
        for(int j = 0 ; j < i ; j++){
            swap(matrix[i][j], matrix[j][i]);
        }
    }
    
    // Reverse each row
    for(int i = 0 ; i < matrix.size() ; i++){
        reverse(matrix[i].begin(), matrix[i].end());
    }  
}
```

## Approach 2 : Transpose the reverse coloumn (Anti - Clockwise)

- Time complexity : O(N^2)  
- Space complexity : O(1)

```cpp
void rotateMatrix(vector<vector<int>>& arr, int n) {
    // Transpose
    for(int i = 0 ; i < n ; i++){
        for(int j = 0 ; j < i ; j++){
            swap(arr[i][j], arr[j][i]);
        }
    } 
    // Reverse each coloumn
    for(int i = 0 ; i < n/2 ; i++){
        for(int j = 0 ; j < n ; j++){
            swap(arr[i][j], arr[n-i-1][j]);
        }
    }
}
```