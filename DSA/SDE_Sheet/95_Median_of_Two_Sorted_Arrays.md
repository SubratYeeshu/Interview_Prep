# Median of Two Sorted Arrays

## Problem statement

- Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

## Approach 1 : Linear Sea

Time complexity : O(N) 
Space complexity : O(N)

```cpp
void merge(vector<int>& nums1,vector<int>& nums2,vector<int>& nums){
    int i = 0, j = 0, k = 0;
    while(i < nums1.size() && j < nums2.size()){
        if(nums1[i] <= nums2[j])nums[k++] = nums1[i++];
        else if(nums1[i] > nums2[j])nums[k++] = nums2[j++];
    }
    while(i < nums1.size())nums[k++] = nums1[i++];
    while(j < nums2.size())nums[k++] = nums2[j++];
    
}
double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
    int n = nums1.size(), m = nums2.size();
    vector<int>nums(n + m);
    merge(nums1, nums2, nums);
    int z = n + m;
    if(z % 2 == 0) return (double)(nums[z / 2 - 1] + nums[z / 2]) / 2.00;
    else return nums[z / 2];
}
```