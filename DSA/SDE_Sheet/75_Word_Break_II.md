# Word Break II

## Problem statement

- Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.

## Approach 1.1 : Backtrack (Prefix Suffix without index)

Time complexity : O(2<sup>N</sup>) Exponential
Space complexity : O(N)

```cpp
void solve(string s, unordered_set<string>set, string curr_ans, vector<string>&res){
    if(s.size() == 0){
        curr_ans.pop_back();
        res.push_back(curr_ans);
        return;
    }
    for(int i = 0 ; i < s.size() ; i++){
        string prefix = s.substr(0, i + 1);
        string suffix = s.substr(i + 1);
        
        if(set.find(prefix) != set.end()){
            solve(suffix, set, curr_ans + prefix + " ", res);
        }
    }
    return;
        
}

vector<string> wordBreak(string s, vector<string>& wordDict) {
    unordered_set<string> set;
    vector<string> res;
    for(auto str : wordDict)set.insert(str);
    solve(s, set, "", res);
    return res;
}
```

## Approach 1.2 : Backtrack (Using Index)

Time complexity : O(2<sup>N</sup>) Exponential
Space complexity : O(N)     

```cpp
void solve(int index, string s, unordered_set<string>set, string curr_ans, vector<string>&res){
    if(index >= s.size()){
        curr_ans.pop_back();
        res.push_back(curr_ans);
        return;
    }
    for(int i = index ; i < s.size() ; i++){
        string temp = s.substr(index, i - index + 1);
        
        if(set.find(temp) != set.end()){
            solve(i + 1, s, set, curr_ans + temp + " ", res);
        }
    }
    return;
        
}

vector<string> wordBreak(string s, vector<string>& wordDict) {
    unordered_set<string>set;
    vector<string>res;
    for(auto str : wordDict)set.insert(str);
    solve(0, s, set, "", res);
    return res;
}
```