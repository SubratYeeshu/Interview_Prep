## Axis Bank - OA Build Dictonary
## https://www.desiqna.in/13212/axis-bank-coding-oa-sde-april-2023

```cpp
int main()
{
    /*
    
        -> Word that appears more than once return in sorted order
    
    */
    
    string str; getline(cin, str);
    map<string, int> mp;
    vector<string> res;
    bool builtDict = false;
    string temp = "";
    for(int i = 0 ; i < str.size() ; i++){
        if(str[i] != ' '){
            temp += str[i];
        }else{
            mp[temp]++;
            temp = "";
        }
    }
    
    for(auto [word, freq] : mp){
        if(freq >= 1){
            builtDict = true;
            res.push_back(word);
        }
    }
    
    if(!builtDict)cout << "NA";
    else{
        for(auto i : res)cout << i << " ";
    }
    
}
```