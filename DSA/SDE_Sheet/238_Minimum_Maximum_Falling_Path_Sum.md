# Minimum / Maximum Falling Path Sum

## Variant 1 : Maximum Falling Path Sum 

## Problem statement

Given a NxN matrix of positive integers. There are only three possible moves from a cell Matrix[r][c].

- Matrix [r+1] [c]
- Matrix [r+1] [c-1]
- Matrix [r+1] [c+1]

Starting from any column in row 0 return the largest sum of any of the paths up to row N-1.

NOTE: We can start from any column in zeroth row and can end at any column in (N-1)th row.

## Approach 1 : DP

- Time Complexity : O(N^2) 
- Space Complexity : O(N^2 + Rec)

```cpp
int solve(int i, int j, int N, vector<vector<int>> &matrix, vector<vector<int>> &dp){
    if(i < 0 || i >= N || j < 0 || j >= N)return 0;
    
    if(dp[i][j] != -1)return dp[i][j];
    
    int down = solve(i + 1, j, N, matrix, dp);
    int right = solve(i + 1, j + 1, N, matrix, dp);
    int left = solve(i + 1, j - 1, N, matrix, dp);
    
    return dp[i][j] = matrix[i][j] + max({down, right, left});
}

int maximumPath(int N, vector<vector<int>> matrix){
    vector<vector<int>> dp(N, vector<int>(N, -1));
    int result = 0;
    
    for(int i = 0 ; i < N ; i++){
        result = max(result, solve(0, i, N, matrix, dp));
    }
    
    return result;
}
```

## Variant 2 : Minimum Falling Path Sum

## Problem statement

Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.

A falling path starts at any element in the first row and chooses the element in the next row that is either directly below or diagonally left/right. Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).

## Approach 1 : DP

- Time Complexity : O(N^2) 
- Space Complexity : O(N^2 + Rec)

```cpp

```