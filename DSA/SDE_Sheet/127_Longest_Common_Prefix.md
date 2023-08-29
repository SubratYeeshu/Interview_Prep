# Longest Common Prefix

## Problem statement

- Write a function to find the longest common prefix string amongst an array of strings. If there is no common prefix, return an empty string "".

## Approach 1 : Brute

Time complexity : O(L \* N) 
Space complexity : O(1) 

```cpp
string longestCommonPrefix(vector<string>& strs) {
    int n = strs.size();
    string res = "";
    
    // Traversing on the first string
    for(int i = 0 ; i < strs[0].size() ; i++){
        char ch = strs[0][i];
        bool match = true;
        
        // For every other strings
        for(int j = 1 ; j < n ; j++){
            if(strs[j][i] != ch){
                match = false;
                break;
            }
        }
        
        if(match)res.push_back(ch);
        else break;
    }
    
    return res;
}
```

## Approach 2 : Sorting

Time complexity : O(NLogN) 
Space complexity : O(1) 

```cpp
string longestCommonPrefix(vector<string>& strs) {
    sort(strs.begin(), strs.end());
    string first = strs[0];
    string last = strs[strs.size() - 1];
    string res = "";
    
    for(int i = 0 ; i < last.size() ; i++){
        if(first[i] == last[i]){
            res += first[i];
        }else{
            break;
        }
    }
    return res;
}
```

## Trie

Time complexity : O(N) 
Space complexity : O(N)

```cpp

```