# Find First and Last Position of Element in Sorted Array

## Problem statement

- Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value. If target is not found in the array, return [-1, -1]. You must write an algorithm with O(log n) runtime complexity.

## Approach 1 : Linear search

Time complexity : O(N) 
Space complexity : O(1)

```cpp
pair<int, int> firstAndLastPosition(vector<int>& arr, int n, int k) {
    int first = -1, last = -1;
    for (int i = 0; i < n; i++) {
        if (arr[i] == k) {
            if (first == -1) first = i;
            last = i;
        }
    }
    return {first, last};
}
```

## Approach 2.1 : Upper Bound / Lower Bound STL

Time complexity : O(LogN) 
Space complexity : O(1)

```cpp
vector<int> searchRange(vector<int>& nums, int target) {
    int n = nums.size();
    vector<int> res {-1, -1};
    
    if(binary_search(nums.begin(), nums.end(), target)){
        res[0] = lower_bound(nums.begin(), nums.end(), target) - nums.begin();
        res[1] = upper_bound(nums.begin(), nums.end(), target) - nums.begin() - 1;
    }
    
    return res;
}
```

## Approach 2.2 : Upper Bound / Lower Bound STL

Time complexity : O(LogN) 
Space complexity : O(1)

```cpp
vector<int> searchRange(vector<int>& nums, int target) {
    int n = nums.size();
    vector<int> res {-1, -1};
    
    auto firstOccurence = lower_bound(nums.begin(), nums.end(), target);
    
    if(firstOccurence == nums.end())return res;
    int firstIndex = firstOccurence - nums.begin();
    if(nums[firstIndex] != target)return res;
    
    res[0] = firstIndex;
    res[1] = upper_bound(nums.begin(), nums.end(), target) - nums.begin() - 1;
    

    
    return res;
}
```

## Approach 3 : Upper Bound / Lower Bound implementation

Time complexity : O(LogN) 
Space complexity : O(1)

```cpp
int upperBound(vector<int> &arr, int n, int x) {
    int low = 0, high = n - 1;
    int ans = n;

    while (low <= high) {
        int mid = (low + high) / 2;
        // maybe an answer
        if (arr[mid] > x) {
            ans = mid;
            //look for smaller index on the left
            high = mid - 1;
        }
        else {
            low = mid + 1; // look on the right
        }
    }
    return ans;
}

int lowerBound(vector<int> &arr, int n, int x) {
    int low = 0, high = n - 1;
    int ans = n;

    while (low <= high) {
        int mid = (low + high) / 2;
        // maybe an answer
        if (arr[mid] >= x) {
            ans = mid;
            //look for smaller index on the left
            high = mid - 1;
        }
        else {
            low = mid + 1; // look on the right
        }
    }
    return ans;
}


pair<int, int> firstAndLastPosition(vector<int>& arr, int n, int k) {
    int lb = lowerBound(arr, n, k);
    if (lb == n || arr[lb] != k) return { -1, -1};
    int ub = upperBound(arr, n, k);
    return {lb, ub - 1};
}
```

## Approach 4 : Plain Binary Search

Time complexity : O(LogN) 
Space complexity : O(1)

```cpp
int firstOccurrence(vector<int> &arr, int n, int k) {
    int low = 0, high = n - 1;
    int first = -1;

    while (low <= high) {
        int mid = (low + high) / 2;
        // maybe an answer
        if (arr[mid] == k) {
            first = mid;
            //look for smaller index on the left
            high = mid - 1;
        }
        else if (arr[mid] < k) {
            low = mid + 1; // look on the right
        }
        else {
            high = mid - 1; // look on the left
        }
    }
    return first;
}

int lastOccurrence(vector<int> &arr, int n, int k) {
    int low = 0, high = n - 1;
    int last = -1;

    while (low <= high) {
        int mid = (low + high) / 2;
        // maybe an answer
        if (arr[mid] == k) {
            last = mid;
            //look for larger index on the right
            low = mid + 1;
        }
        else if (arr[mid] < k) {
            low = mid + 1; // look on the right
        }
        else {
            high = mid - 1; // look on the left
        }
    }
    return last;
}


pair<int, int> firstAndLastPosition(vector<int>& arr, int n, int k) {
    int first = firstOccurrence(arr, n, k);
    if (first == -1) return { -1, -1};
    int last = lastOccurrence(arr, n, k);
    return {first, last};
}
```