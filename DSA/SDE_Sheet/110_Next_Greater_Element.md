# Next Greater Element

## Problem statement

Given an array arr[ ] of size N having elements, the task is to find the next greater element for each element of the array in order of their appearance in the array. Next greater element of an element in the array is the nearest element on the right which is greater than the current element. 

If there does not exist next greater of current element, then next greater element for current element is -1. For example, next greater of the last element is always -1.

## Approach 1.1 : Stack 

- Time complexity : O(N)  
- Space complexity : O(N)

```cpp
vector<long long> nextLargerElement(vector<long long> arr, int n){
    vector<long long> res;
    stack<long long > st;
    
    for(int i = n - 1 ; i >= 0 ; i--){
        if(st.empty())res.push_back(-1);
        else{
            while(st.size() && st.top() <= arr[i])st.pop();
            if(!st.empty())res.push_back(st.top());
            else res.push_back(-1);
        }
        
        st.push(arr[i]);
    }
    
    reverse(res.begin(), res.end());
    return res;
}
```

## Approach 1.2 : Stack (Minor changes)

- Time complexity : O(N)  
- Space complexity : O(N)

```cpp
vector<long long> nextLargerElement(vector<long long> arr, int n){
    vector<long long> res;
    stack<long long > st;
    
    for(int i = n - 1 ; i >= 0 ; i--){
        if(st.empty())res.push_back(-1);
        else if(st.size() > 0 && st.top() > arr[i]){
            res.push_back(st.top());
        }else if(st.size() > 0 && st.top() <= arr[i]){
            while(st.size() > 0 && st.top() <= arr[i])st.pop();
            if(st.size() == 0)res.push_back(-1);
            else res.push_back(st.top());
        }
        
        st.push(arr[i]);
    }
    
    reverse(res.begin(), res.end());
    return res;
}
```

## Approach 1.3 : Stack (Minor changes)

- Time complexity : O(N)  
- Space complexity : O(N)

```cpp
vector<long long> nextLargerElement(vector<long long> arr, int n){
    vector<long long> res (n, -1);
    stack<long long > st;
    
    for (int i = n - 1 ; i >= 0 ; i--) {
        while (!st.empty() && st.top() <= arr[i])st.pop();
        if (!st.empty())res[i] = st.top();
        st.push(arr[i]);
    }

    return res;
}
```

## Approach 1.5 : Stack (Minor changes)

- Time complexity : O(N)  
- Space complexity : O(N)

```cpp
// Index based
vector<long long> nextLargerElement(vector<long long> arr, int n){
    vector<long long> res (n, -1);
    stack<long long > st;
    
    for (int i = n - 1 ; i >= 0 ; i--) {
        while (!st.empty() && arr[st.top()] <= arr[i])st.pop();
        if (!st.empty())res[i] = arr[st.top()];
        st.push(i);
    }

    return res;
}
```

## Approach 1.5 : Stack (Minor changes)

- Time complexity : O(N)  
- Space complexity : O(N)

```cpp
vector<long long> nextLargerElement(vector<long long> arr, int n){
    vector<long long> res (n, -1);
    stack<pair<long long, long long>> st;
    
    for (int i = n - 1 ; i >= 0 ; i--) {
        while(st.size() && st.top().first <= arr[i])st.pop();
        if(st.size())res[i] = st.top().first;
        else res[i] = -1;
        st.push({arr[i], i});
    }

    return res;
}
```

## Approach 1.6 : Stack (Minor changes)

- Time complexity : O(N)  
- Space complexity : O(N)

```cpp
vector<long long> nextLargerElement(vector<long long> arr, int n){
    vector<long long>nge(n, -1);
    stack<pair<long, long>>st;
    for(long long i = 0 ; i < n ; i++){
        long long el = arr[i];
        if(st.empty()){
            st.push({el, i});
            continue;
        }
        
        while(!st.empty() && st.top().first < el){
            nge[st.top().second] = el;
            st.pop();
        }
        
        st.push({el, i});
    }
    return nge;
}
```
# Next Greater Element to the left

## Problem statement 

Find next greater element on the right for every elements. 

## Approach 1.1 : Stack

- Time complexity : O(N)  
- Space complexity : O(N)

```cpp
vector<long long> nextLargerElementToLeft(vector<long long> arr, int n){
    vector<long long> res (n, -1);
    stack<long long > st;
    
    for (int i = 0 ; i < n ; i++) {
        while (!st.empty() && st.top() <= arr[i])st.pop();
        if (!st.empty())res[i] = st.top();
        st.push(arr[i]);
    }

    return res;
}
```

## Approach 1.2 Stack (Minor changes)

- Time complexity : O(N)  
- Space complexity : O(N)

```cpp
vector<long long> nextLargerElementToLeft(vector<long long> arr, int n){
    vector<long long> res (n, -1);
    stack<long long > st;
    
    for (int i = 0 ; i < n ; i++) {
        while (!st.empty() && arr[st.top()] <= arr[i])st.pop();
        if (!st.empty())res[i] = arr[st.top()];
        st.push(i);
    }

    return res;
}

```