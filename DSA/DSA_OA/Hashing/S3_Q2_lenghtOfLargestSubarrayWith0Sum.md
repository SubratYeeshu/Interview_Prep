## Lenght of Largest Subarray With 0 Sum
## https://practice.geeksforgeeks.org/problems/largest-subarray-with-0-sum/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article

```cpp
int maxLen(vector<int>&A, int n)
    {   
        int sum = 0, maxi = -1;
        unordered_map<int, int> mp;
        mp[0] = -1;
        for(int i = 0; i < n; i++){
            sum += A[i];
            // if mp[0] is not set to -1
            // we can do a check here if(sum == k) here k is equal to 0 we can update with i + 1 as max index
            
            // Index upto which the sum is (sum - 0) subarray after this index to ith index will have sum as 0
            // as it is being nullified
            if(mp.find(sum - 0) != mp.end())maxi = max(maxi, i - mp[sum - 0]);
            if(mp.find(sum) == mp.end())mp[sum] = i;
        }
        return maxi == -1 ? 0 : maxi;
    }
```