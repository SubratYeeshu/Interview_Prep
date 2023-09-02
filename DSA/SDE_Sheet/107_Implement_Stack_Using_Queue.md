# Implement a Stack using queue

## Problem statement

Implement a Stack using  queue

## Approach 1 : Using two queues

- Time complexity : O(N) 
- Space complexity : O(2N)

```cpp
/*

    -> Push every elements in queue 1
    -> But before pushing any elements push evey elements of q1 to another queue q2
    -> Then after putting the element in q1 revert back

*/
queue<int> q1;
queue<int> q2;
void QueueStack :: push(int x){
    while(!q1.empty()){
        q2.push(q1.front());
        q1.pop();
    }
    q1.push(x);
    while(!q2.empty()){
        q1.push(q2.front());
        q2.pop();
    }
}

int QueueStack :: pop(){
    if(q1.empty())return -1;
    int x = q1.front();
    q1.pop();
    return x;
}
```

## Approach 2 : Using single queues

- Time complexity : O(N) 
- Space complexity : O(N)

```cpp
queue<int> q1;
void QueueStack :: push(int x){
    q1.push(x);
    
    for(int i = 0 ; i < q1.size() - 1 ; i++){
        q1.push(q1.front());
        q1.pop();
    }
}

int QueueStack :: pop(){
    if(q1.empty())return -1;
    int x = q1.front();
    q1.pop();
    return x;
}

```