## Longest Consequtive Sequence
## https://leetcode.com/problems/longest-consecutive-sequence/

```cpp
class Solution {
public:
    /*
    
        -> Create 2D vector for frequency of each character 
        -> Minimize 
    
    */
    vector<string> commonChars(vector<string>& words) {
        vector<vector<int>> freq (words.size(), vector<int> (26, 0));
        vector<int> commonMin(26, INT_MAX);
        vector<string> ans;
        int i = 0;
        for(auto str : words){
            for(auto ch : str)freq[i][ch - 'a']++;
            i++;
        }
        
        
        for(int i = 0 ; i < freq.size() ; i++){
            for(int j = 0 ; j < 26 ; j++){
                commonMin[j] = min(commonMin[j], freq[i][j]);
            }
        }
        
        for(int i = 0 ; i < commonMin.size() ; i++){            
            if(commonMin[i] > 0){
                // Occurence > 1
                while(commonMin[i]--){
                    char ch = i  + 'a';
                    string t = "";
                    t.push_back(ch);
                    ans.push_back(t);
                }
            }
        }
        return ans;
    }
};
```