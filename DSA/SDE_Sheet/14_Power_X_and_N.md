# Power (x, n)

## Problem statement

- Implement pow(x, n), which calculates x raised to the power n (i.e., xn).

## Approach 1 : Binary Exponentiation (Recursive)

- Time copmlexity : O(LogN)
- Space complexity : O(1)
N is the number of bits

```cpp
double solve(double x, int n){
    if(n == 0)return 1;
    if(n == 1)return x;
    
    // pow(x, n) -> pow(x * x, n / 2)
    // (pow(x, 9)) -> x^9 -> x * x^8 -> x * pow(x, 9)
    double ans = solve(x, n / 2);
    if(n % 2 == 0)ans = ans * ans; 
    else ans = x * ans * ans;
    return ans;
}

double myPow(double x, int n) {
    if(n < 0)return 1 / solve(x, n);
    else return solve(x, n);
}
```

## Approach 2 : Binary Exponentiation (Iterative)

- Time copmlexity : O(LogN)
- Space complexity : O(1)
N is the number of bits

```cpp
// Represent in binary for intution
double myPow(double x, int n) {
    if(n == 0)return 1;
    if(n == 1)return x;
    double res = 1.0;
        // n = abs(n); // Undefined behaviour on different systems
    long nn = abs(n);
    while(nn){
        if(nn & 1){
            res *= x;
        }
        x *= x;
        nn >>= 1;
    }
    return n > 0 ? res : ((double)1 / res);;
}
```