# Longest Common Subsequence

## Problem statement

Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

    For example, "ace" is a subsequence of "abcde".

A common subsequence of two strings is a subsequence that is common to both strings.

## Approach 1 : Brute

- Time complexity : O(2^n\*2^m) ~ O(2^<sup>(m + n)</sup>)
- Space complexity : O(1)

```cpp
int solve(int i, int j, string &str1, string &str2){
    if(i >= str1.size() || j >= str2.size())return 0;
        
    int ans = 0;
    if(str1[i] == str2[j]){
        ans += 1 + solve(i + 1, j + 1, str1, str2);
    }else{
        ans = max(solve(i + 1, j, str1, str2), solve(i, j + 1, str1, str2));
    }
    
    return ans;
}

int longestCommonSubsequence(string text1, string text2) {
    int n = text1.size(), m = text2.size();
    return solve(0, 0, text1, text2);
}
```

## Approach 2 : Memoization

- Time complexity : O(m \* n)
- Space complexity : O(m \* n)

```cpp
int solve(int i, int j, string &str1, string &str2, vector<vector<int>> &dp){
    if(i >= str1.size() || j >= str2.size())return 0;
    
    if(dp[i][j] != -1)return dp[i][j];
    
    int ans = 0;
    if(str1[i] == str2[j]){
        ans += 1 + solve(i + 1, j + 1, str1, str2, dp);
    }else{
        ans = max(solve(i + 1, j, str1, str2, dp), solve(i, j + 1, str1, str2, dp));
    }
    
    return dp[i][j] = ans;
}

int longestCommonSubsequence(string text1, string text2) {
    int n = text1.size(), m = text2.size();
    vector<vector<int>> dp (n + 1, vector<int> (m + 1, -1));
    return solve(0, 0, text1, text2, dp);
}
```