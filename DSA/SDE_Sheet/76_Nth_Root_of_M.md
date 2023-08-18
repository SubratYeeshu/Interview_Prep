# Find Nth root of M

## Problem statement

- You are given 2 numbers (n , m); the task is to find n√m (nth root of m).

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