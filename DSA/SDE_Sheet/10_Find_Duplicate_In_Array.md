# Find the duplicate number

## Problem statement

Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
There is only one repeated number in nums, return this repeated number.

# Approach 1 : (Sorting)

- Time complexity : O(N \* log(N))  
- Space complexity : O(1) (Ignoring space taken by sorting algo)

```cpp
int findDuplicate(vector<int>& nums) {
    sort(nums.begin(), nums.end());
    for(int i = 1 ; i < nums.size() ; i++) {
        if(nums[i] == nums[i - 1]) return nums[i];
    }
    return -1;
}
```

## Approach 2 : (Calculate frequency)

- Time complexity : O(N)  
- Space complexity : O(N)

```cpp
int findDuplicate(vector<int>& nums) {
    int n = nums.size();
    vector<int> freq(n + 1, 0);
    for(int &i : nums)freq[i]++;
    for(int i = 0 ; i < n + 1 ; i++)
        if(freq[i] > 1) return i;
    return -1;
}
```

## Approach 3 : (Floyd's LL Cycle)

- Time complexity : O(N)  
- Space complexity : O(1)

```cpp
int findDuplicate(vector<int>& nums) {
    int fast = nums[0];
    int slow = nums[0];
    
    do{
        slow = nums[slow];
        fast = nums[nums[fast]];
    }while(slow!=fast);
    
    fast = nums[0];
    
    while(slow!=fast){
        slow = nums[slow];
        fast = nums[fast];
    }
    
    return slow;
}   
```

## Approach 4 : Swap Sort (Non Intutive - Scalable)

- Time complexity : O(N)  
- Space complexity : O(N)

```cpp
/*

    - Return nums[i] - > Duplicate
    - Return i + 1 - > Missing

*/
int findDuplicate(vector<int>& nums) {
    int i=0, n= nums.size(); 
    while(i<n){
        if(nums[i] != nums[nums[i] - 1]) swap(nums[nums[i] - 1], nums[i]);
        else i++;
    }
    for(int i = 0 ; i < n ; i++){
        if(nums[i] != i + 1) return nums[i];
    }
    return -1;
}
``` 