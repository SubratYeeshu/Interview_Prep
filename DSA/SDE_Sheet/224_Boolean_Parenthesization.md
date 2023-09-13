# Boolean Parenthesization

## Problem statement

Given a boolean expression S of length N with following symbols.
Symbols
- 'T' ---> true
- 'F' ---> false
and following operators filled between symbols
Operators

- &   ---> boolean AND
- |   ---> boolean OR
- ^   ---> boolean XOR

Count the number of ways we can parenthesize the expression so that the value of expression evaluates to true.

Note: The answer can be large, so return it with modulo 1003

## Approach 1 : DP

- Time Complexity : O(N^3) ~ Inner Loop Contibutes a N
- Space Complexity : O(N^2 + Rec)

```cpp
int mod = 1003;
int solve(int i, int j, string &s, int isTrue, vector<vector<vector<int>>> &dp){
    if(i > j)return 0;
    if(i == j){
        if(isTrue){
            if(s[i] == 'T')return 1;
            return 0;
        }else{
            if(s[i] == 'F')return 1;
            return 0;
        }
    }
    
    if(dp[i][j][isTrue] != -1)return dp[i][j][isTrue];
    
    int ways = 0;
    
    // Number of ways will be decided on the number of ways we get T or F from respective sides of partition and on the operator at the partition
    // Operator are from i + 1 index and each at gap of 2
    for(int k = i + 1 ; k < j ; k += 2){
        int leftTrue = solve(i, k - 1, s, 1, dp);
        int leftFalse = solve(i, k - 1, s, 0, dp);
        int rightTrue = solve(k + 1, j, s, 1, dp);
        int rightFalse = solve(k + 1, j, s, 0, dp);
        
        if(s[k] == '&'){
            if(isTrue){
                ways = ((ways % mod) + ((leftTrue % mod * rightTrue % mod) % mod) % mod);
            }else{
                ways = ((ways % mod) + ((leftTrue % mod * rightFalse % mod) % mod)
                + ((leftFalse % mod * rightTrue % mod) % mod) 
                + ((leftFalse % mod * rightFalse % mod) % mod) % mod); 
            }
        }else if(s[k] == '|'){
            if(isTrue){
                ways = ((ways % mod) + ((leftTrue % mod * rightFalse % mod) % mod) 
                + ((leftFalse % mod * rightTrue % mod) % mod) 
                + ((leftTrue % mod * rightTrue % mod) % mod) % mod);
            }else{
                ways = ((ways % mod) + ((leftFalse % mod * rightFalse % mod) % mod) % mod);
            }
        }else if(s[k] = '^'){
            if(isTrue){
                ways = ((ways % mod) + ((leftTrue % mod * rightFalse % mod) % mod) 
                + ((leftFalse % mod * rightTrue % mod) % mod) % mod);
            }else{
                ways = ((ways % mod) + ((leftTrue % mod * rightTrue % mod) % mod) 
                + ((leftFalse % mod * rightFalse % mod) % mod) % mod);
            }
        }
    } 
    
    return dp[i][j][isTrue] = ways % mod;
    
}

int countWays(int N, string S){
    vector<vector<vector<int>>> dp (N + 1, vector<vector<int>> (N + 1, vector<int> (N + 1, -1)));
    return solve(0, N - 1, S, 1, dp);
}
```