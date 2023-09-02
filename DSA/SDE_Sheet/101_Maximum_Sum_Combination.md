# Maximum Sum Combination

## Problem statement

Given two integer array A and B of size N each. A sum combination is made by adding one element from array A and another element of array B. 

Return the maximum K valid sum combinations from all the possible sum combinations. Note : Output array must be sorted in non-increasing order.


## Approach 1 : Brute 

- Time complexity : O(N^2) 
- Space complexity : O(N)

```cpp
vector<int> maxCombinations(int N, int C, vector<int> &A, vector<int> &B) {
    int n = A.size(), m = B.size();
    priority_queue<int,vector<int>,greater<int>>pq;
    set<pair<int, int>> st;
    vector<int> res;
    
    for(int i = 0 ; i < n ; i++){
        for(int j = 0 ; j < m ; j++){
            int sum = A[i] + B[j];
            
            if(st.find({i, j}) == st.end()){
                pq.push(sum);
                st.insert({i, j});
            }
            if(pq.size() > C)pq.pop();
            
        }
    }
    

    while(pq.size() > 0){
        res.push_back(pq.top());
        pq.pop();
    }
    reverse(res.begin(),res.end());
    return res ;
}
```

## Approach 2 : Optimal 

- Time complexity : O(NLogN) 
- Space complexity : O(1)

```cpp
vector<int> maxCombinations(int N, int C, vector<int> &A, vector<int> &B) {
    int n = A.size(), m = B.size();
    
    
    // {sum, {idx1, idx2}} -> PQ, {idx1, idx2} -> SET for avoiding duplication
    priority_queue<pair<int, pair<int, int>>> pq;
    set<pair<int, int>> st;
    vector<int> res;
    
    sort(A.begin(), A.end());
    sort(B.begin(), B.end());
    
    pq.push({A[n - 1] + B[m - 1], {n - 1, m - 1}});
    st.insert({n - 1, m - 1});
    
    while(C--){
        auto it = pq.top();
        pq.pop();
        res.push_back(it.first);
        
        int L = it.second.first;
        int R = it.second.second;
        
        if(L > 0 && st.find({L - 1, R}) == st.end()){
            pq.push({A[L - 1] + B[R], {L - 1, R}});
            st.insert({L - 1, R});
        }
        
        if(R > 0 && st.find({L, R - 1}) == st.end()){
            pq.push({A[L] + B[R - 1], {L, R - 1}});
            st.insert({L, R - 1});
        }
    }
    
    return res;
}
```