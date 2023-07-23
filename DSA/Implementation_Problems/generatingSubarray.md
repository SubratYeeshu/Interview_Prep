```cpp
int main()
{
    // O(N^3)
    vector<int> v = {1, 2, 3, 4, 5};
    vector<vector<int>> sub;
    // for(int i = 0 ; i < 5 ; i++){
    //     for(int j = i ; j < 5 ; j++){
    //         vector<int> level;
    //         for(int k = i ; k < j ; k++){
    //             level.push_back(v[k]);
    //         }
    //         if(level.size() != 0)sub.push_back(level);
    //     }
    // }
    // for(auto i : sub){
    //     for(auto j : i){
    //         cout << j << " ";
    //     }
    //     cout << endl;
    // }
    
    // O(N^2)
    for(int i = 0 ; i < 5 ; i++){
        vector<int> level;
        for(int j = i ; j < 5 ; j++){
            level.push_back(v[j]);
            sub.push_back(level);
        }
    }
    
    for(auto i : sub){
        for(auto j : i){
            cout << j << " ";
        }
        cout << endl;
    }
    
    return 0;
}
```