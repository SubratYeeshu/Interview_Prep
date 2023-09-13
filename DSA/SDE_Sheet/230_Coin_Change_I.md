# Coin Change - I

## Problem statement

Given a value V and array coins[] of size M, the task is to make the change for V cents, given that you have an infinite supply of each of coins{coins1, coins2, ..., coinsm} valued coins. F

Find the minimum number of coins to make the change. If not possible to make change then return -1.

## Note 
- Whenever asked to minimize or maximize something, in base case we have to either return 0, INT_MAX, INT_MIN based on question condition
- And at last return min/max from respective calls
- Whereas when we are asked to count ways we have to return 1 in case of valid answer that increases the count
- And at last return sum of counts from both the calls

## Greedy does not work here because there can be a better combination possible

## Approach 1.1 : DP + (Index From Start)

- Time Complexity : O(N^2) 
- Space Complexity : O(N^2 + Rec)

```cpp
int solve(int index, int coins[], int M, int V, vector<vector<int>> &dp){
    if(index >= M || V < 0)return 1e7;
    if(V == 0)return 0;
    
    if(dp[index][V] != -1)return dp[index][V];
    
    int pick = 1 + solve(index, coins, M, V - coins[index], dp);  
    int notPick = solve(index + 1, coins, M, V, dp);
    
    return dp[index][V] = min(pick, notPick);
}

int minCoins(int coins[], int M, int V){ 
    vector<vector<int>> dp(M + 1, vector<int> (V + 1, -1));
    return solve(0, coins, M, V, dp) == 1e7 ? -1 : solve(0, coins, M, V, dp);
}
```

## Aproach 1.2 : DP + (Index From End)

- Time Complexity : O(N^2) 
- Space Complexity : O(N^2 + Rec)

```cpp
int solve(int coins[], int M, int V, vector<vector<int>> &dp){
    if(M <= 0 || V < 0)return 1e7;
    
    if(V == 0)return 0;
    
    if(dp[M][V] != -1)return dp[M][V];
    
    int pick = 1 + solve(coins, M, V - coins[M - 1], dp);  
    int notPick = solve(coins, M - 1, V, dp);
    
    return dp[M][V] = min(pick, notPick);
}

int minCoins(int coins[], int M, int V){ 
    vector<vector<int>> dp(M + 1, vector<int> (V + 1, -1));
    return solve(coins, M, V, dp) == 1e7 ? -1 : solve(coins, M, V, dp);
}
```

## Approach 1.3 : DP

- Time Complexity : O(N^2) 
- Space Complexity : O(N^2 + Rec)

```cpp
int helper(int* coins,int i,int V,vector<vector<int>>& memo){
    if(i == 0){
        if(V % coins[0] == 0)
        return V / coins[0];
        else return 1e9;
    }
    
    if(V < 0)return 1e9;
    else if(V == 0)return 0;
    
    if(memo[i][V] != -1)return memo[i][V];
    
    int a, b;
    a = b = 1e9;
    
    a = helper(coins, i - 1, V, memo);
    b = 1 + helper(coins, i, V - coins[i], memo);
    
    return memo[i][V] = min(a, b);
}

int minCoins(int coins[], int M, int V){ 
    vector<vector<int>> memo(M + 1, vector<int>(V + 1, -1));
    int ans = helper(coins, M - 1, V, memo);
    if(ans >= 1e9)
    return -1;
    else return ans;
}
```

## Approach 1.4 : DP 

- Time Complexity : O(N^2) 
- Space Complexity : O(N^2 + Rec)

```cpp
int count(int coins[],int M,int sum,vector<int>&dp){
    if(sum == 0)return 0;
    if(sum < 0)return INT_MAX;

    if(dp[sum] != -1)return dp[sum];
    
    int mini = INT_MAX;
    for(int i = 0 ; i < M ; i++){
        int ans = count(coins, M, sum - coins[i], dp);
        if(ans != INT_MAX){
            mini = min(mini, 1 + ans);
        }
    }
    dp[sum] = mini;
    return dp[sum];
    
}
int minCoins(int coins[], int M, int V){ 
    vector<int>dp(V + 1, -1);
    int ans = count(coins, M, V, dp);
    if(ans == INT_MAX)return -1;
    else return ans;
} 
```

## Approach 2.1 : Tabulation 

- Time Complexity : O(N^2) 
- Space Complexity : O(N^2)

```cpp
int coinChange(vector<int>& coins, int V) {
    int M = coins.size();
    vector<vector<int>> dp(M + 1, vector<int>(V + 1, 0));

    // Initialize the table for V = 0
    for (int i = 0; i <= M; i++) {
        dp[i][0] = 0;
    }

    // Initialize the table for index = M
    for (int v = 1; v <= V; v++) {
        dp[M][v] = 1e7;
    }

    // Fill the table bottom-up
    for (int index = M - 1; index >= 0; index--) {
        for (int v = 1; v <= V; v++) {
            int pick = (v - coins[index] >= 0) ? 1 + dp[index][v - coins[index]] : 1e7;
            int notPick = dp[index + 1][v];
            dp[index][v] = min(pick, notPick);
        }
    }

    return dp[0][V] == 1e7 ? -1 : dp[0][V];
}
```

## Approach 2.2 : Tabulation 

- Time Complexity : O(N^2) 
- Space Complexity : O(N^2)

```cpp
int dp[10000 + 1][12 + 1];
int tabulation(vector<int> wt, int w, int n){
    for (int i = 0; i < w + 1; i++)
        for (int j = 0; j < n + 1; j++)
            if (i == 0 || j == 0)
                dp[i][j] = (i == 0) ? 0 : INT_MAX - 1;
    
    for (int i = 1; i < w + 1; i++) {
        for (int j = 1; j < n + 1; j++) {
            if (wt[j - 1] > i) 
                dp[i][j] = 0 + dp[i - 0][j - 1];
            else 
                dp[i][j] = min(0 + dp[i - 0][j - 1], 1 + dp[i - wt[j - 1]][j - 0]);
        }
    }
    
    return dp[w][n];
}

int coinChange(vector<int>& coins, int amount) {
    memset(dp, -1, sizeof(dp));
    int minCoins = tabulation(coins, amount, coins.size());
    return minCoins == INT_MAX - 1 ? -1 : minCoins;    
}
```

## Approach 2.3 : Tabulation

- Time Complexity : O(N^2) 
- Space Complexity : O(N^2)

```cpp
int minCoins(int coins[], int M, int V){ 
    vector<vector<int>> dp(M, vector<int>(V + 1, 1e9));
    for(int i = 0 ; i <= V ; i++){
        if(i % coins[0] == 0)
        dp[0][i] = i / coins[0];
    }
    
    for(int i = 1 ; i < M ; i++){
        for(int j = 0 ; j <= V ; j++){
            int a,b;
            a = b = 1e9;
            
            a = dp[i - 1][j];
            
            if(j - coins[i] >= 0)
            b = 1 + dp[i][j] - coins[i]];
            
            dp[i][j] = min(a, b);
        }
    }
    
    return dp[M - 1][V] == 1e9 ? -1 : dp[M - 1][V];
    
} 
```