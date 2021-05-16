// Link: https://leetcode.com/problems/integer-to-roman/

// Method1: hardcode every numerical digits
// 3ms, 100%
class Solution {
  private static final String[] digits = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
  private static final String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
  private static final String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
  private static final String[] thousands = {"", "M", "MM", "MMM"};

  public String intToRoman(int num) {
    StringBuilder sb = new StringBuilder();
    sb.append(thousands[num / 1000])
      .append(hundreds[num % 1000 / 100])
      .append(tens[num % 100 / 10])
      .append(digits[num % 10]);
    return sb.toString();
  }
}

/*
 * Time complexity: O(1)
 * Space complexity: O(1)
 *
 * Notes:
 * This hardcode method is not that flexible if we extend the range of num. For example, If we use two more
 * letters to represent 5000 and 10000, then in order to convert 39999, we have to hardcode one more array.
 */

// Method2
// 4ms, 74.95%
class Solution {
  private static final int[] values = {1000, 100, 10, 1};
  private static final String[] digits = {"I", "V", "X"};
  private static final String[] tens = {"X", "L", "C"};
  private static final String[] hundreds = {"C", "D", "M"};
  private static final String[] thousands = {"M"};
  private static final String[][] symbols = {thousands, hundreds, tens, digits};

  public String intToRoman(int num) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; num > 0; ++i) {
      int val = num / values[i];
      if (val == 0) {
        continue;
      }
      sb.append(convert(i, val));
      num %= values[i];
    }
    return sb.toString();
  }

  private String convert(int type, int val) {
    StringBuilder sb = new StringBuilder();
    if (val >= 1 && val <= 3) {
      for (int i = 0; i < val; ++i) {
        sb.append(symbols[type][0]);
      }
    }
    if (val == 4) {
      sb.append(symbols[type][0]).append(symbols[type][1]);
    }
    if (val == 5) {
      sb.append(symbols[type][1]);
    }
    if (val >= 6 && val <=8) {
      sb.append(symbols[type][1]);
      for (int i = 0; i < val - 5; ++i) {
        sb.append(symbols[type][0]);
      }
    }
    if (val == 9) {
      sb.append(symbols[type][0]).append(symbols[type][2]);
    }
    return sb.toString();
  }
}

/*
 * Time complexity: O(1)
 * Space complexity: O(1)
 *
 * Notes:
 * This method is more flexible. We just hardcode the essential letters. If we want to convert 39999, we don't have to
 * modify the methods, and instead we just need to add two letters, such as H for 5000 and P for 10000, in the hardcode
 * array.
 */

