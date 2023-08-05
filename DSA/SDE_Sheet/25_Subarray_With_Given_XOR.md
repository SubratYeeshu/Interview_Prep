# Subarray with given XOR

## Problen statement

- Given an array of integers A and an integer B. Find the total number of subarrays having bitwise XOR of all elements equals to k.

## Approach 1 : Brute

Time complexity : O(N^3)  
Space complexity : O(1)

```cpp
int solve(vector<int> &a, int k) {
    int n = a.size(), cnt = 0;
    for (int i = 0 ; i < n ; i++) {
        for (int j = i ; j < n ; j++) {
            int xorr = 0;
            for (int K = i; K <= j; K++) {
                xorr = xorr ^ a[K];
            }
            if (xorr == k) cnt++;
        }
    }
    return cnt;
}
```

## Approach 2 : Better

Time complexity : O(N^2)  
Space complexity : O(1)

```cpp
int solve(vector<int> &a, int k) {
    int n = a.size();
    int cnt = 0;
    for (int i = 0; i < n; i++) {
        int xorr = 0;
        for (int j = i; j < n; j++) {
            xorr = xorr ^ a[j];
            if (xorr == k) cnt++;
        }
    }
    return cnt;
}
```

## Approach 3 : Optimal 

Time complexity : O(N)  
Space complexity : O(N)

```cpp
/*

    ________________________XR
    . . . . . . . . . . . .| 
    ________|______________|
    x           k

    x ^ k = XR
    (x ^ k) ^ k = XR ^ k
    x = XR ^ k

*/
int solve(vector<int> &A, int k) {
    unordered_map<int, int> mp;
    int xr = 0, cnt = 0;
    mp[xr] = 1;
    for(int i = 0 ; i < A.size() ; i++){
        xr = xr ^ A[i];
        
        int x = xr ^ k;
        cnt += mp[x];
        mp[xr]++; 
    }
    return cnt;
}
```