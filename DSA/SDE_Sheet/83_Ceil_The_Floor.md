# Ceil The Floor

## Problem statement

- Given an unsorted array Arr[] of N integers and an integer X, find floor and ceiling of X in Arr[0..N-1]. Floor of X is the largest element which is smaller than or equal to X. Floor of X doesn’t exist if X is smaller than smallest element of Arr[]. Ceil of X is the smallest element which is greater than or equal to X. Ceil of X doesn’t exist if X is greater than greatest element of Arr[].

- Binary Search (Find min / max pattern)

## Approach 1 : Finding the ceil and the floor

Time complexity : O(LogN) 
Space complexity : O(1)

```cpp
pair<int, int> getFloorAndCeil(int arr[], int n, int x) {
    pair<int, int> p {-1, -1};
    sort(arr, arr + n);
    int low = 0, high = n - 1;
    
    while(low <= high){
        int mid = (low + high) / 2;
        
        if(arr[mid] == x)return {arr[mid], arr[mid]};
        else if(arr[mid] < x){
            // Floor
            p.first = arr[mid];
            low = mid + 1;
        }else {
            // Ceil
            p.second = arr[mid];
            high = mid - 1;
        }
    }
    
    return p;
}
```