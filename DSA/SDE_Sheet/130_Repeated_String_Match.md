# Repeated String Match

## Problem statement

- Given two strings a and b, return the minimum number of times you should repeat string a so that string b is a substring of it. If it is impossible for b​​​​​​ to be a substring of a after repeating it, return -1. 
- Notice: string "abc" repeated 0 times is "", repeated 1 time is "abc" and repeated 2 times is "abcabc".

## Approach 1 : Brute

```cpp
bool check(string &a, string &b){
    int m = a.size(), n = b.size();
    
    for(int i = 0 ; i <= m - n ; i++){
        int j;
        for(j = 0 ; j < n ; j++){
            if(a[i + j] != b[j])break;
        }
        if(j == n)return true;
    }
    return false;
}

int repeatedStringMatch(string a, string b) {
    int m = a.size(), n = b.size();
    
    int ans = 1;
    string tmp = a;
    while(a.size() < b.size()){
        a += tmp;
        ans++;
    }
    
    if(check(a,b))return ans;
    
    a += tmp;
    
    if(check(a, b))return ans + 1;
    
    return -1;
}
```

## Approach 2 : Rabin Karp

Time complexity : O(M + N)
Space complexity : O(2\*M) ~ O(M) (prefix hash array and power array of size M)

```cpp
int Rabin_Karp(string s1, string s2){
    auto val = [&](char &ch){
        return ch - 'a' + 1;
    };
    
    long long m = s1.size(), n = s2.size();
    
    // Hashing environment
    long long p = 31, pow = 1, mod = 1e9+7, targetHashVal = 0;
    
    // Hashing the pattern
    for(long long i = 0 ; i < n ; i++){
        targetHashVal = (targetHashVal + val(s2[i]) * pow) % mod;
        pow = (pow * p) % mod;
    }
    
    // Prefix hash array and power precomputation
    vector<long long> pha(m), pa(m);
    
    pha[0] = val(s1[0]);
    pa[0] = 1;
    pow = p;  // Refresh
    
    // Hashing the string 
    for(long long i=1;i<m;i++){
        pha[i] = (pha[i - 1] + val(s1[i]) * pow) % mod;
        pa[i] = pow;
        pow = (pow * p) % mod;
    }
    
    // Sliing window and comparision
    long long sp = 0, ep = n-1;
    while(ep < m){
        long long win = pha[ep];
        if(sp > 0)win = (win - pha[sp - 1] + mod) % mod; // Mod rule : If negative add the mod
        
        if(win == (targetHashVal * pa[sp]) % mod)return 1;
        sp++,ep++;
    }
    return 0;
}

int repeatedStringMatch(string A, string B){

    if(A == B) return 1;
    int count = 1;
    string source = A;
    while(source.size() < B.size()){
        source += A;
        count++;
    }
    
    if(source == B)return count;
    
    if(Rabin_Karp(source, B) == 1)return count;
    if(Rabin_Karp(source + A, B) == 1)return count + 1;
    return -1;
}
```

## Approach 3 : KMP