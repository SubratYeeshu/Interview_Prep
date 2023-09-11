# Shortest Common Supersequence 

## Problem statement

Given two strings X and Y of lengths m and n respectively, find the length of the smallest string which has both, X and Y as its sub-sequences.
Note: X and Y can have both uppercase and lowercase letters.

## Approach 1 : LCS

- Time complexity : O(M \* N)
- Space complexity : O(M \* N)

```cpp
int shortestCommonSupersequence(string s1, string s2, int n, int m){
    vector < vector < int >> dp(n + 1, vector <int> (m + 1, 0));
    // Base case
    for (int i = 0; i <= n; i++)dp[i][0] = 0;
    for (int i = 0; i <= m; i++)dp[0][i] = 0;
    // Tabulation
    for (int ind1 = 1 ; ind1 <= n ; ind1++) {
        for (int ind2 = 1 ; ind2 <= m ; ind2++) {
            if (s1[ind1 - 1] == s2[ind2 - 1])dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
            else dp[ind1][ind2] = 0 + max(dp[ind1 - 1][ind2], dp[ind1][ind2 - 1]);
        }
    }
    int len = dp[n][m];
    int i = n, j = m;
    int index = len - 1;
    string ans = "";
    
    // Whichever element goes out of scope add it to answer
    while (i > 0 && j > 0) {
        if (s1[i - 1] == s2[j - 1]) {
            ans += s1[i-1];
            index--; i--; j--;      
        }else if (dp[i - 1][j] > dp[i][j - 1]) {
            ans += s1[i-1];
            i--;
        }else {
            ans += s2[j-1];
            j--;
        }    
    }
    
    //Adding Remaing Characters - Only one of the below two while loops will run
    while(i > 0){
        ans += s1[i-1];
        i--;
    }
    while(j > 0){
        ans += s2[j-1];
        j--;
    }
    reverse(ans.begin(),ans.end());
    
    return m + n - len;
}
```