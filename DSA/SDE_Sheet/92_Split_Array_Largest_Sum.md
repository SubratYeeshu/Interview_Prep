# Split Array Largest Sum 

- Given an integer array nums and an integer k, split nums into k non-empty subarrays such that the largest sum of any subarray is minimized. Return the minimized largest sum of the split. A subarray is a contiguous part of the array.

## Approach 1 : Binary Search 

Time complexity : O(NLogN) 
Space complexity : O(1)

```cpp
int getSubarraysCount(vector<int> &nums, int sum){
    int avaibleSum = sum, noOfSubarray = 1;
    for(int i = 0 ; i < nums.size() ; i++){
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

int splitArray(vector<int>& nums, int k) {
    // Binary Search on number of sum

    int n = nums.size();
    if(k > n)return -1;
    
    // sort(nums.begin(), nums.end());  Contiguos allocation
    
    int low = *max_element(nums.begin(), nums.end()), high = accumulate(nums.begin(), nums.end(), 0), ans = 0;

    while(low <= high){
        int mid = (low + high) / 2;


        int noOfSubarrays = getSubarraysCount(nums, mid);
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