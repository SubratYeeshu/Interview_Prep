# Geek's Training

## Problem statement

Geek is going for n days training program, he can perform any one of these three activities Running, Fighting, and Learning Practice. Each activity has some point on each day. as Geek wants to improve all his skills, he can't do the same activity on two consecutive days, help Geek to maximize his merit points as we were given a 2D array of n*3 points corresponding to each day and activity.

## Approach 1.1 : DP (Map)

- Time Complexity : O(N \* 4 \* 3) 
- Space Complexity : O(N \* 3 + Rec)

```cpp
map<pair<int, int>, int> dp;
int solve(int index, vector<vector<int>>& points, int prev){
    if(index >= points.size())return 0;
    
    if(dp.count({index, prev}))return dp[{index, prev}];
    
    int ans = INT_MIN;
    if(prev == -1){
        ans = max({points[index][0] + solve(index + 1, points, 0),
                    points[index][1] + solve(index + 1, points, 1),
                    points[index][2] + solve(index + 1, points, 2)});
    }else if(prev == 0){
        ans = max(points[index][1] + solve(index + 1, points, 1),
                    points[index][2] + solve(index + 1, points, 2));
    }else if(prev == 1){
        ans = max(points[index][0] + solve(index + 1, points, 0),
                    points[index][2] + solve(index + 1, points, 2));
    }else if(prev == 2){
        ans = max(points[index][0] + solve(index + 1, points, 0),
                    points[index][1] + solve(index + 1, points, 1));
    }
    return dp[{index, prev}] = ans;
}

int maximumPoints(vector<vector<int>>& points, int n) {
    return solve(0, points, -1);
}
```

## Approach 1.2 : DP (Array / Vector) 

- Time Complexity : O(N \* 4 \* 3) 
- Space Complexity : O(N \* 3 + Rec)

```cpp
int dp[100001][3];
int solve(int index, vector<vector<int>>& points, int prev){
    if(index >= points.size())return 0;
    
    if(dp[index][prev] != -1)return dp[index][prev];
    
    int ans = INT_MIN;
    if(prev == 3){
        ans = max({points[index][0] + solve(index + 1, points, 0),
                    points[index][1] + solve(index + 1, points, 1),
                    points[index][2] + solve(index + 1, points, 2)});
    }else if(prev == 0){
        ans = max(points[index][1] + solve(index + 1, points, 1),
                    points[index][2] + solve(index + 1, points, 2));
    }else if(prev == 1){
        ans = max(points[index][0] + solve(index + 1, points, 0),
                    points[index][2] + solve(index + 1, points, 2));
    }else if(prev == 2){
        ans = max(points[index][0] + solve(index + 1, points, 0),
                    points[index][1] + solve(index + 1, points, 1));
    }
    return dp[index][prev] = ans;
}

int maximumPoints(vector<vector<int>>& points, int n) {
    memset(dp, -1, sizeof(dp));
    return solve(0, points, 3);
}
```

## Approach 1.3 : DP (Array / Vector) + Index From Start

- Time Complexity : O(N \* 4 \* 3) 
- Space Complexity : O(N \* 3 + Rec)

```cpp
int dp[100001][3];
int solve(int index, vector<vector<int>>& points, int choice){
    if(index >= points.size()) return 0;
    if(dp[index][choice] != -1) return dp[index][choice];
    if(choice == 0){
        return dp[index][choice] = points[index][choice] 
            + max(solve(index + 1, points, 1), solve(index + 1, points, 2));
    }
    else if(choice == 1){
        return dp[index][choice] = points[index][choice] 
            + max(solve(index + 1, points, 0), solve(index + 1, points, 2));
    }
    else if(choice == 2){
        return dp[index][choice] = points[index][choice] 
            + max(solve(index + 1, points, 0), solve(index + 1, points, 1));
    }
}
int maximumPoints(vector<vector<int>>& points, int n) {
    memset(dp, -1, sizeof(dp));
    // For using DP with array / vector we dont have index  -1
    return max(solve(0, points, 0), max(solve(0, points, 1), solve(0, points, 2)));
}
```

## Approach 1.4 : DP (Array / Vector) + Index From End

- Time Complexity : O(N \* 4 \* 3) 
- Space Complexity : O(N \* 3 + Rec)

```cpp
int dp[100001][3];
int solve(int index, vector<vector<int>>& points, int choice){
    if(index < 0) return 0;
    if(dp[index][choice] != -1) return dp[index][choice];
    if(choice == 0){
        return dp[index][choice] = points[index][choice] 
            + max(solve(index - 1, points, 1), solve(index - 1, points, 2));
    }
    else if(choice == 1){
        return dp[index][choice] = points[index][choice] 
            + max(solve(index - 1, points, 0), solve(index - 1, points, 2));
    }
    else if(choice == 2){
        return dp[index][choice] = points[index][choice] 
            + max(solve(index - 1, points, 0), solve(index - 1, points, 1));
    }
}
int maximumPoints(vector<vector<int>>& points, int n) {
    memset(dp, -1, sizeof(dp));
    // For using DP with array / vector we dont have index  -1
    return max(solve(n-1, points, 0), max(solve(n-1, points, 1), solve(n-1, points, 2)));
}
```

## Approach 1.5 : DP (Using For Loop) + Index From Start

- Time Complexity : O(N \* 4 \* 3) 
- Space Complexity : O(N \* 3 + Rec)

```cpp
int dp[100001][3];
int solve(int day, vector<vector<int>>& points, int prev){
    if(day >= points.size())return 0;
    
    if(dp[day][prev] != -1)return dp[day][prev];
    
    int ans = INT_MIN;
    
    for(int i = 0 ; i < 3 ; i++){
        if(i != prev){
            int pts = points[day][i] + solve(day + 1, points, i);
            ans = max(ans, pts);
        }
    }
    
    return dp[day][prev] = ans;
}

int maximumPoints(vector<vector<int>>& points, int n) {
    memset(dp, -1, sizeof(dp));
    return solve(0, points, 3);
}
```

## Approach 1.6 : DP (Using For Loop) + Index From Start

- Time Complexity : O(N \* 4 \* 3) 
- Space Complexity : O(N \* 3 + Rec)

```cpp
int dp[100001][3];
int solve(int day, vector<vector<int>>& points, int prev){
    if(day < 0)return 0;
    if(day == 0){
        int maxi = INT_MIN;
        for(int i = 0 ; i < 3 ; i++){
            if(i != prev)
                maxi = max(maxi, points[day][i]);
        }
        return maxi;
    }
    
    if(dp[day][prev] != -1)return dp[day][prev];
    
    int ans = INT_MIN;
    
    for(int i = 0 ; i < 3 ; i++){
        if(i != prev){
            int pts = points[day][i] + solve(day - 1, points, i);
            ans = max(ans, pts);
        }
    }
    
    return dp[day][prev] = ans;
}

int maximumPoints(vector<vector<int>>& points, int n) {
    memset(dp, -1, sizeof(dp));
    return solve(points.size() - 1, points, 3);
}
```