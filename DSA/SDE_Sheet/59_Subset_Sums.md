# Subset Sums

## Problem statement - 

- Given a list arr of N integers, print sums of all subsets in it.

## Approach 1 : Generate all subsets using power set (Bit Manipulation)

Time complexity : O(N \* 2<sup>n<sup>) 
Auxiliary space : O(2<sub>n<sup>)

```cpp

```

## Approach 2 : Recursion 

Time complexity : O(2<sup>n</sup>) 
Auxiliary space : O(2<sub>n</sup>)

```cpp
void solve(int index, vector<int>& arr, int sum, vector<int>& res){
    if(index >= arr.size()){
        res.push_back(sum);
        return;
    }
    
    solve(index + 1, arr, sum + arr[index], res);
    solve(index + 1, arr, sum, res);
}

vector<int> subsetSums(vector<int> arr, int N){
    int sum = 0;
    vector<int> res;
    solve(0, arr, sum, res);
    return res;
}
```