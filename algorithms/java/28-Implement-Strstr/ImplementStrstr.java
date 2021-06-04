// Link: https://leetcode.com/problems/implement-strstr/

// Method1: Native
// 4ms, 20.74%
class Solution {
  public int strStr(String haystack, String needle) {
    // Assume haystack and needle are not null
    if (needle.length() == 0) {
      return 0;
    }
    for (int i = 0; i <= haystack.length() - needle.length(); ++i) {
      int j = 0;
      for (; j < needle.length(); ++j) {
        if (haystack.charAt(i + j) != needle.charAt(j)) {
          break;
        }
      }
      if (j == needle.length()) {
        return i;
      }
    }
    return -1;
  }
}

/*
 * Time complexity: O(n*m), n is the length of haystack, and m is the length of needle
 *
 * Space complexity: O(1)
 *
 * Notes:
 * This is a native approach. For each position i in haystack, we check whether we can find needle.
 */


// Method2: Rabin-Karp
// 3ms, 29.98%
class Solution {
  public int strStr(String haystack, String needle) {
    // Assume haystack and needle are not null
    if (needle.length() == 0) {
      return 0;
    }
    // Corner case
    if (haystack.length() < needle.length()) {
      return -1;
    }
    int prime = 31;
    int largePrime = 101;
    // seed is used to cache prime^(needle.length - 1)
    int seed = 1;
    for (int i = 1; i < needle.length(); ++i) {
      seed = seed * prime % largePrime;
    }
    // hash = (s0*prime^k + s1*prime^(k-1) + ... + sk*prime^0) % largePrime
    int targetHash = 0;
    int hash = 0;
    for (int i = 0; i < needle.length(); ++i) {
      targetHash = (targetHash * prime % largePrime + needle.charAt(i)) % largePrime;
      hash = (hash * prime % largePrime + haystack.charAt(i)) % largePrime;
    }
    if (hash == targetHash && equals(haystack, 0, needle)) {
      return 0;
    }
    for (int i = 1; i <= haystack.length() - needle.length(); ++i) {
      hash = hash - haystack.charAt(i - 1) * seed % largePrime;
      // convert the negative to positive
      if (hash < 0) {
        hash += largePrime;
      }
      hash = (hash * prime % largePrime + haystack.charAt(i + needle.length() - 1)) % largePrime;

      // check whether hash is equal to targetHash, if equal, we should double check whether
      // the strings are identical letter by letter, because we mod a largePrime on the hash
      // caculation and this will introduce collisions.
      if (hash == targetHash && equals(haystack, i, needle)) {
        return i;
      }
    }
    return -1;
  }

  // check whether in s1, starting from index start, we can find a substring equals to s2
  private boolean equals(String s1, int start, String s2) {
    for (int i = 0; i < s2.length(); ++i) {
      if (s1.charAt(start + i) != s2.charAt(i)) {
        return false;
      }
    }
    return true;
  }
}

/*
 * Time complexity: O(n+m), n is the length of haystack, and m is the length of needle
 *
 * Space complexity: O(1)
 *
 * Notes:
 * 1. Use two prime numbers, one for the hash calculation, and a large prime to avoid overflow
 * 2. The way we use a large prime to avoid overflow can cause hash collision, so when we find
 * the hash value is equal to the target hash value, we should do a double-check, i.e.,
 * comparing letter by letter.
 * 3. Let m be the length of the needle. Then hash = s_0*prime^(n-1) + s_1*prime^(n-2) + ... + s_(n-1)*prime^0
 * 4. For haystack "abcde" and needle "cde", hash(abc) = a*prime^2 + b*prime^1 + c*prime^0. Then
 * hash(bcd) = b*prime^2 + c*prime^1 * d*prime^0 = (hash(abc) - a*prime^2) * prime + c.
 * 5. For each i, we should subtract s_(i-1)*prime^(n-1), so we use a variable called seed to store the result
 * of prime^(n-1)
 * 6. Handle negative hash: (x + y + z) % p = A, x % p = B, if we use A - B to to get result of (x + y + z - x) %p,
 * i.e., (y + z) % p, we may get a negative value. If it is negative, we can use A - B + p to make it correct.
 *
 */


// Method3: KMP
// TODO
class Solution {
  public int strStr(String haystack, String needle) {
  }
}
