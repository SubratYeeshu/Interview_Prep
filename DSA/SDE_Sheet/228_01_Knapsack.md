# 0 - 1 Knapsack Problem

## Problem statement

You are given weights and values of N items, put these items in a knapsack of capacity W to get the maximum total value in the knapsack. Note that we have only one quantity of each item.

In other words, given two integer arrays val[0..N-1] and wt[0..N-1] which represent values and weights associated with N items respectively. Also given an integer W which represents knapsack capacity, find out the maximum value subset of val[] such that sum of the weights of this subset is smaller than or equal to W. You cannot break an item, either pick the complete item or dont pick it (0-1 property).

## Approach 1 : DP + (Index From Start)

Time Complexity : O(N^2) 
Space Complexity : O(N^2 + Rec)

```cpp
int solve(int index, int wt[], int val[], int W, int n, vector<vector<int>> &dp){
    if(index >= n)return 0;
    
    if(W <= 0)return 0;
    
    if(dp[index][W] != -1)return dp[index][W];

    if(W >= wt[index]){
        int pick = val[index] + solve(index + 1, wt, val, W - wt[index], n, dp);
        int notPick = solve(index + 1, wt, val, W, n, dp);
        return dp[index][W] = max(pick, notPick);
    }else return dp[index][W] = solve(index + 1, wt, val, W, n, dp);
    
}


int knapSack(int W, int wt[], int val[], int n){
    vector<vector<int>> dp (n + 1, vector<int> (W + 1, -1));
    return solve(0, wt, val, W, n, dp);
}
```

## Approach 2 : DP + (Index From End For Tabulation)

Time Complexity : O(N^2) 
Space Complexity : O(N^2 + Rec)

```cpp
int solve(int n, int W, int wt[], int val[], vector<vector<int>> &dp){
    if(n == 0 || W == 0)return 0;
    
    if(dp[n][W] != -1)return dp[n][W];
    
    if(wt[n - 1] <= W){
        return dp[n][W] = max(val[n - 1] + solve(n - 1, W - wt[n - 1], wt, val, dp), 
        solve(n - 1, W, wt, val, dp));
    }else{
        return dp[n][W] = solve(n - 1, W, wt, val, dp);
    }
}

int knapSack(int W, int wt[], int val[], int n){ 
    vector<vector<int>> dp (n + 1, vector<int> (W + 1, -1));
    return solve(n, W, wt, val, dp);
}
```

## Approach 3 : Tabulation (Base Case Conversion -> Write All Changing Params -> Copy Recurrence)

Time Complexity : O(N^2) 
Space Complexity : O(N^2)

```cpp
int knapSack(int W, int wt[], int val[], int n){ 
    // Base case adjusted
    vector<vector<int>> dp (n + 1, vector<int> (W + 1, 0));
    
    for(int i = 1 ; i <= n ; i++){
        for(int j = 1 ; j <= W ; j++){
            if(wt[i - 1] <= j){
                dp[i][j] = max(val[i - 1] + dp[i - 1][j - wt[i - 1]], dp[i - 1][j]);
            }
            else{
                dp[i][j] = dp[i - 1][j];
            }
        }
    }
    return dp[n][W];
}
```