# Combination Sum III

## Problem statement 

Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
- Only numbers 1 through 9 are used.
- Each number is used at most once.
Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.

## Approach 1.1 : Recursion + Backtrack (Easier to understand)

Time complexity : O(2<sup>N</sup>) 
Space complexity : O(N + k + X), k - size of the combination, X recursive stack.

```cpp
void solve(int index, int target, vector<vector<int>> &res, vector<int> &ds, vector<int> &nums, int k){
    if(target == 0 && k == 0){
        res.push_back(ds);
        return;
    }
    if(target < 0)return;
    if(index >= nums.size())return;

    ds.push_back(nums[index]);
    solve(index + 1, target - nums[index], res, ds, nums, k - 1);
    ds.pop_back();
    solve(index + 1, target, res, ds, nums, k);

}

vector<vector<int>> combinationSum3(int k, int n) {
    vector<vector<int>>res;
    vector<int> ds;
    vector<int> nums {1, 2, 3, 4, 5, 6, 7, 8, 9};
    
    solve(0, n, res, ds, nums, k);
    
    return res;
}
```

## Approach 1.2 : Recursion + Backtrack

Time complexity : O(2<sup>N</sup>) 
Space complexity : O(k + X)

```cpp
void solve(int index, int target, vector<vector<int>> &res, vector<int> &ds, int k){
    if(k == 0 && target == 0){
        res.push_back(ds);
        return;
    }
            
    for(int i = index ; i <= 9 ; i++){
        ds.push_back(i);
        solve(i + 1, target - i, res, ds, k - 1);
        ds.pop_back();
    }
}

vector<vector<int>> combinationSum3(int k, int n) {
    vector<vector<int>>res;
    vector<int> ds;
    
    solve(1, n, res, ds, k);
    
    return res;
}
```