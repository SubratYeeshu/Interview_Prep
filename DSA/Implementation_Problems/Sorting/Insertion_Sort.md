# Insertion Sort

- Array is divided in two parts sorted and unsorted, choose first element from the unsorted part and put it into the right position in sorted part 

- Insertion Sort is a stable sorting algorithm. We swap elements only when A is less than B. If A is equal to B, we do not swap them, hence relative order between equal elements will be maintained.

- Insertion sort takes the maximum time to sort if elements are sorted in reverse order. And it takes minimum time (Order of n) when elements are already sorted. 

-Insertion sort is used when number of elements is small. It can also be useful when the input array is almost sorted, and only a few elements are misplaced in a complete big array.

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