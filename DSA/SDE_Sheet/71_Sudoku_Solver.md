# Sudoku Solver

## Problem statement 

- Write a program to solve a Sudoku puzzle by filling the empty cells.
A sudoku solution must satisfy all of the following rules:
- Each of the digits 1-9 must occur exactly once in each row.
- Each of the digits 1-9 must occur exactly once in each column.
- Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
The '.' character indicates empty cells.

## Approach 1 : Backtracking

Time complexity : O(9<sup>(N^2)</sup>) All permutations + copy
Space complexity : O(rec)

```cpp
bool isSafe(char c, int row, int col, vector<vector<char>> &board){
    // Down
    for(int i = 0 ; i < 9 ; i++)if(board[i][col] == c)return false;
    
    // Right
    for(int j = 0 ; j < 9 ; j++)if(board[row][j] == c)return false;
    
    // Inside box
    int sub_row = (row / 3) * 3, sub_col = (col / 3) * 3;
    for(int i = sub_row ; i < sub_row + 3 ; i++)
        for(int j = sub_col ; j < sub_col + 3 ; j++)
            if(board[i][j] == c)
                return false;
    
    return true;
    
}

bool solve(int i, int j, vector<vector<char>> &board){
    if(i == 9)return true;
    if(j == 9)return solve(i + 1, 0, board);
    if(board[i][j] != '.')return solve(i, j + 1, board);
    
    for(char ch = '1' ; ch <= '9' ; ch++){
        if(isSafe(ch, i, j, board)){
            board[i][j] = ch;
            if(solve(i, j + 1, board))return true;
            board[i][j] = '.';
        }
    }
    
    return false;
}

void solveSudoku(vector<vector<char>>& board) {
    solve(0, 0, board);
}
```