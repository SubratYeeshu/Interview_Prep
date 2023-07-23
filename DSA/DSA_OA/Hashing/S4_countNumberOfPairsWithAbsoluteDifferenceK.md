## Count Number of Pairs with Absolute Difference K
## https://leetcode.com/problems/count-number-of-pairs-with-absolute-difference-k/description/

```cpp
class Solution {
public:
    /*
    
        -> Check whether nums[i] + k and nums[i] - k is present in the map as we are checking for absolute                  difference and if found increase the res by freq of it
    
    */
    int countKDifference(vector<int>& nums, int k) {
        unordered_map<int, int> mp;
        int res = 0;
        for(int i = 0 ; i < nums.size() ; i++){
            if(mp[nums[i] + k])res += mp[nums[i] + k];
            if(mp[nums[i] - k])res += mp[nums[i] - k];
            mp[nums[i]]++;
        }
        return res;
    }
};
```