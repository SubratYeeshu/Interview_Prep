# Majority Element - 1

## Problem Statement

- Given an array nums of size n, return the majority element. The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

## Approach 1 : Brute

Time complexity : O(N \* 2) 
Space complexity : O(1)

```cpp
int majorityElement(vector<int>& nums) {
    int n = nums.size();
    for(int i = 0 ; i < n ; i++){
        int cnt = 0;
        for(int j = 0 ; j < n ; j++){
            if(nums[i] == nums[j])cnt++;
            if(cnt > n/2){
                return nums[i];
            }
        }
    }
    return 0;
}
```

## Approach 2 : Better (Sorting)

Time complexity : O(N \* LogN) 
Space complexity : O(1)

```cpp
int majorityElement(vector<int>& nums) {
    int n = nums.size();
    sort(nums.begin(), nums.end());
    int ct = 1;
    for(int i = 1 ; i < n ; i++){
        if(nums[i-1] == nums[i])ct += 1;            
        if(ct > n / 2)return nums[i];
        if(nums[i-1] != nums[i])ct = 1;
    }
    return 0;
}
```

## Approach 3 : Better (Using Map)

Time complexity : O(N) 
Space complexity : O(N)

```cpp
int majorityElement(vector<int>& nums) {
    unordered_map<int, int> counter;
    for (int num : nums) {
        if (++counter[num] > nums.size() / 2) {
            return num;
        }
    }
    return 0;
}
```

## Approach 4 : Optimal (Moore's Voting)

Time complexity : O(2N) 
Space complexity : O(1)

```cpp
//Elements are combined and exhausted to get a potential answer then we iterate once again to confirm it
int majorityElement(vector<int>& nums) {
    int n = nums.size();
    int ele = -1, cnt = 0;
    for(int i = 0 ; i < n ; i++){
        if(nums[i] == ele)cnt++;
        else if(cnt == 0){
            ele = nums[i];
            cnt = 1;
        }else cnt--;
    }
    cnt = 0;
    for(auto i : nums)if(i == ele)cnt++;
    return cnt >= n / 2 ? ele : -1;
}
```




