# Egg Drop

## Problem statement

You are given N identical eggs and you have access to a K-floored building from 1 to K.

There exists a floor f where 0 <= f <= K such that any egg dropped from a floor higher than f will break, and any egg dropped from or below floor f will not break.
There are few rules given below. 

- An egg that survives a fall can be used again.
- A broken egg must be discarded.
- The effect of a fall is the same for all eggs.
- If the egg doesn't break at a certain floor, it will not break at any floor below.
- If the eggs breaks at a certain floor, it will break at any floor above.

Return the minimum number of moves that you need to determine with certainty what the value of f is.

## Approach 1.1 : DP

Time Complexity : O(N^3) ~ Inner Loop Contibutes a N
Space Complexity : O(N^2 + Rec)

```cpp
int dropEgg(int e, int f, vector<vector<int>> &dp){ // e stands for egg while f for floor
    if(f == 1 || f == 0)return f;
    if(e == 1) return f;
    int ans = INT_MAX;
    
    if(dp[e][f] != -1) return dp[e][f];
    
    for(int flr = 1 ; flr <= f ; flr++){
        int temp = 1 + max(dropEgg(e - 1, flr - 1, dp), dropEgg(e, f - flr, dp));
        ans = min(ans, temp);
    }
    
    return dp[e][f] = ans;
}

int eggDrop(int e, int f){
    vector<vector<int>> dp (e + 1, vector<int> (f + 1, -1));
    return dropEgg(e, f, dp);
}
```

## Approach 1.2 : DP (Optimized Calls)

Time Complexity : O(N^3) ~ Inner Loop Contibutes a N
Space Complexity : O(N^2 + Rec)

```cpp
int helper(int k, int n, vector<vector<int>>& memo){
    if(n == 0 || n == 1) return n;
    if(k == 1) return n;
    
    if(memo[k][n] != -1) return memo[k][n];
    int mn = INT_MAX;
    
    for(int i = 1 ; i <= n ; i++){
        /* 
            Here is the optimization goes on, the basic idea is before 
            any function call we are checking if any value from that function 
            call is present or not
        */
        int left = 0, right = 0;
        if(memo[k - 1][i - 1] != -1)left = memo[k - 1][i - 1];
        else{
            left = helper(k - 1, i - 1, memo);
            memo[k - 1][i - 1] = left;
        }
        
        if(memo[k][n - i] != -1)right = memo[k][n - i];
        else{
            right = helper(k, n - i, memo);
            memo[k][n - i] = right;
        }
        
        int temp = 1 + max(left, right);
        mn = min(mn, temp); 
    }
    return memo[k][n] = mn;
}

int superEggDrop(int k, int n) {
    vector<vector<int>> memo(k+1, vector<int>(n + 1, -1));
    return helper(k, n, memo);
}
```

## Approach 2 : Tabulation

Time Complexity : O(N^3) ~ Inner Loop Contibutes a N
Space Complexity : O(N^2)

```cpp
int eggDrop(int egg, int floorVal) {
    vector<vector<int>> dp(egg+1,vector<int>(floorVal+1,0));
    
    for(int e=0;e<=egg;e++){
        dp[e][1] = 1;
    }
    for(int f=0;f<=floorVal;f++){
        dp[1][f] = f;
    }

    for(int f=1;f<=floorVal;f++){
        for(int e=2;e<=egg;e++){
            int ans = 1e9,attempts;
    
            for(int flr = 1;flr<=f;flr++){
                attempts = 1 + max(dp[e-1][flr-1],dp[e][f-flr]);
                ans = min(ans,attempts);
            }
            
            dp[e][f] = ans;
        }
    }
    return dp[egg][floorVal];
}
```

## Approach 3 : Binary Search 

Time Complexity : O(N \* K \* LogN) ~ Inner Loop Contibutes a N
Space Complexity : O(N \* K)

```cpp
int helper(int k, int n, vector<vector<int>>& memo){
    if(n == 0 || n == 1) return n;
    if(k == 1) return n;
    
    if(memo[k][n] != -1) return memo[k][n];
    
    int mn = INT_MAX, low = 0, high = n, temp = 0;
    
    while(low<=high){
        
        int mid = (low + high) / 2;  // Number of floors
        
        /* 
            Representing both the choices with memo
            First one, if the egg will break, no. of eggs will decreased and we have to
            down from that floor.
            Second one, if the egg will not break, no. of eggs will not decreased and we
            have to go above form that floor.
        */
        
        int left = helper(k - 1, mid - 1, memo);
        int right = helper(k, n - mid, memo);
        
        temp = 1 + max(left, right);
        
        // Since we need more temp value in worst case, so need to go above
        if(left < right)low = mid + 1;
        else high = mid-1;    
        
        mn = min(mn, temp); // Minimum number of attempts
    }
    return memo[k][n] = mn;
}

int superEggDrop(int k, int n) {
    vector<vector<int>> memo(k + 1, vector<int>(n + 1, -1));
    return helper(k, n, memo);
}
```