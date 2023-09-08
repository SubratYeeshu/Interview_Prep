# Longest Increasing Subsequence

## Problem statement

Given an integer array nums, return the length of the longest strictly increasing subsequence.

## Pattern : LIS

- To solve question that are related to LIS
- Try to think after sorting if the sequence does not matter
- Instead of (nums[index] > nums[prev]) there may be other function or condition 

## Approach 1.1 : Brute + Prev Element

- Time complexity : O(2^N)
- Space complexity : O(N)

```cpp
int solve(int index, vector<int> &nums, int prev){
    if(index >= nums.size())return 0;
    
    
    int pick = 0, notPick = 0;
    if(prev == INT_MIN){
        pick = 1 + solve(index + 1, nums, nums[index]);
        notPick = solve(index + 1, nums, prev);
    }else{
        if(nums[index] > prev){
            pick = 1 + solve(index + 1, nums, nums[index]);
            notPick = solve(index + 1, nums, prev);
        }
        else{
            notPick = solve(index + 1, nums, prev);
        }
    }
    
    return max(pick, notPick);
}


int lengthOfLIS(vector<int>& nums) {
    return solve(0, nums, INT_MIN);
}
```

## Approach 1.2 : Brute + Prev Index

- Time complexity : O(2^N)
- Space complexity : O(N)

```cpp
int solve(int index, vector<int> &nums, int prev){
    if(index >= nums.size())return 0;
    
    
    int pick = 0, notPick = 0;
    
    if(prev == -1 || nums[index] > nums[prev]){
        pick = 1 + solve(index + 1, nums, index);
        notPick = solve(index + 1, nums, prev);
    }
    else{
        notPick = solve(index + 1, nums, prev);
    }
    
    
    return max(pick, notPick);
}


int lengthOfLIS(vector<int>& nums) {
    return solve(0, nums, -1);
}
```

## Approach 1.3 : Memoization + Index Shifting

- Time complexity : O(N^2)
- Space complexity : O(N^2) + O(N)

```cpp
int solve(int index, vector<int> &nums, int prev, vector<vector<int>> &dp){
    if(index >= nums.size())return 0;
    if(dp[index][prev + 1] != -1)return dp[index][prev + 1];
    
    int pick = 0, notPick = 0;
    
    if(prev == -1 || nums[index] > nums[prev]){
        pick = 1 + solve(index + 1, nums, index, dp);
        notPick = solve(index + 1, nums, prev, dp);
    }
    else{
        notPick = solve(index + 1, nums, prev, dp);
    }
    
    
    return dp[index][prev + 1] = max(pick, notPick);
}


int lengthOfLIS(vector<int>& nums) {
    int n = nums.size();
    vector<vector<int>> dp (n + 1, vector<int> (n + 1, -1));
    return solve(0, nums, -1, dp);
}
```

## Approach 2.1 : Tabulation

- Time complexity : O(N^2)
- Space complexity : O(N)

```cpp
int lengthOfLIS(vector<int>& nums) {
    int n = nums.size(), maxi = 1;
    vector<int> dp (n + 1, 1);
    
    for(int index = 0 ; index < n ; index++){
        for(int prev = 0 ; prev < index ; prev++){
            if(nums[index] > nums[prev]){
                dp[index] = max(dp[index], 1 + dp[prev]);
            }
        }
        maxi = max(maxi, dp[index]);
    }
    
    return maxi;
}
```

## Approach 2.2 : Tabulation + Standard LIS

- Time complexity : O(N^2)
- Space complexity : O(N)

```cpp
int lengthOfLIS(vector<int>& nums) {
    int n = nums.size(), maxi = 1;
    vector<int> dp (n + 1, 1);

    for(int index = 0 ; index < n ; index++){
        for(int prev = 0 ; prev < index ; prev++){
            if(nums[index] > nums[prev]){  // Can use different comparator function according to different variants of LIS
                if(1 + dp[prev] > dp[index]){
                    dp[index] = 1 + dp[prev];
                }
            }
        }
        maxi = max(maxi, dp[index]);
    }

    return maxi;
}
```


## Printing The LIS

- Time complexity : O(N^2)
- Space complexity : O(N)

```cpp
int lengthOfLIS(vector<int>& nums) {
    int n = nums.size(), maxi = 1, lastIdx = 0;
    vector<int> dp (n + 1, 1);
    vector<int> trackParent (n, 0);
    for(int i = 0 ; i < n ; i++)trackParent[i] = i;
    
    for(int index = 0 ; index < n ; index++){
        for(int prev = 0 ; prev < index ; prev++){
            if(nums[index] > nums[prev]){
                if(dp[index] < 1 + dp[prev]){
                    // Whenever dp[index] is updated
                    dp[index] = 1 + dp[prev];
                    trackParent[index] = prev;
                }
            }
        }
        if(maxi < dp[index]){
            maxi = dp[index];
            lastIdx = index;
        }
    }
            
    vector<int> temp;
    temp.push_back(nums[lastIdx]);
    while(lastIdx != trackParent[lastIdx]){
        lastIdx = trackParent[lastIdx];
        temp.push_back(nums[lastIdx]);
    } 
    reverse(temp.begin(), temp.end());
    for(auto i : temp)cout << i << " ";
    
    return maxi;
}
```

## Approach 3 : Binary Search (Only For Finding Length)

- Time complexity : O(NLogN)
- Space complexity : O(N)

```cpp
int lengthOfLIS(vector<int>& nums) {
    int n = nums.size();
    vector<int> temp;
    
    for(int i = 0 ; i < n ; i++){
        if(temp.size() == 0 || temp.back() < nums[i])temp.push_back(nums[i]);
        else {
            int idx = lower_bound(temp.begin(), temp.end(), nums[i]) - temp.begin();
            temp[idx] = nums[i];
        }
    }
    
    return temp.size();
}
```