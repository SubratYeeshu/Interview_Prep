# First negative integer in every window of size k

## Problem statement

- Given an array A[] of size N and a positive integer K, find the first negative integer for each and every window(contiguous subarray) of size K.

## Approach 1 : Brute

Time complexity : O(N^2) Near about kn  
Space complexity : O(1)

```cpp
vector<long long> printFirstNegativeInteger(long long int A[], long long int n, long long int k) {
    vector<long long> res;
    int start = 0, end = k;

    while(end <= n){
        bool flag = false;
        for(int i = start ; i < end ; i++){
            if(A[i] < 0){
                flag = true;
                res.push_back(A[i]);
                break;
            }
        }
        
        if(!flag)res.push_back(0);
        end++;
        start++;
    }    
    return res;                                  
}
```

## Approach 2.1 : Optimal (Sliding Window - Fixed Size Template 1)

Time complexity : O(N)
Space complexity : O(N)

```cpp
vector<long long> printFirstNegativeInteger(long long int A[], long long int n, long long int k){
    vector<long long> res;
    queue<long long> q;
    long long i = 0, j = 0;
    
    while(j < n){
        if(A[j] < 0)q.push(A[j]);
        if(j - i + 1 == k){
            if(q.size() != 0){
                res.push_back(q.front());
                if(A[i] == q.front())q.pop();
            }else res.push_back(0);
            
            j++;
            i++;
        }else if(j - i + 1 < k)j++;
    }
    return res;                                  
}
```

## Approach 2.2 (Sliding Window - Fixed Size Template 2)

Time complexity : O(N)
Space complexity : O(N)

```cpp
vector<long long> printFirstNegativeInteger(long long int A[], long long int n, long long int k){
    vector<long long> res;
    queue<long long> q;
    
    // Preparing the first window
    for(long long i = 0 ; i < k ; i++)if(A[i] < 0)q.push(A[i]);
    
    // First window processing
    if(q.size()){
        res.push_back(q.front());
    }else res.push_back(0);
    
    // Sliding of fixed window
    long long j = k, i = 0;
    while(j < n){
        if(q.size() && q.front() == A[i])q.pop();
        
        if(A[j] < 0)q.push(A[j]);
        
        if(q.size()){
            res.push_back(q.front());
        }else res.push_back(0);
        
        i++;
        j++;
    }
    return res;
}
```