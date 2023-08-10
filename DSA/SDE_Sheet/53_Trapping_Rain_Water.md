# Trapping Rain Water

## Problem statement

- Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

## Approach 1 : Prefix and Suffix Array (Extra space)

Time complexity : O(N) 
Auxiliary space : O(N)

```cpp
int trap(vector<int>& height) {
    int n = height.size(), water = 0;
    vector<int>left(n, 0), right(n, 0);

    left[0] = height[0];
    right[n - 1] = height[n - 1];
    
    for(int i = 1 ; i < n ; i++)left[i] = max(left[i - 1], height[i]);
    for(int i = n - 2 ; i >= 0 ; i--)right[i] = max(right[i + 1], height[i]);
        
    for(int i = 0 ; i < n ; i++)water += min(left[i], right[i]) - height[i]; 
    return water;
}
```

## Approach 2 : Without using extra space

Time complexity : O(N) 
Auxiliary space : O(1)

```cpp
int trap(vector<int>& height) {
    int leftMax = 0, rightMax = 0, i = 0, j = height.size() - 1, water = 0;
    
    while(i <= j){
        leftMax = max(leftMax, height[i]);
        rightMax = max(rightMax, height[j]);
        
        if(leftMax < rightMax){
            water += leftMax - height[i];
            i++;
        }else{
            water += rightMax - height[j];
            j--;
        }
    }
    
    return water;
}
```