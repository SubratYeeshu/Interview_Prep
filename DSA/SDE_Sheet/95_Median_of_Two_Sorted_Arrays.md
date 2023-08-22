# Median of Two Sorted Arrays

## Problem statement

- Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

## Approach 1 : Linear Search

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

## Approach 2 : Binary Search

Time complexity : O(Log(min(n, m))) 
Space complexity : O(1)

```cpp
/*
    
    -> For finding the median we have to make a optimal cut such that before and after cut the state should be valid.
    -> Valid state means all the elements to the left of cut must be smaller than all the elements to the right of the cut
    -> To know whether the state is valid we have to check only four variables l1, r1, l2, r2
    -> Using these vairables we will apply binary search for valid cut and we can find median whenever state is valid
    -> (max(l1, l2) + min(r1, r2)) / 2 is median if even length
    -> (min(r1, r2)) is median if odd length because one extra element on the right side
    -> Condition for partition l1 < r2 && l2 < r1 (this is because l1 will always be less then r1 because sorted)
    -> If(l1 > r2)high = cut1 - 1, else if(l2 > r1)low = cut1 + 1
    -> We will change cut1 to adjust
    -> Number of elements in part one or index of cut in part1 is cut1 = (low + high) / 2
    -> Number of elements in part two or index of cut in part2 is cut2 = (n1 + n2) / 2 - cut1 -> (half - cut1) -> number of elements in partition in second array for number of elements to be equal
    -> Setting INT_MIN to the left partition (l1, l2) to adjust the logic

*/
    
double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
    int n = nums1.size(), m = nums2.size();
    if(n > m)return findMedianSortedArrays(nums2, nums1);
    
    int low = 0, high = n;
    
    while(low <= high){
        int cut1 = (low + high) / 2;
        int cut2 = (n + m) / 2 - cut1;   // (n+m)/2 can be stored outside as variable left which is constant
        
        int l1 = cut1 == 0 ? INT_MIN : nums1[cut1 - 1];
        int l2 = cut2 == 0 ? INT_MIN : nums2[cut2 - 1];
        int r1 = cut1 == n ? INT_MAX : nums1[cut1];
        int r2 = cut2 == m ? INT_MAX : nums2[cut2];
        
        
        // Valid partition
        if(l1 <= r2 && l2 <= r1)return (n + m) % 2 == 0 ? (max(l1, l2) + min(r1, r2)) / 2.00 : min(r1, r2);
        
        // Adjust the cut
        else if(l1 > r2)high = cut1 - 1;
        else if(l2 > r1)low = cut1 + 1;
    }
    
    return 0.0  ;
}
```