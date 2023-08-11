# Combination Sum

## Problem statement

- Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
- The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.

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
    solve(index, target - nums[index], res, ds, nums);
    ds.pop_back();
    solve(index + 1, target, res, ds, nums);
}
```

## Approach 1.1 : Recursion + Backtrack 

Time complexity : O(2<sup>N</sup>) 
Auxiliary space : O(K \* X)

```cpp
void solve(int index, int target, vector<vector<int>> &res, vector<int> &ds, vector<int> &nums) {
    if(target == 0){
        res.push_back(ds);
        return;
    }
    if(target < 0)return;
    void solve(int index, int target, vector<vector<int>> &res, vector<int> &ds, vector<int> &nums) {
        if(target == 0){
            res.push_back(ds);
            return;
        }
        if(target < 0)return;
        // if(index >= nums.size())return;
        /*
        Generating All Combinations: To generate all possible combinations, you need to explore all elements in the nums array. If you have this condition, the loop will terminate prematurely once the index exceeds the array size, and you will miss considering combinations that involve elements further down the array.
        */
        
        for (int i = index ; i < nums.size() ; i++) {
            ds.push_back(nums[i]);
            solve(i, target - nums[i], res, ds, nums);
            ds.pop_back();
        }
    }
    for (int i = index ; i < nums.size() ; i++) {
        ds.push_back(nums[i]);
        solve(i, target - nums[i], res, ds, nums);
        ds.pop_back();
    }
}
```