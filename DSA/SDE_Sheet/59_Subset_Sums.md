# Subset Sums

## Problem statement - 

- Given a list arr of N integers, print sums of all subsets in it.

## Approach 1 : Generate all subsets using power set (Bit Manipulation)

Time complexity : O(N \* 2<sup>N</sup>) 
Auxiliary space : O(2<sup>N</sup>)

```cpp
vector<vector<int>> subsets(vector<int>& nums) {
    int n = nums.size();
    vector<vector<int>> res;
    
    for(int i = 0 ; i < (1 << n) ; i++){  // For every binary of (0 to n) is a valid subset 1 represent picking up
        vector<int> sub;
        for(int j = 0 ; j < n ; j++){
            if((1 << j) & i){  // Pick the element
                sub.push_back(nums[j]);                    
            }
        }
        res.push_back(sub);
    }
    
    return res;
}

vector<int> subsetSums(vector<int> arr, int N){
    vector<vector<int>> subs = subsets(arr);
    vector<int> subsetSumsList;

    for (auto sub : subs) {
        int sum = 0;
        for (int num : sub)
            sum += num;
        subsetSumsList.push_back(sum);
    }

    return subsetSumsList;
}
```

## Approach 2 : Recursion 

Time complexity : O(2<sup>N</sup>) 
Auxiliary space : O(2<sup>N</sup>)

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