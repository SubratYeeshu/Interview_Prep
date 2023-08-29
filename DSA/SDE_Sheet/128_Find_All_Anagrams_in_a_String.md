# Find All Anagrams in a String

## Problem statement

- Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order. An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

## Approach 1 : Brute

Time complexity : O(N^M) 
Space complexity : O(N)

```cpp
bool isAnagram(string a, string b){
    int n = a.size(),  m = b.size();
    if(n != m)return false;
    
    vector<int> f1 (26, 0), f2 (26, 0);
    
    for(auto i : a)f1[i - 'a']++;
    for(auto j : b)f2[j - 'a']++;
    
    for(int i = 0 ; i < 26 ; i++){
        if(f1[i] != f2[i])return false;
    }
    
    return true;
}


vector<int> findAnagrams(string s, string p) {
    int n = s.size(), m = p.size();
    vector<int>v;
    for(int i = 0 ; i < n ; i++){
        string temp = s.substr(i, m);
        if(isAnagram(temp, p)){
            v.push_back(i);
        }
    }
    return v;
}
```

## Approach 2.1 : Sliding Window

Time complexity : O(N) 
Space complexity : O(N)

```cpp
vector<int> findAnagrams(string s, string p) {
    int stringSize = s.size(), patternSize = p.size();
    vector<int>windowStringHash(26,0), patternHash(26,0), ansIdx;
    
    //Edge case if patternSize is greater than stringSize
    if(patternSize > stringSize) return {};
    
    int i = 0, j = 0;
    while(j < stringSize){
        if(j - i + 1 <= patternSize){
            patternHash[p[j] - 'a']++;
            windowStringHash[s[j] - 'a']++;
        }else if(j - i + 1 > patternSize){
            if(windowStringHash == patternHash)ansIdx.push_back(i);
            windowStringHash[s[j] - 'a']++; 
            windowStringHash[s[i] - 'a']--; 
            i++;
        }
        j++;
    }
    // Last window
    if(windowStringHash == patternHash)ansIdx.push_back(i);
    
    return ansIdx;
}
```

## Approach 2.2 : Sliding Window

Time complexity : O(N) 
Space complexity : O(N)

```cpp
vector<int> findAnagrams(string s, string p) {
    int stringSize = s.size(), patternSize = p.size();
    vector<int>windowStringHash(26,0), patternHash(26,0), ansIdx;
    
    //Edge case if patternSize is greater than stringSize
    if(patternSize > stringSize) return {};
    
    //First Window
    for(int i = 0 ; i < patternSize ; i++){
        patternHash[p[i] - 'a']++;
        windowStringHash[s[i] - 'a']++;
    }
    
    //Checking for first window 
    if(patternHash == windowStringHash) ansIdx.push_back(0);
  
    
    //Checking for rest of the window
    for(int i = patternSize ; i < stringSize ; i++){
        windowStringHash[s[i - patternSize] - 'a']--; //Removing 
        windowStringHash[s[i] - 'a']++; //Aquiring
        
        if(windowStringHash == patternHash)ansIdx.push_back(i - patternSize + 1);
    }
    
    return ansIdx;
}
```

## Approach 3 : Rolling Hash (Weak Hash) - Wrong Answer Due to collision 

Time complexity : O(N) 
Space complexity : O(1)

```cpp
vector<int> findAnagrams(string s, string p) {
    int stringSize = s.size(), patternSize = p.size(), patternHash = 0, windowHash = 0;
    vector<int> ansIdx;
    
    //Edge case if patternSize is greater than stringSize
    if(patternSize > stringSize) return {};
    
    int i = 0, j = 0;
    while(j < stringSize){
        if(j - i + 1 <= patternSize){
            patternHash += (p[j] - 'i' + 1); 
            windowHash += (s[j] - 'i' + 1); 
        }else if(j - i + 1 > patternSize){
            if(windowHash == patternHash)ansIdx.push_back(i);
            windowHash += (s[j] - 'i' + 1) - (s[i] - 'i' + 1);
            i++;
        }
        j++;
    }
    
    // Last window
    if(windowHash == patternHash)ansIdx.push_back(i);
    
    return ansIdx;
}
```