# Word Ladder

## Problem statement

A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

- Every adjacent pair of words differs by a single letter.
- Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
- sk == endWord

Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

## Approach 1 : Graph + BFS

- Time complexity : O(N^2)  
- Auxiliary space : O(N)

```cpp
int ladderLength(string startWord, string targetWord, vector<string>& wordList) {
    unordered_set<string> st (wordList.begin(), wordList.end());
    queue<pair<string, int>> q;
    q.push({startWord, 1});
    
    while(!q.empty()){
        
        // Extracting each word present in the at each level and changing configuration of each to check 
        // whether the word exists in the word list or not
        
        string currWord = q.front().first;
        int level = q.front().second;
        q.pop();
        st.erase(currWord);  // Infinite loop, as the word can be used again and again

        // Operation on the stirng
        for(int i = 0 ; i < currWord.size() ; i++){
            string temp = currWord;
            for(char j = 'a' ; j <= 'z' ; j++){
                temp[i] = j;
                if(st.find(temp) != st.end()){
                    if(temp == targetWord)
                        return level + 1;

                    q.push({temp, level + 1});
                }
            }
        }
    }
    return 0;
}
```

## Approach 2 : Graph + BFS (Remove Parameter)

- Time complexity : O(N^2)  
- Auxiliary space : O(N)

```cpp
int ladderLength(string startWord, string targetWord, vector<string>& wordList) {
    unordered_set<string> st (wordList.begin(), wordList.end());
    queue<string> q;
    q.push(startWord);
    int level = 1;
    
    while(!q.empty()){
        int size = q.size();
        level++;
        
        for(int k = 0 ; k < size ; k++){
            string currWord = q.front();
            q.pop();
            st.erase(currWord);  

            // Operation on the stirng
            for(int i = 0 ; i < currWord.size() ; i++){
                string temp = currWord;
                for(char j = 'a' ; j <= 'z' ; j++){
                    temp[i] = j;
                    if(st.find(temp) != st.end()){
                        if(temp == targetWord)
                            return level;

                        q.push(temp);
                    }
                }
            }
        }
    }
    return 0;
}
```