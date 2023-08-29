# Rabin Karp (String Matching Algorithm)

## Problem statement (Search Pattern GFG)

- Given two strings, one is a text string and other is a pattern string. The task is to print the indexes of all the occurences of pattern string in the text string. For printing, Starting Index of a string should be taken as 1.

```cpp
vector <int> search(string pat, string txt){
    auto val = [&](char ch){return ch - 'a' + 1;};

    const int p = 31, mod = 1e9 + 7;
    int m = pat.size(), n = txt.size();

    // Precomputing power
    vector<long> p_pow(max(m, n), 1);
    for (int i = 1; i < p_pow.size(); i++)p_pow[i] = (p_pow[i - 1] * p) % mod;

    // Store hash prefix array for the string
    vector<long> hash(n + 1, 0);
    for (int i = 0; i < n ; i++)hash[i + 1] = (hash[i] + val(txt[i]) * p_pow[i]) % mod;
        
        
    // Storing the pattern hash
    long pHash = 0;
    for (int i = 0; i < m; i++)pHash = (pHash + val(pat[i]) * p_pow[i]) % mod;

    // Sliding window
    vector<int> ans;
    for (int i = 0; i < n - m + 1; i++){
        long tHash = (hash[i + m] - hash[i] + mod) % mod;
        
        // Comparision the p_pow to the right instead of dividing to save LogN time for moudlar inverse
        if (tHash == pHash * p_pow[i] % mod)ans.push_back(i + 1);
    }

    if (ans.empty())ans.push_back(-1);
    return ans;
}
```

## String Hashing and Rabin Karp Algorithm

```cpp
/*

	-> Hash is used for faster comparision of comparision
	-> We have to generate unique hash
	-> 10011 : Binary 
	-> Hash of the binary : (1 * 2^4) + (0 * 2^3) + (0 * 2^2) + (1 * 2^1) + (1 * 2^0)
	-> 893 : Decimal
	-> Hash of the decimal : (8 * 10^2) + (9 * 10^1) + (3 * 10^0)
	-> We can change the base for example : We can change the base value for different hashes
	-> "subrat" : String
	-> Hash of the string : (19 * 26^5) + (21 * 26^4) + (2 * 26^3) + (18 * 26^2) + (1 * 26^1) + (20 * 26^0)
	-> We can change the base value as well as the power sequence : it can also be
	-> (19 * 37^0) + (21 * 37^1) + (2 * 37^2) + (18 * 37^3) + (1 * 37^4) + (20 * 37^5)
 	-> We have to use a mod to to stop the integers from overflowing
	-> We take the primeBase > character set to decrease collision
	-> We take ch - 'a' + 1 (+1) to decrease collision 
	-> abc = (1*31^0) + (2*31^1) + (3*31^3)

*/

int calculateHash(string &s){	
	int base = 31;  // Number of possible characters (Assume character set a - z)
	int pow = 31;  // pow = 1
	int mod = 1e9 + 7;
	int hash = (s[0] - 'a' + 1)*1;  // First, 0

	for(int i = 1 ; i < s.size() ; i++){  // i = 0
	    hash = hash + ((s[i] - 'a' + 1) * pow) % mod;
	    pow = pow * base;
	}

	return hash;
}

int calculateHashArray(string &s, vector<int> &hashArray){	
	int base = 31;  // Number of possible characters (Assume character set a - z)
	int pow = 1;
	int mod = 1e9 + 7;
	int hash = 0;  // First

	for(int i = 0 ; i < s.size() ; i++){
		if(i == 0){
			hashArray[i] = s[i] - 'a' + 1;
			pow = pow * base;
			continue;
		}
		hashArray[i] = hashArray[i - 1] + ((s[i] - 'a' + 1) * pow) % mod;
	    pow = pow * base;
	}

	return hash;
}

int calculateHashArray2(string &s, vector<int> &hashArray){	
	int base = 31;  // Number of possible characters (Assume character set a - z)
	int pow = 31;
	int mod = 1e9 + 7;
	int hash = s[0] - 'a' + 1;  // First
	hashArray[0] = hash;

	for(int i = 1 ; i < s.size() ; i++){
		hashArray[i] = hashArray[i - 1] + ((s[i] - 'a' + 1) * pow) % mod;
	    pow = pow * base;
	}

	return hash;
}

int main(){
	string str1 = "abc";
	vector<int> hashArray1(str1.size(), 0);
	cout << calculateHash(str1) << endl;
	calculateHashArray(str1, hashArray1);
	for(auto i : hashArray1)cout << i << " ";
	cout << endl;

	string str2 = "bac";
	vector<int> hashArray2(str2.size(), 0);
	cout << calculateHash(str2) << endl;
	calculateHashArray(str2, hashArray2);


	
	/*
		-> Prefix hash array does not works like prefix sum because powers are different
		-> To fix we divide the sum obtained by pow^left
		-> hash[l] to hash[r] = diff / pow^left , diff hash[r] - hash[l - 1]
		-> But while dividing we also have to take modular inverse (logn)
		-> So instead of dividing we multiply to RHS and compare
		-> Rabin Karp
			-> Calculate prefix hash array
			-> For every window we will get l and r
			-> Find sum of range
			-> For adjusting the factor we divide by pow^l but we do not do it because of modular inverse
			-> calc/p^l == desired
			-> calc == desired * p^l for comparision
	*/

}
```