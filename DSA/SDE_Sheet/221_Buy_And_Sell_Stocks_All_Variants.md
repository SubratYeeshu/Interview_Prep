# Buy And Sell Stocks (All Variants)

## Variant 1 : Buy And Sell Stock I (Atmost 1 Transaction)

## Problem statement

You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

## Approach 1 : Kadane's Algorithm

- Time complexity : O(N)
- Space complexity : O(1)

```cpp
int maxProfit(vector<int>& prices) {
    int currMini = INT_MAX, profit = 0;
    for(int i = 0 ; i < prices.size() ; i++){
        currMini = min(currMini, prices[i]);
        profit = max(profit, prices[i] - currMini);
    }
    
    return profit;
}
```

## Approach 2 : DP

- Time complexity : O(2N)
- Space complexity : O(2N + Recursive Stack Space)

```cpp
int solve(int index, int canBuy, vector<int> &prices, int k, vector<vector<int>> &dp){
    if(index >= prices.size() || k <= 0)return 0;
    
    if(dp[index][canBuy] != -1)return dp[index][canBuy];
    
    if(canBuy){
        // Purchase
        return dp[index][canBuy] = max(solve(index + 1, canBuy, prices, k, dp), -prices[index] + solve(index + 1, !canBuy, prices, k, dp));    
    }else{
        // Sell
        return dp[index][canBuy] = max(solve(index + 1, canBuy, prices, k, dp), +prices[index] + solve(index + 1, !canBuy, prices, k - 1, dp));
    }
}

int maxProfit(vector<int>& prices) {
    int n = prices.size();
    vector<vector<int>> dp (n + 1, vector<int> (2, -1));
    return solve(0, 1, prices, 1, dp);
}
```

## Variant 2 : Buy and Sell Stock II (Infinite Transaction)

## Problem statement

You are given an integer array prices where prices[i] is the price of a given stock on the ith day.

On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.

Find and return the maximum profit you can achieve.

## Approach 1 : DP

- Time complexity : O(2N)
- Space complexity : O(2N + Recursive Stack Space)

```cpp
int solve(int index, int canBuy, vector<int> &prices, vector<vector<int>> &dp){
    if(index >= prices.size())return 0;
    
    if(dp[index][canBuy] != -1)return dp[index][canBuy];
    
    if(canBuy){
        return dp[index][canBuy] = max(solve(index + 1, canBuy, prices, dp), -prices[index] + solve(index + 1, !canBuy, prices, dp));
    }else{
        return dp[index][canBuy] = max(solve(index + 1, canBuy, prices, dp), +prices[index] + solve(index + 1, !canBuy, prices, dp));
    }
}

int maxProfit(vector<int>& prices) {
    int n = prices.size();
    vector<vector<int>> dp (n + 1, vector<int> (2, -1));
    return solve(0, 1, prices, dp);
}
```

## Approach 2 : Tabulation

- Time complexity : O(2N)
- Space complexity : O(2N)

```cpp
int maxProfit(vector<int>& prices) {
    int n = prices.size();
    vector<vector<int>> dp(n + 1,vector<int>(2, -1));
    
    // Base case conversion
    dp[n][0] = dp[n][1] = 0;

    int profit = 0;

    // Recurrence conversion
    for(int ind = n - 1 ; ind >= 0 ; ind--){
        for(int buy = 0 ; buy <= 1 ; buy++){
            if(buy == 0){
                dp[ind][buy] = max(0 + dp[ind + 1][0], -prices[ind] + dp[ind + 1][1]);
            }
            if(buy == 1){
                dp[ind][buy] = max(0 + dp[ind + 1][1], prices[ind] + dp[ind + 1][0]);
            }
        }
    }
    return dp[0][0];
}
```

## Variant 3 : Buy and Sell Stock III (Infinite Transaction + With Cap 2)

## Problem statement

You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete at most two transactions.

Note: 
- You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

## Approach 1 : DP

- Time complexity : O(2\*3\*N)
- Space complexity : O(2\*3\*N + Recursive Stack Space)

