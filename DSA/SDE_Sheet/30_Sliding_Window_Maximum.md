# Sliding Window Maximum

## Problem statement

- You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

## Approach 1 : Brute

Time complexity : O(N^2)
Space complexity : O(1)

```cpp
vector<int> maxSlidingWindow(vector<int>& nums, int k) {
    int n = nums.size();
    vector<int> ans;
    for(int i = 0 ; i <= n - k ; i++){
        int mx = INT_MIN;
        for(int j = i ; j < i + k ; j++){
            mx = max(mx, nums[j]);
        }
        ans.push_back(mx);
    }
    return ans;
}
```

## Approach 2.1 : PriorityQueue - (Sliding Window - Fixed size Template 1)

Time complexity : O(N \* Logk)
Space complexity : O(k)

```cpp
vector<int> maxSlidingWindow(vector<int>& arr, int k) {
    priority_queue<pair<int,int>> pq;
    vector<int> res;
    int right = 0, left = 0, n = arr.size();

    while(right < n){
        pq.push({arr[right] , right});
        
        if(right - left + 1 < k)right++;
        else if(right - left + 1 == k){
            
            // Removing elements which is of no use
            while(pq.top().second < left)pq.pop();
            res.push_back(pq.top().first);
            left++; right++;
        }
    }
    return res;
}
```

## Approach 2.2 : PriorityQueue - (Sliding Window - Fixed size Template 2)

Time complexity : O(N \* Logk)
Space complexity : O(k)

```cpp
vector<int> maxSlidingWindow(vector<int>& arr, int k) {
    priority_queue<pair<int,int>> pq;
    vector<int> res;
    int right = 0, left = 0, n = arr.size();

    // Preparing fist window
    for(int i = 0; i < k; i++){
        pq.push({arr[i] , i});
    }
    res.push_back(pq.top().first);

    // Checking for rest of the window 
    for(int i = k; i < n; i++){
        pq.push({arr[i] , i});
        while(!(pq.top().second > i - k))pq.pop();
        res.push_back(pq.top().first);
    }
    return res;
}
```

## Approach 3.1 : Dequeue - (Sliding Window - Fixed size Template 1)

Time complexity : O(N)
Space complexity : O(N)

```cpp
vector<int> maxSlidingWindow(vector<int>& arr, int k) {
int n = nums.size(), i = 0, j = 0;
    deque<int> dq;
    vector<int> res;
    
    while(j < n){
        // Removing elements which is of no use
        while(dq.size() && nums[j] > dq.back())dq.pop_back();
        dq.push_back(nums[j]);
        
        if(j - i + 1 < k)j++;
        else if(j - i + 1 == k){
            res.push_back(dq.front());
            
            if(dq.front() == nums[i]){
                dq.pop_front();
            }
            
            i++, j++;
        }
    }
    return res;
}
```

## Approach 3.2 : Dequeue - (Sliding Window - Fixed size Template 2) 

Time complexity : O(N)
Space complexity : O(N)

```cpp
vector<int> maxSlidingWindow(vector<int>& nums, int k) {
    int n = nums.size();
    deque <int> dq;
    vector <int> res;

    // Preparing fist window
    for(int i = 0; i < k; i++){
        while(!dq.empty() && nums[i] > dq.back()){
            dq.pop_back();
        }
        dq.push_back(nums[i]);
    }
    res.push_back(dq.front());

    // Checking for rest of the window 
    for(int i = k; i < n; i++){
        while(!dq.empty() && nums[i] > dq.back()){
            dq.pop_back();
        }

        dq.push_back(nums[i]);
        if(dq.front() == nums[i - k]) dq.pop_front();
        res.push_back(dq.front());
    }

    return res;
}
```