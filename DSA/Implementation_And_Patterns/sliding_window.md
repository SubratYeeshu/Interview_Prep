## Identification of sliding window

- Sustring / subarray
- Gven a size
- Maximum / minimum

## Fixed size sliding window - checking window size

```cpp
// -> Sliding window generic template (fixed size)
while(j < n){
	*** calculation ***

	if(j - i + 1 < k)j++;
    if(j - i + 1 == k){
		*** ans calculation logic ***
		
	    *** remove i and aquire j and  logic for it ***
		
	    slide the window (i++, j++) 
	}
}
```

## Common Problems on fixed sized sliding window
- Max / Min sub array of size k
- First negative integer in every window of size k
- Count occurence of anagrams
- Maximum of all subarrays of size k

## Variable size sliding window - checking different condtion

```cpp
// -> Sliding window generic template (variable size)
// -> mp.size() can be replace to count and condition can differ with differnt problems
while(j < n){
	*** calculation ***

    if(mp.size() < k)j++;
    else if(mp.size() == k){
        mx = max(mx, j - i + 1);
        j++;
    }else if(mp.size() > k){
        while(mp.size() > k){
            mp[s[i]]--;
            if(mp[s[i]] == 0)mp.erase(mp[s[i]]);
            i++;
        }
        j++;
    }
}
```

## Common Problems on variable sized sliding window
- Largest Subarray of sum K
- Longest K unique characters substring
- Longest Substring Without Repeating Character
- Fruits into baskets
- Minimum window substring
