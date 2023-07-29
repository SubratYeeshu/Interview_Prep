# Merge Sort

Time Complexity : O(N \* LogN)
Space COmplexity : O(N)

- Merge Sort is a stable sorting algorithm. We swap elements only when A is less than B. If A is equal to B, we do not swap them, hence relative order between equal elements will be maintained.

- Merge sort can be usefull for parallel processing 


```cpp
void merge(vector<int> &nums, int low, int mid, int high){
    int left = low, right = mid + 1;
    vector<int> temp;
    
    while(left <= mid && right <= high){
        if(nums[left] > nums[right])temp.push_back(nums[right++]);
        else temp.push_back(nums[left++]);
    }
    
    while(left <= mid)temp.push_back(nums[left++]);
    while(right <= high)temp.push_back(nums[right++]);
    
    for(int i = low ; i <= high ; i++){
        nums[i] = temp[i - low];
    }
}

void merge_sort(vector<int>& nums, int low, int high){
    if(low >= high)return;
    
    int mid = (low + high) / 2;
    
    merge_sort(nums, low, mid);
    merge_sort(nums, mid + 1, high);
    merge(nums, low, mid, high);
}

void mergeSort(vector<int> &nums){
    int low = 0, high = nums.size() -1;
    merge_sort(nums, low, high);
}
```