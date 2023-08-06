# Finding all subarray

## Method 1 : 
Time Complexity : O(N \* 3)
Space Complexity : O(N \* 2)

```cpp
vector<vector<int>> generateSubarrays(vector<int> v){
    vector<vector<int>> sub;
    int n = v.size();

    // Generating subarray
    for(int i = 0 ; i < n ; i++){
        for(int j = i ; j < n ; j++){
            vector<int> level;
            for(int k = i ; k < j ; k++){
                level.push_back(v[k]);
            }
            if(level.size() != 0)sub.push_back(level);
        }
    }

    // Printing every subarray
    for(auto i : sub){
        for(auto j : i){
            cout << j << " ";
        }
        cout << endl;
    }

    return sub;
}
```

## Method - 2 : 

Time Complexity : O(N \* 3)
Space Complexity : O(N \* 2)

```cpp
vector<vector<int>> generateSubarrays(vector<int> v){
    int n = v.size();
    vector<vector<int>> sub;

    // Generating subarray
    for(int i = 0 ; i < n ; i++){
        vector<int> level;
        for(int j = i ; j < n ; j++){
            level.push_back(v[j]);
            sub.push_back(level);
        }
    }
    
    // Printing subarray
    for(auto i : sub){
        for(auto j : i){
            cout << j << " ";
        }
        cout << endl;
    }

    return sub;
}
```