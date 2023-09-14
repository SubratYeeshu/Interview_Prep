# Partition Equal Subset Sum

## Problem statement

Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.

## Approach 1.1 : DP + (Index from front)

- Time Complexity : O(N^2)
- Space Complexity : O(N^2 + Rec)

```cpp
// Sum must be even such that sum in boths subsets is Sum / 2
bool solve(int index, vector<int> &nums, int target, vector<vector<int>> &dp){
    if(target == 0)return true;
    if(index >= nums.size() || target < 0)return false;
    if(dp[index][target] != -1)return dp[index][target];

    bool pick = solve(index + 1, nums, target - nums[index], dp);
    bool notPick = solve(index + 1, nums, target, dp);

    return dp[index][target] = pick || notPick;
}

bool canPartition(vector<int>& nums){
    int n = nums.size();
    int totalSum = accumulate(nums.begin(), nums.end(), 0);
    if(totalSum & 1)return false;
    int target = totalSum / 2;
    vector<vector<int>> dp (n + 1, vector<int> (target + 1, -1));
    return solve(0, nums, target, dp);
}
```

## Approach 1.2 : DP + (Index from end)

- Time Complexity : O(N^2)
- Space Complexity : O(N^2 + Rec)

```cpp
// Sum must be even such that sum in boths subsets is Sum / 2
bool solve(int index, vector<int> &nums, int target, vector<vector<int>> &dp){
    if(target == 0)return true;
    if(index < 0 || target < 0)return false;
    if(index == 0)return (nums[index] == target);

    if(dp[index][target] != -1)return dp[index][target];

    bool pick = solve(index - 1, nums, target - nums[index], dp);
    bool notPick = solve(index - 1, nums, target, dp);

    return dp[index][target] = pick || notPick;
}

bool canPartition(vector<int>& nums){
    int n = nums.size();
    int totalSum = accumulate(nums.begin(), nums.end(), 0);
    if(totalSum & 1)return false;
    int target = totalSum / 2;
    vector<vector<int>> dp (n + 1, vector<int> (target + 1, -1));
    return solve(n - 1, nums, target, dp);
}
```

## Approach 2 : Tabulation

- Time Complexity : O(N^2)
- Space Complexity : O(N^2)

```cpp
bool canPartition(int n, vector<int> &arr){

    int totSum=0;

    for(int i = 0 ; i < n ; i++){
        totSum+= arr[i];
    }

    if (totSum % 2 == 1) return false;

    else{
        int k = totSum / 2;
        vector<vector<bool>> dp(n,vector<bool>(k + 1, false));

        for(int i = 0 ; i < n ; i++)dp[i][0] = true;
        

        if(arr[0] <= k)dp[0][arr[0]] = true;

        for(int ind = 1 ; ind < n ; ind++){
            for(int target = 1 ; target <= k ; target++){

                bool notTaken = dp[ind - 1][target];

                bool taken = false;
                if(arr[ind] <= target)taken = dp[ind - 1][target - arr[ind]];
                dp[ind][target] = notTaken||taken;
            }
        }
        return dp[n-1][k];
    }
}
```

## Simlar Quesion : 

- Partitions with Given Difference : [Link](https://practice.geeksforgeeks.org/problems/partitions-with-given-difference/1)