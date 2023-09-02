# Implement Stack using array

## Problem statement 

Write a program to implement a Stack using Array. Your task is to use the class as shown in the comments in the code editor and complete the functions push() and pop() to implement a stack. 

## Approach 1 : Using array

- Time complexity : O(N) 
- Space complexity : O(N)

```cpp
int arr[1000];
int top = -1;
void MyStack :: push(int x){
    top++;
    arr[top] = x;
}
int MyStack :: pop(){
    if(top == -1)return -1;
    int x = arr[top];
    top--;
    return x;
}
```