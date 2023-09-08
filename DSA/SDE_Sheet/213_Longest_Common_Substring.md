# Longest Common Substring

## Problem statement

Given two strings. The task is to find the length of the longest common substring.

## Approach 1 : Brute

- Time complexity : O(N^3)
- Space complexity : O(1)

```cpp
int longestCommonSubstr(string S1, string S2, int n, int m){
    int maxLen = 0;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            int len = 0;
            int x = i;
            int y = j;
            while (x < n && y < m && S1[x] == S2[y])
            {
                len++;
                x++;
                y++;
            }
            maxLen = max(maxLen, len);
        }
    }
    return maxLen;
}
```

## Approach 2 : LCS (Recursive)

- Time complexity : O(m \* n)
- Space complexity : O(m \* n)

```cpp
int solve(int i, int j, int n, int m, string &s1, string &s2,vector<vector<int>>&dp){
    if(i == n || j == m){
        return 0;
    }

    if(dp[i][j] != -1) return dp[i][j];

    if(s1[i] == s2[j]){
        return dp[i][j] =  1 + solve(i + 1, j + 1, n, m, s1, s2, dp);
    }

    return 0;
}

int lcs(string &str1, string &str2){
    int n = str1.size();
    int m = str2.size();
    int res = 0;
    vector<vector<int>> dp(n + 1, vector<int>(m + 1, -1));
    for(int i = 0 ; i < n ; i++){
        for(int j = 0 ; j < m ; j++)
        res = max(res, solve(i, j, n, m, str1, str2, dp));
    }
    return res;
}
```

## Approach 3.1 : LCS (Modification) + Tabulation

- Time complexity : O(m \* n)
- Space complexity : O(m \* n)

```cpp
int dp[1001][1001];
int longestCommonSubstr (string S1, string S2, int n, int m)
{
    //Base case initialzation
    for(int i = 0 ; i < n ; i++){
        for(int j = 0 ; j < m ; j++){
            if(i == 0 || j == 0){
                dp[i][j] = 0;
            }
        }
    }
    
    //In LCS we would have taken max of these i - 1 & j - 1 stuffs, but here
    //if the previous one do not match simply fill the block with 0
    //ie, ask for prev guy if they did match add one to it and fill the new block
    for(int i = 1 ; i <= n ; i++){
        for(int j = 1 ; j <= m ; j++){
            if(S1[i - 1] == S2[j - 1]){
                dp[i][j] = 1 + dp[i - 1][j - 1];
            }else{
                dp[i][j] = 0;
            }
        }
    }
    
    int max=INT_MIN;
        for(int i=0;i<=n;i++)
        {
            for(int j=0;j<=m;j++)
            {
                if(dp[i][j]>max)
                {
                    max=dp[i][j];
                }
            }
        }
        return max;
}
```

## Approach 3.2 : LCS (Modification) + Tabulation + Short code

- Time complexity : O(m \* n)
- Space complexity : O(m \* n)

```cpp
int longestCommonSubstr(string S1, string S2, int n, int m){
    vector<vector<int>> dp(n + 1, vector<int>(m + 1));
    int maxLen = 0;
    for(int i = 1 ; i <= n ; i++){
        for(int j = 1 ; j <= m ; j++){
            if(S1[i - 1] == S2[j - 1]){
                dp[i][j] = 1 + dp[i - 1][j - 1];
                maxLen = max(dp[i][j], maxLen);
            }
        }
    }
    return maxLen;
}
```

