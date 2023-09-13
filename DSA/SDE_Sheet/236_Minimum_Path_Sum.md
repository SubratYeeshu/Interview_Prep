# Minimum Path Sum

## Problem statement

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

## Approach 1.1 : DP + (Index From Start)

- Time Complexity : O(M \* N) 
- Space Complexity : O((M \* N) + Rec)

```cpp
int dp[201][201];
int solve(vector<vector<int>>& grid, int i, int j){
    if(i == grid.size() - 1 && j == grid[0].size() - 1){
        return grid[i][j];
    }
    if(i == grid.size() || j == grid[0].size()){
        return INT_MAX - 10000; //Overflow
    }
    if(dp[i][j] != -1)return dp[i][j];
    
    
    //2 directions either right [j + 1] or down [i + 1]
    int x = grid[i][j] + solve(grid, i, j + 1);
    int y = grid[i][j] + solve(grid, i + 1, j);
    
    return dp[i][j] = min(x, y);
    
}


int minPathSum(vector<vector<int>>& grid) {
    //Recurence will find out the result and we will store it in dp table for repeating problems
    memset(dp, -1, sizeof(dp));
    return solve(grid, 0, 0);   
    
}
```

## Approach 1.2 : DP + (Index From End)

- Time Complexity : O(M \* N) 
- Space Complexity : O((M \* N) + Rec)

```cpp
int solve(int x,int y, vector<vector<int>>& grid){
    if(x == 0 && y == 0) return grid[x][y];
    if(x < 0 || y < 0) return INT_MAX;
    int up  =  solve(x - 1, y, grid);
    int left =  solve(x, y - 1, grid);
    return grid[x][y] + min(up, left);
}

int minPathSum(vector<vector<int>>& grid) {
    int n = grid.size(), m = grid[0].size();
    return solve(n - 1, m - 1, grid);
}
```

## Approach 2 : Tabulation

- Time Complexity : O(M \* N) 
- Space Complexity : O(M \* N)

```cpp
int minPathSum(vector<vector<int>>& grid) {
    int row = grid.size(), col = grid[0].size();
    vector<vector<int>> dp(row, vector<int> (col, -1));

    for(int i = 0; i < row; i++) {
        for(int j = 0; j < col; j++) {
            if(i == 0 && j == 0) dp[i][j] = grid[i][j];
            else {
                int left = INT_MAX, up = INT_MAX;
                if(i > 0) up = min(up, dp[i - 1][j]);
                if(j > 0) left = min(left, dp[i][j - 1]);

                dp[i][j] = min(up, left) + grid[i][j];
            }
        }
    }
    return dp[row - 1][col - 1];
}
```