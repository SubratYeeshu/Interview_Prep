# Subarray Sum Equals K

## Problem statement

- Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k. A subarray is a contiguous non-empty sequence of elements within an array.

## Approach 1 : Hashmap

Time complexity : O(N)  
Space complexity : O(N)

```cpp
/*
        _______\  (x - k)
    ->  . . . . \ . . . . | . . .    
        __________________|  sum = x

        if (x - k) exists
        then in between the rest subarray does have the sum as k
        This method can be applied to various questions with somewhat similar pattern
*/
int subarraySum(vector<int>& A, int k) {
    int n = A.size();
    int sum = 0, cnt = 0;
    unordered_map<int, int> mp;
    // mp[0] = 1;
    for(int i = 0; i < n; i++){
        sum += A[i];
        if(sum == k)cnt++;
        // if mp[0] is not set to 1
        if(mp.find(sum - k) != mp.end())cnt += mp[sum - k];
        mp[sum]++; 
    }
    return cnt;
}
```