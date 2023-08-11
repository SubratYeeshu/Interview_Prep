# Minimum Platforms 

## Problem statement

- Given arrival and departure times of all trains that reach a railway station. Find the minimum number of platforms required for the railway station so that no train is kept waiting. Consider that all the trains arrive on the same day and leave on the same day. Arrival and departure time can never be the same for a train but we can have arrival time of one train equal to departure time of the other. At any given instance of time, same platform can not be used for both departure of a train and arrival of another train. In such cases, we need different platforms.

## Approach 1 : Greedy

Time complexity : O(N \* LogN + N) 
Space complexity : O(1)

```cpp
int findPlatform(int arr[], int dep[], int n){
    // Sorting for secuential processing
    sort(arr, arr + n);
    sort(dep, dep + n);
    
    int count = 0, maxi = 0, i = 0, j = 0;
    while(i < n && j < n){
        if(arr[i] <= dep[j]){
            count++;
            i++;
        }else{
            maxi = max(maxi, count);
            count--;
            j++;
        }
    }
    
    return maxi;
}
```