# Reverse Stack

## Problem statement

You are given a stack St. You have to reverse the stack using recursion.

# Approach 1 : Using queue

- Time complexity : O(N) 
- Space complexity : O(N)

```cpp
queue<int>ans;
void Reverse(stack<int> &St){
    
    if (!St.empty()){
        ans.push(St.top());
        St.pop();
    }
    else{
        return;
    }
    
    Reverse(St);
    
    while (!ans.empty()){
        St.push(ans.front());
        ans.pop();
    }
}
```

## Approach 2 : Recursion stack 

- Time complexity : O(N) 
- Space complexity : O(1)

```cpp
void insert_at_bottom(stack<int>& st, int element){
    if(st.empty()){
        st.push(element);
        return;
    }
    
    int top = st.top();
    st.pop();
    insert_at_bottom(st, element);
    
    st.push(top);
}

void reverse_helper(stack<int> &st){
    if(st.empty())return;  // Single element is sorted in itself
    
    int top = st.top();
    st.pop();
    
    reverse_helper(st);
    
    insert_at_bottom(st, top);

}

void Reverse(stack<int> &st){
    reverse_helper(st);
}
```