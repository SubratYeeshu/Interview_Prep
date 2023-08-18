# Trie 

- Trie is a data structure used for storing words in node structure

## Time complexity 
- Insert function: O(k)
- Search function: O(k)
- StartsWith function: O(p)

## Space complexity
- O(N) Where N is total number of nodes

## Uses
- Trie is used because it is the fastest for auto-complete suggestions, even in the worst case.
- Browser History: Web browsers keep track of the history of websites visited by the user So when the prefix of a previously visited URL is written in the address bar the user would be given suggestions of the website to visit.
- Spell Checkers/Auto-correct: It is a 3-step process that includes : Checking for the word in the data dictionary, Generating potential suggestions, Sorting the suggestions with higher priority on top.
- Longest Prefix Matching Algorithm(Maximum Prefix Length Match)
- Disadvantage : The main disadvantage of the trie is that it takes a lot of memory to store all the strings

## Code snippet : With root node as global
```cpp
class Trie {
public:
    // Node structure of a trie has three info -> isEnd, vector of child nodes, and info at current node
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
    Node* root;
    Trie() {
        root = new Node('#');
    }
    
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
};
```

## Code snippet : With root passed as arguement
```cpp
class trie{
    public :
    bool isend;
    vector< trie* > children;
    
    trie( ){
        children = vector< trie* >(26,NULL);
        isend=false;   
    }
};


void insert( trie* root, string word)
{
    trie* temp= root;
    for( auto x : word){
        int index= x-'a';
        
        if( temp->children[index]==NULL)
            temp->children[index]= new trie();
        temp=temp->children[index];
    }
        temp->isend= true;
    
}

    bool search( trie* root, string word)
{
    trie* temp= root;
    for( auto x : word){
        int index= x-'a';
        
        if( temp->children[index]==NULL)
                return false;
        temp=temp->children[index];
    }
    if(temp->isend)
        return true;
        return false;
    
}


// Driver function
int main(){
    trie* root = new trie();
    for( auto words : wordDict)insert(root,words);
}

```
