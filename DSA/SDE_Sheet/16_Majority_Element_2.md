# Majority Element - 2

## Problem Statement

- Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

## Approach 1 : Brute (Using Map)

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

## Approach 2 : Optimal (Moore's Voting)

Time complexity : O(2N) 
Space complexity : O(1)

```cpp
vector<int> majorityElement(vector<int>& nums) {
    int count1 = 0, count2 = 0, val1 = -1, val2 = -1;
    int n = nums.size();
    for(int i = 0 ; i < n ; i++){
        if(nums[i] == val1)count1++;
        else if(nums[i] == val2)count2++;
        else{
            if(count1 == 0){
                val1 = nums[i];
                count1 = 1;
            }
            else if(count2 == 0){
                val2 = nums[i];
                count2 = 1;
            }
            else{
                count1--;
                count2--;
            }
        }
            
    }
    count1 = 0, count2 = 0;
    for(int i = 0 ; i < n ; i++){
        if(nums[i] == val1)count1++;
        else if(nums[i] == val2)count2++;
    }
    vector<int>ans;
    if(count1 > n/3)ans.push_back(val1);
    if(count2 > n/3)ans.push_back(val2);
    
    return ans;
}
```



