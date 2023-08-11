# Comvination Sum IV

## Problem statement

- Given an array of distinct integers nums and a target integer target, return the number of possible combinations that add up to target.

## Approach 1.1 : Recursion + Backtrack (Sum of results from all recursion call)

Time complexity : O(2<sup>N</sup>) 
Space complexity : O(rec + dp)

```cpp
int dp[1001];
int solve(int target, vector<int> &nums){
    if(target == 0)return 1;
    if(target < 0)return 0;
    if(dp[target] != -1)return dp[target];
    
    int ans = 0;
    for(int i = 0 ; i < nums.size() ; i++){
        ans += solve(target - nums[i], nums);
    }
    
    return dp[target] = ans;
}

int combinationSum4(vector<int>& nums, int target) {
    memset(dp, -1, sizeof(dp));
    return solve(target, nums);
}
```

## Approach 1.2 : Recursion + Backtrack 

Time complexity : O(2<sup>N</sup>) 
Space complexity : O(rec + dp)

```cpp
int dp[1001];
int solve(int index, int target, vector<int> &nums){
    if(index == nums.size() - 1 && target == 0)return 1;
    if(index >= nums.size())return 0;
    
    if(dp[target] != -1)return dp[target];
    
    int notPick = solve(index + 1, target, nums);
    int pick = 0;
    if(target >= nums[index])pick = solve(0, target - nums[index], nums);
    
    return dp[target] = pick + notPick;
}

int combinationSum4(vector<int>& nums, int target) {
    memset(dp, -1, sizeof(dp));
    return solve(0, target, nums);
}
```