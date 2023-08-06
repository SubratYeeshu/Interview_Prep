# Count occurence of anagrams

## Problem statement

- Given a word pat and a text txt. Return the count of the occurrences of anagrams of the word in the text.

## Approach 1 (Sliding Window with map)

Time complexity : O(N)
Space complexity : O(N)

```cpp
int search(string pat, string txt) {
    int k = pat.size(), n = txt.size(), i = 0, j = 0;
    unordered_map<char,int> freq;
    
    for(int i = 0 ; i < k ; i++)freq[pat[i]]++;
    int count = freq.size(), ans = 0;
    
    while(j < n){
        if(freq.count(txt[j])){
            freq[txt[j]]--;
            if(freq[txt[j]] == 0)count--;
        }
        
        if(j - i + 1 < k)j++;
        else if(j - i + 1 == k){
            if(count == 0)ans++;
            
            // Rebuilding the pattern map
            if(freq.count(txt[i])){
                freq[txt[i]]++;
                if(freq[txt[i]] == 1)count++;
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
    
    // Edge case if patternSize is greater than stringSize
    if(k > n) return 0;
    
    // First Window
    for(int i = 0 ; i < k ; i++){
        patternHash[p[i] - 'a']++;
        windowStringHash[s[i] - 'a']++;
    }
    
    // Checking for first window 
    if(patternHash == windowStringHash) cnt++;

    // Checking for rest of the window
    for(int i = k ; i < n ; i++){
        windowStringHash[s[i - k] - 'a']--; //Removing 
        windowStringHash[s[i] - 'a']++; //Aquiring
        
        if(windowStringHash == patternHash) cnt++;
    }
    
    return cnt;
}
```