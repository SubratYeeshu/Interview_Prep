# Smallest Sum Subarray

## Problem statement 

- Given an array arr[] of N integers. Find the contiguous sub-array(containing at least one number) which has the minimum sum and return its sum.

## Approach 1 : Sliding window

Time complexity : O(N^2)  
Space complexity : O(1)

```cpp
// Applying sliding window with window size as 1 to n 
```

## Approach 2 : Kadanes Algorithm

Time complexity : O(N)  
Space complexity : O(1)

```cpp
int smallestSumSubarray(vector<int>& nums){
    int min_sum = INT_MAX, curr_sum = 0;
    for(int i = 0 ; i < nums.size() ; i++){
        curr_sum += nums[i];
        min_sum = min(min_sum, curr_sum);
        if(curr_sum > 0)curr_sum = 0;
    }
    return min_sum;
}
```

