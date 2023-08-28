# Heap

- Heap is a Complete Binary Tree (All the levels are completely filled except the lowest one, which is filled from the left) which have heap order priority.

- There are two types of heap Max - Heap, Min - Heap

- Can be implemented using array, for any node at ith index, child nodes are on (2\*i + 1) and (2\*i + 2) and for any child node its parent is present on the ((i - 1) / 2) index (0 based indexing). For (1 based indexing) child nodes are on (2\*i) and (2\*i + 1)index and the parent node on (i / 2) index.

## Use case 


- Priority Queues: Heaps are commonly used to implement priority queues, where elements with higher priority are extracted first. This is useful in many applications such as scheduling tasks, handling interruptions, and processing events.

- Sorting Algorithms: Heapsort, a comparison-based sorting algorithm, is implemented using the Heap data structure. It has a time complexity of O(n log n), making it efficient for large datasets.
Graph algorithms: Heaps are used in graph algorithms such as Dijkstra’s shortest path algorithm, Prim’s minimum spanning tree algorithm, and the A* search algorithm.

- File Compression: Heaps are used in data compression algorithms such as Huffman coding, which uses a priority queue implemented as a min-heap to build a Huffman tree.

- Dynamic programming: Heaps are used in dynamic programming algorithms such as the greedy algorithm, where elements are processed in order of priority.

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
    
    // Inserting into the Heap
    void insert(int value) {
        arr.push_back(value);
        int index = arr.size() - 1;
        while (index > 0 && arr[index] > arr[(index - 1) / 2]) {
            swap(arr[index], arr[(index - 1) / 2]);
            index = (index - 1) / 2;
        }
    }
    
    // Pop the top most element from the heap
    void pop_from_heap(){
        if(arr.size() == 0){
            cout << "Nothing to delete" << endl;
            return;
        }
        
        // Put last element into first index
        arr[0] = arr[arr.size() - 1];
        arr.pop_back();
        
        // Take root to its correct positionw
        int i = 0;
        while(i < arr.size()){
            int leftIdx = 2 * i + 1;
            int rightIdx = 2 * i  + 2;
            
            if(leftIdx < arr.size() && arr[i] < arr[leftIdx]){
                swap(arr[i], arr[leftIdx]);
                i = leftIdx;
            }else if(rightIdx < arr.size() && arr[i] < arr[rightIdx]){
                swap(arr[i], arr[rightIdx]);
                i = rightIdx;
            }else return;
        }
        
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
    // int n = arr.size();
    maxH.buildHeap(maxH.arr, maxH.arr.size());
    
    // maxH.pop_from_heap();
    // maxH.heap_sort();
    maxH.printHeap(maxH.arr, maxH.arr.size());
 
   
    return 0;
}
```