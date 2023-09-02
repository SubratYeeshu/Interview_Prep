# Stock span problem

## Problem statement

The stock span problem is a financial problem where we have a series of n daily price quotes for a stock and we need to calculate the span of stocks price for all n days. 

The span Si of the stocks price on a given day i is defined as the maximum number of consecutive days just before the given day, for which the price of the stock on the current day is less than or equal to its price on the given day.

For example, if an array of 7 days prices is given as {100, 80, 60, 70, 60, 75, 85}, then the span values for corresponding 7 days are {1, 1, 1, 2, 1, 4, 6}.


## Approach 1.1 : Stack

- Time complexity : O(N)  
- Space complexity : O(N)

```cpp
vector <int> calculateSpan(int price[], int n){
        stack <pair<int, int>> st;
        vector<int> res;
        
        for(int i = 0 ; i < n ; i++){
            int span = 1;
            
            while(!st.empty() && st.top().first <= price[i])span +=  st.top().second, st.pop();
            
            st.push({price[i], span});
            res.push_back(span);
            
        }
        
        return res;
    }
```

## Approach 2 : Stack (Next greater element left + Index)

Time complexity : O(N)  
Space complexity : O(N)

```cpp
vector<int> nextLargerElementToLeft(int arr[], int n){
    vector<int> res (n, -1);
    stack<int > st;
    
    for (int i = 0 ; i < n ; i++) {
        while (!st.empty() && arr[st.top()] <= arr[i])st.pop();
        if (!st.empty())res[i] = st.top();
        else res[i] = -1;
        st.push(i);
    }

    return res;
}
vector <int> calculateSpan(int price[], int n){
    vector<int> res (n, 1);
    vector<int> nge = nextLargerElementToLeft(price, n);

    for(int i = 0 ; i < n ; i++){
        if(nge[i] != -1){
            res[i] = i - nge[i];
        }
        else{
            if(i == 0)continue;
            else res[i] = i + 1;
        }
    }
    
    return res;
}
```