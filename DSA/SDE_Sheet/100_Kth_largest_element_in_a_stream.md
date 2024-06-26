# Kth largest element in a stream

## Problem statement

Given an input stream arr[] of n integers. Find the Kth largest element (not Kth largest unique element) after insertion of each element in the stream and if the Kth largest element doesn't exist, the answer will be -1 for that insertion.  

Return a list of size n after all insertions.

## Approach 1 : Priority Queue

- Time complexity : O(NLogK) 
- Space complexity : O()

```cpp
vector<int> kthLargest(int k, int arr[], int n) {
    vector<int> res;
    priority_queue<int, vector<int>, greater<int>> pq;
    
    for(int i = 0 ; i < n ; i++){
        if(pq.size() < k){
            pq.push(arr[i]);
            if(pq.size() < k)res.push_back(-1);
            else res.push_back(pq.top());
        }else if(pq.top() < arr[i]){
            pq.pop();
            pq.push(arr[i]);
            res.push_back(pq.top());
        }else{
            res.push_back(pq.top());
        }
        
    }
    
    return res;
}
```

## Leetcode Version

```cpp
int size = 0;
priority_queue<int, vector<int>, greater<int>>pq;
KthLargest(int k, vector<int>& nums) {
    size = k;
    for(int i = 0 ; i < nums.size() ; i++){
        pq.push(nums[i]);
        if(pq.size() > k)pq.pop();
    }
}

int add(int val) {
    pq.push(val);
    if(pq.size() > size)pq.pop();
    return pq.top();
}
```