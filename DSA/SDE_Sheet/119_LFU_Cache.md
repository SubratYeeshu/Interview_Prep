# LFU Cache

## Problem statement

Design and implement a data structure for a Least Frequently Used (LFU) cache.
Implement the LFUCache class:
- LFUCache(int capacity) Initializes the object with the capacity of the data structure.
- int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
- void put(int key, int value) Update the value of the key if present, or inserts the key if not already present. When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item. For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with the smallest use counter is the least frequently used key. When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). The use counter for a key in the cache is incremented either a get or put operation is called on it. The functions get and put must each run in O(1) average time complexity.

## Approach 1 : 

Time complexity : O(1) 
Space complexity : O(N)

```cpp
class LFUCache {
public:
    int cap, minFreq = 0, size = 0;
    unordered_map<int, list<int>> frequencyList;
    unordered_map<int, list<int>::iterator> address;
    unordered_map<int, pair<int, int>> frequency;

    LFUCache(int capacity) {
        cap = capacity;
        minFreq = 0, size = 0;
    }

    int get(int key) {
        if (address.find(key) == address.end()) return -1;
        int keyFreq = frequency[key].second;
        int value = frequency[key].first;
        
        // Deletion and updation
        frequencyList[keyFreq].erase(address[key]);
        frequency[key].second++;
        frequencyList[frequency[key].second].push_front(key);
        address[key] = frequencyList[frequency[key].second].begin();
        if (frequencyList[minFreq].size() == 0) minFreq++;
        
        return value;
    }

    void put(int key, int value) {
        if (cap <= 0) return;
        
        // Element already present update and refresh the cache
        if (address.find(key) != address.end()) {
            frequency[key].first = value;
            get(key);
            return;
        }
        
        // Insert new element, Delete LRU's LFU
        if (size == cap) {
            int minFreqBack = frequencyList[minFreq].back();
            address.erase(minFreqBack);
            frequency.erase(minFreqBack);
            frequencyList[minFreq].pop_back();
            size--;
        }
        
        minFreq = 1;
        frequencyList[minFreq].push_front(key);
        address[key] = frequencyList[minFreq].begin();
        frequency[key].second = 1;
        frequency[key].first = value;
        size++;
    }
};
```