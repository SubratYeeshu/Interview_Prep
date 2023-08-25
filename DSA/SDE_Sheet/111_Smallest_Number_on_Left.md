# Smallest Number on Left

## Problem statement

- Given an array a of integers of length n, find the nearest smaller number for every element such that the smaller element is on left side.If no small element present on the left print -1.

## Approach 1 : Stack (Left Smaller)

Time complexity : O(N)  
Space complexity : O(N)

```cpp
vector<int> leftSmaller(int n, int arr[]){
    vector<int> res (n, -1);
    stack<int > st;
    
    for (int i = 0 ; i < n ; i++) {
        while (!st.empty() && st.top() >= arr[i])st.pop();
        if (!st.empty())res[i] = st.top();
        st.push(arr[i]);
    }

    return res;
}
```

# Smallest Number on Left

## Problem statement

- Find next smaller element for every element of the array

## Approach 1 : Stack (Right Smaller)

Time complexity : O(N)  
Space complexity : O(N)

```cpp
vector<int> leftSmaller(int n, int arr[]){
    vector<int> res(n, -1);
    stack<int> st;
    
    for(int i = n - 1 ; i >= 0 ; i--){
        while(st.size() && st.top() > arr[i])st.pop();
        if(!st.empty())res[i] = st.top();
        st.push(arr[i]);
    }
    
    return res;
}
```