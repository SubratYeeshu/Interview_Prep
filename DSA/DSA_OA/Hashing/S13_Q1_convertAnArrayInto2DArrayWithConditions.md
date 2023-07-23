## Convert an Array Into a 2D Array With Conditions
## https://leetcode.com/problems/convert-an-array-into-a-2d-array-with-conditions/

```cpp
class Solution {
public:
    // Approach 2 : Using hashing
    vector<vector<int>> findMatrix(vector<int>& nums) {
        vector<vector<int>> res;
        map<int, int> mp; 
        for(auto i : nums)mp[i]++;
        
        while(true){
            vector<int> temp;
            bool newRowCreated = false;
            for(auto &[num, numFreq] : mp){
                if(numFreq >= 1){
                    temp.push_back(num);
                    newRowCreated = true;
                }
                numFreq--;
            }
            if(temp.size())res.push_back(temp);
            if(!newRowCreated)break;
        }
        return res;
    }
    
    // Approach 1 : O(maxFreq of Number) 
    // vector<vector<int>> findMatrix(vector<int>& nums) {
    //     vector<vector<int>> res;
    //     vector<int> vis (nums.size(), 0);
    //     int x = 0;
    //     while(x != nums.size()){
    //         vector<int> temp;
    //         unordered_set<int> st;
    //         for(int i = 0 ; i < nums.size() ; i++){
    //             if(!vis[i] && !st.count(nums[i])){
    //                 x++;
    //                 temp.push_back(nums[i]);
    //                 vis[i] = 1;
    //                 st.insert(nums[i]);
    //             }
    //         }
    //         res.push_back(temp);
    //     }
    //     return res;
    // }
};
```