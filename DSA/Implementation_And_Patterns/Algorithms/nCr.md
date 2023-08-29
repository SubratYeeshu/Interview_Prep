# Finding nCr value 

## Method - 1 : Using basic maths optimization

Time complexity : O(N)  
Space complexity : O(N)

```cpp
int ncr(int n, int r){
    // If we calulate ncr in basic way it would take a lot of time and space
    // So we have to optimize it by resolving the formula
    // eg: 12C3 = 12 * 11 * 10 / 1 * 2 * 3 we go three up and three down 
    
    int ans = 1;
    for(int i = 1 ; i <= r ; i++){
        ans = ans * (n - i + 1);
        ans = ans / i;
    }
    return ans;
}
```

## Method - 2 : Using pascal triangle (Useful) for large numbers

Time complexity : O(N \* 2)  
Space complexity : O(N)

```cpp
int mod = 1000000007;
int nCr(int n, int r){
    if(n < r)return 0;
    vector<int> pascal;
    for(int i = 0 ; i <= n ; i++){
        vector<int> temp (i + 1, 1);
        for(int j = 1 ; j < i ; j++){
            temp[j] = (pascal[j] % mod + pascal[j - 1] % mod) % mod;
        }
        pascal = temp;
    }
    return pascal[r];
}
```
