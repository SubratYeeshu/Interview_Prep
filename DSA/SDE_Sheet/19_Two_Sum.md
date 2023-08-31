# Two Sum

## Problem statement

Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target. You may assume that each input would have exactly one solution, and you may not use the same element twice. You can return the answer in any order

## Approach 1 : Brute

- Time complexity : O(N^2) 
- Space complexity : O(1)

```cpp
vector<int> twoSum(vector<int>& nums, int target) {
    vector<int> ans;
    for(int i = 0; i < nums.size() ; i++){
        for(int j = i + 1; j < nums.size() ; j++ ){
            if(nums[i] + nums[j] == target ){
                ans.push_back(i);
                ans.push_back(j);
                return ans;
            }        
        }
    }
    return ans;
}
```

## Approach 2 : Map

- Time complexity : O(N) 
- Space complexity : O(N)

```cpp
vector<int> twoSum(vector<int>& nums, int target) {
    int n = nums.size();
    unordered_map<int, int> mp;
    for(int i = 0 ; i < n ; i++){
        if(mp.count(target - nums[i]))return {i, mp[i]};
        mp[nums[i]] = i;
    }
    return {-1};
}
```