# String to Integeer (ATOI)

## Problem statement 

Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function). The algorithm for myAtoi(string s) is as follows:

- Read in and ignore any leading whitespace.

- Check if the next character (if not already at the end of the string) is '-' or '+'. Read this character in if it is either. This determines if the final result is negative or positive respectively. Assume the result is positive if neither is present.

- Read in next the characters until the next non-digit character or the end of the input is reached. The rest of the string is ignored.

- Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read, then the integer is 0. Change the sign as necessary (from step 2). If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then clamp the integer so that it remains in the range. Specifically, integers less than -231 should be clamped to -231, and integers greater than 231 - 1 should be clamped to 231 - 1.

- Return the integer as the final result.

## Approach 1 : Implementation

Time complexity : O(N) 
Space complexity : O(1) 

```cpp
string processString(string &s){
    removeLeadingWhitespace(s);
    string sign = getSign(s);
    getNumber(s);
    removeLeadingZeroes(s);
    return sign;
}

void removeLeadingWhitespace(string &s){
    int i = 0;
    while(s[i] == ' ')i++;
    s = s.substr(i);
}

string getSign(string &s){
    if(s[0] == '-'){
        s = s.substr(1);
        return "negative";
    }
    if(s[0] == '+'){
        s = s.substr(1);
        return "positive";
    }
    
    return "positive";
}

void getNumber(string &s){
    int i = 0;
    
    while(i < s.size() && s[i] - '0' <= 9 && s[i] - '0' >= 0)i++;
    s = s.substr(0, i);
}

void removeLeadingZeroes(string &s){
    int i = 0;
    while(i < s.size() && s[i] == '0')i++;
    s = s.substr(i);
}

void clampInteger(long long &res, string sign){
    if(sign == "positive"){
        res = INT_MAX;
    }
    
    if(sign == "negative"){
        res = INT_MIN;
    }
}

int myAtoi(string s) {
    string sign = processString(s);
    
    long long res = 0;
    for(int i = 0 ; i < s.size() ; i++){
        int num = s[i] - '0';
        res = res * 10 + num;
        
        if(res > INT_MAX){
            clampInteger(res, sign);
            return (int)res;
        }
    }
    
    if(sign == "negative")res *= -1;
    return (int)res;
}
```