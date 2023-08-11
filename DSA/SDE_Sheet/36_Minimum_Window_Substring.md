# Minimum Window Substring

## Problem statement

- Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "". The testcases will be generated such that the answer is unique.

## Approach 1.1 : Sliding window  (Variable size sliding window template)

Time complexity : O(N)  
Space complexity : O(N)

```cpp
string minWindow(string s, string t) {
    int count = 0, i = 0, j = 0, mini = INT_MAX, start = 0, n = s.size(), m = t.size();
    unordered_map<char,int> mp;
    if(m > n)return "";
    for(int i = 0 ; i < t.size() ; i++)mp[t[i]]++;
    count = mp.size();
    
    while(j < n){
        // Calculation
        if(mp.find(s[j]) != mp.end()){
            mp[s[j]]--;
            if(mp[s[j]] == 0){
                count--;
            }
        }
        
        // Window minimization
        if(count == 0){
            while(count == 0){

                if(j - i + 1 < mini){
                    mini = j - i + 1;
                    start = i;
                }

                if(mp.find(s[i]) != mp.end()){
                    mp[s[i]]++;
                    if(mp[s[i]] > 0){
                        count++;
                    }
                }
                i++;
            }
            j++;
        }else if(count > 0)j++;
        
    }

    if(mini == INT_MAX)return "";
    return s.substr(start,mini);
}
```

## Approach 1.2 : Sliding window  (Variable size sliding window template) - Using vector

Time complexity : O(N)  
Space complexity : O(N)

```cpp
/*

    -> Slightly faster for gfg platform

*/
string smallestWindow (string s, string p){
    int n = s.size(), m = p.size();
    if(m > n) return "-1";
    int i = 0, j = 0, start = 0, mn = INT_MAX, count=0;
    vector<int>mp(26, 0);
    
    // Store the frequency of p and the size of unique elements
    for(int i = 0 ; i < m ; i++){
        if(mp[p[i] - 'a'] == 0){
            count++;
        }
        mp[p[i] - 'a']++;
    }
    
    while(j < n){
        mp[s[j] - 'a']--;
        if(mp[s[j] - 'a'] == 0)count--;
    
        // Window minimization
        if(count == 0){
            while(count == 0){
                    mp[s[i] - 'a']++;
                    if(mp[s[i] - 'a'] > 0)count++;
                    if(mn > j - i + 1){
                        mn = j - i + 1;
                        start = i;
                    }
                    i++;
                }
                j++;
        }else if(count > 0)j++;
    }

    if(mn == INT_MAX) return "-1";
    return s.substr(start, mn);
}
```

```cpp
// -> Sliding window generic template (variable size)
// -> mp.size() can be replace to count and condition can differ with differnt problems
while(j < n){
	*** calculation ***

    if(mp.size() < k)j++;
    else if(mp.size() == k){
        mx = max(mx, j - i + 1);
        j++;
    }else if(mp.size() > k){
        while(mp.size() > k){
            mp[s[i]]--;
            if(mp[s[i]] == 0)mp.erase(mp[s[i]]);
            i++;
        }
        j++;
    }
}