# Best Time To Buy And Sell Stock

## Problem Statement
- You are given an array prices where prices[i] is the price of a given stock on the ith day. You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock. Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0

## Approach 1 : 

- Time complexity : O(N)  
- Space complexity : O(1)

```cpp
int maxProfit(vector<int>& prices) {
    int curr_min = INT_MAX, maxi = INT_MIN;
    for(int i = 0 ; i < prices.size() ; i++){
        curr_min = min(curr_min, prices[i]);
        maxi = max(maxi, prices[i] - curr_min);
    }
    return maxi;
}
```

## Approach 2 : Using DP

- Time complexity : O(N)  
- Space complexity : O(1)

```cpp
int dp[100001][2];
int helper(vector<int> &prices, int i, int canBuy, int maxTrans){
    if(i >= prices.size() || maxTrans <= 0)return 0;
    if(dp[i][canBuy]!=-1) return dp[i][canBuy];
    
    if(canBuy){
        int buy = -prices[i] + helper(prices, i + 1, 0, 1);
        int notBuy = 0 + helper(prices, i + 1, 1, 1);
        return dp[i][canBuy] = max(buy, notBuy);
    }else{
        int sell = +prices[i] + helper(prices, i + 1, 1, 0);
        int notSell = 0 + helper(prices, i + 1, 0, 1);
        return dp[i][canBuy] = max(sell, notSell);
    }
}
int maxProfit(vector<int>& prices) {
    memset(dp, -1, sizeof(dp));
    int n = prices.size();
    return helper(prices, 0, 1, 1);
}
```