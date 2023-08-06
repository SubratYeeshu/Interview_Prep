# Heap Sort

- Heap Sort is a not a stable sorting algorithm. 

- The heap sort algorithm consists of two phases. In the first phase, the array is converted into a max heap. And in the second phase, the highest element is removed (i.e., the one at the tree root) and the remaining elements are used to create a new max heap.

- The Merge sort is slightly faster than the Heap sort. But on the other hand merge sort takes extra memory. Depending on the requirement, one should choose which one to use.

- Not a divide and conquer algorithm

Time Complexity : O(N \* LogN)
Space Complexity : O(1)

## Code

```cpp
#include <bits/stdc++.h>
using namespace std;
 
class MaxHeap{
    public: 
    // vector<int> arr; 
    vector<int> arr {1, 3, 5, 4, 6, 13, 10, 9, 8, 15, 17}; 
    void heapify(vector<int> &arr, int N, int i){
        int largest = i;   // Initialize largest as root
        int l = 2 * i + 1; // left = 2 * i + 1
        int r = 2 * i + 2; // right = 2 * i + 2
     
        // If left child is larger than root
        if (l < N && arr[l] > arr[largest])largest = l;
        // If right child is larger than root
        if (r < N && arr[r] > arr[largest])largest = r;
            
        if (largest != i) {
            swap(arr[i], arr[largest]);
            heapify(arr, N, largest);
        }
    }
     
    // Build Heap from array
    void buildHeap(vector<int> &arr, int N){
        int startIdx = (N / 2) - 1;
     
        // Perform reverse level order traversal, leaf node are already in heap
        for (int i = startIdx ; i >= 0; i--) {
            heapify(arr, N, i);
        }
    }
    
    // Printing the heap array
    void printHeap(vector<int> arr, int N){
        cout << "Array representation of Heap is:\n";
        for (int i = 0; i < N; ++i)
            cout << arr[i] << " ";
        cout << "\n";
    }
    
    // Heap Sort 
    void heap_sort(){
        int n = arr.size();
        buildHeap(arr, n);
        
        for (int i = n - 1; i > 0; i--) {
            swap(arr[0], arr[i]);
            heapify(arr, i, 0);
        }
    }
}; 

 
int main(){
    MaxHeap maxH;
    // maxH.arr.push_back(1);
    // vector<int> arr {1, 3, 5, 4, 6, 13, 10, 9, 8, 15, 17};
    // insert(1); insert(3); insert(5); insert(4); insert(6); insert(13); insert(10); insert(9); insert(8); insert(15); insert(17);   

    maxH.heap_sort();
    maxH.printHeap(maxH.arr, maxH.arr.size());
 
   
    return 0;
}
```