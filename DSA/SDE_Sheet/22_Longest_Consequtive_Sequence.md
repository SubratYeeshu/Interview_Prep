# Longest Consecutive Sequence

## Problem statement

- Given an unsorted array of integers `nums`, return the length of the longest consecutive elements sequence.`

## Approach 1 : Sorting

Time complexity : O(N \* log(N))  
Space complexity : O(N) (sort)

```cpp
int longestConsecutive(vector<int>& nums) {
    if(nums.size() == 0) return 0;
    sort(nums.begin(), nums.end());
    int res = 0, c = 1, n = nums.size();
    for(int i = 1 ; i < n ; i++) {
        if(nums[i] == nums[i - 1] + 1)c++;
        else {
            res = max(res, c);
            c = 1;
        }
    }
    res = max(res, c);
    return res;
}
```

## Approach 1 : Optimal using set

Time complexity : O(N + 2N)  
Space complexity : O(N) 

```cpp
int longestConsecutive(vector<int>& nums) {
    int n = nums.size();
    if(n == 0)return 0;
    unordered_set<int> st;
    int maxi = 0;
    
    for(auto i : nums)st.insert(i);
    
    for(auto x : st){
        if(st.find(x - 1) != st.end())continue;
        else{
            // Smallest number of a sequence
            int currNum = x, currStreak = 1;
            while(st.count(currNum + 1)){
                currNum++;
                currStreak++;
            }
            maxi = max(maxi, currStreak);
        }
    }
    return maxi;
}
```