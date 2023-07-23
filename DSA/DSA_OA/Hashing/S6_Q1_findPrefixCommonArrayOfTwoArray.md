## Find Prefix Common Array of Two Arrays
## https://leetcode.com/problems/find-the-prefix-common-array-of-two-arrays/

```cpp
class Solution {
public:
    vector<int> findThePrefixCommonArray(vector<int>& A, vector<int>& B) {
        vector<int> res(A.size(), 0);
        unordered_set<int> st;
        for(int i = 0 ; i < A.size() ; i++){
            st.insert(A[i]);
            int count = 0;
            for(int j = 0 ; j <= i ; j++){
                if(st.count(B[j]))count++;
            }
            res[i] = count;
        }
        return res;
        
        // vector<int> ans;
        // vector<int> v(51,0);
        // int x = 0;
        // for(int i = 0; i < A.size(); i++){
        //     if(v[A[i]]<0)
        //         x++;
        //     v[A[i]]++;
        //     if(v[B[i]]>0)
        //         x++;
        //     v[B[i]]--;
        //     ans.push_back(x);
    }
};
```