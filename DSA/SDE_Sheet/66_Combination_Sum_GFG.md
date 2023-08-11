# Combination Sum GFG

## Problem statement

Given an array of integers and a sum B, find all unique combinations in the array where the sum is equal to B. The same number may be chosen from the array any number of times to make B.
- All numbers will be positive integers.
- Elements in a combination (a1, a2, …, ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
- The combinations themselves must be sorted in ascending order.

## Approach 1 : Recursion + Backtrack

Time complexity : O(2<sup>N</sup> + N \* LogN) 
Space complexity : O(N + rec)

```cpp
void solve(int index, int target, vector<vector<int>> &res, vector<int> &ds, vector<int> &nums){
    if(target == 0){
        res.push_back(ds);
        return;
    }
    if(target < 0)return;
    if(index >= nums.size())return;
    
    ds.push_back(nums[index]);
    solve(index, target - nums[index], res, ds, nums);
    ds.pop_back();
    solve(index + 1, target, res, ds, nums);
}
void removeDuplicates(vector<int> &arr,vector<int> &nums){
    for(int i = 0 ;  i < arr.size() - 1 ; i++)
        if(arr[i] != arr[i + 1])nums.push_back(arr[i]);
        
        nums.push_back(arr[arr.size() - 1]);
}
vector<vector<int> > combinationSum(vector<int> &candidates, int target) {
    vector<vector<int>> res;
    vector<int> ds, nums;
    
    // Sorting for ordering
    sort(candidates.begin(), candidates.end());
    removeDuplicates(candidates, nums);
    solve(0, target, res, ds, nums);
    
    return res;
}
```