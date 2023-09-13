## Coin Change II

## Problem statement

You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.

You may assume that you have an infinite number of each kind of coin.

The answer is guaranteed to fit into a signed 32-bit integer.

## Note 
- Whenever asked to minimize or maximize something, in base case we have to either return 0, INT_MAX, INT_MIN based on question condition
- And at last return min/max from respective calls
- Whereas when we are asked to count ways we have to return 1 in case of valid answer that increases the count
- And at last return sum of counts from both the calls

## Greedy does not work here because there can be a better combination possible

## Approach 1.1 : DP

- Time Complexity : O(N^2) 
- Space Complexity : O(N^2 + Rec)

```cpp
int solve(int index, int V, vector<int> &coins, vector<vector<int>> &dp){
    if(index >= coins.size())return 0;
    if(V < 0)return 0;
    if(V == 0)return 1;
    
    if(dp[index][V] != -1)return dp[index][V];
    
    int pick = solve(index, V - coins[index], coins, dp);
    int notPick = solve(index + 1, V, coins, dp);
    
    return dp[index][V] = pick + notPick;        
}

int change(int amount, vector<int>& coins) {
    int n = coins.size();
    vector<vector<int>> dp (n + 1, vector<int> (amount + 1, -1));
    sort(coins.begin(), coins.end(), greater<int> ());
    return solve(0, amount, coins, dp) == 1e7 ? -1 : solve(0, amount, coins, dp);
}
```

## Approach 1.2 : DP

- Time Complexity : O(N^2) 
- Space Complexity : O(N^2 + Rec)

```cpp
// Unbounded Knapsack
int dfs(int ind, int target, vector<int>& coins, vector<vector<int>>& dp) {
    if (ind == 0) {
        if (target % coins[0] == 0) return 1;
        return 0;
    }
    if (dp[ind][target] != -1) return dp[ind][target];
    int notTake = dfs(ind-1, target, coins, dp);
    int take = 0;
    if (target >= coins[ind]) take = dfs(ind, target-coins[ind], coins, dp);
    return dp[ind][target] = notTake + take;
}
int change(int amount, vector<int>& coins) {
    int n = coins.size();
    vector<vector<int>> dp(n, vector<int>(amount+1, -1));
    return dfs(n-1, amount, coins, dp);
}
```

## Approach 2 : Tabulation

- Time Complexity : O(N^2) 
- Space Complexity : O(N^2)

```cpp
int change(int amount, vector<int>& coins) {
    int n = coins.size();
    vector<vector<int>> dp(n, vector<int>(amount + 1, 0));
    for (int t = 0 ; t <= amount ; t++) {
        if (t % coins[0] == 0) dp[0][t] = 1;
    }
    for (int ind = 1 ; ind < n ; ind++) {
        for (int target = 0 ; target <= amount ; target++) {
            int notTake = dp[ind - 1][target];
            int take = 0;
            if (target >= coins[ind]) take = dp[ind][target - coins[ind]];
            dp[ind][target] = notTake + take;
        }
    }
    return dp[n - 1][amount];
}
```