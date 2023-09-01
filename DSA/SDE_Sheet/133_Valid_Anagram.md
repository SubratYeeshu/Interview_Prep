# Valid Anagram

## Problem statement
 
Given two strings s and t, return true if t is an anagram of s, and false otherwise. An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

## Approach 1 : Implementation

- Time complexity : O(N)
- Space complexity : O(N) 

```cpp
bool isAnagram(string s, string t) {
    vector<int> sHash(256, 0), tHash (256, 0);
    
    for(auto i : s)sHash[i - 'a']++;
    for(auto i : t)tHash[i - 'a']++;
    
    return sHash == tHash;
}
```