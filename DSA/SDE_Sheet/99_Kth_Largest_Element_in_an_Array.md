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

## Approach 2 : Priority queue (Min Heap) 

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

## Approach 3 : Quick Select

Time complexity : O(NLogN) 
Space complexity : O(1)

```cpp
//     int partition(vector<int> &nums, int low, int high){
//         int i = low, j = high;  
//         int pivot = nums[low];
//         while(i < j){
//             while(i <= high && nums[i] >= pivot)i++;
//             while(j >= low && nums[j] < pivot)j--;
//             if(i < j)swap(nums[i], nums[j]);
//         }

//         swap(nums[low], nums[j]);
//         return j;
//     }
    
// Faster 
int partition(vector<int>& nums, int left, int right) {
    int pivot = nums[left], l = left + 1, r = right;
    while (l <= r) {
        if (nums[l] < pivot && nums[r] > pivot)swap(nums[l++], nums[r--]);
        if (nums[l] >= pivot)l++;
        if (nums[r] <= pivot)r--;
    }
    swap(nums[left], nums[r]);
    return r;
}


int findKthLargest(vector<int> &nums, int k) {
    int n = nums.size();
    
    int left = 0, right = nums.size() - 1;
    while (true) {
        int pos = partition(nums, left, right);
        if (pos == k - 1) return nums[pos];
        else if (pos < k - 1) left = pos + 1;
        else right = pos - 1;
    }
}
```