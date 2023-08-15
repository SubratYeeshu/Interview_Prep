# Permutations

## Problem statement

- Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

## Approach 1 : Backtrack + Map

Time complexity : O(N \* N!) 
Space complexity : O(N)

```cpp
void permute(int index, vector<int> &nums, vector<int> &ds, vector<vector<int>> &res, unordered_map<int, int> &vis){
    if(ds.size() == nums.size()){
        res.push_back(ds);
        return;
    }
    
    for(int i = 0 ; i < nums.size() ; i++){
        if(vis[nums[i]] == 0){
            vis[nums[i]] = 1;
            ds.push_back(nums[i]);
            permute(i + 1, nums, ds, res, vis);
            ds.pop_back();
            vis[nums[i]] = 0;
        }
    }
}
```

## Approach 2 : Backtrack + Swap

Time complexity : O(N \* N!) 
Space complexity : O(1)

```cpp
void permute(int index, vector<int> &nums, vector<vector<int>> &res){
    if(index >= nums.size()){
        res.push_back(nums);
        return;
    }
    
    for(int i = index ; i < nums.size() ; i++){
        swap(nums[i], nums[index]);
        permute(index + 1, nums, res);
        swap(nums[i], nums[index]);
    }
}
```