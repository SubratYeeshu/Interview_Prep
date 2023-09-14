# Cherry Pickup II

## Problem statement

You are given a rows x cols matrix grid representing a field of cherries where grid[i][j] represents the number of cherries that you can collect from the (i, j) cell.

You have two robots that can collect cherries for you:

- Robot #1 is located at the top-left corner (0, 0), and
- Robot #2 is located at the top-right corner (0, cols - 1).

Return the maximum number of cherries collection using both robots by following the rules below:

- From a cell (i, j), robots can move to cell (i + 1, j - 1), (i + 1, j), or (i + 1, j + 1).
- When any robot passes through a cell, It picks up all cherries, and the cell becomes - empty cell.
- When both robots stay in the same cell, only one takes the cherries.
- Both robots cannot move outside of the grid at any moment.
- Both robots should reach the bottom row in grid.

## Approach 1.1 : DP

- Time Complexity : O(N\*M\*M\*9) 
- Space Complexity : O(N\*M\*M + Rec)

```cpp
int dy[3] = {0, -1, 1};
int dp[71][71][71];
int dfs(vector<vector<int>>& grid, int i, int c1, int c2, int n, int m) {

    if (i == n) return 0;
    if (c1 < 0 || c2 < 0 || c1 >= m || c2 >= m) return INT_MIN;
    if (dp[i][c1][c2] != -1) return dp[i][c1][c2];
    
    int maxi = 0;
    // Total 9 combination of movements
    for (int r1 = 0 ; r1 < 3 ; r1++) {
        for (int r2 = 0 ; r2 < 3; r2++) {
            maxi = max(maxi, dfs(grid, i + 1, c1 + dy[r1], c2 + dy[r2], n, m));
        }
    }
    
    // If both at same position only pick once else take from both places
    maxi += (c1 == c2) ? grid[i][c1] : grid[i][c1] + grid[i][c2];
    return dp[i][c1][c2] = maxi;
}


int cherryPickup(vector<vector<int>>& grid) {
    memset(dp, -1, sizeof(dp));
    int n = grid.size(), m = grid[0].size();
    if(!n)return 0;
    return dfs(grid, 0, 0, m - 1, n, m);
}
```

## Approach 1.2 : DP

- Time Complexity : O(N\*M\*M\*9) 
- Space Complexity : O(N\*M\*M + Rec)

```cpp
int solve(int i, int j1, int j2, int r, int c, vector<vector<int>>& grid, vector<vector<vector<int>>>& dp){
    if(j1 < 0 || j2 < 0 || j1 >= c || j2 >= c)return -1e8;
    if(i == r - 1){
        if(j1 == j2)return grid[i][j1];
        else return grid[i][j1] + grid[i][j2];
    }
    
    if(dp[i][j1][j2] != -1) return dp[i][j1][j2];
    
    int maxi = -1e8;
    for(int dj1 = -1 ; dj1 <= 1 ; dj1++){
        for(int dj2 = -1 ; dj2 <= 1 ; dj2++){
            int value = 0;
            if(j1 == j2)value = grid[i][j1];
            else value = grid[i][j1] + grid[i][j2];
            value += solve(i + 1, j1 + dj1, j2 + dj2, r, c, grid, dp);
            maxi = max(maxi, value);
        }
    }
    return dp[i][j1][j2] = maxi;
}

int cherryPickup(vector<vector<int>>& grid) {
    int r = grid.size(), c = grid[0].size();
    vector<vector<vector<int>>> dp(r, vector<vector<int>>(c, vector<int>(c, -1)));
    return solve(0, 0, c - 1, r, c, grid, dp);
}
```

## Approach 2 : Tabulation

- Time Complexity : O(N\*M\*M\*9) 
- Space Complexity : O(N\*M\*M)

```cpp
int cherryPickup(vector<vector<int>>& grid){
    int n = grid.size(), m = grid[0].size();
    vector<vector<vector<int>>> dp(n, vector<vector<int>>(m, vector<int>(m, 0)));

    for(int j1 = 0; j1 < m ; j1++){
        for(int j2 = 0; j2 < m ; j2++){
            if(j1 == j2)dp[n - 1][j1][j1] = grid[n - 1][j1];
            else dp[n - 1][j1][j2] = grid[n - 1][j1] + grid[n - 1][j2];
        }
    }

    for(int i = n - 2 ; i >= 0 ; i--){
        for(int j1 = 0; j1 < m ; j1++){
            for(int j2 = 0; j2 < m ; j2++){
                int maxi = INT_MIN;
                for(int k1 = -1 ; k1 <= 1 ; k1++){
                    int sum = 0;
                    for(int k2 = -1; k2 <= 1 ; k2++){
                        if(j1 == j2)sum = grid[i][j1];
                        else sum = grid[i][j1] + grid[i][j2];
                        
                        if(j1 + k1 < 0 || j1 + k1 >= m || j2 + k2 < 0 || j2 + k2 >= m)sum += -1e9;
                        else sum += dp[i+1][j1+k1][j2+k2];
                        
                        maxi = max(maxi, sum);
                    }
                }
                dp[i][j1][j2] = maxi;  
            }
        }
    }
    return dp[0][0][m - 1];
}
```