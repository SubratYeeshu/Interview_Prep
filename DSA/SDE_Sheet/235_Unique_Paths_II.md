# Unique Paths II

## Problem statement

You are given an m x n integer array grid. There is a robot initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot include any square that is an obstacle.

Return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The testcases are generated so that the answer will be less than or equal to 2 * 109.

## Approach 1 : DP + (Index From Start)

- Time Complexity : O(M \* N) 
- Space Complexity : O((M \* N) + Rec)

```cpp
int n, m;
int dfs(vector<vector<int>>&grid, int i, int j, vector<vector<int>> &dp){
    if(i >= n || j >= m || grid[i][j])return 0;
    if(i == n - 1 && j == m - 1)return 1;
    
    if(dp[i][j] != -1)return dp[i][j];
    
    return dp[i][j] = dfs(grid, i + 1, j, dp) + dfs(grid, i, j + 1, dp);
}


int uniquePathsWithObstacles(vector<vector<int>>& obstacleGrid) {
    n = obstacleGrid.size(), m = obstacleGrid[0].size();
    vector<vector<int>> dp (n + 1, vector<int> (m + 1, -1));
    return dfs(obstacleGrid, 0, 0, dp);
}
```

## Approach 2 : Tabulation

- Time Complexity : O(M \* N) 
- Space Complexity : O(M \* N)

```cpp
int uniquePathsWithObstacles(vector<vector<int>>& grid) {
    int n = grid.size(), m = grid[0].size();
    if(grid[0][0] == 1 || grid[n - 1][m - 1] == 1)return 0;
    vector<vector<int>> dp(n, vector<int>(m, -1)) ;
    for(int i = 0 ; i < n ; i++){
        for(int j = 0 ; j < m ; j++){
            if(i == 0 && j == 0)dp[i][j] = 1;
            else if(grid[i][j] != 1){
                int up = 0, left = 0;
                if(i > 0)up = dp[i - 1][j];
                if(j > 0)left = dp[i][j - 1];
                dp[i][j] = up + left;   
            }
            else dp[i][j] = 0;
        }
    }
    return dp[n - 1][m - 1];
}
```