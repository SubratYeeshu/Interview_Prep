# Count inversions

## Problem Statement

Given an array of integers. Find the Inversion Count in the array. Inversion Count: For an array, inversion count indicates how far (or close) the array is from being sorted. If array is already sorted then the inversion count is 0. If an array is sorted in the reverse order then the inversion count is the maximum. Formally, two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j.

## Approach 1 : Merge Sort (Using Global Variable)

- Time complexity : O(N \* LogN) 
- Space complexity : O(N)

```cpp
long long int cnt = 0;
void merge(long long arr[], long long N, int i, int mid, int j){
    long long left = i, right = mid + 1;
    vector<long long> temp;
    
    while(left <= mid && right <= j){
        if(arr[left] <= arr[right]){
            temp.push_back(arr[left]);
            left++;
        }else{
            cnt += mid - left + 1;
            temp.push_back(arr[right]);
            right++;
        }
    }
    
    while(left <= mid)temp.push_back(arr[left++]);
    while(right <= j)temp.push_back(arr[right++]);
    
    for(long long x = i ; x <= j ; x++){
        arr[x] = temp[x - i];
    }
    
}

void merge_sort(long long arr[], long long N, int i, int j){
    if(i < j){
        int mid = (i + j) / 2;
        merge_sort(arr, N, i, mid);
        merge_sort(arr, N, mid + 1, j);
        merge(arr, N, i, mid, j);
    }
}
long long getInversions(long long *arr, int n){
    merge_sort(arr, n, 0, n - 1);
    return cnt;
}
```

## Approach 2 : Merge Sort (With return statements)

- Time complexity : O(N \* LogN) 
- Space complexity : O(N)

```cpp
long long merge(long long arr[], long long N, int i, int mid, int j){
        long long int cnt = 0;
        long long left = i, right = mid + 1;
        long long int temp[j - i + 1];  // We can also use vector instead of array here
        long long k = 0;
        
        while(left <= mid && right <= j){
            if(arr[left] <= arr[right]){
                temp[k++] = (arr[left]);
                left++;
            }else{
                cnt += mid - left + 1;
                temp[k++] = (arr[right]);
                right++;
            }
        }
        
        while(left <= mid)temp[k++] = (arr[left++]);
        while(right <= j)temp[k++] = (arr[right++]);
        
        for(long long x = i ; x <= j ; x++){
            arr[x] = temp[x - i];
        }
        
        return cnt;
        
    }
    
    long long merge_sort(long long arr[], long long N, int i, int j){
        long long int count = 0;
        if(i < j){
            int mid = (i + j) / 2;
            count += merge_sort(arr, N, i, mid);
            count += merge_sort(arr, N, mid + 1, j);
            count += merge(arr, N, i, mid, j);
        }
        
        return count;
    }
    
    
    long long int inversionCount(long long arr[], long long N){
        return merge_sort(arr, N, 0, N - 1);
    }
```