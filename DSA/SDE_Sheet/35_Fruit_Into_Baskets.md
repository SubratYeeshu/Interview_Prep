# Fruit Into Baskets

## Problem statement

You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.

You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:
- You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
- Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
- Once you reach a tree with fruit that cannot fit in your baskets, you must stop.

Given the integer array fruits, return the maximum number of fruits you can pick.

## Approach 1 : Sliding window  (Variable size sliding window template)

Time complexity : O(N)  
Auxiliary space : O(N)

```cpp
/*

    -> Similar to Longest K unique characters substring 

*/
int totalFruit(vector<int> &fruits){
    int i = 0, j = 0, n = fruits.size(), mx = INT_MIN;
    unordered_map<int, int> m;
    while (j < n){
        m[fruits[j]]++;
        
        if (m.size() <= 2){
            mx = max(mx, j - i + 1);
            j++;
        }
        else if (m.size() > 2){
            while (m.size() > 2){
                m[fruits[i]]--;
                if (m[fruits[i]] == 0)m.erase(fruits[i]);
                i++;
            }
            j++;
        }
    }
    if (mx == INT_MIN)return 0;
    return mx;
}
```
