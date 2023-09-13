# Unbounded Knapsack

## Problem statement

Given a set of N items, each with a weight and a value, represented by the array w[] and val[] respectively. Also, a knapsack with weight limit W.
The task is to fill the knapsack in such a way that we can get the maximum profit. 

Return the maximum profit.

Note: Each item can be taken any number of times.

## Approach 1 : DP

- Time Complexity : O(N^2) 
- Space Complexity : O(N^2 + Rec)

```cpp
int solve(int index, int wt[], int val[], int W, int n, vector<vector<int>> &dp){
    if(index >= n)return 0;
    
    if(W <= 0)return 0;
    
    if(dp[index][W] != -1)return dp[index][W];

    if(W >= wt[index]){
        int pick = val[index] + solve(index, wt, val, W - wt[index], n, dp); // Stand still on same index after picking
        int notPick = solve(index + 1, wt, val, W, n, dp);
        return dp[index][W] = max(pick, notPick);
    }else return dp[index][W] = solve(index + 1, wt, val, W, n, dp);
    
}

int knapSack(int n, int W, int val[], int wt[]){
    vector<vector<int>> dp (n + 1, vector<int> (W + 1, -1));
    return solve(0, wt, val, W, n, dp);
}
```

## Approach 2 :  DP + (Index From Start)

- Time Complexity : O(N^2) 
- Space Complexity : O(N^2 + Rec)

```cpp
int solve(int n, int W, int wt[], int val[], vector<vector<int>> &dp){
    if(n == 0 || W == 0)return 0;
    
    if(dp[n][W] != -1)return dp[n][W];
    
    if(wt[n - 1] <= W){
        return dp[n][W] = max(val[n - 1] + solve(n, W - wt[n - 1], wt, val, dp), 
        solve(n - 1, W, wt, val, dp));
    }else{
        return dp[n][W] = solve(n - 1, W, wt, val, dp);
    }
}

int knapSack(int n, int W, int val[], int wt[]){
    vector<vector<int>> dp (n + 1, vector<int> (W + 1, -1));
    return solve(n, W, wt, val, dp);
}
```

## Approach 3 : Tabulation

- Time Complexity : O(N^2) 
- Space Complexity : O(N^2)

```cpp
int knapSack(int n, int W, int val[], int wt[]){
    vector<vector<int>> dp (n + 1, vector<int> (W + 1, 0));
    
    for(int i = 1 ; i <= n ; i++){
        for(int j = 1 ; j <= W ; j++){
            if(wt[i - 1] <= j){
                dp[i][j] = max(val[i - 1] + dp[i][j - wt[i - 1]], dp[i - 1][j]);
            }
            else{
                dp[i][j] = dp[i - 1][j];
            }
        }
    }
    return dp[n][W];
}
```