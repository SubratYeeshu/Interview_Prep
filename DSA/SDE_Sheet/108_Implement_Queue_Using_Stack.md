# Implement a Queue using stack

## Problem statement

Implement a queue data structure using stack 

## Approach 1 : Two Queues

- Time complexity : O(N) 
- Space complexity : O(2N)

```cpp
class Queue {
stack<int> input, output;
public:
    void enqueue(int x) {
        while(!input.empty()){
            output.push(input.top());
            input.pop();
        }
        input.push(x);
        while(!output.empty()){
            input.push(output.top());
            output.pop();
        }
    }

    int dequeue() {
        int x = input.top();
        input.pop();
        return x;
    }
};
```

## Approach 2 : Two Queues + Amortised

- Time complexity : O(1) Amortised (Most cases) 
- Space complexity : O(2N)

```cpp
class Queue {
stack<int> input, output;
public:
    void enqueue(int x) {
        input.push(x);
    }

    int dequeue() {
        if(output.empty()){
            while(!input.empty()){
                output.push(input.top());
                input.pop();
            }
        }
        int x = output.top();
        output.pop();
        return x;
    }
};
```