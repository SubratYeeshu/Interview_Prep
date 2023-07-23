## Square of sorted array
## https://leetcode.com/problems/squares-of-a-sorted-array/

```cpp
class Solution {
public:
    vector<int> sortedSquares(vector<int>& nums) {
        int i = 0, j = nums.size() - 1, idx = nums.size() - 1;
        vector<int> res (nums.size(), 0);
        while(i <= j){
            int val1 = nums[i] * nums[i];
            int val2 = nums[j] * nums[j];
            // Either the first element can be the largest or the last element after squarring
            if(val1 > val2){
                res[idx] = val1;
                i++;
            }else{
                res[idx] = val2;
                j--;
            }
            
            idx--;
        }
        return res;
    }
};
```