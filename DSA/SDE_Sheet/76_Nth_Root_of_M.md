# Find Nth root of M

## Problem statement

- You are given 2 numbers (n , m); the task is to find n√m (nth root of m).

## Approach 1 : Linear search

Time complexity : O(M) : Iterating on whole range
Space complexity : O(1)


```cpp
int solve(int x, int n){
    if(n == 0)return 1;
    if(n == 1)return x;
    
    int ans = solve(x, n / 2);
    if(n % 2 == 0)ans = ans * ans; 
    else ans = x * ans * ans;
    return ans;
}

int NthRoot(int n, int m){
    for(int i = 1 ; i <= m ; i++){
        long long val = solve(i, n);
        if(val == m)return i;
        else if(val > m)break;
    }
    
    return -1;
}  
```

## Approach 2 : Binary Search

Time complexity : O(LogM) : Binary search on range
Space complexity : O(1)

```cpp
string check(int n, int m, int mid){
    long long res = 1;
    for(int i = 0 ; i < n ; i++){
        res *= mid;
        if(res > m)return "greater";
    }
    
    if(res == m)return "equal";
    return "smaller";
}
int NthRoot(int n, int m){
    int i = 1, j = m;
    
    while(i <= j){
        int mid = j + (i - j) / 2;
        
        string temp = check(n, m, mid);
        
        if(temp == "equal")return mid;
        else if(temp == "greater")j = mid - 1;
        else i = mid + 1;
    }
    
    return -1;
} 
```