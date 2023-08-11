#  Max Consecutive Ones

## Problem statement 

- Given a binary array nums, return the maximum number of consecutive 1's in the array.

## Approach 1 : 

Time complexity : O(N) 
Space complexity : O(1)

```cpp
int findMaxConsecutiveOnes(vector<int>& nums) {
    int n = nums.size(), count = 0, maxi = 0;
    
    for(int i = 0 ; i < n ; i++){
        if(nums[i] == 1)count++;
        else{
            maxi = max(maxi, count);
            count = 0;
        }
    }
    maxi = max(maxi, count);
    
    return maxi;
}
```