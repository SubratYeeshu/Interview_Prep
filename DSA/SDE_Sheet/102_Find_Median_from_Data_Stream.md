# Find Median from Data Stream

## Problem statement

The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values. For example, for arr = [2,3,4], the median is 3. For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.

Implement the MedianFinder class: MedianFinder() initializes the MedianFinder object. void addNum(int num) adds the integer num from the data stream to the data structure. double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.

## Approach 1 : Brute (Insertion Sort)

- Time complexity : O(N^2) 
- Space complexity : O(N)

```cpp
vector<int> store; 
void addNum(int num){
    if (store.empty())store.push_back(num);
    else store.insert(lower_bound(store.begin(), store.end(), num), num);     
}

double findMedian(){
    int n= store.size()-1;
    double ans;
        
    if(n % 2 == 0)ans = store[n/2];
    else ans=(store[n/2]+ store[(n/2) + 1]) / 2.0;        
    return ans;
}
```

## Approach 2.1 : Optimal (2 Priority Queues (Min / Max))

- Time complexity : O(NLogN) 
- Space complexity : O(N)

```cpp
/*

    -> We have to declare two pq min heap and max heap
    -> We will store the numbers in those heaps 
    -> The max heap will be on the left side and can have one more element than the right min heap to adjust odd size cases
    -> The median will be equal to left_max_heap.top() when the size is odd
    -> The median will be equal to (left_max.heap.top() + right_min_heap.top()) / 2
    -> To visualize think of different stream of integers comming

*/
priority_queue<int, vector<int>, greater<int>> right_min_heap;
priority_queue<int> left_max_heap;

MedianFinder() {
    
}

void addNum(int num) {
    // Number to the left of the partition
    if(left_max_heap.size() == 0 || left_max_heap.top() > num)left_max_heap.push(num);
    // Number to the right of partition
    else right_min_heap.push(num);
    
    // Adjustment of the heap to get the sorted type behaviour
    if(left_max_heap.size() > right_min_heap.size() + 1){
        right_min_heap.push(left_max_heap.top());
        left_max_heap.pop();
    }else if(right_min_heap.size() > left_max_heap.size()){
        left_max_heap.push(right_min_heap.top());
        right_min_heap.pop();
    }        
}

double findMedian() {
    return left_max_heap.size() > right_min_heap.size() ? 
        left_max_heap.top() : (left_max_heap.top() + right_min_heap.top()) / 2.00;
}
```

