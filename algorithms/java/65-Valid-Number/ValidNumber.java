// Link: https://leetcode.com/problems/valid-number/

 /*
  * Intuitive method
  * 2ms, 69.16%
  *
  * The pattern of a valid number is:
  * [+/-] xxx.    [ e/E [+/-] xxx]
  *       xxx.xxx
  *       .xxx
  *       xxx
  *
 * step-1: check +/-
 * step-2: check decimal/integer
 * step-3: check e/E and its followings
 */
class Solution {
  public boolean isNumber(String s) {
    int n = s.length();
    int i = 0;
    boolean dotSeen = false;
    boolean digitSeen = false;
    boolean eSeen = false;
    // Step-1: skip +/-
    if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
      i++;
    }
    // Step-2: Check decimal or integer. The number of dot cannot be more than 1, and the number
    // of digits should be at least 1.
    for (; i < n && (isDigit(s.charAt(i)) || s.charAt(i) == '.'); ++i) {
      if (isDigit(s.charAt(i))) {
        digitSeen = true;
      }
      if (s.charAt(i) == '.') {
        if (dotSeen) {
          return false;
        } else {
          dotSeen = true;
        }
      }
    }
    if (!digitSeen) {
      return false;
    }
    // Step-3: Check e or E
    if (i < n && (s.charAt(i) == 'e' || s.charAt(i) == 'E')) {
      eSeen = true;
      i++;
    }
    if (eSeen) {
      digitSeen = false;
      // Skip + or -
      if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
        i++;
      }
      // Check integer after e/E
      for(; i < n && isDigit(s.charAt(i)); ++i) {
        digitSeen = true;
      }
      if (!digitSeen) {
        return false;
      }
    }
    return i == s.length();
  }

  private boolean isDigit(char c) {
    return c - '0' >= 0 && c - '0' <= 9;
  }
}

/*
 * Time complexity: O(n)
 *
 * Space complexity: O(1)
 *
 * NOTES:
 *
 */
