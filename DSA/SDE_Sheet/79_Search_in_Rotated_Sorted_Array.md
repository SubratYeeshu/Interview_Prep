# Search in Rotated Sorted Array

## Problem statement

- There is an integer array nums sorted in ascending order (with distinct values). Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2]. Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.


## Approach 1 : Binary Search (Finding Pivot)

Time complexity : O(3LogN) 
Space complexity : O(1)

```cpp
// Fiding pivot as smallest element 
int findPivot(vector<int>&nums){
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

int getPivot(vector<int> &temp){
    int low = 0, high = temp.size() - 1;
    while(low <= high){
        int mid = (low + high) / 2;

        if(mid < high && temp[mid] > temp[mid + 1])return mid + 1;
        else if(mid > low && temp[mid] < temp[mid - 1])return mid;
        else if(temp[low] >= temp[mid])high = mid - 1;
        else low = mid + 1;
    }

    return 0;
}

int binarySearch(vector<int>nums, int start, int end, int target){
    while(start <= end){
        int mid = start + (end - start) / 2;
        if(nums[mid] == target)return mid;
        else if(nums[mid] < target)start = mid + 1;
        else end = mid - 1;
    }
    return -1;
}

int search(vector<int>& nums, int target) {
    int pivot = findPivot(nums);
    // int pivot = getPivot(nums);
    int left = binarySearch(nums, 0, pivot - 1, target);
    return left != -1 ? left : binarySearch(nums, pivot, nums.size() -1, target);
    
    return 0;
}
```

## Approach 2 : Binary Search (Single Pass)

Time complexity : O(LogN) 
Space complexity : O(1)

```cpp
int search(vector<int>& nums, int target) {
    int low = 0, high = nums.size() - 1;
    
    while(low <= high){
        int mid = (low + high) / 2;
        
        if(nums[mid] == target)return mid;
        
        // Left half is sorted
        if(nums[low] <= nums[mid]){
            if(nums[low] <= target && nums[mid] >= target)high = mid - 1;  // Target lies in between
            else low = mid + 1;
        }else{
            // Right half is sorted
            if(nums[mid] <= target && target <= nums[high])low = mid + 1;
            else high = mid - 1;
        }
    }
    return -1;
}
```