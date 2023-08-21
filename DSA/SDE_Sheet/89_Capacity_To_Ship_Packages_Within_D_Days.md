# Capacity To Ship Packages Within D Days

## Problem statement

- A conveyor belt has packages that must be shipped from one port to another within days days. The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship. Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.

- Binary Search (Find min / max pattern)

## Approach 1 : Binary Search 

Time complexity : O(NLogN) 
Space complexity : O(1)

```cpp
int check(vector<int> &nums, int capacity){
    int c = capacity, day = 1, sum = 0;
    for(auto i : nums){
        if(c > 0 && c >= i){
            c -= i;
        }else {
            c = capacity;
            c -= i;
            day++;
            }
    }
    return day;
}
int shipWithinDays(vector<int>& nums, int days) {
    
    int low = *max_element(nums.begin(), nums.end()), high = accumulate(nums.begin(), nums.end(), 0), ans = 0;
    
    while(low <= high){
        int mid = (low + high) / 2;  // Binary search on wt capacity
                    
        int dayNeeded = check(nums, mid); 
        
        if(dayNeeded <= days){
            ans = mid;
            high = mid - 1;
        }else{
            low = mid + 1;
        }
    }
    return ans;
}
```

## Approach 2 : Binary Search (Standard Check Syntax)

Time complexity : O(NLogN) 
Space complexity : O(1)

```cpp
bool solve(vector<int> weights, int mid, int days){
    int sum = 0;
    int D = 1;
    
    for(int i = 0 ; i < weights.size() ; i++){
        if(sum + weights[i] <= mid)sum+=weights[i];
        else{
            sum = weights[i];
            D++;
        }
    }
    if(D <= days)return true;
    return false;
}

int shipWithinDays(vector<int>& weights, int days) {
    int low = *max_element(weights.begin(), weights.end());
    int high = accumulate(weights.begin(), weights.end(), 0);
    int ans = 0;
    
    while(low <= high){
        int mid = (low + high) / 2;
        if(solve(weights, mid, days)){
            ans = mid;
            high = mid - 1;
        }else 
            low = mid + 1;
    }
    
    return ans;
}
```