// Link: https://leetcode.com/problems/plus-one/

/*
 * 0ms, 100%
 */
class Solution {
  public int[] plusOne(int[] digits) {
    int i = digits.length - 1;
    // Start from the end, set all 9s to 0
    for (; i >= 0 && digits[i] == 9; --i) {
      digits[i] = 0;
    }
    // If all digits are 9, we need to create a new array with the first digit 0 and return it
    if (i == -1) {
      int[] newDigits = new int[digits.length + 1];
      newDigits[0] = 1;
      return newDigits;
    }
    // Increase the digit left to the last 9 by 1
    digits[i] += 1;
    return digits;
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
