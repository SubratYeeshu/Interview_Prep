## Number of subarrays having sum exactly equal to k
## https://practice.geeksforgeeks.org/problems/subarrays-with-sum-k/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article

```cpp
int findSubArraySum(int nums[], int n, int k)
    {
        int sum = 0, count = 0;
        unordered_map<int, int> mp;
        mp[0] = 1;
        for(int i = 0 ; i < n ; i++){
            sum += nums[i];
            
            if(mp.find(sum - k) != mp.end()){
                count += mp[sum - k];
            }
            mp[sum]++;
        }
        return count;
    }
```