# Roman To Integer

## Problem statement 

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

- I can be placed before V (5) and X (10) to make 4 and 9. 

- -X can be placed before L (50) and C (100) to make 40 and 90. 

- C can be placed before D (500) and M (1000) to make 400 and 900.

Given a roman numeral, convert it to an integer.

## Approach 1 : Map

- Time complexity : O(N) 
- Space complexity : O(N) 

```cpp
int romanToInt(string s) {
    int n = s.size();
    unordered_map<char, int> mp = {
        { 'I', 1 },
        { 'V' , 5 },
        { 'X' , 10 },
        { 'L' , 50 },
        { 'C' , 100 },
        { 'D' , 500 },
        { 'M' , 1000 }
    };
    int res = 0;
        
    for(int i = 0 ; i < n ; i++){
        if(i + 1 < n && mp[s[i]] < mp[s[i + 1]])res -= mp[s[i]];
        else res += mp[s[i]];
    }
    return res;       
}
```