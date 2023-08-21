# Single Element in a Sorted Array

## Problem statement

- You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once. Return the single element that appears only once. Your solution must run in O(log n) time and O(1) space.

- Binary Search (Sorted Rotated pattern)
## Approach 1 : Brute

Time complexity : O(N) 
Space complexity : O(1)

```cpp
int singleNonDuplicate(vector<int>& arr) {
    int n = arr.size(); //size of the array.
    if (n == 1) return arr[0];

    for (int i = 0; i < n; i++) {

        //Check for first index:
        if (i == 0) {
            if (arr[i] != arr[i + 1])
                return arr[i];
        }
        //Check for last index:
        else if (i == n - 1) {
            if (arr[i] != arr[i - 1])
                return arr[i];
        }
        else {
            if (arr[i] != arr[i - 1] && arr[i] != arr[i + 1])
                return arr[i];
        }
    }

    // dummy return statement:
    return -1;
}
```

## Approach 2 : XOR

Time complexity : O(N) 
Space complexity : O(1)

```cpp
int singleNonDuplicate(vector<int>& arr) {
    int n = arr.size(); //size of the array.
    int ans = 0;
    // XOR all the elements:
    for (int i = 0; i < n; i++) {
        ans = ans ^ arr[i];
    }
    return ans;
}
```

## Approach 3 : Binary Search (Optimal)

Time complexity : O(LogN) 
Space complexity : O(1)

```cpp
// Odd index -> second occurence of element, Even index -> first occurence of element
// If mid - 1 && mid is equal && mid is odd then the left half is correct we have to move to the right half to get the answer
// If mid + 1 && mid is equal && mid is even then same

int singleNonDuplicate(vector<int>& nums) {
    int n = nums.size();
    int low = 1, high = n - 2;
    
    // Edge cases
    if(n == 1)return nums[0];
    if(nums[0] != nums[1])return nums[0];
    if(nums[n - 2] != nums[n - 1])return nums[n - 1];
    
    while(low <= high){
        int mid = (low + high) / 2;
        
        if(nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1])return nums[mid];
        
        if(!(mid&1) && nums[mid] == nums[mid + 1] || (mid&1) && nums[mid] == nums[mid - 1])low = mid + 1;
        else high = mid - 1;
    }
    
    return -1;
}
```