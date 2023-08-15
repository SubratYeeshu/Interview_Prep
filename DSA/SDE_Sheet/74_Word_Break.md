# Word Break

## Problem statement

- Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words. Note that the same word in the dictionary may be reused multiple times in the segmentation. 

## Approach 1.1 : Backtrack (Prefix Suffix without index)

Time complexity : O(2<sup>N</sup>) Exponential
Space complexity : O(N)

```cpp
bool solve(string str, unordered_set<string> &st){
    if(str.size() == 0){
        return true;
    }
    
    for(int i = 0 ; i < str.size() ; i++){
        string prefix = str.substr(0, i + 1);
        string suffix = str.substr(i + 1);
        
        if(st.find(prefix) != st.end()){
            if(solve(suffix, st))
            return true;
        }
    }
    
    return false;
}
```

## Approach 1.2 : Backtract (Using Index)

Time complexity : O(2<sup>N</sup>) Exponential
Space complexity : O(N)

```cpp
bool solve(int index, string &str, unordered_set<string> &st){
    if(index >= str.size()){
        return true;
    }
    
    string temp = "";
    for(int i = index ; i < str.size() ; i++){
        temp += str[i];  // or string temp = str.substr(index, i - index + 1);
        if(st.find(temp) != st.end()){
            if(solve(i + 1, str, st))
            return true;
        }
    }
    
    return false;
}
```

## Approach 2 : DP

Time complexity : O(N^3) 
Space complexity : O(N)

```cpp
bool solve(int index, string &str, unordered_set<string> &st, vector<int> &dp){
    if(index >= str.size()){
        return true;
    }
    if(dp[index] != -1)return dp[index];
    
    for(int i = index ; i < str.size() ; i++){
        string temp = str.substr(index, i - index + 1);
        if(st.find(temp) != st.end()){
            if(solve(i + 1, str, st, dp))
            return dp[index] = true;
        }
    }
    
    return dp[index] = false;
}

int wordBreak(string A, vector<string> &B) {
    vector<int> dp (1101, -1);
    unordered_set<string> st;
    for(auto i : B)st.insert(i);
    
    return solve(0, A, st, dp);
}
```