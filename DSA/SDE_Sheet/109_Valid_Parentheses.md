# Valid Parentheses

## Problem statement

Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid. An input string is valid if:

- Open brackets must be closed by the same type of brackets.
- Open brackets must be closed in the correct order.
- Every close bracket has a corresponding open bracket of the same type.

## Approach 1 : Using stack

- Time complexity : O(N) 
- Space complexity : O(N)

```cpp
bool isOpening(char ch){
    return ch == '(' || ch == '[' || ch == '{';
}
bool isValid(string s) {
    stack <char> st;
    
    for(auto &ch : s){
        if(isOpening(ch))st.push(ch);
        else{
            if(st.empty())return false;
            char last = st.top();
            if(last == '(' && ch == ')')st.pop();
            else if(last == '[' && ch == ']')st.pop();
            else if(last == '{' && ch == '}')st.pop();
            else st.push(ch);
        }
    }
    
    return st.size() == 0;
}
```