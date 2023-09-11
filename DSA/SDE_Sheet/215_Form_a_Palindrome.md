# Form a Palindrome (Mimimum Insertion)

## Problem statement

Form a palindrome

Given a string, find the minimum number of characters to be inserted to convert it to palindrome.

For Example:
- ab: Number of insertions required is 1. bab or aba
- aa: Number of insertions required is 0. aa
- abcd: Number of insertions required is 3. dcbabcd

## Approach 1 : LPS (Modification)

- Time complexity : O(M \* N)
- Space complexity : O(M \* N)

```cpp
int countMin(string s1){
    int n = s1.size();
    int dp[n + 1][n + 1];
    string s2 = s1;
    reverse(s1.begin(), s1.end());
    
    for(int i = 0 ; i <= n ; i++){
        for(int j = 0 ; j <= n ; j++){
            if(i == 0 || j == 0)dp[i][j] = 0;
            else if(s1[i - 1] == s2[j - 1])dp[i][j] = dp[i - 1][j - 1] + 1;
            else dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
        }
    }

    return n - dp[n][n];  // n is max operation, that is reversing and attaching the string will make it palindrome anyhow - already built palindrome
}
```