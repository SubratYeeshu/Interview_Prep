## Count Binary substring with same number of zero and one with ones and zero grouped together
## https://www.desiqna.in/12820/vmware-sde1-coding-questions-and-solutions-india-april-2023

```cpp
#include <bits/stdc++.h>
using namespace std;

int main()
{
    /*
    
        -> eg 00011000 : op = 4
        -> eg 0001110000 : op = 6
        -> eg 0011110010100111 : op 10
        Approach 1 : Count continuous elements for every index
                     Find the minimum between two adjancent indexes and sum += min(adjIndexs)
                     
        Approach 2 : Create two arrays zero and one and store the count of contiguous elements at each index in respective arrays
                     Now in second pass there are two cases 1st the number is 0 second the number is 1
                        Case 1 : The number is 0
                             idx = i - zero[i];
                             if(idx >= 0)if(one[idx] >= zero[i]) cnt++;
                        Case 2 : The number is 1
                              int idx = i-one[i];
                              if(idx >= 0)if(zero[idx] >= one[i]) cnt++;
    
    */
    
    // Approach 1 : 
    // string s; cin >> s;
    // vector<int> aux;
    // int cz = 0, co = 0,res = 0;
    // for(int i = 0 ; i < s.size() ; i++){
    //     if(s[i] == '0'){
    //         if(co != 0)aux.push_back(co);
    //         co = 0;
    //         cz++;
    //     }else{
    //         if(cz != 0)aux.push_back(cz);
    //         cz = 0;
    //         co++;
    //     }
    // }
    // if(cz != 0)aux.push_back(cz);
    // if(co != 0)aux.push_back(co);
    
    // for(int i = 1 ; i < aux.size() ; i++){
    //     int x = min(aux[i], aux[i - 1]);
    //     res += x;
    // }
    
    // for(auto i : aux)cout << i <<" ";
    // cout << endl;
    
    // cout << res;
    
    // Approach 2 : 
    string s; cin >> s;
    vector<int> zero (s.size(), 0), one (s.size(), 0);
    
    if(s[0] == '0')zero[0] = 1;
    else one[0] = 1;
    
    for(int i = 1 ; i < s.size() ; i++){
        if(s[i] == '0')zero[i] = 1 + zero[i - 1];
        else zero[i] = 0;
    }
    
    for(int i = 1 ; i < s.size() ; i++){
        if(s[i] == '1')one[i] = 1 + one[i - 1];
        else one[i] = 0;
    }
    
    // for(auto j : zero)cout << j << " ";
    // cout << endl;
    // for(auto i : one)cout << i << " ";
    
    int count = 0;
    
    for(int i = 0 ; i < s.size() ; i++){
        if(s[i] == '0'){
            int idx = i - zero[i];
            if(idx >= 0){
                if(one[idx] >= zero[i])count++;
            }
        }else{
            int idx = i - one[i];
            if(idx >= 0){
                if(zero[idx] >= one[i])count++;
            }
        }
    }
    
    cout << count;
    
    return 0;
}
```