```cpp
int dp[100001][2][3];  
int solve(vector<int>& prices, int index, int canBuy, int capTrans){
    if(capTrans == 0)return 0; 
    if(index >= prices.size())return 0;   
    
    if(dp[index][canBuy][capTrans] != -1)return dp[index][canBuy][capTrans];
    
    if(canBuy)
        return dp[index][canBuy][capTrans] = max(-prices[index] + solve(prices, index + 1, 0, capTrans), 
                                                    solve(prices, index + 1, 1, capTrans));
    else return dp[index][canBuy][capTrans] = max(+prices[index] + solve(prices, index + 1,  1, capTrans - 1), 
                                                    solve(prices, index + 1, 0, capTrans));
}

int maxProfit(vector<int>& prices) {
    memset(dp, -1, sizeof(dp));        
    return solve(prices, 0, 1, 2);
}
```

## Approach 2 : Tabulation 

- Time complexity : O(2\*3\*N)
- Space complexity : O(2\*3\*N)

```cpp
int maxProfit(vector<int>& prices) {
    int n = prices.size();
    //Base case conversion from memoized code
    
    //For Cap == 0,  it will return 0 for any index and buy
    //Base case 1 
    vector<vector<vector<int>>>dp(n+1, vector<vector<int>>(2, vector<int>(3, 0)));  // n + 1 * 2 * 3
    for(int index = 0 ; index < n ; index++){
        for(int buy = 0 ; buy <= 1 ; buy++){
            dp[index][buy][0] = 0;
        }
    }
    //For index = n, it will return 0 for any buy and cap
    //Base case 2 
    for(int buy = 0 ; buy <= 1 ; buy++){
        for(int cap = 0 ; cap <= 2 ; cap++){
            dp[n][buy][cap] = 0;
        }
    }
    
    //Now range in reverse order filling, just opposite of starting in recursive relation
    for(int index = n - 1 ; index >= 0 ; index--){
        for(int buy = 0 ; buy <= 1 ; buy++){
            for(int cap = 1 ; cap <= 2 ; cap++){
                //for cap == 0, all cases have been handled above
                if(buy)
                    dp[index][buy][cap] = max(-prices[index] + dp[index + 1][0][cap] 
                                                    , 0 + dp[index + 1][1][cap]);
                else
                    dp[index][buy][cap] = max(+prices[index] + 
                                                    dp[index + 1][1][cap - 1] , 0 + dp[index + 1][0][cap]);
            }
        }
    }
        
    return dp[0][1][2];
}
```

## Variant 4 : Buy and Sell Stock IV (Infinite Transaction + With Cap K)

## Problem statement

You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.

Find the maximum profit you can achieve. You may complete at most k transactions: i.e. you may buy at most k times and sell at most k times.

Note: 
- You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

## Approach 1 : DP

- Time complexity : O(2\*K\*N)
- Space complexity : O(2\*K\*N + Recursive Stack Space)

```cpp
int solve(vector<int>& prices, int index, int canBuy, int capTrans, vector<vector<vector<int>>>&dp){
    if(capTrans == 0)return 0;  
    if(index == prices.size())return 0;   
    
    //Memoize
    if(dp[index][canBuy][capTrans] != -1)return dp[index][canBuy][capTrans];
    
    if(canBuy)
        return dp[index][canBuy][capTrans] = max(-prices[index] + solve(prices, index + 1, 0, capTrans, dp),
                                                    0 + solve(prices, index + 1, 1, capTrans, dp));
    else
        return dp[index][canBuy][capTrans] = max(+prices[index] + solve(prices, index + 1, 1, capTrans - 1, dp),
                                                    0 + solve(prices, index + 1, 0, capTrans, dp));
}


int maxProfit(int k, vector<int>& prices) {
    int n = prices.size();
    vector<vector<vector<int>>>dp(n + 1, vector<vector<int>>(2, vector<int>(k + 1, -1)));
    return solve(prices, 0, 1, k, dp);
}
```

## Approach 2 : Tabulation

- Time complexity : O(2\*K\*N)
- Space complexity : O(2\*K\*N)

