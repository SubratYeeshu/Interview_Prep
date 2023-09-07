# Word Ladder II

## Problem statement

Given two distinct words startWord and targetWord, and a list denoting wordList of unique words of equal lengths. Find all shortest transformation sequence(s) from startWord to targetWord. You can return them in any order possible.
Keep the following conditions in mind:

- A word can only consist of lowercase characters.
- Only one letter can be changed in each transformation.
- Each transformed word must exist in the wordList including the targetWord.
- startWord may or may not be part of the wordList.
- Return an empty list if there is no such transformation sequence.

## Approach 1 : BFS

```cpp
vector<vector<string>> findSequences(string beginWord, string endWord, vector<string>& wordList) {
    vector<vector<string>> res;
    
    unordered_set<string> st (wordList.begin(), wordList.end());
    
    vector<string> usedOnLevel;
    usedOnLevel.push_back(beginWord);
    
    queue<vector<string>> q;
    q.push({beginWord});
    
    while(!q.empty()){
        int size = q.size();
        
        for(int i = 0 ; i < size ; i++){
            vector<string> temp = q.front();
            q.pop();
            
            // Manipulate the last word to check whether other combination is possible or not
            string word = temp.back();
            
            // Storing the result
            if(word == endWord){
                if(res.size() == 0)res.push_back(temp);
                else if(res[0].size() == temp.size())res.push_back(temp);
            }
            
            // Manipulating and increasing the length of sequence by 1 each time, pushing the sequence to queue
            for (int i = 0; i < word.size(); i++){   
                string tempWord = word;
                for (char c = 'a'; c <= 'z'; c++){
                    tempWord[i] = c;
                    if (st.find(tempWord) != st.end()){ 
                        temp.push_back(tempWord);
                        q.push(temp);
                        usedOnLevel.push_back(tempWord);
                        temp.pop_back();
                    }
                }
            }
        }
        
        // On exploration of each level remove the words for avoiding TLE (repeatition of word exploration)
        for(auto it : usedOnLevel)st.erase(it);
        usedOnLevel.clear();
    }
    
    return res;
}
```