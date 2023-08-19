#  Search in Rotated Sorted Array II

## Problem statement 

- There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values). Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1] (0-indexed). For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4]. Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums. You must decrease the overall operation steps as much as possible. 

## Approach 1 : Binary Search (Single Pass)

Time complexity : O(LogN) 
Space complexity : O(1)

```cpp
bool search(vector<int>& nums, int target) {
    int low = 0, high = nums.size() - 1;

    while(low <= high){
        int mid = (low + high) / 2;

        if(nums[mid] == target)return true;
        
        // When all three are equal it is impossible to know where the target is so we shrink down the space
        if(nums[low] == nums[mid] && nums[mid] == nums[high]){
            low++; high--; continue;
        }
        
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
    return false;
}
```