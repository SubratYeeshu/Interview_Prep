# Maximal Rectangle

## Problem statement

Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

## Approach 1 : Stack + (Area under histrogram)

- Time complexity : O(N^2)  
- Space complexity : O(N^2)

```cpp
vector<int> getNextSmaller(vector<int> &nums){
    int n = nums.size();
    vector<int> nextSmaller (n, -1);
    stack<int> st;

    for(int i = n - 1 ; i >= 0 ; i--){
        while(st.size() && nums[i] <= nums[st.top()])st.pop();
        if(st.size())nextSmaller[i] = st.top(); 
        st.push(i);
    }        

    return nextSmaller;
}

vector<int> getPrevSmaller(vector<int> &nums){
    int n = nums.size();
    vector<int> prevSmaller(n, -1);
    stack<int> st;

    for(int i = 0 ; i < n ; i++){
        while(st.size() && nums[i] <= nums[st.top()])st.pop();
        if(st.size())prevSmaller[i] = st.top();
        st.push(i);
    }

    return prevSmaller;
}

// We have to check how much a building can be expanded, a smaller building will stop stop the expansion
int largestRectangleArea(vector<int>& heights) {
    int n = heights.size(), max_area = INT_MIN;
    vector<int> prevSmaller = getPrevSmaller(heights);
    vector<int> nextSmaller = getNextSmaller(heights);
    vector<int> areaPossible (n, 0);

    for(int i = 0 ; i < n ; i++){
        int leftSmallerIndex = prevSmaller[i];
        int rightSmallerIndex = nextSmaller[i];
        if(rightSmallerIndex == -1)rightSmallerIndex = n;  // No constraint on expansion to the right 
        if(leftSmallerIndex == -1)leftSmallerIndex = -1;  // No constraint on expansion to the left
        int length = heights[i], breadth = rightSmallerIndex - leftSmallerIndex - 1;
        areaPossible[i] = length * breadth;
        max_area = max(max_area, areaPossible[i]);
    }

    return max_area;
}

// Create histogram for every dimension and find the max from them (eg: 1X4, 2X4, 3X4, 4X4)
int maximalRectangle(vector<vector<char>>& matrix) {
    int n = matrix.size(), m = matrix[0].size(), maxi = INT_MIN;
    vector<vector<int>> histo (n, vector<int> (m, 0));
    
    for(int i = 0 ; i < n ; i++){
        for(int j = 0 ; j < m ; j++){
            if(i == 0){
                if(matrix[i][j] == '1'){
                    histo[i][j] = 1;
                }else{
                    histo[i][j] = 0;
                }
            }else{
                if(matrix[i][j] == '1'){
                    histo[i][j] = histo[i - 1][j] + 1;
                }else{
                    histo[i][j] = 0;
                }
            }
        }
    }
    
    for(int i = 0 ; i < n ; i++){
        int areaUnderGivenDimension = largestRectangleArea(histo[i]);
        maxi = max(maxi, areaUnderGivenDimension);
    }
    
    return maxi;
}
```

## Approach 1.2 : Stack + (Area under histogram)

- Time complexity : O(N^2)  
- Space complexity : O(N)

```cpp
vector<int> getNextSmaller(vector<int> &nums){
    int n = nums.size();
    vector<int> nextSmaller (n, -1);
    stack<int> st;

    for(int i = n - 1 ; i >= 0 ; i--){
        while(st.size() && nums[i] <= nums[st.top()])st.pop();
        if(st.size())nextSmaller[i] = st.top(); 
        st.push(i);
    }        

    return nextSmaller;
}

vector<int> getPrevSmaller(vector<int> &nums){
    int n = nums.size();
    vector<int> prevSmaller(n, -1);
    stack<int> st;

    for(int i = 0 ; i < n ; i++){
        while(st.size() && nums[i] <= nums[st.top()])st.pop();
        if(st.size())prevSmaller[i] = st.top();
        st.push(i);
    }

    return prevSmaller;
}

// We have to check how much a building can be expanded, a smaller building will stop stop the expansion
int largestRectangleArea(vector<int>& heights) {
    int n = heights.size(), max_area = INT_MIN;
    vector<int> prevSmaller = getPrevSmaller(heights);
    vector<int> nextSmaller = getNextSmaller(heights);
    vector<int> areaPossible (n, 0);

    for(int i = 0 ; i < n ; i++){
        int leftSmallerIndex = prevSmaller[i];
        int rightSmallerIndex = nextSmaller[i];
        if(rightSmallerIndex == -1)rightSmallerIndex = n;  // No constraint on expansion to the right 
        if(leftSmallerIndex == -1)leftSmallerIndex = -1;  // No constraint on expansion to the left
        int length = heights[i], breadth = rightSmallerIndex - leftSmallerIndex - 1;
        areaPossible[i] = length * breadth;
        max_area = max(max_area, areaPossible[i]);
    }

    return max_area;
}

// Create histogram for every dimension and find the max from them (eg: 1X4, 2X4, 3X4, 4X4)
int maximalRectangle(vector<vector<char>>& matrix) {
    int n = matrix.size(), m = matrix[0].size(), maxi = INT_MIN;
    vector<int> histo (m, 0);
    
    for(int j = 0 ; j < m ; j++)
        if(matrix[0][j] == '1')histo[j] = 1;
        else histo[j] = 0;
    
    maxi = max(maxi, largestRectangleArea(histo));
    
    for(int i = 1 ; i < n ; i++){
        for(int j = 0 ; j < m ; j++){
            if(matrix[i][j] == '1')histo[j] = histo[j] + 1;
            else histo[j] = 0;
        }
        maxi = max(maxi, largestRectangleArea(histo));
    }
    
    return maxi;
}
```