// Link: https://leetcode.com/problems/string-to-integer-atoi/

// 1ms, 100%
class Solution {
  public int myAtoi(String s) {
    int len = s.length();
    int cur = 0;
    // Step1: ignore the leading whitespaces
    for (; cur < len && s.charAt(cur) == ' '; ++cur);
    // Step2: sign
    int sign = 1;
    if (cur < len && (s.charAt(cur) == '-' || s.charAt(cur) == '+')) {
      if (s.charAt(cur) == '-') {
        sign = -1;
      }
      cur++;
    }
    // Step3: read all the continuous digit characters and build the integer
    int integer = 0;
    while (cur < len && s.charAt(cur) - '0' >= 0 && s.charAt(cur) - '0' <= 9 ) {
      int digit = s.charAt(cur++) - '0';
      // Overflow
      if (integer > (Integer.MAX_VALUE - digit) / 10) {
        return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
      }
      integer = integer * 10 + digit;
    }
    return integer * sign;
  }
}

/*
 * Time complexity: O(n), n is the length of s
 * Space complexity: O(1)
 *
 * Notes:
 * - We should check cur is not out of bound on every step.
 * - For a positive integer x, if we want to check whether x * 10 + d is overflow, we just need to check whether
 * x is larger than (Integer.MAX_VALUE - d ) / 10
 */
