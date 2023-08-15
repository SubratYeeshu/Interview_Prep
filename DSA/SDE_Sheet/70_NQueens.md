# N - Queens

## Problem statement 

- The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other. Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order. Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.

## Approach 1 : Backtracking

Time complexity : O(N \* N!) All permutations + copy
Space complexity : O(N^2)

```cpp
int n = 0;
bool isSafe(vector<string> &grid, int row, int col){        
    for(int i = col; i >= 0; i--)if(grid[row][i] == 'Q') return false;  // Left
    for(int i = row, j = col; i >= 0 && j >= 0; i--, j--)if(grid[i][j] == 'Q') return false;  // UPLEFT
    for(int i = row, j = col; i < n && j >= 0; i++, j-- )if(grid[i][j] == 'Q') return false;  // DOWNLEFT
    
    return true;
}

void solve(int col, vector<string> &board, vector<vector<string>> &res){
    if(col >= n)res.push_back(board);
    
    for(int row = 0; row < n; row++){
        if(isSafe(board, row, col)){
            board[row][col] = 'Q';
            solve(col + 1, board, res);
            board[row][col] = '.';
        }
        
    }
}

vector<vector<string>> solveNQueens(int n) {
    this -> n = n;
    vector<vector<string>> res;
    
    // Creating empty board
    vector<string> board (n, string(n, '.'));
        
    // Solve helper function
    solve(0, board, res);
    
    return res;
}
```
## Approach 2 : 


