# Cherry Pickup - I

## Problem statement

You are given an n x n grid representing a field of cherries, each cell is one of three possible integers.

- 0 means the cell is empty, so you can pass through,
- 1 means the cell contains a cherry that you can pick up and pass through, or
- -1 means the cell contains a thorn that blocks your way.

Return the maximum number of cherries you can collect by following the rules below:

- Starting at the position (0, 0) and reaching (n - 1, n - 1) by moving right or down through valid path cells (cells with value 0 or 1).
- After reaching (n - 1, n - 1), returning to (0, 0) by moving left or up through valid path cells.
- When passing through a path cell containing a cherry, you pick it up, and the cell becomes an empty cell 0.
- If there is no valid path between (0, 0) and (n - 1, n - 1), then no cherries can be collected.

## Note : 
- In questions where we are only going down and down not return back in any case we can use DP but cases like 4 direction movement where we have to use backtracking sometime for avoiding infinite loop think of Dijkstra type algorithm because backtrack with DP can break the questions or the input.
- Or we can also say DP stores the answer of solved part, when we use backtracking it might gives wrong answer because the stored values are changed and the answer might differ

## Brute : 
- Explore all paths to top when reached at bottom right from all paths, path with max cherry is answer

## Approach 1 : Backtracking

- Time Complexity : O(Exponential) 
- Space Complexity : O(Rec)

```cpp
/*

    -> Collecting max from calls will not work maybe there exists different better paths 
    -> So we explore every possiblity from top to bottom then from bottom to top

*/
int maxCherries = 0;
void bottomToTop(int i, int j, int cherryBottomToTop, vector<vector<int>>& grid){
    if(i < 0 || j < 0 || grid[i][j] == -1)return;
    if(i == 0 && j == 0){
        maxCherries = max(maxCherries, cherryBottomToTop);
        return;
    }
    
    int cherries = grid[i][j];
    grid[i][j] = 0;
    
    bottomToTop(i, j - 1, cherryBottomToTop + cherries, grid);
    bottomToTop(i - 1, j, cherryBottomToTop + cherries, grid);
    
    grid[i][j] = cherries;
}

void topToBottom(int i, int j, int cherryTopToBottom, vector<vector<int>>& grid){
    if(i >= grid.size() || j >= grid[0].size() || grid[i][j] == -1)return;
    if(i == grid.size() - 1 && j == grid[0].size() - 1){
        bottomToTop(i, j, cherryTopToBottom, grid);
    }
    
    int cherries = grid[i][j];
    grid[i][j] = 0;
    
    topToBottom(i, j + 1, cherryTopToBottom + cherries, grid);
    topToBottom(i + 1, j, cherryTopToBottom + cherries, grid);
    
    grid[i][j] = cherries;
}

int cherryPickup(vector<vector<int>>& grid) {
    if(grid.size() == 1 && grid[0][0] == 1)return 1;
    int cherryTopToBottom = 0;
    topToBottom(0, 0, cherryTopToBottom, grid);
    return maxCherries;
}
```

## Approach 2.1 : DP

- Time Complexity : O(N^4) 
- Space Complexity : O(N^4 + Rec)

```cpp
/*

    -> Instead of backtracking we can send two sided dfs calls
    -> And collect maximum cherries 
    -> If both the bots stand on same cell add once else both cheries

*/
int solve(int r1, int c1, int r2, int c2, vector<vector<int>> &grid, vector<vector<vector<vector<int>>>> &dp){
    if(r1 >= grid.size() || r2 >= grid.size() ||  
        c1 >= grid[0].size() || c2 >= grid[0].size() ||
            grid[r1][c1] == -1 || grid[r2][c2] == -1)return INT_MIN;
        
    if(r1 == grid.size() - 1 && c1 == grid[0].size() - 1 
        && r2 == grid.size() - 1 && c2 == grid[0].size() - 1)return grid[r1][c1];           
    
    if(dp[r1][c1][r2][c2] != -1)return dp[r1][c1][r2][c2];
    
    int cherries = 0;
    if(r1 == r2 && c1 == c2)cherries += grid[r1][c1];
    else cherries += grid[r1][c1] + grid[r2][c2];
    
    // Direction of both the bots at any instance
    int f1 = solve(r1, c1 + 1, r2, c2 + 1, grid, dp); // hh
    int f2 = solve(r1, c1 + 1, r2 + 1, c2, grid, dp); // hv
    int f3 = solve(r1 + 1, c1, r2 + 1, c2, grid, dp); // vv
    int f4 = solve(r1 + 1, c1, r2, c2 + 1, grid, dp); // vh  
        
    cherries += max({f1, f2, f3, f4});
    return dp[r1][c1][r2][c2] = cherries;
    
}

int cherryPickup(vector<vector<int>>& grid){
    int n = grid.size(), m = grid[0].size();
    vector<vector<vector<vector<int>>>> dp(n + 1,
        vector<vector<vector<int>>> (m + 1, 
                vector<vector<int>> (n + 1, 
                        vector<int> (m + 1, -1))));
    int ans = solve(0, 0, 0, 0, grid, dp);
    return ans < 0 ? 0 : ans;
}
```

## Approach 2.2 : DP (Elminating variable)

- Time Complexity : O(N^4) 
- Space Complexity : O(N^3 + Rec)

```cpp
// r1 + c1 = r2 + c2
int solve(int r1, int c1, int r2, vector<vector<int>> &grid, vector<vector<vector<int>>> &dp){
    int c2 = r1 + c1 - r2;
    if(r1 >= grid.size() || r2 >= grid.size() ||  
        c1 >= grid[0].size() || c2 >= grid[0].size() ||
            grid[r1][c1] == -1 || grid[r2][c2] == -1)return INT_MIN;
        
    if(r1 == grid.size() - 1 && c1 == grid[0].size() - 1 
        && r2 == grid.size() - 1 && c2 == grid[0].size() - 1)return grid[r1][c1];           
    
    if(dp[r1][c1][r2] != -1)return dp[r1][c1][r2];
    
    int cherries = 0;
    if(r1 == r2 && c1 == c2)cherries += grid[r1][c1];
    else cherries += grid[r1][c1] + grid[r2][c2];
    
    // Direction of both the bots at any instance
    int f1 = solve(r1, c1 + 1, r2, grid, dp); // hh
    int f2 = solve(r1, c1 + 1, r2 + 1, grid, dp); // hv
    int f3 = solve(r1 + 1, c1, r2 + 1, grid, dp); // vv
    int f4 = solve(r1 + 1, c1, r2, grid, dp); // vh  
        
    cherries += max({f1, f2, f3, f4});
    return dp[r1][c1][r2] = cherries;
    
}

int cherryPickup(vector<vector<int>>& grid){
    int n = grid.size(), m = grid[0].size();
    vector<vector<vector<int>>> dp (n + 1, vector<vector<int>> (m + 1, vector<int> (n + 1, -1)));
    int ans = solve(0, 0, 0, grid, dp);
    return ans < 0 ? 0 : ans;
}
```