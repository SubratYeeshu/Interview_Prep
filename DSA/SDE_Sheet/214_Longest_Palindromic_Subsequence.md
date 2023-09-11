# Longest Palindromic Subsequence

## Problem statement

Given a string s, find the longest palindromic subsequence's length in s.

A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.

## Approach 1 : Reverse + LCS

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

    return dp[n][m];
}

int longestPalindromeSubseq(string s) {
    string t = s;
    reverse(t.begin(), t.end());
    return longestCommonSubsequence(s, t);
}
```