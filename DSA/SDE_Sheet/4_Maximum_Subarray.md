# Maximum Subarray

## Problem Statement

Given an integer array nums, find the subarray with the largest sum, and return its sum.

## Approach 1 : Brute (Generate All Subarray)

- Time complexity : O(N^3)  
- Space complexity : O(1)

## Approach 2 : Brute (Generate All Subarray with optimization)

- Time complexity : O(N^2)  
- Space complexity : O(1)

## Approach 3 : Kadanes Algorithm 

- Time complexity : O(N)  
- Space complexity : O(1)

```cpp
int maxSubArray(vector<int>& nums) {
    int max_sum = INT_MIN, curr_sum = 0;
    for(int i = 0 ; i < nums.size() ; i++){
        curr_sum += nums[i];
        max_sum = max(max_sum, curr_sum);
        if(curr_sum < 0)curr_sum = 0;
    }
    return max_sum;
}
```

## Printing the subarray

- Time complexity : O(N)  
- Space complexity : O(1)

```cpp
int maxSubArray(vector<int>& nums) {
    int max_sum = INT_MIN, curr_sum = 0;
    int ans_start_index = -1, ans_end_index = -1, start_index = 0; 
    
    for(int i = 0 ; i < nums.size() ; i++){
        if(curr_sum == 0)start_index = i;
        
        curr_sum += nums[i];
        if(curr_sum > max_sum){
            max_sum = curr_sum;
            ans_start_index = start_index;
            ans_end_index = i;
        }
        if(curr_sum < 0)curr_sum = 0;
    }
    cout << ans_start_index << " " << ans_end_index << endl;
    return max_sum;
}
```