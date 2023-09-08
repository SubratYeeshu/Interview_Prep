# Number of Longest Increasing Subsequence

## Problem statement

Given an integer array nums, return the number of longest increasing subsequences.

Notice that the sequence has to be strictly increasing.

## Approach 1 : LIS + Count

- Time complexity : O(N^2)
- Space complexity : O(N)

```cpp
/*
    
    -> If on running the loop of prev, dp[index] == 1 + dp[prev] again and again then increase count
    -> Because it means that there can be more subsequence formed of same length using different elements

*/
int findNumberOfLIS(vector<int>& nums) {
    int n = nums.size(), maxi = 0;
    vector<int> dp(n + 1, 1), count(n + 1, 1);
    
    for(int index = 0 ; index < n ; index++){
        for(int prev = 0 ; prev < index ; prev++){
            if(nums[prev] < nums[index]){
                if(1 + dp[prev] > dp[index]){  // On adding element to the sequence increases the LIS
                    dp[index] = 1 + dp[prev]; 
                    count[index] = count[prev];  // Populate the count / new element
                }else if(1 + dp[prev] == dp[index]){  // On adding element to the sequence produces same len LIS
                    count[index] += count[prev];
                }
            }
        }
        maxi = max(maxi, dp[index]);
    }
    
    int nos = 0;
    for(int i = 0 ; i < n ; i++){
        if(dp[i] == maxi){
            nos += count[i];
        }
    }
    
    return nos;        
}
```