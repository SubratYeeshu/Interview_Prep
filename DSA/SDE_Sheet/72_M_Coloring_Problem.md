# M-Coloring Problem

## Problem statement 

- Given an undirected graph and an integer M. The task is to determine if the graph can be colored with at most M colors such that no two adjacent vertices of the graph are colored with the same color. Here coloring of a graph means the assignment of colors to all vertices. Print 1 if it is possible to colour vertices and 0 otherwise.

## Approach 1 : Backtracking

Time complexity : O(N<sup>M</sup>) 
Space complexity : O(N)

```cpp
bool isSafe(int node, bool graph[101][101], vector<int>&color, int n, int coloredWith){
    for(int k = 0 ; k < n ; k++){
        if(k != node && graph[node][k] == 1 && color[k] == coloredWith)return false;
    }
    return true;
}

bool solve(int node, bool graph[101][101], int m, int n, vector<int> &color){
    if(node == n)return true;
    
    for(int i = 1 ; i <= m ; i++){
        if(isSafe(node, graph, color, n, i)){
            color[node] = i;
            if(solve(node + 1, graph, m, n, color))return true;
            color[node] = 0;
        }
    }
    
    return false;
}

bool graphColoring(bool graph[101][101], int m, int n) {
    vector<int> color (n, 0);
    return solve(0, graph, m, n, color);
}
```