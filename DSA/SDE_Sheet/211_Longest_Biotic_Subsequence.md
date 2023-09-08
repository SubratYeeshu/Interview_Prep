# Longest Bitonic subsequence

## Problem statement

Given an array of positive integers. Find the maximum length of Bitonic subsequence. 
A subsequence of array is called Bitonic if it is first strictly increasing, then strictly decreasing.

## Approach 1 : LIS (2 Pass Find Mountain)

- Time complexity : O(N^2)
- Space complexity : O(N)

```cpp
int LongestBitonicSequence(vector<int>nums){
    int n = nums.size();
    vector<int>dp1(n, 1);
    vector<int>dp2(n, 1);
    vector<int>lbs(n, 0);
    
    //Front LIS
    for(int index = 0 ; index < n ; index++){
        for(int prev = 0 ; prev < index ; prev++){
            if(nums[prev] < nums[index])
                dp1[index] = max(dp1[index], 1 + dp1[prev]);
        } 
    }
    
    //Back LIS
    for(int index = n - 1 ; index >= 0 ; index--){
        for(int prev = n - 1 ; prev > index ; prev--){
            if(nums[prev] < nums[index])
                dp2[index] = max(dp2[index], 1 + dp2[prev]);
        } 
    }
    
    //LBS
    int maxi = INT_MIN;
    for(int i = 0 ; i < n ; i++){
        lbs[i] = dp1[i] + dp2[i] - 1;
        maxi = max(maxi, lbs[i]);
    }
    
    return maxi;
}
```