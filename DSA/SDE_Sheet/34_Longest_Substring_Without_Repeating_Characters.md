# Longest Substring Without Repeating Characters

## Problem statement

- Given a string s, find the length of the longest substring without repeating characters.

## Approach 1.1 : Sliding window  

Time complexity : O(N)  
Space complexity : O(N)

```cpp
int lengthOfLongestSubstring(string s) {
    int n = s.size(), mx = 0, i = 0, j = 0;
    unordered_map<char, int> mp;

    while(j < n){
        mp[s[j]]++;

        // This part will never be used
        if(mp.size() > j - i + 1){
            j++;
        }else if(mp.size() == j - i + 1){
            mx = max(mx, j - i + 1);
            j++;
        }else if(mp.size() < j - i + 1){  // Character repeated
            while(mp.size() < j - i + 1){
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

## Approach 1.2 : Sliding window  

Time complexity : O(N) - Slightly faster 
Space complexity : O(256)

```cpp
int lengthOfLongestSubstring(string s) {
    int freq[256] = {0};
    int l = 0, r = 0, ans = 0, n = s.size();
    while(r < n){
        freq[s[r]]++;
        while(freq[s[r]] > 1){
            freq[s[l]]--;
            l++;
        }
        ans = max(ans,r - l + 1);
        r++;
    }
    return ans;
}
```

## Approach 1.3 : Sliding window  

Time complexity : O(N) 
Space complexity : O(N)

```cpp
int lengthOfLongestSubstring(string s) {
    unordered_map<int, int> mp;
    int ans = 0, j = 0;
    for(int i = 0; i < s.length(); i++){
        // Maximizing the last occurence if j is already ahead than dont update j
        if(mp.find(s[i]) != mp.end())j = max(mp[s[i]] + 1, j);
        
        // Last occurence 
        mp[s[i]] = i;
        
        // Window maximization
        ans = max(ans, i - j + 1);
    }
    return ans;
}
```


