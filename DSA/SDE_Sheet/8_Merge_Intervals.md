# Merge Intervals

## Problem Statement

Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

## Approach 1 : Sortig + Using two for loops

- Time complexity : O(N \* LogN + 2 \* N)  
- Space complexity : O(N)

```cpp
vector<vector<int>> merge(vector<vector<int>>& intervals) {
    int n = intervals.size();
    vector<vector<int>> ans;
    sort(intervals.begin(), intervals.end());
    for(int i = 0 ; i < n ; i++){
        int start = intervals[i][0];
        int end = intervals[i][1];

        if(!ans.empty() && ans.back()[1] >= end)continue;

        for(int j = i + 1 ; j < n ; j++){
            int newStart = intervals[j][0];
            int newEnd = intervals[j][1];

            if(newStart <= end)end = max(end, newEnd);
            else break;
        }

        ans.push_back({start, end});
    }

    return ans;
}
```

## Approach 2 : Sortig + Optimization

- Time complexity : O(N \* LogN + N)  
- Space complexity : O(N)

```cpp
vector<vector<int>> merge(vector<vector<int>>& intervals) {
    int n = intervals.size();
    vector<vector<int>> ans;
    sort(intervals.begin(), intervals.end());
    
    for(int i = 0 ; i < n ; i++){
        int start = intervals[i][0];
        int end = intervals[i][1];
        
        if(ans.empty() || ans.back()[1] < start)ans.push_back({start, end});
        else ans.back()[1] = max(ans.back()[1], end);
    }
    return ans;
}
```