```cpp
int maxProfit(int k, vector<int>& prices) {
    int n = prices.size();
    //Base case conversion from memoized code

    //For Cap == 0,  it will return 0 for any index and buy
    //Base case 1 
    vector<vector<vector<int>>>dp(n + 1, vector<vector<int>>(2, vector<int>(k + 1, 0)));  
    for(int index = 0 ; index < n ; index++){
        for(int buy = 0 ; buy <= 1 ; buy++){
            dp[index][buy][0] = 0;
        }
    }
    //For index = n, it will return 0 for any buy and cap
    //Base case 2 
    for(int buy = 0 ; buy <= 1 ; buy++){
        for(int cap = 0 ; cap <= k ; cap++){
            dp[n][buy][cap] = 0;
        }
    }

    //Now range in reverse order filling, just opposite of starting in recursive relation
    for(int index = n - 1 ; index >= 0 ; index--){
        for(int buy = 0 ; buy <= 1 ; buy++){
            for(int cap = 1 ; cap <= k ; cap++){
                //for cap == 0, all cases have been handled above
                if(buy)
                    dp[index][buy][cap] = max(-prices[index] + dp[index + 1][0][cap] 
                                                    , 0 + dp[index + 1][1][cap]);
                else
                    dp[index][buy][cap] = max(+prices[index] + 
                                                    dp[index + 1][1][cap - 1] , 0 + dp[index + 1][0][cap]);
            }
        }
    }

    return dp[0][1][k];
}
```

## Variant 5 : Buy and Sell Stock with Cooldown

## Problem statement

You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:

After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).

Note: 
- You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

## Approach 1 : DP

- Time complexity : O(2N)
- Space complexity : O(2N + Recursive Stack Space)

```cpp
int solve(int index, int canBuy, vector<int> &prices, vector<vector<int>> &dp){
    if(index >= prices.size())return 0;
    
    if(dp[index][canBuy] != -1)return dp[index][canBuy];
    
    if(canBuy){
        return dp[index][canBuy] =  max(-prices[index] + solve(index + 1, !canBuy, prices, dp), 
                                        solve(index + 1, canBuy, prices, dp));
    }else{
        return dp[index][canBuy] = max(+prices[index] + solve(index + 2, !canBuy, prices, dp), 
                                        solve(index + 1, canBuy, prices, dp));
    }
}

int maxProfit(vector<int>& prices) {
    int n = prices.size();
    vector<vector<int>> dp (n + 2, vector<int> (2, -1));
    return solve(0, 1, prices, dp);
}
```

## Approach 2 : Tabulation

- Time complexity : O(2N)
- Space complexity : O(2N)

```cpp
int maxProfit(vector<int>& prices) {
    int n = prices.size();
    vector<vector<int>> dp(n + 2, vector<int>(2, 0));
    for(int ind = n - 1 ; ind >= 0 ; ind--){
        for(int buy = 0 ; buy < 2 ; buy++){
            if(buy){
                dp[ind][buy] = max(-prices[ind] + dp[ind + 1][0], dp[ind + 1][1]);
            }
            else{
                dp[ind][buy] = max(prices[ind] + dp[ind + 2][1], dp[ind + 1][0]);
            }
        }
    }
    return dp[0][1];
}
```

## Variant 6 : Buy and Sell Stock with Transaction Fee 

## Problem statement

You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.

Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.

Note:

- You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
- The transaction fee is only charged once for each stock purchase and sale.


## Approach 1 : DP

- Time complexity : O(2N)
- Space complexity : O(2N + Recursive Stack Space)

```cpp
int solve(int index, int canBuy, int fee, vector<int> &prices, vector<vector<int>> &dp){
    if(index >= prices.size())return 0;
    
    if(dp[index][canBuy] != -1)return dp[index][canBuy];
    
    if(canBuy)
        return dp[index][canBuy] =  max(-prices[index] + solve(index + 1, !canBuy, fee, prices, dp),
                    solve(index + 1, canBuy, fee, prices, dp));
    else 
        return dp[index][canBuy] = max(+prices[index] - fee + solve(index + 1, !canBuy, fee, prices, dp),
                    solve(index + 1, canBuy, fee, prices, dp));
    
}

int maxProfit(vector<int>& prices, int fee) {
    int n = prices.size();
    vector<vector<int>> dp (n + 1, vector<int> (2, -1));
    return solve(0, 1, fee, prices, dp);
}
```

## Approach 2 : Tabulation

- Time complexity : O(2N)
- Space complexity : O(2N + Recursive Stack Space)

```cpp
int maxProfit(vector<int>& prices, int fee) {
    int n = prices.size();
    vector<vector<int>> dp(n + 2, vector<int>(2, 0));
    for(int ind = n - 1 ; ind >= 0 ; ind--){
        for(int buy = 0 ; buy < 2 ; buy++){
            if(buy){
                dp[ind][buy] = max(-prices[ind] + dp[ind + 1][0], dp[ind + 1][1]);
            }
            else{
                dp[ind][buy] = max(prices[ind] + dp[ind + 1][1] - fee, dp[ind + 1][0]);
            }
        }
    }
    return dp[0][1];
}
```
