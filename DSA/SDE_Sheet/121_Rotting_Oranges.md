# Rotting Oranges

You are given an m x n grid where each cell can have one of three values:
- 0 representing an empty cell,
- 1 representing a fresh orange, or
- 2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten. Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.

## Approach 1 : Using BFS (Three loop format)

Time complexity : O(N \* M) 
Space complexity : O(N \* M)

```cpp
int orangesRotting(vector<vector<int>>& grid) {
    int n = grid.size(), m = grid[0].size();
    vector<vector<int>> vis (n, vector<int> (m, 0));
    vector<int> drx {1, 0, -1, 0}, dry {0, 1, 0, -1}; 
    queue<pair<int, int>> q;
    
    for(int i = 0 ; i < n ; i++){
        for(int j = 0 ; j < m ; j++){
            if(grid[i][j] == 2){
                q.push({i, j});
                vis[i][j] = 2;
            }
        }
    }
    
    int time = 0;
    bool f = false;
    while(!q.empty()){
        f = true;
        int size = q.size();
        time++;
        for(int i = 0 ; i < size ; i++){
            auto node = q.front();
            q.pop();
            for(int k = 0 ; k < 4 ; k++){
                int nx = node.first + drx[k];
                int ny = node.second + dry[k];
                
                if(nx >= 0 && ny >= 0 && nx < n && ny < m){
                    if(grid[nx][ny] == 1 && vis[nx][ny] == 0){
                        q.push({nx, ny});
                        vis[nx][ny] = 2;
                    }
                }
            }
        }
    }
    
    for(int i = 0 ; i < n ; i++){
        for(int j = 0 ; j < m ; j++){
            if(vis[i][j] != 2 && grid[i][j] == 1)return -1;
        }
    }
    
    if(!f)return 0;
    return time - 1;
}
```

## Approach 2 : Using BFS (Two loop format)

Time complexity : O(N \* M) 
Space complexity : O(N \* M)

```cpp
int orangesRotting(vector<vector<int>>& grid) {
    int n = grid.size(), m = grid[0].size();
    vector<vector<int>> vis(n, vector<int> (m, 0));
    queue<pair<pair<int, int>, int>>q;
    vector<int> drx {1, 0, -1, 0};
    vector<int> dry {0, 1, 0, -1};
    int tMax = 0;
    
    // Multisource bfs initial configuration
    for(int i = 0 ; i < n ; i++){
        for(int j = 0 ; j < m ; j++){
            if(grid[i][j] == 2){
                q.push({{i, j}, 0});
                vis[i][j] = 2;
            }
        }
    }
    
    while(!q.empty()){
        int row = q.front().first.first;
        int col = q.front().first.second;
        int time = q.front().second;
        tMax = max(time, tMax);
        q.pop();
        for(int k = 0 ; k < 4 ; k++){
            int newRow = row + drx[k];
            int newCol = col + dry[k];
            
            if(newRow >= 0 && newCol >= 0 && newRow < n && newCol < m){
                if(grid[newRow][newCol] == 1 && vis[newRow][newCol] == 0){
                    q.push({{newRow, newCol}, time + 1});
                    vis[newRow][newCol] = 2;
                }
            }
        }
    }
    
    
    // Checking if all oranges are rotten or not
    for(int i = 0 ; i < n ; i++){
        for(int j = 0 ; j < m ; j++){
            if(vis[i][j] != 2 && grid[i][j] == 1)return -1;
        }
    }
    
    return tMax;
}
```