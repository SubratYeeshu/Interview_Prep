# Painter's Partition Problem (Same as Split array largest sum)

## Problem statement

- Dilpreet wants to paint his dog's home that has n boards with different lengths. The length of ith board is given by arr[i] where arr[] is an array of n integers. He hired k painters for this work and each painter takes 1 unit time to paint 1 unit of the board.  The problem is to find the minimum time to get this job done if all painters start together with the constraint that any painter will only paint continuous boards, say boards numbered {2,3,4} or only board {1} or nothing but not boards {2,4,5}.

## Approach 1 : Binary Search 

Time complexity : O(NLogN) 
Space complexity : O(1)

```cpp
long long getSubarraysCount(int nums[], int n,  long long sum){
    long long avaibleSum = sum, noOfSubarray = 1;
    for(int i = 0 ; i < n ; i++){
        if(avaibleSum >= nums[i]){
            avaibleSum -= nums[i];
        }else{
            avaibleSum = sum;
            noOfSubarray++;
            if(avaibleSum >= nums[i]) avaibleSum -= nums[i];  
            else break;
        }
    }
    return noOfSubarray;
}
long long minTime(int nums[], int n, int k){
    // Binary Search on number of sum

    // sort(nums.begin(), nums.end());  Contiguos allocation
    
    long long low = *max_element(nums, nums + n), high = accumulate(nums, nums + n, 0LL), ans = -1;
    if(k >= n)return low;  // Every painter will pick up the task (Change between )

    while(low <= high){
        long long mid = (low + high) / 2;


        long long noOfSubarrays = getSubarraysCount(nums, n, mid);
        if(noOfSubarrays <= k){  // Decrease the sum to adjust more candidates
            ans = mid;
            high = mid - 1;
        }else{
            low = mid + 1;
        }
    }

    return ans; 
}
```