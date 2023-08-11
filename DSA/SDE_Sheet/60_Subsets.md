# Subsets

- Given an integer array nums of unique elements, return all possible subsets (the power set).
- The solution set must not contain duplicate subsets. Return the solution in any order.

## Approach 1.1 : Recursion + Backtrack (Easier to understand)

Time complexity : O(2<sup>N</sup>) 
Auxiliary space : O(2<sup>N</sup>)

```cpp
void solve(int index, vector<int> &nums, vector<int> &ds, vector<vector<int>> &res){
    if(index >= nums.size()){
        res.push_back(ds);
        return;
    }
    
    // Pick
    ds.push_back(nums[index]);
    solve(index + 1, nums, ds, res);
    ds.pop_back();
        
    // Not pick
    solve(index + 1, nums, ds, res);
}
```

## Approach 1.2 : Recursion + Backtrack

Time complexity : O(2<sup>N</sup>) 
Auxiliary space : O(2<sup>N</sup>)

```cpp
void solve(int index, vector<int> &nums, vector<int> &ds, vector<vector<int>> &res){
    res.push_back(ds);
    
    for(int i = index ; i < nums.size() ; i++){
        ds.push_back(nums[i]);
        solve(i + 1, nums, ds, res);
        ds.pop_back();
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

vector<vector<int>> subsets(vector<int>& nums) {
    int n = nums.size();
    vector<vector<int>> res;
    
    for(int i = 0 ; i < (1 << n) ; i++){  // For every binary of (0 to n) is a valid subset 1 represent picking up
        vector<int> sub;
        for(int j = 0 ; j < n ; j++){
            if((1 << j) & i){  // Pick the element
                sub.push_back(nums[j]);                    
            }
        }
        res.push_back(sub);
    }
    
    return res;
}
```