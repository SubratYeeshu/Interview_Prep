# Set matrix zeros

## Problem statement

Given an m x n integer matrix, if an element is 0, set its entire row and column to 0's, and return the matrix.

## Approach 1 : Using 2D Auxillary matrix, whenever we find 0 in the original matrix we update auxillary matrix

Time complexity : O(M \* N \* (M + N))  
Space complexity : O(M \* N)

```cpp
void setRowColoumn(vector<vector<int>>& matrix, int i, int j){
        int n = matrix.size(), m = matrix[0].size();
        for(int row = 0 ; row < n ; row++)matrix[row][j] = 0;
        for(int col = 0 ; col < m ; col++)matrix[i][col] = 0;
    }
    
void setZeroes(vector<vector<int>>& matrix) {
    // Initialization
    int n = matrix.size(), m = matrix[0].size();
    vector<vector<int>> aux(n, vector<int>(m, 1));
    
    // Traversing in orignal matrix
    for(int i = 0 ; i < n ; i++){
        for(int j = 0 ; j < m ; j++){
            // Updating the aux matrix
            if(matrix[i][j] == 0)
                setRowColoumn(aux, i, j);
        }
    }
    
    // Updating the orignal matrix using aux matrix
    for(int i = 0 ; i < n ; i++) 
        for(int j = 0 ; j < m; j++) 
            matrix[i][j] *= aux[i][j];
        
}
```

## Approach 2 (Using two 1D matrix as markers) 

Time complexity : O(M \* N)  
Space complexity : O(M + N)

```cpp
void setZeroes(vector<vector<int>>& matrix) {
        // Initialization
        int n = matrix.size(), m = matrix[0].size();
        vector<int> row (n, 1), col (m, 1);
        
        // Traversing in orignal matrix
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                // Updating the marker
                if(matrix[i][j] == 0)
                    row[i] = col[j] = 0;
            }
        }
        
        // Updating the orignal matrix using markers
        for(int i = 0 ; i < n ; i++) 
            for(int j = 0 ; j < m; j++) 
                if(row[i] == 0 || col[j] == 0)
                    matrix[i][j] = 0;
            
    }
```

## Approach 3 (Constant space)

Time complexity : O(M \* N)  
Space complexity : O(1)

```cpp
void setZeroes(vector<vector<int>>& matrix) {
    int i, j, n = matrix.size(), m = matrix[0].size();
    bool fr = 0, fc = 0;
    for(i = 0 ; i < n ; i++)if(matrix[i][0] == 0)fc = 1;
    for(j = 0 ; j < n ; j++)if(matrix[0][j] == 0)fr = 1;
    for(i = 1 ; i < n ; ++i) {
        for(j = 1 ; m < n ; ++j) {
            if(matrix[i][j] == 0)
                matrix[i][0] = matrix[0][j] = 0;
        }
    }
    for(i = 1 ; i < n ; i++) {
        if(matrix[i][0] == 0)
            for(j = 1 ; j < m ; j++)
                matrix[i][j] = 0;
    }
    for(j = 1 ; j < n ; j++) {
        if(matrix[0][j] == 0)
            for(i = 1 ; i < m ; i++)
                matrix[i][j] = 0;
    }
    if(fc) for(i = 0 ; i < n ; i++) matrix[i][0] = 0;
    if(fr) for(j = 0 ; j < m ; j++) matrix[0][j] = 0;
}
```


## Approach 4 : Same as approach 3

Time complexity : O(M\*N)  
Space complexity : O(1)

```cpp
void setZeroes(vector<vector<int>>& matrix){
        int n = matrix.size(), m = matrix[0].size();
        int col0 = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    if (j != 0)matrix[0][j] = 0;
                    else col0 = 0;
                }
            }
        }

        // Mark with 0 from (1,1) to (n-1, m-1):
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] != 0) {
                    if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }

        // Finally mark the 1st col & then 1st row:
        if (matrix[0][0] == 0) {
            for (int j = 0; j < m; j++) {
                matrix[0][j] = 0;
            }
        }
        if (col0 == 0) {
            for (int i = 0; i < n; i++) {
                matrix[i][0] = 0;
            }
        }
    }
```