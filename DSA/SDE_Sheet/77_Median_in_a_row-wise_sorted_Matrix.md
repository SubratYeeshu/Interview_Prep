# Median in a row-wise sorted Matrix

## Problem statement

- Given a row wise sorted matrix of size R*C where R and C are always odd, find the median of the matrix.

## Approach 1 : Extra space + Sorting

Time complexity : O(N \* M) 
Space complexity : O(N \* M)

```cpp
int median(vector<vector<int>> &matrix, int R, int C){
    vector<int> temp;
    for(auto i : matrix)
        for(auto j : i)
            temp.push_back(j);
            
    sort(temp.begin(), temp.end());
    int idx = (R * C) / 2;  // As R and C is always odd the median will always lie on this index
    return temp[idx];
}
```

## Approach 2 : Binary Search   

Time complexity : O(R * log C) : Upper bound function takes logC time.
Space complexity : O(1)

```cpp
/*

    -> Low and High will depend on the count of elements less than (R*C)/2 
    -> The median / mid should lie on the break point
    -> Break point is the point at which the count of elements is equal behind and after it
    -> The answer will lie on the low pointer / break point lies on low
    -> We can find the count of number less than mid or using the upper bound

*/
int checkCount(int temp, vector<vector<int>> &matrix, int R, int C){
    int cnt = 0;
    for(int i = 0 ; i < R ; i++){
        int low = 0, high = C - 1;
        while(low <= high){
            int mid = (low + high) / 2;
            
            if(matrix[i][mid] <= temp)low = mid + 1;
            else high = mid - 1;
        }
        
        cnt += low;
    }
    
    return cnt;
}
int median(vector<vector<int>> &matrix, int R, int C){
    int low = 1, high = INT_MAX;
    int cnt_bef_and_aft = (R * C) / 2;
    
    while(low <= high){
        int mid = (low + high) / 2;
        int countLessThanMid = checkCount(mid, matrix, R, C);
        if(countLessThanMid <= cnt_bef_and_aft)low = mid + 1;
        else high = mid - 1;
        
    }
    
    return low;
}
```