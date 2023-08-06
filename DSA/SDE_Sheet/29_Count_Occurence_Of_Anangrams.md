# Count occurence of anagrams

## Problem statement

- Given a word pat and a text txt. Return the count of the occurrences of anagrams of the word in the text.

## Approach 1.1 (Sliding Window with map)

Time complexity : O(N)
Space complexity : O(N)

```cpp
int search(string pat, string txt) {
    int i=0, j=0, n = txt.size(), k = pat.size(), ans = 0;
    unordered_map<char,int>mp;
    
    if(k > n)return 0;
    
    for(int i = 0 ; i< k ; i++) mp[pat[i]]++;
    int count_distinct = mp.size();
    
    while(j < n){
        if(mp.find(txt[j]) != mp.end()){
            mp[txt[j]]--;
            if(mp[txt[j]] == 0)
                count_distinct--;
        }
        
        if(j - i + 1 < k)j++;
        else if(j - i + 1 == k){
            if(!count_distinct)ans++;
    
            // Rebuilding the pattern map - 
            if(mp.find(txt[i]) != mp.end()){
                mp[txt[i]]++;
                if(mp[txt[i]])count_distinct++;
            }
            i++;
            j++;
        }
    }
    return ans; 
}
```

## Approach 2.1 (Sliding Window - Fixed Size Template 1)

Time complexity : O(N)
Space complexity : O(26)

```cpp
int search(string pat, string txt) {
        int n = txt.length(), k = pat.length(), ans = 0, i = 0 , j = 0;; 
        vector<int>hashPat(26,0), hashTxt(26,0);
        
        for(int i = 0 ; i < k ; i++)hashPat[pat[i]-'a']++;
        
        while(j < n){ 
            hashTxt[txt[j]-'a']++;
            
            if(j - i + 1 == k){
                if(hashTxt == hashPat)ans++;
                hashTxt[txt[i] - 'a']--;
                i++;
            }
            j++;
        }
    return ans;
}
```

## Approach 2.2 (Sliding Window - Fixed Size Template 2)

Time complexity : O(N)
Space complexity : O(26)

```cpp
int search(string p, string s) {
    int n = s.size(), k = p.size(), cnt = 0;
    vector<int>windowStringHash(26,0), patternHash(26,0);
    
    //Edge case if patternSize is greater than stringSize
    if(k > n) return 0;
    
    //First Window
    for(int i = 0 ; i < k ; i++){
        patternHash[p[i] - 'a']++;
        windowStringHash[s[i] - 'a']++;
    }
    
    //Checking for first window 
    if(patternHash == windowStringHash) cnt++;

    //Checking for rest of the window
    for(int i = k ; i < n ; i++){
        windowStringHash[s[i - k] - 'a']--; //Removing 
        windowStringHash[s[i] - 'a']++; //Aquiring
        
        if(windowStringHash == patternHash) cnt++;
    }
    
    return cnt;
}
```