# Word Break

## Problem statement

- Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words. Note that the same word in the dictionary may be reused multiple times in the segmentation. 

## Approach 1.1 : Backtrack (Prefix Suffix without index)

Time complexity : O(2<sup>N</sup>) Exponential
Space complexity : O(N)

```cpp
bool solve(string str, unordered_set<string> &st){
    if(str.size() == 0){
        return true;
    }
    
    for(int i = 0 ; i < str.size() ; i++){
        string prefix = str.substr(0, i + 1);
        string suffix = str.substr(i + 1);
        
        if(st.find(prefix) != st.end()){
            if(solve(suffix, st))
            return true;
        }
    }
    
    return false;
}
```

## Approach 1.2 : DP (Prefix Suffix without index)

Time complexity : O(N^2 \* M) - N^2 for subproblems recursion, M for substring complexity, Total O(N^3)
Space complexity : O(N)

```cpp
unordered_map<string, bool> dp;
bool solve(string str, unordered_set<string> &st){
    if(str.size() == 0){
        return true;
    }
    if(dp.find(str) != dp.end())return dp[str];
    
    for(int i = 0 ; i < str.size() ; i++){
        string prefix = str.substr(0, i + 1);
        string suffix = str.substr(i + 1);
        
        if(st.find(prefix) != st.end()){
            if(solve(suffix, st))
            return dp[str] = true;
        }
    }
    
    return dp[str] = false;
}
```

## Approach 2.1 : Backtrack (Using Index)

Time complexity : O(2<sup>N</sup>) Exponential
Space complexity : O(N)

```cpp
bool solve(int index, string &str, unordered_set<string> &st){
    if(index >= str.size()){
        return true;
    }
    
    string temp = "";
    for(int i = index ; i < str.size() ; i++){
        temp += str[i];  // or string temp = str.substr(index, i - index + 1);
        if(st.find(temp) != st.end()){
            if(solve(i + 1, str, st))
            return true;
        }
    }
    
    return false;
}
```

## Approach 2.2 : DP (Using Index)

Time complexity : O(N^2 \* M) 
Space complexity : O(N)

```cpp
bool solve(int index, string &str, unordered_set<string> &st, vector<int> &dp){
    if(index >= str.size()){
        return true;
    }
    if(dp[index] != -1)return dp[index];
    
    for(int i = index ; i < str.size() ; i++){
        string temp = str.substr(index, i - index + 1);
        if(st.find(temp) != st.end()){
            if(solve(i + 1, str, st, dp))
            return dp[index] = true;
        }
    }
    
    return dp[index] = false;
}

int wordBreak(string A, vector<string> &B) {
    vector<int> dp (1101, -1);
    unordered_set<string> st;
    for(auto i : B)st.insert(i);
    
    return solve(0, A, st, dp);
}
```

## Approach 3 : Trie instead of set

Time complexity: O(N^2 \* M)
Space complexity: O(N \* M)

```cpp
class Node{
    public:
    
    bool isEnd = false;
    vector<Node*> children;
    char ch;
    
    Node(char ch){
        children.resize(26, NULL);
        this -> ch = ch;
    }
};

Node* root = new Node('#');

void insert(string word) {
    Node* rootCopy = root;
    for(int i = 0 ; i < word.size() ; i++){
        if(rootCopy -> children[word[i] - 'a'] == NULL){
            // New Node forming and connecting
            Node* toAdd = new Node(word[i] - 'a');
            rootCopy -> children[word[i] - 'a'] = toAdd;
            rootCopy = rootCopy -> children[word[i] - 'a'];
        }else{
            // Already existing prefix then simply traverse to the nearest node
            rootCopy = rootCopy -> children[word[i] - 'a'];
        }
    }
    // When the whole word is connected than turn on isEnd of the current node to mark the end of word
    rootCopy -> isEnd = true;
    
}

bool search(string word) {
    Node* rootCopy = root;
    for(int i = 0 ; i < word.size() ; i++){
        if(rootCopy -> children[word[i] - 'a'] == NULL){
            // If the character does not exists
            return false;
        }else{
            // Keep on traversing
            rootCopy = rootCopy->children[word[i] - 'a'];
        }
    }
    if(rootCopy->isEnd)return true;
    return false;
}

bool startsWith(string prefix) {
    Node* rootCopy = root;
    for(int i = 0 ; i < prefix.size() ; i++){
        if(rootCopy -> children[prefix[i] - 'a'] == NULL){
            // If the character does not exists
            return false;
        }else{
            // Keep on traversing
            rootCopy = rootCopy->children[prefix[i] - 'a'];
        }
    }
    return true;
}

// Solve function
unordered_map<string, bool> dp;
bool solve(string str, Node* trie){
    if(str.size() == 0){
        return true;
    }
    if(dp.find(str) != dp.end())return dp[str];
    
    for(int i = 0 ; i < str.size() ; i++){
        string prefix = str.substr(0, i + 1);
        string suffix = str.substr(i + 1);
        
        if(search(prefix)){
            if(solve(suffix, trie))
            return dp[str] = true;
        }
    }
    
    return dp[str] = false;
}

bool wordBreak(string s, vector<string>& wordDict) {
    for(auto word : wordDict)insert(word);        
    return solve(s, root);
}
```