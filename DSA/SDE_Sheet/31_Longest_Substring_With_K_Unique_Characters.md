# Longest K unique characters substring 

## Problem statement

- Given a string you need to print the size of the longest possible substring that has exactly K unique characters. If there is no possible substring then print -1.

## Approach 1

Time complexity : O(N)  
Space complexity : O(k)

```cpp
int longestKSubstr(string s, int k) {
    int n = s.size(), mx = -1, i = 0, j = 0;
    unordered_map<char, int> mp;
    
    while(j < n){
        mp[s[j]]++;
        
        if(mp.size() < k){
            j++;
        }else if(mp.size() == k){
            mx = max(mx, j - i + 1);
            j++;
        }else if(mp.size() > k){
            while(mp.size() > k){
                mp[s[i]]--;
                if(mp[s[i]] == 0)mp.erase(s[i]);
                i++;
            }
            j++;
        }
    }
    return mx;   
}
```

[Problem Link](https://practice.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1)

