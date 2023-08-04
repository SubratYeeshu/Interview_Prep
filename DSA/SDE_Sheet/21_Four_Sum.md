# Four sum problem

## Problem statement

Given an array `nums` of `n` integers, return an array of all the unique quadruplets `[nums[a], nums[b], nums[c], nums[d]]` such that:

- 0 <= a, b, c, d < n
- a, b, c, and d are distinct.
- nums[a] + nums[b] + nums[c] + nums[d] == target

You may return the answer in any order.

## Approach 1 (Brute force)

Time complexity : O(N^4 \* log(N))  
Space complexity : O(N)

```cpp
vector<vector<int>> fourSum(vector<int> &nums, int target){
    int n = nums.size();
    vector<vector<int>> res;
    set<vector<int>> st;
    for (int i = 0; i < n; i++)
        for (int j = i + 1; j < n; j++)
            for (int k = j + 1; k < n; k++)
                for (int l = k + 1; l < n; l++)
                    if (nums[i] + nums[j] + nums[k] + nums[l] == target){
                        vector<int> v = {nums[i], nums[j], nums[k], nums[l]};
                        sort(v.begin(), v.end());
                        st.insert(v);
                    }
    for (auto it : st)res.push_back(it);
    return res;
}
```

## Approach 2 (3 loop + binary search)

Time complexity : O(N^3 \* log(N) \* log(N))  
Space complexity : O(N)

```cpp
vector<vector<int>> fourSum(vector<int> &nums, int target){
    int n = nums.size();
    sort(nums.begin(), nums.end());
    vector<vector<int>> res;
    set<vector<int>> st;
    for (int i = 0; i < n; i++)
        for (int j = i + 1; j < n; j++)
            for (int k = j + 1; k < n; k++){
                long long x = (long long)target - nums[i] - nums[j] - nums[k];
                if (binary_search(nums.begin() + k + 1, nums.end(), x))
                    st.insert({nums[i], nums[j], nums[k], (int)x});
            }
    for (auto it : st)
        res.push_back(it);
    return res;
}
```

## Approach 3 (2 loop + 1 iteration)

Time complexity : O(N^3)  
Space complexity : O(no. of quad.)

```cpp
vector<vector<int>> fourSum(vector<int>& nums, int target) {
    int n = nums.size();
    sort(nums.begin(), nums.end());
    vector<vector<int>> res;
    
    for(int i = 0 ; i < n ; i++){
        if(i > 0 && nums[i] == nums[i - 1])continue;  // Eliminating duplicates on ith pointer
        for(int j = i + 1 ; j < n ; j++){
            if(j > i + 1 && nums[j] == nums[j - 1])continue;  // Eliminating duplicates on jth pointer
            int k = j + 1, l = n - 1;
            while(k < l){
                long long sum = 1ll*nums[i] + 1ll*nums[j] + 1ll*nums[k] + 1ll*nums[l];
                if(sum == target){
                    res.push_back({nums[i], nums[j], nums[k], nums[l]});
                    k++; l--;
                    while (k < l && nums[k] == nums[k - 1]) k++;  // Eliminating duplicates on kth pointer
                    while (k < l && nums[l] == nums[l + 1]) l--;  // Eliminating duplicates on lth pointer 
                }else if (sum < target) k++;
                else l--;
            }
        }
    }
    return res;
}
```
