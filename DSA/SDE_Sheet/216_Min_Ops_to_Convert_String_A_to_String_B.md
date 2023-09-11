# Minimum Operations to Convert String A to String B / Delete Operation for Two Strings

## Problem statement

Given two strings str1 and str2. The task is to remove or insert the minimum number of characters from/in str1 so as to transform it into str2. It could be possible that the same character needs to be removed/deleted from one point of str1 and inserted to some another point.

## Approach 1 : LCS 

- Time complexity : O(M \* N)
- Space complexity : O(M \* N)

```cpp
int LCS(string text1, string text2) {
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

int minDistance(string word1, string word2) {
    int n = word1.size(), m = word2.size();
    return n + m - 2 * LCS(word1, word2);
}
```