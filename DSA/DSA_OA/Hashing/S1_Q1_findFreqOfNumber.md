## Find Frequency of Number
## https://practice.geeksforgeeks.org/problems/find-the-frequency/1

```cpp
int findFrequency(vector<int> v, int x){
    int res = 0;
    
    for(int i = 0 ; i < v.size() ; i++)
        if(v[i] == x)
            res++;
    
    return res;
}```