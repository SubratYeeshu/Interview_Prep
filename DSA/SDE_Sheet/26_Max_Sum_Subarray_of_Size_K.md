# Max Sum Subarray of size K

## Problem statement

- Given an array of integers Arr of size N and a number K. Return the maximum sum of a subarray of size K.

## Approach 1 : Brute

Time complexity : O(N^2)  
Space complexity : O(1)


```cpp
long maximumSumSubarray(int k, vector<int> &nums , int n){
        int i = 0, j = 0;
        long maxi = LONG_MIN;
        for(int i = 0 ; i <= n - k ; i++){
            long currSum = 0;
            for(int j = i ; j < i + k ; j++){
                currSum += nums[j];
            }
            maxi = max(maxi, currSum);
        }
        return maxi;
    }
```

## Approach 2.1 : Optimal (Fixed size Sliding Window - Template 1)

Time complexity : O(N)  
Space complexity : O(1)

```cpp
long maximumSumSubarray(int K, vector<int> &Arr , int N){
      long maxi = -1, sum = 0;
       
      // Preparing the first window
      for(int i = 0 ; i < K ; i++)sum += Arr[i];
      maxi = max(maxi, sum);
       
      int i = 0, j = K;
      while(j < N){
          sum += Arr[j] - Arr[i];
          maxi = max(maxi, sum);
          i++, j++;
      }
       
      return maxi;
    }
```

## Approach 2.2 : Optimal (Fixed size Sliding Window - Template 2)

Time complexity : O(N)  
Space complexity : O(1)

```cpp
long maximumSumSubarray(int k, vector<int> &nums , int n){
    int i = 0, j = 0;
    long maxi = LONG_MIN, currSum = 0;
    while(j < n){
        currSum += nums[j];
        if(j - i + 1 == k){
            maxi = max(maxi, currSum);
            currSum -= nums[i];
            i++;
        }
        j++;
    }
    
    return maxi;
}
```