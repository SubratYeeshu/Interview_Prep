# Selection Sort

- The default implementation of the Selection Sort Algorithm is not stable. 

Time Complexity : O(N \* 2)
Space COmplexity : O(1)

## Code

```cpp
void insertionSort(vector<int> &nums){
    int n = nums.size();
    for(int i = 1 ; i < n ; i++){
        int key = nums[i], j = i - 1;
        // We have to insert the key to the right position
        while(j >= 0 && key < nums[j]){
            nums[j + 1] = nums[j];
            j--;
        }
        nums[j + 1] = key;+
    }
}
```