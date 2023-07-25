# Pascal Triangle

## Problem statement

There can be three variation to this problem

## Variation 1 : Find the element present at the give row number and coloumn number

Time complexity : O(M \* N \* (M + N))  
Space complexity : O(M \* N)

```cpp
int ncr(int n, int r){
    // If we calulate ncr in basic way it would take a lot of time and space
    // So we have to optimize it by resolving the formula
    // eg: 12C3 = 12 * 11 * 10 / 1 * 2 * 3 we go three up and three down 
    
    int ans = 1;
    for(int i = 1 ; i <= r ; i++){
        ans = ans * (n - i + 1);
        ans = ans / i;
    }
    return ans;
}
    
void type1(int row, int col){
    // So to find any element of a pascal triangle we have formula (r-1)C(c-1) for given row coloumn
    // So bascially we have to calculate the ncr formula efficiently 
    cout << ncr(row - 1, col - 1); 
}
```

## Variation 2 : Generate the row with given row number

Time complexity :  Time complexity : O(N)  
Space complexity : O(1)

```cpp
void type2(int row){
    int val = 1;
    for(int i = 1 ; i <= row ; i++){
        // 1st Method 
        // type1(row, i);
        // cout << " ";
        
        // 2nd Method (Faster)
        cout << val << " ";
        val = val * (row - i);
        val = val / i;
    }
}

type2(5);
```

## Variation 3 : Generate the whole pascal triangle

Time complexity : O(N \* 2)  
Space complexity : O(1)

```cpp
// Generating a row O(N)
vector<int> type3(int row){
    vector<int> generatedRow;
    generatedRow.push_back(1);
    int val = 1;
    for(int i = 1 ; i < row ; i++){
        val = val * (row - i);
        val = val / i;
        generatedRow.push_back(val);
    }   
    return generatedRow;
}

// Generating the triangle
vector<vector<int>> generate(int numRows) {
    vector<vector<int>> pascalTriangle;
    for(int i = 1 ; i <= numRows ; i++){
        pascalTriangle.push_back(type3(i));
    }
}
```

## Variation 3 : Generate the whole pascal triangle 

Time complexity : O(N \* 2)  
Space complexity : O(1)

```cpp
// Another approach
vector<vector<int>> pascalTriangle;
    for(int i = 0 ; i < numRows ; i++){
        vector<int> tempRow (i + 1, 1);
        for(int col = 1 ; col < i ; col++){
            tempRow[col] = pascalTriangle[i - 1][col - 1] + pascalTriangle[i - 1][col];
        }
        pascalTriangle.push_back(tempRow);
}
```

