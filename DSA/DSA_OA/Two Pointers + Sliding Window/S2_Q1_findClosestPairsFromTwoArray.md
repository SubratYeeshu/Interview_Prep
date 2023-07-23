## Find closest pair from two array
## https://practice.geeksforgeeks.org/problems/find-the-closest-pair-from-two-arrays4215/1

```cpp
class Solution{
  public:
    vector<int> printClosest(int arr[], int brr[], int n, int m, int x) {
        int i = 0, j = m - 1, diff = INT_MAX;
        vector<int> ans;
        while(i < n && j >= 0){
            int sum = arr[i] + brr[j];
            
            if(diff > abs(sum - x)){
                diff = abs(sum - x);
                ans = {arr[i], brr[j]};
            }
            
            // Pointer conditions
            if(sum >= x)j--;
            else i++;
        }
        
        return ans;
    }
};
```