# Reverse Words in a String

## Problem statement 

Given an input string s, reverse the order of the words. A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space. Return a string of the words in reverse order concatenated by a single space. Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.

## Approach 1 : Using utility (Stringstream)

- Time complexity : O(N) 
- Space complexity : O(N)

```cpp
string reverseWords(string s) {
    stringstream ss(s);
    string token = ""; 
    string result;
    
    while(ss >> token){  // By default seperates using " "
        result = token + " " + result;
    }
    
    result.pop_back();
    return result;
}
```

## Approach 2 : Implementation + Linear space

- Time complexity : O(N) 
- Space complexity : O(N)

```cpp
string reverseWords(string s) {
    string result = "";
    int i = 0, j = 0, n = s.size();
    
    while(true){
        // Skipping spaces
        while(i < n && s[i] == ' ')i++;
        if(i >= n)break;
        j = i + 1;
        
        // Getting strings
        while(j < n && s[j] != ' ')j++;
        string temp = s.substr(i, j - i);
        result = temp + (result.size() ? " " : "") + result;
        i = j + 1;
    }
    
    return result;
}
```

## Approach 3 : Implementation + Constant space

- Time complexity : O(N) 
- Space complexity : O(1)

```cpp
string reverseWords(string s) {
    int n = s.size(), i = 0, r = 0, l = 0;
    reverse(s.begin(), s.end());
    
    while(i < n){
        // Removing extra space / String compression
        while(i < n && s[i] != ' ')s[r++] = s[i++];
        
        if(l < r){
            reverse(s.begin() + l, s.begin() + r);
            s[r] = ' ';
            r++;
            l = r;
        }        
        i++;
    }
    
    return s.substr(0, r - 1);
}
```