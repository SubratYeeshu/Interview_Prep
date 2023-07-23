## Amazon OA - Data moved from locations
## https://drive.google.com/drive/folders/1YlH_-5uQCFWTE9WJGoMudyRgHZAkIAeU

```cpp
#include <bits/stdc++.h>
using namespace std;

int main()
{
    vector<int> locations = {1, 7, 6, 8}, movedFrom = {1, 7, 2}, movedTo = {2, 9, 5};
    set<int> st;
    for(auto i : locations)st.insert(i);
    
    for(int i = 0 ; i < movedFrom.size() ; i++){
        int x = movedFrom[i], y = movedTo[i];
        if(st.find(x) != st.end()){
            // Present
            st.erase(x);
            st.insert(y);
        }
    }
    vector<int>res;
    auto it = st.begin();
    while(it != st.end())res.push_back(*it++);
    
    for(auto i : res)cout << i << " ";

    return 0;
}


```