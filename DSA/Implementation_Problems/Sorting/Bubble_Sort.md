# Bubble Sort

Time Complexity : O(N \* 2)
Space COmplexity : O(1)

- Bubble Sort is a stable sorting algorithm. We swap elements only when A is less than B. If A is equal to B, we do not swap them, hence relative order between equal elements will be maintained.

- Bubble sort takes minimum time (Order of n) when elements are already sorted. Hence it is best to check if the array is already sorted or not beforehand, to avoid O(N2) time complexity.

```cpp
void bubbleSort(vector<int> &nums){
    int n = nums.size();
    for(int i = 0 ; i < n ; i++){
        bool swapped = false;
        // Last i elements are in - place in ith pass
        for(int j = 0 ; j < n - i - 1 ; j++){
            if(nums[j + 1] < nums[j]){
                swap(nums[j + 1], nums[j]);
                swapped = true;
            }
        }
        // Optimization
        if(!swapped)break;
    }
}
```