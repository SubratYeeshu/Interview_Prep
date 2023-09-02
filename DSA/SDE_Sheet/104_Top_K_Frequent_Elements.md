# Top K Frequent Elements

## Problem statement 

Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

## Approach 1 : Brute 

- Time complexity : O(NLogN) 
- Space complexity : O(N)

```cpp
vector<int> topKFrequent(vector<int>& nums, int k) {
    unordered_map<int, int> freq;
    vector<pair<int, int>> vec;
    vector<int>res;
    
    for(auto i : nums)freq[i]++;
    
    // Top k frequent elements in the heap
    for(auto it : freq)vec.push_back({it.second, it.first});
    
    // Generating the answer
    sort(vec.begin(), vec.end(), greater<pair<int, int>>());
    for(int i = 0 ; i < k ; i++)res.push_back(vec[i].second);
    
    return res;
}
```

## Approach 2 : Optimal

- Time complexity : O(NLogK) min heap, O(NLog(N - K)) max heap
- Space complexity : O(N)

```cpp
vector<int> topKFrequent(vector<int>& nums, int k) {
    unordered_map<int, int> freq;
    vector<int>res;
    
    // Min heap
    priority_queue<pair<int, int>, vector<pair<int,int>>, greater<pair<int,int>>>pq;
    for(auto i : nums)freq[i]++;
    
    // Top k frequent elements in the heap
    for(auto it : freq){
        pq.push({it.second, it.first});
        if(pq.size() > k)pq.pop();
    }
    
    // Generating the answer
    while(!pq.empty()){
        res.push_back(pq.top().second);
        pq.pop();
    }
    
    return res;
}
```