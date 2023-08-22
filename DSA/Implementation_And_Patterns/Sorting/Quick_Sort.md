# Quick Sort

- Quick Sort is a not a stable sorting algorithm. 

- How can QuickSort's performance be improved?
Randomize pivot selection to avoid worst-case scenarios.
Switch to insertion sort for small sub-arrays to reduce overhead.

Time Complexity : O(N \* LogN)
Space COmplexity : O(1)

```cpp
// All elements smaller to the pivot must be one the left and greater to the right after placing the pivot
int findPivot(vector<int> &nums, int low, int high){
    int i = low, j = high;  
    int pivot = nums[low];
    while(i < j){
        while(nums[i] <= pivot && i <= high)i++;
        while(nums[j] > pivot && j >= low)j--;
        if(i < j)swap(nums[i], nums[j]);
    }
    
    swap(nums[low], nums[j]);
    return j;
}

void quickSort(vector<int> &nums, int low, int high){
    if(low > high)return;
    
    
    int pivotIndex = findPivot(nums, low, high);
    quickSort(nums, low, pivotIndex - 1);
    quickSort(nums, pivotIndex + 1, high);
}
```