## Count Number of Subarrays with Given XOR
## https://www.codingninjas.com/codestudio/problems/subarrays-with-xor-k_6826258?utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_Arrayproblems

```cpp
#include<bits/stdc++.h>
int subarraysWithSumK(vector<int> nums, int k) {
/*
       _____________________  -> XR value
       . . . . . . . . . . .
       _ _________________
       x        k value subarray

       x^k = XR (xoring both side with k)
       x = XR ^ k  

*/

    // Xor vs freq 
    int res = 0, currXor = 0;
    unordered_map<int, int> mp;
    mp[0] = 1;

    for(int i = 0 ; i < nums.size() ; i++){
        currXor ^= nums[i];

        if(mp.find(currXor ^ k) != mp.end())res += mp[currXor ^ k];
        mp[currXor]++;
    }
    return res;

}
```