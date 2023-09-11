# Longest Common Subsequence

## Problem statement

Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

    For example, "ace" is a subsequence of "abcde".

A common subsequence of two strings is a subsequence that is common to both strings.

## Pattern : DP on Strings

- Think of using LCS when two strings are given
- And we are asked to make it palindrome, number of occurences 
- These type of questions where two strings can be involved and TC is becoming exponential

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

## Approach 2.1 : Memoization (Index from start)

- Time complexity : O(M \* N)
- Space complexity : O(M \* N)

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

## Approach 2.2 : Memoization (Index from end)

- Time complexity : O(M \* N)
- Space complexity : O(M \* N)

```cpp
int solve(int i, int j, string &str1, string &str2, vector<vector<int>> &dp){
    if(i < 0 || j < 0)return 0;
    
    if(dp[i][j] != -1)return dp[i][j];
    
    if(str1[i] == str2[j]){
        return dp[i][j] = 1 + solve(i - 1, j - 1, str1, str2, dp);
    }else{
        return dp[i][j] = max(solve(i - 1, j, str1, str2, dp), solve(i, j - 1, str1, str2, dp));
    }
    
}

int longestCommonSubsequence(string text1, string text2) {
    int n = text1.size(), m = text2.size();
    vector<vector<int>> dp (n + 1, vector<int> (m + 1, -1));
    return solve(n - 1, m - 1, text1, text2, dp);
}
```

## Approach 2.3 : Memoization (Index from end) + Shifting of index

- Time complexity : O(M \* N)
- Space complexity : O(M \* N)

```cpp
// Shift index by 1 because -1 cant be accessed
int solve(int i, int j, string &str1, string &str2, vector<vector<int>> &dp){
    if(i == 0 || j == 0)return 0;
    
    if(dp[i][j] != -1)return dp[i][j];
    
    if(str1[i - 1] == str2[j - 1]){
        return dp[i][j] = 1 + solve(i - 1, j - 1, str1, str2, dp);
    }else{
        return dp[i][j] = max(solve(i - 1, j, str1, str2, dp), solve(i, j - 1, str1, str2, dp));
    }
    
}

int longestCommonSubsequence(string text1, string text2) {
    int n = text1.size(), m = text2.size();
    vector<vector<int>> dp (n + 1, vector<int> (m + 1, -1));
            
    return solve(n, m, text1, text2, dp);
}
```

## Approach 3.1 : Tabulation (Index from start) Bottom Up + Standard LCS

- Time complexity : O(M \* N)
- Space complexity : O(M \* N)

```cpp
// For tabulation dp[i][j] represents the LCS when length of str1 is i and length of str2 is j
int longestCommonSubsequence(string text1, string text2) {
    int n = text1.size(), m = text2.size();
    
    // if(i == 0 || j == 0)return 0;
    vector<vector<int>> dp (n + 1, vector<int> (m + 1, 0));
    
    // i and j denotes respective lengths
    for(int i = 1 ; i < n + 1 ; i++){
        for(int j = 1 ; j < m + 1 ; j++){
            if(text1[i - 1] == text2[j - 1]){
                dp[i][j] = 1 + dp[i - 1][j - 1];
            }else{
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }
    
    return dp[n][m];
}
```

## Printing The LCS

- Time complexity : O(M \* N)
- Space complexity : O(M \* N)

```cpp
int longestCommonSubsequence(string text1, string text2) {
    int n = text1.size(), m = text2.size();

    // if(i == 0 || j == 0)return 0;
    vector<vector<int>> dp (n + 1, vector<int> (m + 1, 0));

    // i and j denotes respective lengths
    for(int i = 1 ; i < n + 1 ; i++){
        for(int j = 1 ; j < m + 1 ; j++){
            if(text1[i - 1] == text2[j - 1]){
                dp[i][j] = 1 + dp[i - 1][j - 1];
            }else{
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }

    for(auto i : dp){
        for(auto j : i){
            cout << j << " ";
        }
        cout << endl;
    }

    int i = n, j = m;
    string lcs = "";  // Initialize the LCS string

    while (i > 0 && j > 0) {
        if (text1[i - 1] == text2[j - 1]) {
            lcs += text1[i - 1] + lcs;  // Prepend the character to LCS
            i--;
            j--;
        } else {
            if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
    }
    
    cout << lcs;
    
    return dp[n][m];
}
```