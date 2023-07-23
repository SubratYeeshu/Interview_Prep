## Microsoft OA - Max Sum of Pair with equal digit sum
## https://www.desiqna.in/13267/microsoft-coding-oa-sde-1-may-3-2023

```cpp
#include <bits/stdc++.h>
using namespace std;
typedef long long int ll;

ll getDigitSum(ll num){
    ll sum = 0;
    while(num > 0){
        sum += num % 10;
        num /= 10;
    }
    return sum;
}

int main()
{
    // Approach 1 : Most Optimal
    int n; cin >> n;
    ll nums[n] = {0};
    ll i = 0;
    while(i < n)cin >> nums[i++];
    unordered_map<ll, ll> digitSumMap;
    
    ll answer = -1;
    
    for(ll i = 0 ; i < n ; i++){
        ll currNumDigSum = getDigitSum(nums[i]);
        if(digitSumMap.find(currNumDigSum) != digitSumMap.end()){
            // There is some number whose digit sum is same
            ll currSum = digitSumMap[currNumDigSum] + nums[i];
            answer = max(answer, currSum);
            digitSumMap[currNumDigSum] = max(nums[i], digitSumMap[currNumDigSum]);
        }else{
            digitSumMap[currNumDigSum] = nums[i];
        }
    }
    
    // Approach 2 : Better
    int n; cin >> n;
    ll nums[n] = {0};
    ll i = 0;
    int answer = -1;
    while(i < n)cin >> nums[i++];
    
    unordered_map<ll, vector<ll>> mp;
    for(int i = 0 ; i < n ; i++)mp[getDigitSum(nums[i])].push_back(nums[i]);
    
    for(auto i : mp){
        int x = *max_element(mp[i.first].begin(), mp[i.first].end());
        int y = -1;
        for(auto j : mp[i.first]){
            if(j != x){
                if(y < j)y = j;
            }
        }
        if(y != -1)answer = max(answer, x + y);
    }
    
    
    // Approach 3 : Brute check for every pairs
    
    
    cout << answer;
    
    return 0;
}
```