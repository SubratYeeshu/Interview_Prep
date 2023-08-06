# Longest Substring with At Least K Repeating Characters  

## Problem statement

- Given a string s and an integer k, return the length of the longest substring of s such that the frequency of each character in this substring is greater than or equal to k, if no such substring exists, return 0.

## Approach 1 : Recursive  

Time complexity : O(N \* LogN)  
Auxiliary space : O(N)

```cpp
int longestSubstring(string s, int k) {
    int n = s.size(), left = 0, right = 0;
    unordered_map<char, int> mp;
    for(auto i : s)mp[i]++;
    
    for(int i = 0 ; i < n ; i++){
        
        if(mp[s[i]] < k){
            left = longestSubstring(s.substr(0, i), k);
            right = longestSubstring(s.substr(i + 1), k);
            break;
        }
        if(i == n - 1)return n;
    }
    return max(left, right);
}
```

## Approach 2 : Iterative

Time complexity : O(N^2)  
Auxiliary space : O(26)

```cpp
int longestSubstring(string s, int k) {
    int ans = 0, n = s.size();
    if(n < k) return 0; 
    
    for(int l = 0; l < n ; ++l){
        vector<int> countMap(26, 0); 
        for(int r = l ; r < n; r++){ 
            countMap[s[r] - 'a']++;
            bool hasKFrequencyCharacters = 1; 
            for(int i = 0 ; i < 26 ; i++){
                if(countMap[i] > 0 && countMap[i] < k){ 
                    hasKFrequencyCharacters = 0;
                }
            }
            if(hasKFrequencyCharacters){
                ans = max(ans, r-l+1);
            }
        }
    }
    return ans;
}
```