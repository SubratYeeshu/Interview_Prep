# Triangle Path Sum

## Problem statement

Given a triangle array, return the minimum path sum to reach from top to bottom.

For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.

## Approach 1

- Time Complexity : O(N^2) 
- Space Complexity : O(N^2 + Rec)

```cpp
int solve(int i, int j, vector<vector<int>>& triangle, vector<vector<int>> &dp){
    if(i == triangle.size() - 1)return triangle[i][j];
    
    if(dp[i][j] != -1)return dp[i][j];
    
    return dp[i][j] = triangle[i][j] + min(solve(i + 1, j, triangle, dp), solve(i + 1, j + 1, triangle, dp));
}

int minimizeSum(int n, vector<vector<int>>& triangle) {
    vector<vector<int>> dp (n + 1, vector<int> (n + 1, -1));
    return solve(0, 0, triangle, dp);
}
```

## Approach 2 : Tabulation

- Time Complexity : O(N^2) 
- Space Complexity : O(N^2)

```cpp
int minimizeSum(int n, vector<vector<int>>& triangle) {
    vector<vector<int> > dp(n, vector<int>(n, 0));

    for(int j = 0 ; j < n ; j++){
        dp[n - 1][j] = triangle[n - 1][j];
    }
    
    for(int i = n - 2 ; i >= 0 ; i--){
        for(int j = i ; j >= 0 ; j--){
            
            int down = triangle[i][j] + dp[i + 1][j];
            int diagonal = triangle[i][j] + dp[i + 1][j + 1];
            
            dp[i][j] = min(down, diagonal);
        }
    }
    
    return dp[0][0];
}
```