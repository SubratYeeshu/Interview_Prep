## Count vowel strings in in range
## https://leetcode.com/problems/count-vowel-strings-in-ranges/

```cpp
class Solution {
public:
    bool isVowel(char ch){
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
    
    vector<int> vowelStrings(vector<string>& words, vector<vector<int>>& queries) {
        int n = words.size();
        
        vector<int> v (n, 0), res;
        
        for(int i = 0 ; i < words.size() ; i++){
            string currWord = words[i];
            if(isVowel(currWord[0]) && isVowel(currWord[currWord.size() - 1]))v[i]++;
            if(i!=0)v[i] = v[i - 1] + v[i];
        }
        
        for(auto i : queries){
            int s = i[0], e = i[1];
            if(s == 0) res.push_back(v[e]);
            else res.push_back(v[e] - v[s - 1]);
        }
        
        return res;
    }
};
```