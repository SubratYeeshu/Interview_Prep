# Remove Duplicates from Sorted Array

## Problem statement

- Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same. Then return the number of unique elements in nums. Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things: 
- Change the array nums such that the first k elements of nums contain the unique elements in the order they were present in nums initially. The remaining elements of nums are not important as well as the size of nums.
- Return k.

## Approach 1 : Using set

Time complexity : O(N) 
Auxiliary space : O(N)

```cpp
int removeDuplicates(vector<int>& nums) {
    int n = nums.size(), i = 0;
    set <int> s;
    for(int i = 0 ; i < n ; i++)s.insert(nums[i]);
    
    for(auto x : s){
        nums[i] = x;
        i++;
    }
    
    return i;
}
```

## Approach 2 : Using two pointers

Time complexity : O(N) 
Auxiliary space : O(1)

```cpp
int removeDuplicates(vector<int>& nums) {
    int n = nums.size(), i = 0, j = 1;
    if(n <= 1)return n;
    
    for(j ; j < n ; j++){
        if(nums[i] != nums[j]){
            i++;
            nums[i] = nums[j];
        }
    }
    
    return i + 1;
}
```