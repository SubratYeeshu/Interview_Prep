## Altassian OA - Greedy developer skills team size minimization
## https://www.desiqna.in/7299/atlassian-sde1-coding-questions-solutions-2022-oncampus-2022

```cpp
#include <bits/stdc++.h>
using namespace std;
typedef long long int ll;
int main()
{
    /*
    -> Approach Prefix Sum
        -> This questions can be solved using greedy approach
        -> a1 < a2 < a3 < a4 < a5
        -> Sort the array
        -> Concept 1 : For minimizing a array always choose the middle element to minimize the whole array because
                       it will be at a minimum distance to all elements nearby 
                            -> Case 1.1 : In case of odd elements we have to check for only one element
                            -> Case 1.2 : In case of even elements we have to check for two middle elements
        -> Concept 2 : Check for subarray consecutive ones because it will be beneficial to subract elements k (middle element) 
                       from subarray rather than distant elements as it will increase the sum
        -> Sum part can also be solved using sliding window
        
    > Approach Slower than prefix Sum
        -> Calculate the sum differnece in between
    
    */
    
    
    vector<ll> v;
    ll n, k; cin >> n >> k;
    ll minSum = INT_MAX;
    for(ll i = 0 ; i < n ; i++){
        ll t; cin >> t;
        v.push_back(t);
    }
    
    // Sorting And Creating a copy 
    sort(v.begin(), v.end());
    vector<ll> prefix = v;
    
    // Calculating the prefix sum
    for(ll i = 1 ; i < n ; i++)prefix[i] = prefix[i - 1] + prefix[i];
    
    // Sliding Window
    ll i = 0;
    ll res = INT_MAX;
    
    while(i <= n - k){

        ll start = i;
        ll end = k + i - 1 ; 

        if(k %2 != 0){
            // Check for one middle element
            
            ll mid = (start + end) / 2;
            // (start --> md - 1), as we have to find the absolute so we are breaking it in two part
            ll eleLeft = abs(mid - start);
            ll sum1 = 0;
            if(start == 0)sum1 = prefix[mid - 1];
            else sum1 = prefix[mid - 1] - prefix[start - 1];
            ll ans1 = abs(eleLeft * v[mid] - sum1);
           
            // (md + 1 --> end)
            ll eleRight = abs(end - mid);
            ll sum2 = 0;
            if(start == 0)sum2 = prefix[mid - 1];
            else sum2 = prefix[mid - 1] - prefix[start - 1];
            ll ans2 = abs(eleRight * v[mid] - sum2);
            
            // Combining Both Side
            ll final_ans = ans1 + ans2 ;
            res = min(res, final_ans);
           
        } else {
            // Check for two middle elements 
            
            ll mid1 = (start + end) / 2;
            ll mid2 = mid1 + 1;
           
            // For mid 1
            // (start --> md - 1), as we have to find the absolute so we are breaking it in two part
            ll eleLeft = abs(mid1 - start);
            ll sum1 = 0;
            if(start == 0)sum1 = prefix[mid1 - 1];
            else sum1 = prefix[mid1 - 1] - prefix[start - 1];
            ll ans1 = abs(eleLeft * v[mid1] - sum1);
           
            // (md + 1 --> end)
            ll eleRight = abs(end - mid1);
            ll sum2 = 0;
            if(start == 0)sum2 = prefix[mid1 - 1];
            else sum2 = prefix[mid1 - 1] - prefix[start - 1];
            ll ans2 = abs(eleRight * v[mid1] - sum2);
            
            // Combining Both Side
            ll final_ans = ans1 + ans2 ;
            res = min(res, final_ans);
            
            
            // For mid 2
            // (start --> md - 1), as we have to find the absolute so we are breaking it in two part
            eleLeft = abs(mid2 - start);
            if(start == 0)sum1 = prefix[mid2 - 1];
            else sum1 = prefix[mid2 - 1] - prefix[start - 1];
            ans1 = abs(eleLeft * v[mid2] - sum1);
           
            // (md + 1 --> end)
            eleRight = abs(end - mid2);
            if(start == 0)sum2 = prefix[mid2 - 1];
            else sum2 = prefix[mid2 - 1] - prefix[start - 1];
            ans2 = abs(eleRight * v[mid2] - sum2);
            
            // Combining Both Side
            final_ans = ans1 + ans2 ;
            res = min(res, final_ans);
           

        }
        i++;
    }

    cout<<res;
  
    return 0 ;
}
```