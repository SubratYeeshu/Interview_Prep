# Majority Element - 2

## Problem Statement

- Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

## Approach 1 : Brute 

Time complexity : O(N \* 2) 
Space complexity : O(1)

```cpp
vector<int> majorityElement(vector<int>& nums) {
    int n = nums.size();
    vector<int> res;
    unordered_set<int> st;
    for(int i = 0 ; i < n ; i++){
        if(res.size() == 1 && res.back() == nums[i])continue;
        int cnt1 = 0;
        for(int j = 0 ; j < n ; j++)if(nums[j] == nums[i])cnt1++;
        if(cnt1 > n / 3)st.insert(nums[i]);
    }
    for(auto i : st)res.push_back(i);
    return res;
}
```

## Approach 2 : Better (Using Map)

Time complexity : O(N) 
Space complexity : O(N)

```cpp
vector<int> majorityElement(vector<int>& nums) {
    unordered_map<int, int> mpp;
    vector<int> ans;
    int size = nums.size();
    for(int i = 0 ; i < size ; i++){
        mpp[nums[i]]++;
    }
    for(auto it=mpp.begin();it!=mpp.end();it++)
    {
        if(it->second>size/3)
        {
            ans.push_back(it->first);
        }
    }
    return ans;
}
```

## Approach 3 : Optimal (Moore's Voting)

Time complexity : O(2N) 
Space complexity : O(1)

```cpp
vector<int> majorityElement(vector<int>& nums) {
    int n = nums.size();
    int cnt1 = 0, val1 = -1, cnt2 = 0, val2 = -1;
    vector<int> res;
    
    for(int i = 0 ; i < n ; i++){
        if(nums[i] == val1)cnt1++;
        else if(nums[i] == val2)cnt2++;
        else if(cnt1 == 0 && nums[i] != val2){
            val1 = nums[i];
            cnt1 = 1;
        }else if(cnt2 == 0 && nums[i] != val1){
            val2 = nums[i];
            cnt2 = 1;
        }else{
            cnt1--;
            cnt2--;
        }
    }
    
    cnt1 = cnt2 = 0;
    for(auto i : nums){
        if(i == val1)cnt1++;
        else if(i == val2)cnt2++;
    }
    
    if(cnt1 > n / 3)res.push_back(val1);
    if(cnt2 > n / 3)res.push_back(val2);
    
    return res;
}
```



