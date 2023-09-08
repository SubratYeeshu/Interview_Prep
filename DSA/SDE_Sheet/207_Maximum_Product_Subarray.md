# Maximum Product Subarray

## Problem statement 

Given an integer array nums, find a subarray that has the largest product, and return the product.

The test cases are generated so that the answer will fit in a 32-bit integer.

## Approach 1 : Brute

- Time complexity : O(N^2)
- Space complexity : O(1)

```cpp
int maxProduct(vector<int>& nums) { 
    int maxi = INT_MIN;
    for(int i = 0 ; i < nums.size() ; i++){
        int product = 1;
        for(int j = i ; j < nums.size() ; j++){
            product = product * nums[j];
            maxi = max(maxi, product);
        }
    }  
    return maxi;
}
```

## Approach 2.1 : Observation

- Time complexity : O(2N) 
- Space complexity : O(1)

```cpp
/*

    -> Answer is either prefix or the suffix 
    -> Three case dry run
        -> All +ve
        -> Even -ve, ans prod of whole array
        -> Odd -ve, Eliminate one -ve

*/
int maxProduct(vector<int>& nums) { 
    int ans = INT_MIN, prod = 1;
    
    for(int i = 0 ; i < nums.size() ; i++){
        prod *= nums[i];
        ans = max(ans, prod);
        
        if(nums[i] == 0)prod = 1;
    }
    
    prod = 1;
    for(int i = nums.size() - 1 ; i >= 0 ; i--){
        prod *= nums[i];
        ans = max(ans, prod);
        
        if(nums[i] == 0)prod = 1;
    }
    
    return ans;
}
```

## Approach 2.2 : Observation

- Time complexity : O(N) 
- Space complexity : O(1)

```cpp
int maxProduct(vector<int>& nums) { 
    int ans = INT_MIN, pref = 1, suff = 1, n = nums.size();
    
    for(int i = 0 ; i < n ; i++){
        // From front
        pref *= nums[i];
        ans = max(ans, pref);
        
        if(nums[i] == 0)pref = 1;
        
        // From back 
        suff *= nums[n - i - 1];
        ans = max(ans, suff);
        
        if(nums[n - i - 1] == 0)suff = 1;
    }
    
    return ans;
}
```

## Approach 3.1 : Kadanes (1 Pass)

- Time complexity : O(N) 
- Space complexity : O(1)

```cpp
int maxProduct(vector<int>& nums) { 
    int ans = nums[0];
    int mi = ans;
    int ma = ans;
    
    for(int i = 1 ; i < nums.size() ; i++){
        if(nums[i] < 0)swap(mi,ma);
        
        ma = max(nums[i], ma*nums[i]);
        mi = min(nums[i], mi*nums[i]);
        ans = max(ans,ma);
    }
    return ans;
}
```

