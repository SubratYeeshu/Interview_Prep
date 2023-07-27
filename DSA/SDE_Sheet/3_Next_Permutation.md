# Next Permutation

## Problem Statement
- A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
    For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].

- The next permutation of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).
    For example, the next permutation of arr = [1,2,3] is [1,3,2].
    Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
    While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
- Given an array of integers nums, find the next permutation of nums.
- The replacement must be in place and use only constant extra memory.

## Approach 1 : Using STL

Time complexity : O(N)  
Space complexity : O(1)

```cpp
/* 
    -> next_permutation STL
    -> Takes 3 argurments / 1 Optional
    -> next_permutation(itr.begin(), itr.end(), custom_comp_fn)
    -> Return type bool
    
    -> example : 
        vector<int> v = {9, 6, 2, 6, 5, 6, 4};
        bool isPermPossible = next_permutation(v.begin(), v.end());
        cout << "Permutation possible = " << isPermPossible << " " << print(v);
*/
void next_permutation_stl(){
    vector<int> v = {9, 6, 2, 6, 5, 6, 4};
    bool isPermPossible = next_permutation(v.begin(), v.end());
    cout << "Permutation possible = " << isPermPossible << " : Possible Perm -> ";
    for(auto i : v)cout << i << " ";
    
    // O(N * N!)
    do{
        for(auto i : v)cout << i << " ";
        cout << endl;
    }while(next_permutation(v.begin(), v.end()));
}
void nextPermutation(vector<int>& nums) {
    // next_permutaion_stl();
    next_permutation(nums.begin(), nums.end());
}
```

## Approach 2 : Implementation Based

Time complexity : O(N)  
Space complexity : O(1)

```cpp
void nextPermutation(vector<int>& nums) {
    // Step 1 : Find the break point where the behaviour of array is changed from back
    int index = -1, n = nums.size();
    for(int i = n - 2 ; i >= 0 ; i--){
        if(nums[i] < nums[i + 1]){
            index = i;
            break;
        }
    }
    
    if(index == -1){
        reverse(nums.begin(), nums.end());
        return;
    }
    
    // Step 2 : Swap the first number which is greater than num[index]
    for(int i = n - 1 ; i >= 0 ; i--){
        if(nums[i] > nums[index]){
            swap(nums[i], nums[index]);
            break;
        }
    }
    
    // Step 3 : Sort / reverse the remaining after the index because we have a monotonic behavior
    // sort(nums.begin() + index + 1, nums.end());
    reverse(nums.begin() + index + 1, nums.end());
    
}

```
