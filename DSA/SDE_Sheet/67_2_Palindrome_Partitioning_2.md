# Palindrome Partitioning II

## Problem statement 

Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

## Approach 1.1 : DP

Time Complexity : O(N^3) ~ Inner Loop Contibutes a N
Space Complexity : O(N^2 + Rec)

```cpp
bool isPalindrome(int i, int j, string &s){        
    while(i <= j){
        if(s[i] == s[j]){
            i++; j--;
        }else return false;
    }
    return true;
}

int solve(int i, int j , string &s, vector<vector<int>> &dp){
    if(i >= j)return 0;
    
    if(dp[i][j] != -1)return dp[i][j];
    
    int mini = INT_MAX;
    
    if(isPalindrome(i, j, s))return 0;
    
    for(int k = i ; k < j ; k++){
        // int temp = 1 + solve(i, k, s, dp) + solve(k + 1, j, s, dp);
        
        // Optimization
        if(isPalindrome(i, k, s)){                         
            int temp = solve (k + 1, j, s, dp) + 1;
            mini = min (mini, temp);
        }            
    }
    
    return dp[i][j] = mini;
}

int minCut(string s) {
    int n = s.size();
    vector<vector<int>> dp (n + 1, vector<int> (n + 1, -1));
    return solve(0, n - 1, s, dp);
}
```

## Approach 1.2 : DP

Time Complexity : O(N^3) ~ Inner Loop Contibutes a N
Space Complexity : O(N^2 + Rec)

```cpp
int dp1[2001][2001];
bool isPalindrome(int i, int j, string &temp) {
    if (dp1[i][j] != -1) return dp1[i][j];

    int start = i, end = j;  
    while (start <= end) {
        if (temp[start++] != temp[end--]) {
            dp1[i][j] = 0;
            return false;  
        }
    }
    return dp1[i][j] = 1;
}


int dp[2001];
int solve(int i, int n, string &str) {
    if (i == n) return 0;
    if(dp[i] != -1)return dp[i];
    
    int minCost = INT_MAX;
    for (int j = i; j < n; j++) {
        if (isPalindrome(i, j, str)) {
            int cost = 1 + solve(j + 1, n, str);
            minCost = min(minCost, cost);
        }
    }
    return dp[i] = minCost;
}
    
            

int minCut(string s) {
    memset(dp, -1, sizeof dp);
    memset(dp1, -1, sizeof dp1);
    return solve(0, s.size(), s) - 1;  // Partition at the ending
}
```

## Approach 2 : Tabulation

Time Complexity : O(N^3) ~ Inner Loop Contibutes a N
Space Complexity : O(N^2)

```cpp
bool is_palin(int i, int j, string &s){
    while(i <= j){
        if(s[i++] != s[j--]) return false;
    } 
    return true;
}

int minCut(string s) {
    int n = s.length():
    vector<int>dp(n+1, 0);
    for(int i=n-1;i>=0;i--){
        int ans = INT_MAX;
        for(int k=i; k<n; k++){
            if(is_palin(i, k,s)){                         
                int temp = dp[k+1] + 1;
                ans = min (ans, temp);
            }
        }

        dp[i] = ans;
    }
    return dp[0]-1;
}
```