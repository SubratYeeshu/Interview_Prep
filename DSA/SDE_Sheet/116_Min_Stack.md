# Min Stack

## Problem statement

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
Implement the MinStack class: 
- MinStack() initializes the stack object.
- void push(int val) pushes the element val onto the stack.
- void pop() removes the element on the top of the stack.
- int top() gets the top element of the stack.
- int getMin() retrieves the minimum element in the stack.

You must implement a solution with O(1) time complexity for each function.


## Approach 1.1 : Using two stacks

Time complexity : O(1) 
Space complexity : O(N)

```cpp
stack<int> regularStack;
stack<int> minStack;
MinStack() {
    
}

void push(int val) {
    regularStack.push(val);
    if(minStack.size() == 0 || val <= minStack.top())minStack.push(val);
}

void pop() {
    if(regularStack.empty())return;
    if(regularStack.top() == minStack.top()){
        regularStack.pop();
        minStack.pop();
    }else{
        regularStack.pop();
    }
}

int top() {
    if(regularStack.empty())return -1;
    return regularStack.top();
}

int getMin() {
    if(minStack.empty())return -1;
    return minStack.top();
}
```

## Approach 1.2 : Using stack with pair

Time complexity : O(1) 
Space complexity : O(N)

```cpp
stack<pair<int,int>>st;
MinStack() {
    
}

void push(int val) {
    int min;
    if(st.empty()){
        st.push({val, val});
    }else{
        if(val < st.top().second)st.push({val, val});
        else st.push({val, st.top().second});
    } 
}

void pop() {
    if(st.size() > 0)st.pop();
}

int top() {
    return st.top().first;
}

int getMin() {
    return st.top().second;
}
```

## Approach 2 : Using stack 

Time complexity : O(1) 
Space complexity : O(1)

```cpp

```