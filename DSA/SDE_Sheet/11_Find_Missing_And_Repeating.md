# Find the repeating and the missing number in an array

## Problem statement

Given an unsorted array of size n. Array elements are in the range from 1 to n. One number from set {1, 2, …n} is missing and one number occurs twice in the array. Find these two numbers.

## Approach 1 (Brute force)

Time complexity : O(N \* log(n))  
Space complexity : O(1)

```cpp
vector<int> findTwoElement(vector<int> A, int n) {
    sort(A.begin(), A.end());
    int repeating = -1, missing = n, j = 1;
    bool missingFound = false;
    for(int i = 0 ; i < n ; i++, j++){
        if(i > 0 && A[i] == A[i - 1])repeating = A[i], j--;
        if(!missingFound && A[i] != j)missing = j, missingFound = true;
    }
    return {repeating, missing};
}
```

## Approach 2 (Extra array)

Time complexity : O(N)  
Space complexity : O(N)

```cpp
vector<int> findTwoElement(vector<int> A, int n) {
    int rep = -1, miss = n;
    unordered_map<int, int> mp;
    
    for(auto i : A)mp[i]++;
    for(int i = 1 ; i <= n ; i++)
        if(mp[i] == 2)rep = i;
        else if(mp[i] == 0)miss = i;
            
    return {rep, miss};        
}
```

## Approach 3 (Mathematics)

Time complexity : O(N)  
Space complexity : O(1)  
it requires long int to store large values

```cpp
vector<int> findTwoElement(vector<int> A, int n) {
    long int actualSum = 0;
    long int optimalcasesum = long(n * long(n + 1))/2;
    long int optimalcasesquaresum = long(n*long(n+1)*long(2*n+1))/6;
    long int actualSumOfSquares = 0;

    for(int i = 0;i<n;i++){
        actualSum += A[i];
        actualSumOfSquares += long(A[i])*long(A[i]);
    }
    long int a_minus_b = actualSum - optimalcasesum;
    long int a2_minus_b2 = actualSumOfSquares - optimalcasesquaresum;
    long int a_plus_b = a2_minus_b2/a_minus_b;

    long int a = long(a_minus_b +a_plus_b)/2;
    long int b = a_plus_b - a;

    return {a,b};
}
```

## Approach 4 (XOR)

Time complexity : O(N)  
Space complexity : O(1)

```cpp
int *findTwoElement(int *arr, int n) {
    int num = 0, i;
    for(i = 0 ; i < n ; i++) num = (num ^ arr[i]);
    for(i = 1 ; i <= n ; i++) num = (num ^ i);

    int setbit = num & ~(num - 1);

    int x = 0, y = 0;
    for(i = 0 ; i < n ; i++) {
        if(arr[i] & setbit)x = (x ^ arr[i]);
        else y = (y ^ arr[i]);
    }
    for(i = 1 ; i <= n ; i++){
        if(i & setbit)x = (x ^ i);
        else y =(y ^ i);
    }

    int * res = new int[2];
    res[0] = x;
    res[1] = y;
    for(i = 0 ; i < n ; i++) {
        if(arr[i] == res[1]) {
            swap(res[0], res[1]);
            break;
        }
    }
    return res;
}
```
