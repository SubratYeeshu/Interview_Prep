# Fractional Knapsack

## Problem statement

- Given weights and values of N items, we need to put these items in a knapsack of capacity W to get the maximum total value in the knapsack. 
- Note: Unlike 0/1 knapsack, you are allowed to break the item. 

## Approach 1.1 : 

Time complexity : O(N \* LogN) 
Auxiliary space : O(N)

```cpp
class ItemsInfo{
    public:
    double value;
    double weight;
    double fraction;
};
double fractionalKnapsack(int W, Item arr[], int n){
    // Creating a vector for sorting based on value / weight
    vector<ItemsInfo> items;
    for(int i = 0 ; i < n ; i++){
        ItemsInfo temp;
        temp.value = arr[i].value;
        temp.weight = arr[i].weight;
        temp.fraction = (double)arr[i].value / (double)arr[i].weight;
        items.push_back(temp);
    }
    
    // Sorting based on value / weight fraction
    sort(items.begin(), items.end(), [](ItemsInfo i1, ItemsInfo i2){
        return i1.fraction > i2.fraction;
    });
    
    double res = 0.0;
    
    // Filling the knapsack
    for(int i = 0 ; i < n ; i++){
        if(items[i].weight <= W){
            res += items[i].value;
            W -= items[i].weight;
        }else{
            if(W != 0){
                res += items[i].fraction * W;
                W = 0;
            }else break;
        }
    }
    
    return res;
}
```

## Approach 2 : 

Time complexity : O(N \* LogN) 
Auxiliary space : O(N)

```cpp
double fractionalKnapsack(int W, Item arr[], int n){
    vector <pair <double,double> > v;
    double res=0.0;
    for(int i = 0 ; i < n ; i++)v.push_back({arr[i].value, arr[i].weight});
    sort (v.begin(), v.end(), comp);
    
    for(int i = 0 ; i < n ; i++){
        if (v[i].second <= W){ 
            res += v[i].first; 
            W -= v[i].second;
        }else{
            if (W != 0) 
            res+= (W* (v[i].first/v[i].second));
            W=0;
            break;
        }
    } 
    return res;
}

```