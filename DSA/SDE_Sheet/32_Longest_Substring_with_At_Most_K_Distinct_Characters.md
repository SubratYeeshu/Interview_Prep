# Longest Substring with At Most K Distinct Characters 

## Problem statement

- You are given a string 'str' and an integer ‘K’. Your task is to find the length of the largest substring with at most ‘K’ distinct characters.

## Approach 1

Time complexity : O(N)  
Space complexity : O(k)

```cpp
#include<bits/stdc++.h>
int kDistinctChars(int k, string &s){
    int n = s.size(), mx = -1, i = 0, j = 0;
    unordered_map<char, int> mp;
    
    while(j < n){
        mp[s[j]]++;
        
        if(mp.size() < k){
            mx = max(mx,j-i+1);  // Only difference bw atmost k char and exactly k char longest substring
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

[Problem Link](https://www.codingninjas.com/studio/problems/distinct-characters_2221410?leftPanelTab=0)

