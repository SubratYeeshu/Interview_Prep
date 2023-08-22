# Merge k sorted arrays 

## Problem stateent 

- Given K sorted arrays arranged in the form of a matrix of size K*K. The task is to merge them into one sorted array.

## Approach 1 : Brute 

Time complexity : O(N^2) 
Space complexity : O(N)

```cpp
typedef pair<int, pair<int, int>> info;
vector<int> mergeKArrays(vector<vector<int>> arr, int K){
    priority_queue<info, vector<info>, greater<info>> pq;
    vector<int> res;
    
    for(int i = 0 ; i < K ; i++)pq.push({arr[i][0], {i, 0}});
    
    while(!pq.empty()){
        auto it = pq.top();
        pq.pop();
        int ele = it.first;
        int row = it.second.first;
        int col = it.second.second;
        
        res.push_back(ele);
        
        if(col + 1 < K)pq.push({arr[row][col + 1], {row, col + 1}});
    }
    
    return res;
}
```

## Approach 2 : Priority Queue 

Time complexity : O(NLogK) 
Space complexity : O(N)

```cpp
typedef pair<int, pair<int, int>> info;
vector<int> mergeKArrays(vector<vector<int>> arr, int K){
    priority_queue<info, vector<info>, greater<info>> pq;
    vector<int> res;
    
    for(int i = 0 ; i < K ; i++)pq.push({arr[i][0], {i, 0}});
    
    while(!pq.empty()){
        auto it = pq.top();
        pq.pop();
        int ele = it.first;
        int row = it.second.first;
        int col = it.second.second;
        
        res.push_back(ele);
        
        if(col + 1 < K)pq.push({arr[row][col + 1], {row, col + 1}});
    }
    
    return res;
}
```

## Approach 3.1 : Priority Queue (Using class (Pointers to node)) 

Time complexity : O(NLogK) 
Space complexity : O(N)

```cpp
class node{
    public:
    
    int data;
    int i;
    int j;
    
    node(int data, int i, int j){
        this -> data = data;
        this -> i = i;
        this -> j = j;
    }
};

class compare{
    public:
    bool operator()(node* n1, node* n2){
        return n1 -> data > n2 -> data;
    }
};
class Solution{
    public:


    vector<int> mergeKArrays(vector<vector<int>> arr, int K){
        priority_queue<node*, vector<node*>, compare> pq;
        vector<int> res;
        
        for(int i = 0 ; i < K ; i++){
            node* mynode = new node(arr[i][0], i, 0);
            pq.push(mynode);
        }
        
        while(!pq.empty()){
            auto it = pq.top();
            pq.pop();
            
            int ele = it -> data;
            int row = it -> i;
            int col = it -> j;
            
            res.push_back(ele);
            
            if(col + 1 < K){
                node* mynode = new node(arr[row][col + 1], row, col + 1);
                pq.push(mynode);
            }
        }
        
        return res;
    }
};
```

## Approach 3.2 : Priority Queue (Using class (Node directly)) 

Time complexity : O(NLogK) 
Space complexity : O(N)

```cpp
//User function Template for C++

class node{
    public:
    
    int data;
    int i;
    int j;
    
    node(int data, int i, int j){
        this -> data = data;
        this -> i = i;
        this -> j = j;
    }
};

class compare{
    public:
    bool operator()(node n1, node n2){
        return n1.data > n2.data;
    }
};
class Solution{
    public:

    
    vector<int> mergeKArrays(vector<vector<int>> arr, int K){
        priority_queue<node, vector<node>, compare> pq;
        vector<int> res;
        
        for(int i = 0 ; i < K ; i++){
            node mynode(arr[i][0], i, 0);
            pq.push(mynode);
        }
        
        while(!pq.empty()){
            auto it = pq.top();
            pq.pop();
            
            int ele = it.data;
            int row = it.i;
            int col = it.j;
            
            res.push_back(ele);
            
            if(col + 1 < K){
                node mynode(arr[row][col + 1], row, col + 1);
                pq.push(mynode);
            }
        }
        
        return res;
    }
};

```