# Binary Search

## Identification of binary search

- Sorted Array
- Rotated Sorted Array
- Given a range 
- Find minimum / maximum value to fulfill a condtition 
- Maximize the minimum / Minimize the maximum

## Sorted Rotated Pattern

## Search Element in sorted rotated array

```cpp
bool search(vector<int>& nums, int target) {
    int low = 0, high = nums.size() - 1;

    while(low <= high){
        int mid = (low + high) / 2;

        if(nums[mid] == target)return true;
        
        // When all three are equal it is impossible to know where the target is so we shrink down the space
        if(nums[low] == nums[mid] && nums[mid] == nums[high]){
            low++; high--; continue;
        }
        
        // Left half is sorted
        if(nums[low] <= nums[mid]){
            if(nums[low] <= target && nums[mid] >= target)high = mid - 1;  // Target lies in between
            else low = mid + 1;
        }else{
            // Right half is sorted
            if(nums[mid] <= target && target <= nums[high])low = mid + 1;
            else high = mid - 1;
        }
    }
    return false;
}
```

## Find Pivot

```cpp
int findMin(vector<int>& temp) {
    int n = temp.size();
    int low = 0, high = n - 1;
    if(n == 1)return temp[0];
    if(n == 2)return min(temp[0], temp[1]);
    
    
    while(low <= high){
        int mid = (low + high) / 2;
        if(temp[low] < temp[high])return temp[low];  // Sorted
        if(mid < high && temp[mid] > temp[mid + 1])return temp[mid + 1];
        else if(mid > low && temp[mid] < temp[mid - 1])return temp[mid];
        else if(temp[low] >= temp[mid])high = mid - 1;
        else low = mid + 1;
    }
    
    return 0;
}
```

```cpp
int findMin(vector<int>& nums) {
    int low = 0, high = nums.size() - 1, ans = INT_MAX;
    if(nums[0] < nums[high])return nums[0];
    
    while(low < high){
        int mid = low + (high - low) /2;
        
        if(nums[0] <= nums[mid]){
            low = mid + 1;
        }else{
            high = mid;
        }
    }
    return nums[high];
}
```

## Find Min / Max Pattern

```cpp
int check(vector<int> &nums, int mid){
    
}

// Apply binary search on range of divisor (mid -> divisor)
int smallestDivisor(vector<int>& nums, int threshold) {
    int low = 1, high = *max_element(nums.begin(), nums.end()), ans = 0;
    while(low <= high){
        int mid = (low + high) / 2;
        
        int temp = check(nums, mid);
        
        if(temp (condition) threshold){
            ans = mid;
            high = mid - 1;
        }else{
            low = mid + 1;
        }
    }
    
    return ans;
}
```

```cpp
bool check(vector<int> &nums, int mid){
    

    return x condition y;
}

// Apply binary search on range of divisor (mid -> divisor)
int smallestDivisor(vector<int>& nums, int threshold) {
    int low = 1, high = *max_element(nums.begin(), nums.end()), ans = 0;
    while(low <= high){
        int mid = (low + high) / 2;
        
        int temp = check(nums, mid);
        
        if(check(nums, mid)){
            ans = mid;
            high = mid - 1;
        }else{
            low = mid + 1;
        }
    }
    
    return ans;
}
```

## Min (Max) / Max (Min) Pattern

- Similar to min max pattern

```cpp
bool canPlace(vector<int> &nums, int distance, int cows){
    // Greedly put the first cow at index 0 because putting it later has no meaning since distance is positive
    
    int last = nums[0], cowsArranged = 1;
    for(int i = 1 ; i < nums.size() ; i++){
        if(nums[i] - last >= distance){
            last = nums[i];
            cowsArranged++;
        }
    }
    
    return cowsArranged >= cows;
}

int solve(int n, int k, vector<int> &nums) {
    sort(nums.begin(), nums.end());
    int low = 1, high = nums[nums.size() - 1] - nums[0] + 1, ans = 0;
    
    // Mid -> distance, we have to maximize the distance between any two cows, and the minimum distance between two cows is when they are consequtive
    while(low <= high){
        int mid = (low + high) / 2;
        
        if(canPlace(nums, mid, k)){
            ans = mid;
            low = mid + 1;
        }else {
            high = mid - 1;
        }
    }
    
    return ans;
}
```