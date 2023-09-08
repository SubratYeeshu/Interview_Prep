# Longest Divisible Subset

## Problem statement

Given a set of distinct positive integers nums, return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:

- answer[i] % answer[j] == 0, or
- answer[j] % answer[i] == 0

If there are multiple solutions, return any of them.

## Approach 1 : Sorting + LIS (Variation)

- Time complexity : O(NLogN)
- Space complexity : O(N)

```cpp
/*
    
    -> After sorting
    -> Longest divisible subsequence

*/
vector<int> largestDivisibleSubset(vector<int>& nums) {
    sort(nums.begin(), nums.end());
    
    int n = nums.size(), maxi = 1, lastIdx = 0;
    vector<int> dp (n + 1, 1);
    vector<int> trackParent (n, 0);
    for(int i = 0 ; i < n ; i++)trackParent[i] = i;

    for(int index = 0 ; index < n ; index++){
        for(int prev = 0 ; prev < index ; prev++){
            if(nums[index] % nums[prev] == 0){
                if(dp[index] < 1 + dp[prev]){
                    // Whenever dp[index] is updated
                    dp[index] = 1 + dp[prev];
                    trackParent[index] = prev;
                }
            }
        }
        if(maxi < dp[index]){
            maxi = dp[index];
            lastIdx = index;
        }
    }

    vector<int> temp;
    temp.push_back(nums[lastIdx]);
    while(lastIdx != trackParent[lastIdx]){
        lastIdx = trackParent[lastIdx];
        temp.push_back(nums[lastIdx]);
    } 
    reverse(temp.begin(), temp.end());

    return temp;
}
```