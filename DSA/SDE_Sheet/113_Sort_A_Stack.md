# Sort a Stack

# Problem statement 

Given a stack, the task is to sort it such that the top of the stack has the greatest element.

## Approach 1 : Stack

- Time complexity : O(N^2)  
- Space complexity : O(1)

```cpp
void insert_helper(stack<int> &st, int element){
    if(st.empty() || st.top() < element){
        st.push(element);
        return;
    }
    
    
    int top = st.top();
    st.pop();
    
    insert_helper(st, element);
    
    st.push(top);
}

void sort_helper(stack<int> &st){
    if(st.empty())return;
    
    int top = st.top();
    st.pop();
    sort_helper(st);
    
    insert_helper(st, top);
}

void SortedStack :: sort(){
   sort_helper(s);
    
}
```