# Finding the pivot Element

## Approach 1 

```cpp
int getPivot(vector<int> &temp){
    int low = 0, high = temp.size() - 1;
    while(low <= high){
        int mid = (low + high) / 2;

        if(mid < high && temp[mid] > temp[mid + 1])return mid + 1;
        else if(mid > low && temp[mid] < temp[mid - 1])return mid;
        else if(temp[low] >= temp[mid])high = mid - 1;
        else low = mid + 1;
    }

    return 0;
}
```

## Approach 2 

```cpp
// Fiding pivot as smallest element 
int findPivot(vector<int>&nums){
    int start = 0;
    int end = nums.size() - 1;
    // Finding smallest as pivot
    while(start < end){
        int mid = start + (end - start) /2;
        if(nums[mid] < nums[end]){
            end = mid;
        }else{
            start = mid + 1;
        }
    }
    return start;
}
```