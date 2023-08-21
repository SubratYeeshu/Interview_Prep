# Aggressive Cows

## Problem statement

- You are given an array consisting of n integers which denote the position of a stall. You are also given an integer k which denotes the number of aggressive cows. You are given the task of assigning stalls to k cows such that the minimum distance between any two of them is the maximum possible. The first line of input contains two space-separated integers n and k.
The second line contains n space-separated integers denoting the position of the stalls.

- Binary Search (Find min of max / max of min pattern)

## Approach 1 : Binary Search 

Time complexity : O(NLogN) 
Space complexity : O(1)

```cpp
bool canPlace(vector<int> &nums, int distance, int cows){
    // Greedly put the first cow at index 0 because putting it later has no meaning since distance is positive
    
    int last = nums[0], cowsArranged = 1;
    for(int i = 1 ; i < nums.size() ; i++){
        if(nums[i] - last >= distance){
            last = nums[i];
            cowsArranged++;
        }
    }
    
    return cowsArranged >= cows;
}

int solve(int n, int k, vector<int> &nums) {
    sort(nums.begin(), nums.end());
    int low = 1, high = nums[nums.size() - 1] - nums[0] + 1, ans = 0;
    
    // Mid -> distance, we have to maximize the distance between any two cows, and the minimum distance between two cows is when they are consequtive
    while(low <= high){
        int mid = (low + high) / 2;
        
        if(canPlace(nums, mid, k)){
            ans = mid;
            low = mid + 1;
        }else {
            high = mid - 1;
        }
    }
    
    return ans;
}
```