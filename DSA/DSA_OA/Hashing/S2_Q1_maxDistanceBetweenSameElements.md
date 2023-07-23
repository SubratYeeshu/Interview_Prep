## Max Distance between same elements
## https://practice.geeksforgeeks.org/problems/max-distance-between-same-elements/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article

```cpp
int maxDistance(int arr[], int n){
        unordered_map <int, int> mp;
        int maxi = 0;
        for(int i = 0 ; i < n ; i++){
            if(mp.count(arr[i]))maxi = max(maxi, i - mp[arr[i]]);
            else mp[arr[i]] = i;
        }
        return maxi;
    }
```