## Fun with anagrams
## https://www.desiqna.in/12814/morgan-sde1-coding-questions-and-solutions-india-april-2023

```cpp
bool areAnagrams(string a, string b){
    vector<int> f1 (26, 0), f2 (26, 0);
    for(auto i : a)f1[i - 'a']++;
    for(auto j : b)f2[j - 'a']++;
    return f1 == f2;
}

int main()
{
    /*
    
        -> ip : code, aagmnrs, anagrams, doce
        -> op : code, aagmnrs
        Approach : Use a stack to get the answer
    
    */
    vector<string> v = {"code", "aaagmnrs", "anagrams", "doce"};
    stack <string> st;
    for(int i = 0 ; i < v.size() - 1 ; i++){
        if(st.empty())st.push(v[i]);
        else{
            string a = v[i];
            string b = v[i + 1];
            if(areAnagrams(a, b)){
                st.push(v[i]);
                i++;
            }
            else{
                st.push(v[i]);
            }
        }
        
    }
    
    vector<string>res;
    while(!st.empty()){
        string temp = st.top();
        res.push_back(temp);
        st.pop();
    }
    
    for(auto i : res)cout << i << " ";
    return 0;
}
```