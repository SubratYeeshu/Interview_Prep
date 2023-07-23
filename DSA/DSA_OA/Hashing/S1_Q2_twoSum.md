## Two Sum
## https://leetcode.com/problems/two-sum/

```cpp
class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        unordered_map<int, int> mp;
        
        // O(N)
        for(int i = 0 ; i < nums.size() ; i++){
            // O(1)
            int secondNumber = target - nums[i];
            if(mp.count(secondNumber))return {i, mp[secondNumber]};
            else mp[nums[i]] = i;
        }
        
        return {-1};
    }
};

```