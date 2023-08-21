# Koko Eating Bananas

## Problem statement 

- Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours. Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour. Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
Return the minimum integer k such that she can eat all the bananas within h hours.

- Binary Search (Find min / max pattern)

## Approach 1 : Binary Search

Time complexity : O(NLogN) 
Space complexity : O(1)

```cpp
long long check(vector<int> &piles, int rate){
    long long totalHours = 0;
    for(auto bananas : piles)
        totalHours += ceil((double)bananas / (double)rate); 
    
    return totalHours;
}

int minEatingSpeed(vector<int>& piles, int h) {
    int low = 1, high = *max_element(piles.begin(), piles.end()), ans = INT_MAX;
    
    while(low <= high){
        int mid = (low  + high) / 2;
        
        if(check(piles, mid) <= h){
            ans = mid;
            high = mid - 1;  // Decreasing the rate
        }
        else {
            low = mid + 1;  // Increasing the range
        }
    }
    
    return ans;
}
```