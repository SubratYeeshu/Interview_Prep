# Edit Distance

## Problem statement

Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

You have the following three operations permitted on a word:

- Insert a character
- Delete a character
- Replace a character


## Approach 1.1 : Brute + (Index from start)

- Time complexity : O(3^N)
- Space complexity : O(Rec)

```cpp
int solve(int i, int j, string &word1, string &word2){
    if(j >= word2.size())return word1.size() - i;
    if(i >= word1.size())return word2.size() - j;
    
    int ans = 0;
    if(word1[i] == word2[j]){
        ans += solve(i + 1, j + 1, word1, word2);
    }else{
        // 1 op + min (all three rec ops)
        ans += 1 + min({solve(i + 1, j, word1, word2),  // Delete
                        solve(i + 1, j + 1, word1, word2),  // Replace
                        solve(i, j + 1, word1, word2)});  // Insert
    }
    
    return ans;
}

int minDistance(string word1, string word2) {
    return solve(0, 0, word1, word2);
}
```

## Approach 1.2 : Brute + (Index from end)

- Time complexity : O(3^N)
- Space complexity : O(Rec)

```cpp
int solve(int i, int j, string& word1, string& word2){
    //Base case
    if(i < 0)return j + 1;
    if(j < 0)return i + 1;
    
    int ans = 0;
    if(word1[i] == word2[j]){
        //Char matched no operations needed and the string is reduced
        ans += solve(i - 1, j - 1, word1, word2);
    }else{
        //We can do three types of operations
        ans += 1 + min({solve(i - 1, j, word1, word2),  // Delete
            solve(i - 1, j - 1, word1, word2),  // Replace
            solve(i, j - 1, word1, word2)});  // Insert
    }
    
    return ans;
}

int minDistance(string word1, string word2) {
    int n = word1.size(), m = word2.size();
    return solve(n - 1, m - 1, word1, word2);
}
```

## Approach 1.3 : Brute + (Index from end) + Shifting of index for conversion to tabulation

- Time complexity : O(3^N)
- Space complexity : O(Rec)

```cpp
int solve(int i, int j, string& word1, string& word2){
    //Base case
    if(i == 0)return j;
    if(j == 0)return i;
    
    int ans = 0;
    if(word1[i - 1] == word2[j - 1]){
        //Char matched no operations needed and the string is reduced
        ans += solve(i - 1, j - 1, word1, word2);
    }else{
        //We can do three types of operations
        ans += 1 + min({solve(i - 1, j, word1, word2),  // Delete
            solve(i - 1, j - 1, word1, word2),  // Replace
            solve(i, j - 1, word1, word2)});  // Insert
    }
    
    return ans;
}

int minDistance(string word1, string word2) {
    int n = word1.size(), m = word2.size();
    return solve(n, m, word1, word2);
}   
```

## Approach 2.1 : Memoization + (Index from start)

- Time complexity : O(N^2)
- Space complexity : O(N^2)

```cpp
int solve(int i, int j, string &word1, string &word2, vector<vector<int>> &dp){
    if(j >= word2.size())return word1.size() - i;
    if(i >= word1.size())return word2.size() - j;
    
    if(dp[i][j] != -1)return dp[i][j];
    
    int ans = 0;
    if(word1[i] == word2[j]){
        ans += solve(i + 1, j + 1, word1, word2, dp);
    }else{
        // 1 op + min (all three rec ops)
        ans += 1 + min({solve(i + 1, j, word1, word2, dp),  // Delete
                        solve(i + 1, j + 1, word1, word2, dp),  // Replace
                        solve(i, j + 1, word1, word2, dp)});  // Insert
    }
    
    return dp[i][j] = ans;
}

int minDistance(string word1, string word2) {
    int n = word1.size(), m = word2.size();
    vector<vector<int>> dp (n + 1, vector<int> (m + 1, -1));
    return solve(0, 0, word1, word2, dp);
}
```

## Approach 2.2 : Memoization + (Index from end) + Shifting of index for conversion to tabulation

- Time complexity : O(N^2)
- Space complexity : O(N^2)

```cpp
nt solve(int i, int j, string& word1, string& word2, vector<vector<int>> &dp){
    //Base case
    if(i == 0)return j;
    if(j == 0)return i;

    if(dp[i][j] != -1)return dp[i][j];
    
    int ans = 0;
    if(word1[i - 1] == word2[j - 1]){
        //Char matched no operations needed and the string is reduced
        ans += solve(i - 1, j - 1, word1, word2, dp);
    }else{
        //We can do three types of operations
        ans += 1 + min({solve(i - 1, j, word1, word2, dp),  // Delete
            solve(i - 1, j - 1, word1, word2, dp),  // Replace
            solve(i, j - 1, word1, word2, dp)});  // Insert
    }

    return dp[i][j] = ans;
}

int minDistance(string word1, string word2) {
    int n = word1.size(), m = word2.size();
    vector<vector<int>> dp (n + 1, vector<int> (m + 1, -1));
    return solve(n, m, word1, word2, dp);
}
```

## Approach 3 : Tabulation 

- Time complexity : O(N^2)
- Space complexity : O(N^2)

```cpp
int minDistance(string word1, string word2) {
    int n = word1.size(), m = word2.size();
    vector<vector<int>> dp (n + 1, vector<int> (m + 1, 0));
    
    for(int i = 0 ; i < n + 1 ; i++)dp[i][0] = i;
    for(int j = 0 ; j < m + 1 ; j++)dp[0][j] = j;
    
    for(int i = 1 ; i < n + 1 ; i++){
        for(int j = 1 ; j < m + 1 ; j++){
            if(word1[i - 1] == word2[j - 1]){
                dp[i][j] = dp[i - 1][j - 1];
            }else{
                dp[i][j] = 1 + min({dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]});
            }
        }
    }
    
    return dp[n][m];
}
```