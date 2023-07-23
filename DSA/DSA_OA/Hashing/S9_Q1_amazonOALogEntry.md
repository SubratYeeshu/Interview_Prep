## Amazon OA - Log Entry 
## https://leetcode.com/discuss/interview-question/3114099/AMAZON-OA-(INTERN-2024)

```cpp
    #include <iostream>
    #include <map>
    using namespace std;
     
    int main() {
    	string s, t; 
    	cin >> s >> t;
     
    	unordered_map<char, int> freq_s, freq_t;
     
    	for (char letter : s)freq_s[letter]++;
    	for (char letter : t)freq_t[letter]++;
     
    	int ans = INT_MAX;
     
    	for (char letter : t) 
    		ans = min(freq_s[letter] / freq_t[letter], ans);
     
    	cout << ans;
    	return 0;
    }
```