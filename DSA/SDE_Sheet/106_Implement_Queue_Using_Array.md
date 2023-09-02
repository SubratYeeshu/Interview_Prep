# Implement Queue using array

## Problem statement

Implement a Queue using an Array. Queries in the Queue are of the following type:

- A query of this type means  pushing 'x' into the queue
- A query of this type means to pop element from queue and print the poped element

## Approach 1 : Using Arraya

- Time complexity : O(N) 
- Space complexity : O(N)

```cpp
int arr[100005];
int front = 0, back = 0;
void MyQueue :: push(int x){
    arr[rear] = x;
    rear++;
}

int MyQueue :: pop(){
    if(front == rear){
        return -1;
    }else{
        int x = arr[front];
        arr[front] = -1;
        front++;
        return x;
    }
}
```

## Approach 1 : Using circular array

- Time complexity : O(N) 
- Space complexity : O(N)

```cpp
int arr[100005];
int front = 0, rear = 0;
void MyQueue :: push(int x){
    arr[rear] = x;
    rear++;
    rear %= 100005;
}

int MyQueue :: pop(){
    if(front == rear){
        return -1;
    }else{
        int x = arr[front];
        arr[front] = -1;
        front++;
        front %= 100005;
        return x;
    }
}
```