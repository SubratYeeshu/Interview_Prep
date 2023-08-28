# Longest Palindromic Substring

## Problem statement

- Given a string s, return the longest palindromic substring in s.

## Approach 1 : Brute

Time complexity : O(N^3) 
Space complexity : O(N)

```cpp
bool isPalindrome(string temp){
    int i = 0, j = temp.size() - 1;
    
    while(i < j){
        if(temp[i] == temp[j]){
            i++;
            j--;
        }else return false;
    }
    
    return true;
}

string longestPalindrome(string s) {
    int n = s.size(), maxi = INT_MIN;
    string res = "";
    
    for(int i = 0 ; i < n ; i++){
        string temp = "";
        for(int j = i ; j < n ; j++){
            temp += s[j];
            if(isPalindrome(temp)){
                if(j - i + 1 > maxi){
                    res = temp;
                    maxi = j - i + 1;
                }
            }
        }
    }
    return res;
}
```

## Approach 2 : Better

Time complexity : O(N^2) 
Space complexity : O(N)

```cpp
string longestPalindrome(string s) {
    int n = s.size();
    string res = "";
    
    for(int i = 0 ; i < n ; i++){
        int l = i - 1, r = i + 1;
        
        // Odd length
        while(l >= 0 && r <= n - 1){
            if(s[l] == s[r]){
                l--;
                r++;
            }else break;
        }
        
        if(r - l - 1 > res.size())res = s.substr(l + 1, r - l - 1);
        
        // Odd length
        l = i - 1, r = i;
        while(l >= 0 && r <= n - 1){
            if(s[l] == s[r]){
                l--;
                r++;
            }else break;
        }
        
        if(r - l - 1 > res.size())res = s.substr(l + 1, r - l - 1);
    }
    
    return res;
}
```

## Approach 3 : DP

Time complexity : O(N^2) 
Space complexity : O(N^2) 

```cpp
string longestPalindrome(string s) {
    int n = s.size();
    //Initially all rows and coloumns are set to 0
    vector<vector<bool>> dp(n, vector<bool>(n, false));
    int maxDiff = -1, start = 0, end = 0;
    int cnt = 0;
    for(int diff = 0 ; diff < s.size() ; diff++){
        for(int i = 0, j = diff ; j < s.size() ; i++,j++){
            if(diff == 0){
                //First diagonal 
                dp[i][j] = true;
            }
            else if(diff == 1){
                //Second diagonal
                if(s[i]==s[j]){
                    dp[i][j] = true;
                }else{
                    dp[i][j] = false;
                }
            }else{
                //Check outer then inner from DP Table
                if(s[i] == s[j]){
                    dp[i][j] = dp[i+1][j-1];
                }else{
                    dp[i][j] = false;
                }
            }
            if(dp[i][j]){
                if(diff > maxDiff){
                    maxDiff = diff;
                    start = i;
                    end = j;
                }
            }
        }
    }
    return s.substr(start, end - start + 1);
}
```