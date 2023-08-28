# Integer To Roman

## Problem statement

- Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
- I can be placed before V (5) and X (10) to make 4 and 9. 
- X can be placed before L (50) and C (100) to make 40 and 90. 
- C can be placed before D (500) and M (1000) to make 400 and 900.
Given an integer, convert it to a roman numeral.

## Approach 1 : Map

Time complexity : O(Number of digits / Value) -> constant 
Space complexity : O(1) 

```cpp
string intToRoman(int num) {
    string res = "";
    int value[13] = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
    string roman[13] = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
    
    int i = 0;
    while(i < 13>){
        while(num >= value[i]){
            res += roman[i];
            num -= value[i];
        }
        i++;
    }
    
    return res;
}
```

## Approach 2 : Map

Time complexity : O(Number of digits / Value) -> constant 
Space complexity : O(1) 

```cpp
string intToRoman(int num) {
    string res = "";
    vector<string> ones {"","I","II","III","IV","V","VI","VII","VIII","IX"};
    vector<string> tens {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
    vector<string> hundred {"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"};
    vector<string> thousand {"","M","MM","MMM"};
    
    return thousand[num / 1000] + hundred[(num % 1000) / 100] + tens[(num % 100) / 10] + ones[num % 10];
}
```