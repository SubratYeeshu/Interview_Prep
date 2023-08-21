# Find Minimum in Rotated Sorted Array

## Problem statement

- Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become: 
- [4,5,6,7,0,1,2] if it was rotated 4 times.
- [0,1,2,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2].Given the sorted rotated array nums of unique elements, return the minimum element of this array.

- Binary Search (Sorted Rotated pattern)

## Approach 1 : Binary Search (Pivot finding)

Time complexity : O(LogN) 
Space complexity : O(1)

```cpp
int findMin(vector<int>& temp) {
    int n = temp.size();
    int low = 0, high = n - 1;
    if(n == 1)return temp[0];
    if(n == 2)return min(temp[0], temp[1]);
    
    
    while(low <= high){
        int mid = (low + high) / 2;
        if(temp[low] < temp[high])return temp[low];  // Sorted
        if(mid < high && temp[mid] > temp[mid + 1])return temp[mid + 1];
        else if(mid > low && temp[mid] < temp[mid - 1])return temp[mid];
        else if(temp[low] >= temp[mid])high = mid - 1;
        else low = mid + 1;
    }
    
    return 0;
}
```

## Approach 2 : Binary Search (Pivot finding)

Time complexity : O(LogN) 
Space complexity : O(1)

```cpp
int findMin(vector<int>& nums) {
    int start = 0;
    int end = nums.size() - 1;
    // Finding smallest as pivot
    while(start < end){
        int mid = start + (end - start) /2;
        if(nums[mid] < nums[end]){
            end = mid;
        }else{
            start = mid + 1;
        }
    }
    return start;
}
```

## Approach 3 : Binary Search (Part Elimination)

Time complexity : O(LogN) 
Space complexity : O(1)

```cpp
int findMin(vector<int>& nums) {
    int low = 0, high = nums.size() - 1, ans = INT_MAX;
    if(nums[0] < nums[high])return nums[0];
    
    // Eliminate the sorted part
    while(low <= high){
        int mid = low + (high - low) /2;
        
        if(nums[low] <= nums[mid]){ 
            ans = min(ans, nums[low]);
            low = mid + 1;
        }else{
            ans = min(ans, nums[mid]);
            high = mid - 1;
        }
    }
    return ans;
}
```
## Approach 4.1 : Binary Search (Graph Representation)

Time complexity : O(LogN) 
Space complexity : O(1)

```cpp
int findMin(vector<int>& nums) {
    int low = 0, high = nums.size() - 1, ans = INT_MAX;
    if(nums[0] < nums[high])return nums[0];
    if(nums.size() == 1)return nums[0];
    while(low <= high){
        int mid = low + (high - low) /2;
        
        if(nums[0] <= nums[mid]){
            low = mid + 1;
        }else{
            ans = min(ans, nums[mid]);
            high = mid - 1;
        }
    }
    return ans;
}
```
## Approach 4.2 : Binary Search (Graph Representation)

Time complexity : O(LogN) 
Space complexity : O(1)

```cpp
int findMin(vector<int>& nums) {
    int low = 0, high = nums.size() - 1, ans = INT_MAX;
    if(nums[0] < nums[high])return nums[0];
    
    while(low < high){
        int mid = low + (high - low) /2;
        
        if(nums[0] <= nums[mid]){
            low = mid + 1;
        }else{
            high = mid;
        }
    }
    return nums[high];
}
```
