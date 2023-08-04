# Two Sum

## Problem statement

- Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target. You may assume that each input would have exactly one solution, and you may not use the same element twice. You can return the answer in any order

## Approach 1 (Brute force)

Time complexity : O(N^3 \* log(M))  
Space complexity : O(M) (M = no. of triplets in final answer)

```cpp
vector<vector<int>> threeSum(vector<int>& nums) {
    int n = nums.size();
    set<vector<int>> st;
    for(int i = 0 ; i < n ; i++) {
        for(int j = i + 1 ; j < n ; j++) {
            for(int k = j + 1 ;  k < n ; k++) {
                if(nums[i] + nums[j] + nums[k] == 0) {
                    vector<int> v = {nums[i], nums[j], nums[k]};
                    sort(v.begin(), v.end());
                    st.insert(v);
                }
            }
        }
    }
    vector<vector<int>> res;
    for(auto it:st) res.push_back(it);
    return res;
}
```

## Approach 2 : Better

Time complexity : O(N \* N + Insertion of n triplets) 
Space complexity : O(N)

```cpp
vector<vector<int>> threeSum(vector<int> &num){
    int n = num.size(), target = 0;
    vector<vector<int>> res;
    set<vector<int>> st;
    sort(num.begin(), num.end());
    
    for(int i = 0 ; i < n - 2 ; i++){
        int j = i + 1, k = n - 1;
        while(j < k){
            int sum = num[i] + num[j] + num[k];
            if(sum == target){
                st.insert({num[i], num[j], num[k]});
                j++, k--;
            }else if(sum > target)k--;
            else j++;
        }
    }
    
    for(auto i : st)res.push_back(i);
    return res;
}
```

## Approach 3 : Optimal

Time complexity : O(N \* N)  
Space complexity : O(M)

```cpp
vector<vector<int>> threeSum(vector<int>& nums) {
    int n = nums.size(), target = 0;
    sort(nums.begin(), nums.end());
    vector<vector<int>> res;
    for(int i = 0 ; i < n - 1 ; i++){
        if(i > 0 && nums[i] == nums[i - 1])continue;    
        int j = i + 1, k = n - 1;
            while(j < k){
            int sum = nums[i] + nums[j] + nums[k];
                if(sum == target){
                    res.push_back({nums[i] , nums[j] , nums[k]});
                    while(j < k && (nums[j + 1] == nums[j]))j++;
                    while(j < k && (nums[k - 1] == nums[k]))k--;
                    j++, k--;
                }
                else if(target < sum)k--;
                else j++;
            }
    }
    return res;
}
```