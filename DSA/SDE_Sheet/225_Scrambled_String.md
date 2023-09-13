# Scrambled String

## Problem statement

Given two strings S1 and S2 of equal length, the task is to determine if S2 is a scrambled form of S1.

Scrambled string: Given string str, we can represent it as a binary tree by partitioning it into two non-empty substrings recursively.
Below is one possible representation of str = coder:
 
To scramble the string, we may choose any non-leaf node and swap its two children. 
Suppose, we choose the node co and swap its two children, it produces a scrambled string ocder.
Similarly, if we continue to swap the children of nodes der and er, it produces a scrambled string ocred.

Note: Scrambled string is not the same as an Anagram.

Print "Yes" if S2 is a scrambled form of S1 otherwise print "No".

## Approach 1 : DP

Time Complexity : O(N^3) ~ Inner Loop Contibutes a N
Space Complexity : O(N^2 + Rec)

```cpp
// Recursively break at each index from i = 1 -> n - 1, and check if ther are scrambeled or not
// if(boolWithoutSwap || boolSwap)return true
unordered_map<string, bool> mp;
bool solve(string s1, string s2){
    int n = s1.size(), m = s2.size();
    if(n != m)return false;
    if(s1 == s2)return true;
    
    string key = s1 + " " + s2;
    if(mp.find(key) != mp.end())return mp[key];
    
    for(int k = 1 ; k < n ; k++){
        // Ex : Without swap -> gr|eat and rg|eat
        bool withoutSwap = (solve(s1.substr(0, k), s2.substr(0, k)) // First part
                            && solve(s1.substr(k), s2.substr(k)));  // Second part
        if(withoutSwap)return true;
                    
        // Ex : With swap -> gr|eat  rge|at                  
        bool withSwap = (solve(s1.substr(0, k), s2.substr(n - k)) 
                            && solve(s1.substr(k), s2.substr(0, n - k)));
        if(withSwap)return true;
    }
    
    return mp[key] = false;
}


bool isScramble(string s1, string s2){
    return solve(s1, s2);
}
```