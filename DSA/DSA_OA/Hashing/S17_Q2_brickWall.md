## Brick Wall
## https://leetcode.com/problems/brick-wall/

```cpp
class Solution {
public:
    int leastBricks(vector<vector<int>>& wall) {
        int maxBricksEdge = 0;
        unordered_map<int, int> mp;
        for(int i = 0 ; i < wall.size() ; i++){
            for(int j = 0 ; j < wall[i].size() - 1 ; j++){  // Ignore the last brick
                if(j!=0)wall[i][j] = wall[i][j] + wall[i][j - 1];
                mp[wall[i][j]]++;
                maxBricksEdge = max(maxBricksEdge, mp[wall[i][j]]);
            }
        }
        // Maxmium edge == minimum intersection
        return wall.size() - maxBricksEdge;
    }
};
// class Solution {
// public:
//     int leastBricks(vector<vector<int>>& wall) {
//         int maxBricksEdge = 0;
//         unordered_map<int, int> mp;
//         for(int i = 0 ; i < wall.size() ; i++){
//             for(int j = 0 ; j < wall[i].size() - 1 ; j++){  // Ignore the last brick
//                 if(j!=0)wall[i][j] = wall[i][j] + wall[i][j - 1];
//                 mp[wall[i][j]]++;
//             }
//         }
//         // Maxmium edge == minimum intersection
//         for(auto i : mp)maxBricksEdge = max(maxBricksEdge, i.second);
//         return wall.size() - maxBricksEdge;
//     }
// };
```