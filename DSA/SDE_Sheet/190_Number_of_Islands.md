# Number of Islands

## Problem statement

Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

## Approach 1 : DFS

- Time complexity : O(N\*M) 
- Space complexity : O(N\*M) ~ Recursion Depth

```cpp
int dx[4] = {-1, 1, 0, 0}, dy[4] = {0, 0, 1, -1};
void dfs(vector<vector<char>> &grid, int i, int j) {
    int n = grid.size();
    int m = grid[0].size();

    if (i < 0 || i >= n || j < 0 || j >= m || grid[i][j] == '0')return;

    grid[i][j] = '0';

    for (int k = 0; k < 4; k++) {
        int ni = i + dx[k];
        int nj = j + dy[k];
        dfs(grid, ni, nj);
    }
}

int numIslands(vector<vector<char>>& grid){
    int n = grid.size(), m = grid[0].size(), c = 0;
    
    for(int i = 0 ; i < n ; i++){
        for(int j = 0 ; j < m ; j++){
            if(grid[i][j] == '1'){
                dfs(grid, i, j);
                c++;
            }
        }
    }
    
    return c;
}
```

## Approach 2 : BFS

- Time complexity : O(N\*M) 
- Space complexity : O(N\*M) 

```cpp
int dx[4] = {-1, 1, 0, 0}, dy[4] = {0, 0, 1, -1};
void bfs(int i, int j, vector<vector<char>> &grid){
    queue<pair<int, int>> q;
    q.push({i, j});
    
    while(!q.empty()){
        pair<int, int> temp = q.front(); q.pop();
        
        for(int k = 0 ; k < 4 ; k++){
            int newX = temp.first + dx[k];
            int newY = temp.second + dy[k];
            
            if(newX >= 0 && newX < grid.size() && newY >= 0 && newY < grid[i].size() && grid[newX][newY] == '1'){
                grid[newX][newY] = '2';
                q.push({newX, newY});
            }
        }
    }
}

int numIslands(vector<vector<char>>& grid){
    int n = grid.size(), m = grid[0].size(), c = 0;
    
    for(int i = 0 ; i < n ; i++){
        for(int j = 0 ; j < m ; j++){
            if(grid[i][j] == '1'){
                c++;
                bfs(i, j, grid);
            }
        }
    }
    
    return c;
}
```

## Approach 3 : DSU

- Time complexity : O(N\*M) 
- Space complexity : O(N\*M) 

```cpp
int find(vector<int>& par, int i) {
    if (par[i] == i) return i;
    return par[i] = find(par, par[i]);
}
void unionFind(vector<int>& par,vector<int>& rank, int i, int j) {
    if (par[i] > par[j])
        par[j] = i;
    else if (par[j] > par[i])
        par[i] = j;
    else {
        par[i] = j;
        rank[j]++;
    }
}
int numIslands(vector<vector<char>>& grid) {
    int n = grid.size(), m = grid[0].size();
    if (n == 0 || m == 0) return 0;
    vector<int> par(n * m, 0);
    vector<int> rank(n * m, 1);
    for (int i = 0; i < par.size(); ++i)par[i] = i;
    vector<vector<int>> dir {{1, 0}, {0, 1}};
    int count = 0;
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            if (grid[i][j] == '1') {
                count++;
                for (int d = 0; d < dir.size(); ++d) {
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];
                    if (r < 0 or c < 0 or r >= n or c >= m or grid[r][c] == '0')
                        continue;
                    else {
                        int par1 = find(par, i * m + j);
                        int par2 = find(par, r * m + c);
                        if (par1 != par2) {
                            unionFind(par, rank, par1, par2);
                            count--;
                        }
                    }
                }
            }
        }
    }
    return count;
}
```