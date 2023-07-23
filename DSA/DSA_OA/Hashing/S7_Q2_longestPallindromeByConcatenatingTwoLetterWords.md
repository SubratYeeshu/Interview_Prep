## Longest Palindrome by Concatenating Two Letter Words
## https://leetcode.com/problems/longest-palindrome-by-concatenating-two-letter-words/

```cpp
class Solution {
public:
    int longestPalindrome(vector<string>& words) {
        unordered_map<string, int> mp;
        int countOfPairs = 0;
        bool middleSingleElement = false;
        for(auto &word : words)mp[word]++;
        
        // Checking if its reverese is present or not
        for(auto word : mp){
            string firstWord = word.first;
            string temp = firstWord;
            reverse(firstWord.begin(), firstWord.end());
            
            if(firstWord == temp){
                countOfPairs += word.second / 2;
                if(word.second & 1) middleSingleElement = true;
            }else{
                if(mp.count(firstWord))
                    countOfPairs += min(word.second, mp[firstWord]), mp.erase(firstWord);
            }
        }
        int lenOfString = countOfPairs * 4;
        if(middleSingleElement)lenOfString += 2;
        return lenOfString;
    }
};
```