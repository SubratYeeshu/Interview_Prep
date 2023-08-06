# Modular Exponentiation

- For calculating powers of large number under mod

Time copmlexity : O(LogN)
Space complexity : O(1)
N is the number of bits

```cpp
long long modularExponentiation(long long base, long long exponent, long long modulus) {
    long long result = 1;
    base %= modulus; // Reduce base to [0, modulus-1] range

    while (exponent > 0) {
        if (exponent & 1) {
            result = (result * base) % modulus;
        }
        base = (base * base) % modulus;
        exponent >>= 1; // Right shift exponent (equivalent to dividing exponent by 2)
    }
    return result;
}
```