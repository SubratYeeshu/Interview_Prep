# Find the Smallest Divisor Given a Threshold

## Problem statement

- Given an array of integers nums and an integer threshold, we will choose a positive integer divisor, divide all the array by it, and sum the division's result. Find the smallest divisor such that the result mentioned above is less than or equal to threshold. Each result of the division is rounded to the nearest integer greater than or equal to that element. (For example: 7/3 = 3 and 10/2 = 5). The test cases are generated so that there will be an answer.

- Binary Search (Find min / max pattern)

## Approach 1 : Binary Search

Time complexity : O(NLogN) 
Space complexity : O(1)

```cpp
int check(vector<int> &nums, int mid){
    int sum = 0;
    for(auto i : nums)sum += (ceil)((double)i / (double)mid);  // Always remember ceil is only useful in case of double
    return sum;
}

// Apply binary search on range of divisor (mid -> divisor)
int smallestDivisor(vector<int>& nums, int threshold) {
    int low = 1, high = *max_element(nums.begin(), nums.end()), ans = 0;
    while(low <= high){
        int mid = (low + high) / 2;
        
        int sum = check(nums, mid);
        
        if(sum <= threshold){
            ans = mid;
            high = mid - 1;
        }else{
            low = mid + 1;
        }
    }
    
    return ans;
}
```