# Merge two sorted arrays

## Problem statement

- Given two sorted arrays, we need to merge them into a sorted array, when n is the size of the first array, and m is the size of the second array. In final output, first array contains first n sorted elements, second array contains last m sorted elements in sorted order.

## Approach 1 (Extra memory)

Time complexity : O(N + M)  
Space complexity : O(N + M)

```cpp
void merge(long long arr1[], long long arr2[], int n, int m) 
{ 
    vector<int> temp;
    
    for(int i = 0 ; i < n ; i++)temp.push_back(arr1[i]);
    for(int i = 0 ; i < m ; i++)temp.push_back(arr2[i]);
    
    sort(temp.begin(), temp.end());
    
    int i = 0;
    while(i < n)arr1[i] = temp[i++];
    while(i < n + m)arr2[i - n] = temp[i++];
} 
```

## Approach 2 (Insertion sort)

Time complexity : O(N \* M)  
Space complexity : O(1)

```cpp
void merge(long long arr1[], long long arr2[], int n, int m)
{
    for(int i = 0 ; i < n ; i++) {
        if(arr1[i] > arr2[0]) {
            swap(arr1[i], arr2[0]);
            for(int j = 0 ; j < m - 1 ; j++) {
                if(arr2[j] > arr2[j + 1])
                    swap(arr2[j], arr2[j + 1]);
                else break;
            }
        }
    }
}
```

## Approach 3 (Two pointer and one time sort)

Time complexity : O(N \* log(N)) + O(M \* log(M))  
Space complexity : O(1) (Ignoring space of sorting algo), otherwise O(N + M) due to merge sort

```cpp
void merge(long long arr1[], long long arr2[], int n, int m)
{
    int i = n - 1, j = 0;
    while(i >= 0 && j < m) {
        if(arr1[i] > arr2[j])
            swap(arr1[i--], arr2[j++]);
        else
            break;
    }
    sort(arr1, arr1 + n);
    sort(arr2, arr2 + m);
}
```

## Approach 4 (Gap method)

Time complexity : O((N + M) \* log(N + M))  
Space complexity : O(1)

```cpp
int nextGap(int gap) {
    if(gap <= 1) return 0;
    return (gap / 2) + (gap % 2);
}
void merge(long long arr1[], long long arr2[], int n, int m)
{
    int sz = n + m, gap = nextGap(sz);
    while(gap) {
        for(int i = 0, j = i + gap; j < sz; i++, j++) {
            long long *x = i < n ?  arr1 + i : arr2 + i - n;
            long long *y = j < n ? arr1 + j : arr2 + j - n;
            if(*x > *y)
                swap(*x, *y);
        }
        gap = nextGap(gap);
    }
}
```
