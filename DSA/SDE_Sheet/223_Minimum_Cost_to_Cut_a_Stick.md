# Minimum Cost to Cut a Stick

## Problem statement

Given a wooden stick of length n units. The stick is labelled from 0 to n. For example, a stick of length 6 is labelled as follows:

Given an integer array cuts where cuts[i] denotes a position you should perform a cut at.

You should perform the cuts in order, you can change the order of the cuts as you wish.

The cost of one cut is the length of the stick to be cut, the total cost is the sum of costs of all cuts. When you cut a stick, it will be split into two smaller sticks (i.e. the sum of their lengths is the length of the stick before the cut). Please refer to the first example for a better explanation.

Return the minimum total cost of the cuts.

## Approach 1 : DP

- Time Complexity : O(N^3) ~ Inner Loop Contibutes a N
- Space Complexity : O(N^2 + Rec)

```cpp
// If cuts are sorted than only it will work because they become independent after cuts
int solve(int i, int j, vector<int> &cuts, vector<vector<int>> &dp){
    if(i > j)return 0;
    
    if(dp[i][j] != -1)return dp[i][j];
    
    int ans = INT_MAX;
    
    for(int k = i ; k <= j ; k++){
        int temp = solve(i, k - 1, cuts, dp) + solve(k + 1, j, cuts, dp) + cuts[j + 1] - cuts[i - 1];
        ans = min(ans, temp);
    }
    
    return dp[i][j] = ans;
}

int minCost(int n, vector<int>& cuts) {   
    int c = cuts.size();
    
    cuts.insert(cuts.begin(), 0);
    cuts.push_back(n);
    sort(cuts.begin(), cuts.end());
    vector<vector<int>> dp(c + 1, vector<int>(c + 1, -1));       
    
    return solve(1, c, cuts, dp);
}
```

## Approach 2 : Tabulation

- Time Complexity : O(N^3) ~ Inner Loop Contibutes a N
- Space Complexity : O(N^2)

```cpp
// If cuts are sorted than only it will work because they become independent after cuts
int minCost(int n, vector<int>& cuts) {   
    int c = cuts.size();
    
    cuts.insert(cuts.begin(), 0);
    cuts.push_back(n);
    sort(cuts.begin(), cuts.end());
    
    // Base case conversion
    vector<vector<int>> dp(c + 2, vector<int>(c + 2, 0));       
    
    // Two changing variable + Loop + reverse
    for(int i = c ; i >= 1 ; i--){
        for(int j = 1 ; j <= c ; j++){
            if(i > j)continue;

            int ans = INT_MAX;

            for(int k = i ; k <= j ; k++){
                int temp = dp[i][k - 1] + dp[k + 1][j] + cuts[j + 1] - cuts[i - 1];
                ans = min(ans, temp);
            }

            dp[i][j] = ans;
        }
    }
    
    return dp[1][c];
}
```