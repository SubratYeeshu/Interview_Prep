# Find Peak Element

## Problem statement 

- A peak element is an element that is strictly greater than its neighbors. Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks. You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array. You must write an algorithm that runs in O(log n) time.

- Binary Search (Sorted Rotated pattern)

## Approach 1 : Linear Search

Time complexity : O(N) 
Space complexity : O(1)

```cpp
int findPeakElement(vector<int>& arr) {
    int n = arr.size();
    // Lies between index 1 and n - 2
    for(int i = 0 ; i < n ; i++){
        if((i != 0 && (arr[i] > arr[i - 1])) && 
        (i != n - 1 && (arr[i] > arr[i + 1])))return i;
    }

    // Lies on index 0 or n - 1
    if(arr[0] > arr[n - 1])return 0;
    else return n - 1;
}
```

## Approach 2 : Binary Search

Time complexity : O(LogN) 
Space complexity : O(1)

```cpp  
int findPeakElement(vector<int>& nums) {
    int n = nums.size();
    int low = 0, high = n - 1, idx = -1, ans = INT_MAX;
    
    // Edge case
    if(n == 0)return 0;
    if(n == 1)return 0;
    if(nums[0] > nums[1])return 0;
    if(nums[n - 1] > nums[n - 2])return n - 1;
    
    // Standard binary search
    while(low <= high){
        int mid = (low + high) / 2;
        
        // Biotonic / Peak point
        if(nums[mid] > nums[mid + 1] && nums[mid] > nums[mid - 1])return mid;
        
        if(nums[mid] > nums[mid + 1])high = mid - 1;  // Decreasing curve
        else low = mid + 1;  // Increasing curve or bottom point handling for case like 1 5 1 2 1 we can go anyside
    }
    return -1;    
}
```