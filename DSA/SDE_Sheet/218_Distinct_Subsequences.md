# Distinct Subsequences 

## Problem statement

We are given two strings S1 and S2, we want to know how many distinct subsequences of S2 are present in S1.

## Approach 1.1 : Brute + (Index from start)

- Time complexity : O(2^N)
- Space complexity : O(Rec)

```cpp
int solve(int i, int j, string &s1, string &s2){
	if(j >= s2.size())return 1;
	if(i >= s1.size())return 0;

	int ans = 0;
	if(s1[i] == s2[j])ans += solve(i + 1, j + 1, s1, s2) + solve(i + 1, j, s1, s2);
	else ans += solve(i + 1, j, s1, s2);

	return ans;
}

int distinctSubsequences(string &str, string &sub){
	return solve(0, 0, str, sub);
}
```

## Approach 1.2 : Brute + (Index from back)

- Time complexity : O(2^N)
- Space complexity : O(Rec)

```cpp
int solve(int i, int j, string &s1, string &s2){
	if(j < 0)return 1;
	if(i < 0)return 0;

	int ans = 0;
	if(s1[i] == s2[j])ans += solve(i - 1, j - 1, s1, s2) + solve(i - 1, j, s1, s2);
	else ans += solve(i - 1, j, s1, s2);

	return ans;
}

int distinctSubsequences(string &str, string &sub){
	int n = str1.size(), m = sub.size();
	return solve(n - 1, m - 1, str, sub);
}
```

## Approach 2 : Memoization

- Time complexity : O(N^2)
- Space complexity : O(N^2)

```cpp
int dp[1002][1002];
int m = 1e9 + 7;
int solve(int i, int j, string &s1, string &s2){
	if(j >= s2.size())return 1;
	if(i >= s1.size())return 0;

	if(dp[i][j] != -1)return dp[i][j];

	int ans = 0;
	if(s1[i] == s2[j])ans += (solve(i + 1, j + 1, s1, s2) % m + solve(i + 1, j, s1, s2) % m) % m;
	else ans += solve(i + 1, j, s1, s2) % m;

	return dp[i][j] = ans % m;
}

int distinctSubsequences(string &str, string &sub){
	memset(dp, -1, sizeof(dp));
	return solve(0, 0, str, sub);
}
```

## Approach 3 : Tabulation

- Time complexity : O(N^2)
- Space complexity : O(N^2)

```cpp
int numDistinct(string s,string t){
    int n = s.size(), m = t.size();
    vector<vector<int>>dp(n + 1, vector<int>(m + 1, 0));
    for(int i = 0 ; i <= n ; i++){
        dp[i][0] = 1;
    }
    
    for(int i = 1 ; i <= n ; i++){
        for(int j = 1 ; j <= m; j++){
            
                int notPick = dp[i-1][j], pick=0;
        
                if(s[i-1] == t[j-1]){
                        pick = dp[i-1][j-1]; 
                }
        
                dp[i][j] = (pick + notPick) % 1000000007;
            }
        }
    
    return dp[n][m];
}
```