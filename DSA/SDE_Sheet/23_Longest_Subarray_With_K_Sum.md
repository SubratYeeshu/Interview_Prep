# Largest subarray with 0 sum

## Problem statement

- Given an array containing N integers and an integer K., Your task is to find the length of the longest Sub-Array with the sum of the elements equal to the given value K.

## K will be zero for the problem longest subarray with 0 sum

## Approach 1 (Brute force, generating all subarrays)

Time complexity : O(N^3)  
Space complexity : O(1)

```cpp
int maxLen(vector<int>&A, int n, int k) {
    int res = 0;
    for(int i = 0 ; i < n ; i++) {
        for(int j = i ; j < n ; j++) {
            int sum = 0;
            for(int kk = i ; kk <= j ; kk++)
                sum += A[kk];
            if(sum == k)
                res = max(res, j - i + 1);
        }
    }
    return res;
}
```

## Approach 2 (Better)

Time complexity : O(N^2)  
Space complexity : O(1)

```cpp
int maxLen(vector<int>&A, int n, int k) {
    int res = 0;
    for(int i = 0 ; i < n ; i++) {
        int sum = 0;
        for(int j = i ; j < n ; j++) {
            sum += A[j];
            if(sum == k)
                res = max(res, j - i + 1);
        }
    }
    return res;
}
```

## Approach 3 (Hash map)

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
int maxLen(vector<int>&A, int n, int k){   
    int sum = 0, maxi = 0;
    unordered_map<int, int> mp;
    // mp[0] = -1;
    for(int i = 0; i < n; i++){
        sum += A[i];
        if(sum == k)maxi = max(maxi, i + 1);
        // if mp[0] is not set to -1
        // we can do a check here if(sum == k) here k is equal to 0 we can update with i + 1 as max index
        // Index upto which the sum is (sum - 0) subarray after this index to ith index will have sum as 0
        // as it is being nullified
        if(mp.find(sum - k) != mp.end())maxi = max(maxi, i - mp[sum - k]);
        if(mp.find(sum) == mp.end())mp[sum] = i;  // Handling multiple zeroes
    }
    return maxi;
}
```

## Approach 4 (Sliding Window - Only for array with positives)

Time complexity : O(N)  
Space complexity : O(1)

```cpp
int longestSubarrayWithSumK(vector<int> a, long long k) {
    int n = a.size();
    long long sum = 0;
    int left = 0, right = 0, maxi = -1;

    while(right < n){
        sum += a[right];
        if(sum == k){
            maxi = max(maxi, right - left + 1);
            right++;
        }else if(sum < k)right++;
        else{
            while (sum > k && left <= right) {
              sum -= a[left];
              left++;
              if(sum == k)maxi = max(maxi, right - left + 1);
            }
            right++;
        }    
    }
    return maxi;
}
```