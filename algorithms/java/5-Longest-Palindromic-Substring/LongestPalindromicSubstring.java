// Link: https://leetcode.com/problems/longest-palindromic-substring/

class Solution {
  public String longestPalindrome(String s) {
    // Record the length and the center index of the longest palindrome so far
    int maxLen = 0;
    int centerIdx = 0;
    // For each char i, we check the palindrome centered on i, and the one centered on i and i+1
    for (int i = 0; i < s.length(); ++i) {
      int len1 = getPalindromeLen(s, i, i);
      int len2 = getPalindromeLen(s, i, i + 1);
      int len = Math.max(len1, len2);
      if (len > maxLen) {
        maxLen = len;
        centerIdx = i;
      }
    }
    return s.substring(centerIdx - (maxLen - 1) / 2, centerIdx + maxLen / 2 + 1);
  }

  // Get the length of the palindrome centered by i and j
  private int getPalindromeLen(String s, int i, int j) {
    while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
      i--;
      j++;
    }
    return j - i - 1;
  }
}

/*
 * Time complexity: O(n^2)
 * Space complexity: O(1)
 *
 * Notes:
 *  - We only need one traverse, and for each i, we check the palindrome centered on i (odd length), and
 * the palindrome centered on i and i+1 (even length). And update the length of the longest palindrom so
 * far and its center index, i.e., index i.
 *  - At the end when we extract the substing, no matter whether the longest palindrome length is odd or even,
 * we can calculate its starting and ending indices via one and the same formula.
 */
