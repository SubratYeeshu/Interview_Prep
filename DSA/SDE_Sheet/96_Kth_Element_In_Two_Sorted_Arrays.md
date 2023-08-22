# Kth Element in Two Sorted Arrays

## Problem statement

- Given two sorted arrays arr1 and arr2 of size N and M respectively and an element K. The task is to find the element that would be at the kth position of the final sorted array.


## Approach 1 : Binary Search (Median of two sorted arrays modification)

Time complexity : O(Log(min(n, m))) 
Space complexity : O(1)

```cpp
int kthElement(int nums1[], int nums2[], int n, int m, int k){
    if(n > m)return kthElement(nums2, nums1, m, n, k);
    
    int low = max(0, k - m), high = min(k, n);
    
    while(low <= high){
        int cut1 = (low + high) / 2;
        int cut2 = k - cut1;
        
        int l1 = cut1 == 0 ? INT_MIN : nums1[cut1 - 1];
        int l2 = cut2 == 0 ? INT_MIN : nums2[cut2 - 1];
        int r1 = cut1 == n ? INT_MAX : nums1[cut1];
        int r2 = cut2 == m ? INT_MAX : nums2[cut2];
        
        
        // Valid partition
        if(l1 <= r2 && l2 <= r1)return max(l1, l2);
        
        // Adjust the cut
        else if(l1 > r2)high = cut1 - 1;
        else if(l2 > r1)low = cut1 + 1;
    }
    
    return 0.0  ;
}
```