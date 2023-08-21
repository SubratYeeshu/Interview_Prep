# Square root of a number

## Problem statement

- Given an integer x, find the square root of x. If x is not a perfect square, then return floor(√x).

- Binary Search (Find min / max pattern)

## Approach 1 : Linear Search

Time complexity : O(N) 
Space complexity : O(1)

```cpp
int mySqrt(int x) {
    int ans = 0;
    for(long long i = 1 ; i <= x ; i++){
        if(i * i <= x)ans = i;
        else break;
    }
    
    return ans;
}
```

## Approach 2.1 : Binary Search

Time complexity : O(LogN) 
Space complexity : O(1)

```cpp
int mySqrt(int x) {
    int ans = 0, low = 1, high = x;
    
    while(low <= high){
        long long mid = low + (high - low) / 2;
        
        if(mid * mid <= x){
            ans = mid;
            low = mid + 1;
        }else {
            high = mid - 1;
        }
    }
    
    return ans;  // return high as the right part is eliminated and we are finding the floor (min)
}
```

## Approach 2.2 : Binary Search

Time complexity : O(LogN) 
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
long long int floorSqrt(long long int x) 
{
    int i = 1, j = x, n = 2;

    while(i <= j){
        int mid = j + (i - j) / 2;
        
        string temp = check(n, x, mid);
        
        if(temp == "equal")return mid;
        else if(temp == "greater")j = mid - 1;
        else i = mid + 1;
    }
    
    return j;
}
```