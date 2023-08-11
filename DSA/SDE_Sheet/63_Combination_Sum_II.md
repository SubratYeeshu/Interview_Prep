# Combination Sum II

## Problem statement 

- Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.
- Each number in candidates may only be used once in the combination.

## Approach 1.1 : Recursion + Backtrack (Easier to understand)

Time complexity : O(2<sup>N</sup>) 
Space complexity : O(K \* X)

```cpp
void solve(int index, int target, vector<vector<int>> &res, vector<int> &ds, vector<int> &nums){
    if(target == 0){
        res.push_back(ds);
        return;
    }
    if(target < 0)return;
    if(index >= nums.size())return;
    
    ds.push_back(nums[index]);
    solve(index + 1, target - nums[index], res, ds, nums);
    ds.pop_back();
    
    // Skipping duplicates
    while(index + 1 < nums.size() && nums[index] == nums[index + 1])index++;
    solve(index + 1, target, res, ds, nums);
    
}

vector<vector<int>> combinationSum2(vector<int>& candidates, int target) {
    vector<vector<int>> res;
    vector<int> ds;
    
    // Sorting for ordering
    sort(candidates.begin(), candidates.end());
    solve(0, target, res, ds, candidates);
    
    return res;
}
```

## Approach 1.2 : Recursion + Backtrack

Time complexity : O(2<sup>N</sup>) 
Space complexity : O(K \* X)

```cpp
void solve(int index, int target, vector<vector<int>> &res, vector<int> &ds, vector<int> &nums){
    if(target == 0){
        res.push_back(ds);
        return;
    }
    if(target < 0)return;
    if(index >= nums.size())return;

    for (int i = index ; i < nums.size() ; i++) {
        if(i > index && nums[i] == nums[i - 1])continue;
        ds.push_back(nums[i]);
        solve(i + 1, target - nums[i], res, ds, nums);
        ds.pop_back();
    }
}

vector<vector<int>> combinationSum2(vector<int>& candidates, int target) {
    vector<vector<int>> res;
    vector<int> ds;
    
    // Sorting for ordering
    sort(candidates.begin(), candidates.end());
    solve(0, target, res, ds, candidates);
    
    return res;
}
```