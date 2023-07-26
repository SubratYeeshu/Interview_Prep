# Sort Colors

## Problem Statement

- Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue. We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively. You must solve this problem without using the library's sort function.

## Using Dutch National Flag Algorithm

Time complexity : O(N)  
Space complexity : O(1)

```cpp
/*
    
    -> DNF Algorithm
    -> Maintain three pointers low, mid and high
    -> Check for three conditions
    -> Every number on or before low will be 0 and every number on or after high will be 2
    -> We use mid for traversal
    -> low will be responsible for 0, mid for 1 and high for 2

*/
void sortColors(vector<int>& nums) {
    int low = 0, mid = 0, high = nums.size() - 1;
    
    while(mid <= high){
        if(nums[mid] == 0){
            swap(nums[low++], nums[mid++]);
        }else if(nums[mid] == 1){
            mid++;
        }else if(nums[mid] == 2){
            // high-- because we dont know whats comming 0 or 1
            swap(nums[mid], nums[high--]);
        }
    }
}
```