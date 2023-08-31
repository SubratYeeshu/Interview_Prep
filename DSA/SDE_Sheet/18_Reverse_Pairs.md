# Reverse Pairs

## Problem statement

Given an integer array nums, return the number of reverse pairs in the array. A reverse pair is a pair (i, j) where:
- 0 <= i < j < nums.length and
- nums[i] > 2 * nums[j].


## Approach 1 : (Global variable)

- Time complexity : O(N \* LogN) 
- Space complexity : O(N)

```cpp
int count = 0;
void merge(vector<int> &nums, int low, int mid, int high) {
    vector<int> temp;
    int left = low, right = mid + 1;  
    while (left <= mid && right <= high) {
        if (nums[left] <= nums[right])temp.push_back(nums[left++]);
        else temp.push_back(nums[right++]);
    }
    while (left <= mid)temp.push_back(nums[left++]);
    while (right <= high)temp.push_back(nums[right++]);
    for (int i = low; i <= high; i++)nums[i] = temp[i - low];
}

void countPairs(vector<int> &nums, int low,int mid, int high) {
    int right = mid + 1;
    for(int i = low ; i <= mid ; i++) {
        while(right <= high && nums[i]> 2 * 1ll*nums[right])right++;
        count += (right - (mid + 1));
    }
}
void mergeSort(vector<int>& nums, int low, int high) {
    if (low < high){
        int mid = (low + high) / 2 ;
        mergeSort(nums, low, mid);
        mergeSort(nums, mid + 1, high);
        countPairs(nums, low, mid, high);
        merge(nums, low, mid, high);
    }
    
}
int reversePairs(vector<int>& nums) {
    mergeSort(nums, 0, nums.size() - 1);
    return count;
}
```

## Approach 2 : (Without global variable)

- Time complexity : O(N \* LogN) 
- Space complexity : O(N)

```cpp
void merge(vector<int> &nums, int low, int mid, int high) {
    vector<int> temp;
    int left = low, right = mid + 1;  
    while (left <= mid && right <= high) {
        if (nums[left] <= nums[right])temp.push_back(nums[left++]);
        else temp.push_back(nums[right++]);
    }
    while (left <= mid)temp.push_back(nums[left++]);
    while (right <= high)temp.push_back(nums[right++]);
    for (int i = low; i <= high; i++)nums[i] = temp[i - low];
}

int countPairs(vector<int> &nums, int low,int mid, int high) {
    int right = mid + 1;
    int cnt = 0;
    for(int i = low ; i <= mid ; i++) {
        while(right <= high && nums[i]> 2* 1ll * nums[right])right++;
        cnt += (right - (mid + 1));
    }
    return cnt;
}
int mergeSort(vector<int>& nums, int low, int high) {
    int count = 0;
    if (low < high){
        int mid = (low + high) / 2 ;
        count += mergeSort(nums, low, mid);
        count += mergeSort(nums, mid + 1, high);
        count += countPairs(nums, low, mid, high);
        merge(nums, low, mid, high);
        return count;
    }
    return 0;
    
}
int reversePairs(vector<int>& nums) {
    return mergeSort(nums, 0, nums.size() - 1);
}
```