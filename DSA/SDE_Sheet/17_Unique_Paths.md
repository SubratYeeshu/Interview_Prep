## Unique Paths

## Problem Statement

- There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time. Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner. The test cases are generated so that the answer will be less than or equal to 2 * 109.


## Approach 1.1 : Brute 

Time complexity : O(2<sup>m\*n</sup>) 
Space complexity : O(1)

```cpp
int solve(int i, int j, int m, int n){
    if(i == m - 1 && j == n - 1)return 1;
    if(i < 0 || j < 0 || i >= m || j >= n)return 0;
        
    int down = solve(i + 1, j, m, n);
    int right = solve(i, j + 1, m, n);
    
    return down + right;
}

int uniquePaths(int m, int n) {
    return solve(0, 0, m, n);
}
```

## Approach 1.2 : Brute 

Time complexity : O(2<sup>m\*n</sup>) 
Space complexity : O(1)

```cpp
int drx[2] = {1, 0};
int dry[2] = {0, 1};

bool isValid(int i, int j, int m, int n){
    return i >= 0 && j >= 0 && i < m && j < n;
}

int solve(int i, int j, int m, int n){
    if(i == m - 1 && j == n - 1)return 1;
    
    int res = 0;
    for(int k = 0 ; k < 2 ; k++){
        int newX = i + drx[k];
        int newY = j + dry[k];
        if(isValid(newX, newY, m, n)){
            res += solve(newX, newY, m, n);
        }
    }
    
    return res;
}

int uniquePaths(int m, int n) {
    return solve(0, 0, m, n);
}
```

## Approach 2 : Memoization 

Time complexity : O(m \* n) 
Space complexity : O((n - 1) + (m - 1) + m\*n)

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

## Approach 3.1 : Tabulation (Building from observation)

Time complexity : O(m \* n) 
Space complexity : O(m\*n)

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

## Approach 3.2 : Tabulation (Building from recursion logic)

Time complexity : O(m \* n) 
Space complexity : O(m\*n)

```cpp
// Recursion Logic
{
    if(i==0 && j==0) return 1;
    if(i<0 || j<0) return 0;
    if(dp[i][j]!=-1) return dp[i][j];
    int up=f(i-1, j, dp);
    int down=f(i, j-1, dp);
    return dp[i][j]=up+down;
}
    
int uniquePaths(int m, int n) {
    int dp[m][n];
    for(int i = 0 ; i < m ; i++){
        for(int j = 0 ; j < n ; j++){
            if(i == 0 && j == 0)dp[i][j] = 1;
            else{
                int up = 0, left = 0;
                if(i>0)up = dp[i - 1][j];
                if(j>0)left = dp[i][j - 1];
                dp[i][j] = up + left;
            }
        }
    }
    return dp[m - 1][n - 1];
}
```