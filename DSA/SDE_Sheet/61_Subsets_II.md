# Subsets II

## Problem statement

- Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
- The solution set must not contain duplicate subsets. Return the solution in any order.

## Approach 1.1 : Recursion + Backtrack (Easier to understand)

Time complexity : O(2<sup>N</sup>) 
Auxiliary space : O(2<sup>N</sup>)

```cpp
void solve(int index, int target, vector<vector<int>> &res, vector<int> &ds, vector<int> &nums){
    if(index >= nums.size() && target == 0){
        res.push_back(ds);
        return;
    }
    if(index >= nums.size() || target < 0)return;
    
    ds.push_back(nums[index]);
    solve(index, target - nums[index], res, ds, nums);
    ds.pop_back();
    solve(index + 1, target, res, ds, nums);
}
```

## Approach 1.2 : Recursion + Backtrack

Time complexity : O(2<sup>N</sup>) 
Auxiliary space : O(2<sup>N</sup>)

```cpp
void recur(vector<int> nums,vector<int> &v,vector<vector<int>> &ans,int index){
    ans.push_back(v);
    for(int i = index ; i < nums.size() ; i++)
    {
        if(i != index && nums[i] == nums[i - 1])continue;
        v.push_back(nums[i]);
        recur(nums, v, ans, i + 1);
        v.pop_back();
    }
}
```

## Approach 2 : Using Bit Manipulation

Time complexity : O(N \* 2<sup>N</sup>) 
Auxiliary space : O(N \* 2<sup>N</sup>) 

```cpp
/*
    
    -> For n = 3, total subsets => 2^3 = 8
    -> 000, 001, 010, 011, 100, 101, 110, 111
    -> 1 represent picking up that indexed number from nums vector
    
*/

vector<vector<int>> subsetsWithDup(vector<int>& nums) {
	int n = nums.size();
	vector<vector<int>> subsets; 
	sort(nums.begin(),nums.end());

	set<vector<int>>unique; 

	for(int i = 0; i < (1 << n) ; i++){
		vector<int>subset;
		for(int j = 0 ; j < n ; j++){
			if(((1<<j) & i) != 0)subset.push_back(nums[j]);
		}
		unique.insert(subset);
	}

	for (auto it = unique.begin();it != unique.end();it++) subsets.push_back(*it);
	return subsets;
}
```