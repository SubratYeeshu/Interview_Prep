# Largest Area Under Histogram

## Problem statement

Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.
 
## Approach 1 : Brute

- Time complexity : O(N^2)  
- Space complexity : O(1)

```cpp
int largestRectangleArea(vector<int>& heights) {
    int n = heights.size(), ans = INT_MIN;
    for(int i = 0 ; i < n ; i++){
        int m = INT_MAX;
        for(int j = i ;  j < n ; j++){
            m = min(m, heights[j]);
            ans = max(ans, (j - i + 1) * m);
        }
    }
    return ans;
}
```

## Approach 2 : Optimal (Stack)

- Time complexity : O(N)  
- Space complexity : O(N)

```cpp
vector<int> getNextSmaller(vector<int> &nums){
    int n = nums.size();
    vector<int> nextSmaller (n, -1);
    stack<int> st;
    
    for(int i = n - 1 ; i >= 0 ; i--){
        while(st.size() && nums[i] <= nums[st.top()])st.pop();
        if(st.size())nextSmaller[i] = st.top(); 
        st.push(i);
    }        
    
    return nextSmaller;
}
vector<int> getPrevSmaller(vector<int> &nums){
    int n = nums.size();
    vector<int> prevSmaller(n, -1);
    stack<int> st;
    
    for(int i = 0 ; i < n ; i++){
        while(st.size() && nums[i] <= nums[st.top()])st.pop();
        if(st.size())prevSmaller[i] = st.top();
        st.push(i);
    }
    
    return prevSmaller;
}

// We have to check how much a building can be expanded, a smaller building will stop stop the expansion
int largestRectangleArea(vector<int>& heights) {
    int n = heights.size(), max_area = INT_MIN;
    vector<int> prevSmaller = getPrevSmaller(heights);
    vector<int> nextSmaller = getNextSmaller(heights);
    vector<int> areaPossible (n, 0);
    
    for(int i = 0 ; i < n ; i++){
        int leftSmallerIndex = prevSmaller[i];
        int rightSmallerIndex = nextSmaller[i];
        if(rightSmallerIndex == -1)rightSmallerIndex = n;  // No constraint on expansion to the right 
        if(leftSmallerIndex == -1)leftSmallerIndex = -1;  // No constraint on expansion to the left
        int length = heights[i], breadth = rightSmallerIndex - leftSmallerIndex - 1;
        areaPossible[i] = length * breadth;
        max_area = max(max_area, areaPossible[i]);
    }
    
    return max_area;
}
```