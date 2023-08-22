# Kth Largest Element in an Array

## Problem statement

- Given an integer array nums and an integer k, return the kth largest element in the array. Note that it is the kth largest element in the sorted order, not the kth distinct element. Can you solve it without sorting?

## Approach 1 : Sorting 

Time complexity : O(NLogN) 
Space complexity : O(1)

```cpp
int findKthLargest(vector<int>& nums, int k) {
    sort(nums.begin(), nums.end(), greater<int> ());
    return nums[k - 1];
}
```

## Approach 2 : 

Time complexity : O(NLogK) min heap, O(NLog(N - K)) max heap
Space complexity : O(K) min heap, O(N - K) max heap

```cpp
int findKthLargest(vector<int>& nums, int k) {
    priority_queue<int, vector<int>, greater <int>> pq;
    
    for(int i = 0 ; i < nums.size() ; i++){
        pq.push(nums[i]);
        if(pq.size() > k)pq.pop();
    }
    return pq.top();
}
```