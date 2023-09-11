# Wildcard Matching

## Problem statement

Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

    '?' Matches any single character.
    '*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

## Approach 1 : Brute + Index From Start 

- Time complexity : O(2^N)
- Space complexity : O(Rec)

```cpp
/*
    abbacbaa
    ab*?a
    * -> matches : bacb 
    ? -> matches : a
        
    abdejced
    ab*c?d
    * matches with empty, j, je, jed than the string is matched
*/
bool solve(int i, int j, string &s, string &p){
    if(i >= s.size() && j >= p.size())return true;
    if(i >= s.size()){
        // s completed
        for(int k = j ; k < p.size() ; k++){
            if(p[k] != '*')return false;
        }
        return true;
    }
    
    
    // Match / ?
    if(s[i] == p[j] || p[j] == '?')return solve(i + 1, j + 1, s, p);
    
    // *, take character from s and stand on same in p || go ahead in p
    if(p[j] == '*'){
        return solve(i + 1, j, s, p) || solve(i, j + 1, s, p);
    }
    
    // if(s[i] != p[j])return false;
    
    return false;
}

bool isMatch(string s, string p) {
    return solve(0, 0, s, p);
}
```

## Approach 1.2 : Brute + Index From End

- Time complexity : O(2^N)
- Space complexity : O(Rec)

```cpp
bool solve(int i, int j, string &s, string &p){
    if(i <= -1 && j <= -1)return true;
    
    if(i <= -1){
        // s completed
        for(int k = 0 ; k <= j ; k++)
            if(p[k] != '*')return false;
        return true;
    }
    
    if(j <= -1)return false;
    
    
    // Match / ?
    if(s[i] == p[j] || p[j] == '?')return solve(i - 1, j - 1, s, p);
    
    // *, take character from s and stand on same in p || go ahead in p
    if(p[j] == '*'){
        return solve(i - 1, j, s, p) || solve(i, j - 1, s, p);
    }
    
    // if(s[i] != p[j])return false;
    
    return false;
}

bool isMatch(string s, string p) {
    int n = s.size(), m = p.size();
    vector<vector<int>> dp (n + 1, vector<int> (m + 1, -1));
    return solve(n - 1, m - 1, s, p);
}
```

## Approach 1.3 : Index shifiting for tabulation (1 Based indexing)

- Time complexity : O(2^N)
- Space complexity : O(Rec) 

```cpp
// 1 Based indexing
bool solve(int i, int j, string &s, string &p){
    if(i == 0 && j == 0)return true;
    if(j == 0 && i != 0)return false;
    
    if(i == 0){
        // s completed
        for(int k = 1 ; k <= j ; k++)
            if(p[k - 1] != '*')return false;
        return true;
    }
    
    
    // Match / ?
    if(s[i - 1] == p[j - 1] || p[j - 1] == '?')return solve(i - 1, j - 1, s, p);
    
    // *, take character from s and stand on same in p || go ahead in p
    if(p[j - 1] == '*'){
        return solve(i - 1, j, s, p) || solve(i, j - 1, s, p);
    }
    
    // if(s[i] != p[j])return false;
    
    return false;
}

bool isMatch(string s, string p) {
    int n = s.size(), m = p.size();
    vector<vector<int>> dp (n + 1, vector<int> (m + 1, -1));
    return solve(n, m, s, p);
}
```

## Approach 2.1 : Memoization + Index From Start

- Time complexity : O(N^2)
- Space complexity : O(Rec)

```cpp
bool solve(int i, int j, string &s, string &p, vector<vector<int>> &dp){
    if(i >= s.size() && j >= p.size())return true;
    if(i >= s.size()){
        // s completed
        for(int k = j ; k < p.size() ; k++){
            if(p[k] != '*')return false;
        }
        return true;
    }
    
    if(dp[i][j] != -1)return dp[i][j];
    
    // Match / ?
    if(s[i] == p[j] || p[j] == '?')return dp[i][j] = solve(i + 1, j + 1, s, p, dp);
    
    // *, take character from s and stand on same in p || go ahead in p
    if(p[j] == '*'){
        return dp[i][j] = solve(i + 1, j, s, p, dp) || solve(i, j + 1, s, p, dp);
    }
    
    // if(s[i] != p[j])return false;
    
    return dp[i][j] = false;
}

bool isMatch(string s, string p) {
    int n = s.size(), m = p.size();
    vector<vector<int>> dp (n + 1, vector<int> (m + 1, -1));
    return solve(0, 0, s, p, dp);
}
```

## Approach 2.2 : Memoization + Index From Start (For conversion to tabulation)

- Time complexity : O(N^2)
- Space complexity : O(Rec)

```cpp
// 1 Based indexing
bool solve(int i, int j, string &s, string &p, vector<vector<int>> &dp){
    if(i == 0 && j == 0)return true;
    if(j == 0 && i > 0)return false;
    
    if(i == 0 && j > 0){
        // s completed
        for(int k = 1 ; k <= j ; k++)
            if(p[k - 1] != '*')return false;
        return true;
    }
    
    if(dp[i][j] != -1)return dp[i][j];
    
    // Match / ?
    if(s[i - 1] == p[j - 1] || p[j - 1] == '?')return dp[i][j] = solve(i - 1, j - 1, s, p, dp);
    
    // *, take character from s and stand on same in p || go ahead in p
    if(p[j - 1] == '*'){
        return dp[i][j] = solve(i - 1, j, s, p, dp) || solve(i, j - 1, s, p, dp);
    }
    
    // if(s[i] != p[j])return false;
    
    return dp[i][j] = false;
}

bool isMatch(string s, string p) {
    int n = s.size(), m = p.size();
    vector<vector<int>> dp (n + 1, vector<int> (m + 1, -1));
    return solve(n, m, s, p, dp);
}
```

## Approach 3 : Tabulation

- Time complexity : O(N^2)
- Space complexity : O(N^2)

```cpp
bool isMatch(string s, string p) {
    int n = s.size(), m = p.size();
    vector<vector<bool>> dp(n + 1, vector<bool>(m + 1));
    
    // Base case coversion
    dp[0][0] = true;
    for (int i = 1; i <= n; i++)dp[i][0] = false;

    bool flag = true;
    for (int i = 1; i <= m; i++) {
        if (p[i - 1] != '*') {
            flag = false;
        }
        dp[0][i] = flag;
    }

    // Recursive relation conversion
    for (int i = 1 ; i <= n ; i++) {
        for (int j = 1 ; j <= m ; j++) {
            if (s[i - 1] == p[j - 1] || p[j - 1] == '?'){
                dp[i][j] = dp[i - 1][j - 1];
            }else if (p[j - 1] == '*'){
                dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
            }else{
                dp[i][j] = false;
            }
        }
    }

    return dp[n][m];
}
```