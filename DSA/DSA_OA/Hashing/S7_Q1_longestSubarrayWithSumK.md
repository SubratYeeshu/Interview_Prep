## Longest Subarray With Sum K
## https://practice.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article

```cpp
int lenOfLongSubarr(int A[],  int n, int K) 
    { 
        int sum = 0, maxi = 0;
        unordered_map<int, int> mp;
        mp[0] = -1;
        for(int i = 0; i < n; i++){
            sum += A[i];
            if(mp.find(sum - K) != mp.end())maxi = max(maxi, i - mp[sum - K]);
            if(mp.find(sum) == mp.end()) mp[sum] = i;
        }
        return maxi;
    } 
```