# LRU Cache

## Problem statement

Design a data structure that follows the constraints of a Least Recently Used (LRU) cache. Implement the LRUCache class:
- LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
- int get(int key) Return the value of the key if the key exists, otherwise return -1.
- void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. - the number of keys exceeds the capacity from this operation, evict the least recently used key.
- The functions get and put must each run in O(1) average time complexity.

## Approach 1 : Brute

Time complexity : O(N) 
Space complexity : O(cap)

```cpp
/* 

    -> LRU at the front
    -> 2 Problems
        -> Eliminate shifting : After deleting we have to shift every elements - Fix : DLL (Changing the previous pointer while standing on any node for changing next pointer)
        -> Optimize searching : For finding a element we have to go find the element by iterating - Fix : Map<int, address/itr>

*/
vector<pair<int, int>> cache;
int cap;
int size;
LRUCache(int capacity) {
    this -> cap = capacity;
    this -> size = 0;
}

int get(int key) {
    for(int i = 0 ; i < cache.size() ; i++){
        if(cache[i].first == key){
            int val = cache[i].second;
            
            pair<int, int> temp = cache[i];
            cache.erase(cache.begin() + i);
            cache.push_back(temp);
            
            return val;
        }
    }
    return -1;
    
}

void put(int key, int value) {
    // If key is already present
    for(int i = 0 ; i < cache.size() ; i++){
        if(cache[i].first == key){
            cache[i].second = value;
            
            pair<int, int> temp = cache[i];
            cache.erase(cache.begin() + i);
            cache.push_back(temp);
            
            return;
        }
    }
    
    // If the key is not present
    if(cap == size){
        cache.erase(cache.begin());
        size--;
    }
            
    cache.push_back({key, value});
    size++;
    
}
```

## Approach 2 : List + Map KeyValue + Map KeyIterator

Time complexity : O(1) 
Space complexity : O(cap)

```cpp
/*
    
    -> We have to use three containers
    -> map<int, listIterator>, map<key, value>, list<int>
    -> Whenever a try to get a key, we put it in front of the list
    -> On the back of lru the LRU is present
    -> When we try to put the key in the lru
        -> We check whether it is present, if it is present, first we hit the get() then we update it
        -> If it is not present
            -> We check if space available then we put
            -> Else we erase the LRU then put
            
*/


unordered_map<int, int> mp;
unordered_map<int, list<int>::iterator> listIterator;
list<int> LRU;
int cap;
int size = 0;
LRUCache(int capacity) {
    this -> cap = capacity;
    this -> size = 0;
}

int get(int key) {
    if(mp.find(key) == mp.end())return -1;
    
    // Getting the iterator
    auto itr = listIterator[key];
    
    // Erasing from both LRU, and the iterator map
    LRU.erase(itr);
    listIterator.erase(key);
    
    // Pushing in front and updating the address / iterator
    LRU.push_front(key);
    listIterator[key] = LRU.begin();
    return mp[key];
}

void put(int key, int value) {
    if(mp.find(key) != mp.end()){
        get(key);
        mp[key] = value;
        return;
    }
    
    if(size == cap){
        // Erasing the LRU 
        mp.erase(LRU.back());
        listIterator.erase(LRU.back());
        LRU.pop_back();
        size--;
    }
    
    LRU.push_front(key);
    listIterator[key] = LRU.begin();
    mp[key] = value;
    size++;
    return;
}
```

## Approach 3 : Complete implementation

Time complexity : O(1) 
Space complexity : O(cap)

```cpp
class Node{
    public: 
    int key, val;
    Node *prev;
    Node *next;
    
    Node(int key, int value):key(key), val(value){};
};

// Dummy nodes
Node *head = new Node(-1, -1);
Node *tail = new Node(-1, -1);

// Node functions
    void addNode(Node *newNode) {
    Node *temp = head -> next;
    newNode -> next = temp;
    newNode -> prev = head;
    head -> next = newNode;
    temp -> prev = newNode;
}

void deleteNode(Node *delNode) {
    Node * delprev = delNode -> prev;
    Node * delnext = delNode -> next;
    delprev -> next = delnext;
    delnext -> prev = delprev;
}

// Map node vs address
unordered_map<int, Node*> mp;
int size, cap;

// LRU initialization
LRUCache(int capacity) {
    cap = capacity;
    head -> next = tail;
    tail -> prev = head;
}

int get(int key_) {
    if (mp.find(key_) != mp.end()) {
        Node *resNode = mp[key_];
        int res = resNode -> val;
        mp.erase(key_);
        deleteNode(resNode);
        addNode(resNode);
        mp[key_] = head -> next;
        return res;
    }
    return -1;
}

void put(int key_, int value) {
    if (mp.find(key_) != mp.end()) {
        Node *temp = mp[key_];
        mp.erase(key_);
        deleteNode(temp);
    }
    if (mp.size() == cap) {
        mp.erase(tail -> prev -> key);
        deleteNode(tail -> prev);
    }

    addNode(new Node(key_, value));
    mp[key_] = head -> next;
}
```