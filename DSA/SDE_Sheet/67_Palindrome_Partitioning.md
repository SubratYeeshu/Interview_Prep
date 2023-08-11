# Palindrome Partitioning

## Problem statement - 

- Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.

## Approach 1.1 : Recursion

```cpp
bool isPalindrome(string s, int i, int j){
    while(i <= j)if(s[i++] != s[j--])return false;
    return true;
}

void solve(int index, vector<vector<string>> &res, vector<string> &ds, string s){
    if(index >= s.size()){
        res.push_back(ds);
        return;
    }
    
    for(int i = index ; i < s.size() ; i++){
        if(isPalindrome(s, index, i)){
            ds.push_back(s.substr(index, i - index + 1));
            solve(i + 1, res, ds, s);
            ds.pop_back();
        }
    }
}
vector<vector<string>> partition(string s) {
    vector<vector<string>> res;
    vector<string> ds;
    
    solve(0, res, ds, s);
            
    return res;
}
```


## Approach 1.2 : Recursion

```cpp
bool isPalindrome(string &temp) {
    int i = 0, j = temp.size() - 1;
    while (i <= j) {
        if (temp[i++] != temp[j--]) return false;
    }
    return true;
}

void solve(int index, vector<vector<string>> &res, vector<string> &ds, string s){
    if(index >= s.size()){
        res.push_back(ds);
        return;
    }
    
    string temp = "";
    for(int i = index ; i < s.size() ; i++){
        temp += s[i];
        if(isPalindrome(temp)){
            ds.push_back(temp);
            solve(i + 1, res, ds, s);
            ds.pop_back();
        }
    }
}

vector<vector<string>> partition(string s) {
    vector<vector<string>> res;
    vector<string> ds;
    
    solve(0, res, ds, s);
            
    return res;
}
```

## Approach 1.3 : Recursion (Most readable and easy)

```cpp

/*
    -> STL function substr()
        1> s.substr(index, size) exclusive of last 
        2> s.sustr(index) till last 
*/
bool isPalindrome(string &temp) {
    int i = 0, j = temp.size() - 1;
    while (i <= j) {
        if (temp[i++] != temp[j--]) return false;
    }
    return true;
}

void solve(vector<vector<string>> &res, vector<string> &ds, string s){
    if(s.size() == 0){
        res.push_back(ds);
        return;
    }
    
    for(int i = 0 ; i < s.size() ; i++){
        string prefix = s.substr(0, i + 1);
        string suffix = s.substr(i + 1);
        
        if(isPalindrome(prefix)){
            ds.push_back(prefix);
            solve(res, ds, suffix);
            ds.pop_back();
        }
    }
}
vector<vector<string>> partition(string s) {
    vector<vector<string>> res;
    vector<string> ds;
    
    solve(res, ds, s);
            
    return res;
}
```