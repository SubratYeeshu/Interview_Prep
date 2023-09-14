# Unique Paths I

## Problem statement

There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to 2 * 109.

## Note : 
- In questions where we are only going down and down not return back in any case we can use DP but cases like 4 direction movement where we have to use backtracking sometime for avoiding infinite loop think of Dijkstra type algorithm because backtrack with DP can break the questions or the input.
- Or we can also say DP stores the answer of solved part, when we use backtracking it might gives wrong answer because the stored values are changed and the answer might differ

## Approach 1.1 : DP + (Index From Start)

- Time Complexity : O(M \* N) 
- Space Complexity : O((M \* N) + Rec)

```cpp
int solve(int i, int j, int m, int n, vector<vector<int>> &dp){
    if(i == m - 1 && j == n - 1)return 1;
    if(i >= m || j >= n)return 0;
    
    if(dp[i][j] != -1)return dp[i][j];
    
    int r = solve(i + 1, j, m, n, dp);
    int b = solve(i, j + 1, m, n, dp);
    
    return dp[i][j] = r + b;
}

int uniquePaths(int m, int n){
    vector<vector<int>> dp(m + 1, vector<int> (n + 1, -1));
    return solve(0, 0, m, n, dp);
}
```

## Approach 1.2 : DP + (Index From End)

- Time Complexity : O(M \* N) 
- Space Complexity : O((M \* N) + Rec)

```cpp
int solve(int m, int n, vector<vector<int>> &dp){
        if(m == 0 && n == 0)return 1;
        if(m < 0 || n < 0)return 0;
        
        if(dp[m][n] != -1)return dp[m][n];
        
        int r = solve(m - 1, n, dp);
        int b = solve(m, n - 1, dp);
        
        return dp[m][n] = r + b;
    }
    
    int uniquePaths(int m, int n){
        vector<vector<int>> dp(m + 1, vector<int> (n + 1, -1));
        return solve(m - 1, n - 1, dp);
    }
```

## Approach 1.3 : DP (Generalized)

- Time Complexity : O(M \* N) 
- Space Complexity : O((M \* N) + Rec)  

```cpp
int drx[2] = {1, 0};
int dry[2] = {0, 1};

bool isValid(int i, int j, int m, int n){
    return i >= 0 && j >= 0 && i < m && j < n;
}

int solve(int i, int j, int m, int n, vector<vector<int>>& dp){
    if(i == m - 1 && j == n - 1)return 1;
    if(dp[i][j] != -1)return dp[i][j];
    
    int res = 0;
    for(int k = 0 ; k < 2 ; k++){
        int newX = i + drx[k];
        int newY = j + dry[k];
        if(isValid(newX, newY, m, n)){
            res += solve(newX, newY, m, n, dp);
        }
    }
    
    return dp[i][j] = res;
}

int uniquePaths(int m, int n) {
    vector<vector <int>> dp (m, vector<int> (n, -1));
    return solve(0, 0, m, n, dp);
}
```

## Approach 2 : Tabulation 

- Time Complexity : O(M \* N) 
- Space Complexity : O(M \* N)  

```cpp
int uniquePaths(int m, int n) {
    vector<vector<int>> dp (m, vector<int> (n, 0));
    for(int i = 0 ; i < m ; i++){
        for(int j = 0 ; j < n ; j++){
            if(i == 0 || j == 0)dp[i][j] = 1;
            else dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        }
    }
    return dp[m - 1][n - 1];
}
```