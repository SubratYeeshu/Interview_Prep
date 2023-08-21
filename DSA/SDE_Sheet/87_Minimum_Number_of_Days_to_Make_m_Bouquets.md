# Minimum Number of Days to Make m Bouquets

## Problem statement

- You are given an integer array bloomDay, an integer m and an integer k. You want to make m bouquets. To make a bouquet, you need to use k adjacent flowers from the garden. The garden consists of n flowers, the ith flower will bloom in the bloomDay[i] and then can be used in exactly one bouquet. Return the minimum number of days you need to wait to be able to make m bouquets from the garden. If it is impossible to make m bouquets return -1.

- Binary Search (Find min / max pattern)

## Approach 1 : Binary Search

Time complexity : O(NLogN) 
Space complexity : O(1)

```cpp
int collectBouquets(vector<int> &nums, int day, int bqtSize){
    int count = 0, bqt = 0;
    for(int i = 0 ; i < nums.size() ; i++){
        if(nums[i] <= day){
            count++;
            
            if(count == bqtSize){
                bqt++;
                count = 0;
            }
        }else count = 0;
    }
        
    
    return bqt;
    
}
int minDays(vector<int>& bloomDay, int m, int k) {
    int low = 1, high = *max_element(bloomDay.begin(), bloomDay.end()), ans = -1;
            
    while(low <= high){
        int mid = (low + high) / 2;
        
        int collectedBouquets = collectBouquets(bloomDay, mid, k);
        if(collectedBouquets >= m){
            ans = mid;
            high = mid - 1;
        }else{
            low = mid + 1;
        }
    }
    
    return ans;
}
```