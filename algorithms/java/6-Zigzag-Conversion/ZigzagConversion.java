// Link: https://leetcode.com/problems/zigzag-conversion/

/*
 * Method1: 2ms, 99.91%
 * Get the characters on each row directly based on the relationship between row i (starting from 0)
 * and the index of the character on this row. If i is:
 * - 0 or numRows-1: indices of characters is i+(n-1)*2*k, k is starting from 0, and we call (n-1)*2 cycle.
 * - others: they are i+cycle*k and (i+cycle*k)+(n-1-i)*2
 */
class Solution {
  public String convert(String s, int numRows) {
    // This is essential. Otherwise cycle will be 0 and the second for-loop won't exit.
    if (numRows == 1) {
      return s;
    }
    int slen = s.length();
    StringBuilder sb = new StringBuilder();
    int cycle = (numRows - 1) * 2;
    for (int row = 0; row < numRows; ++row) {
      for (int k = 0; row + cycle * k < slen; ++k) {
        // characters at i + cycle * k
        int idx1 = row + cycle * k;
        sb.append(s.charAt(idx1));
        // characters at (i + cycle * k) + (n - 1 - i) * 2
        int idx2 = idx1 + (numRows - 1 - row) * 2;
        if (row != 0 && row != numRows - 1 && idx2 < slen) {
          sb.append(s.charAt(idx2));
        }
      }
    }
    return sb.toString();
  }
}

/*
 * Time complexity: O(n) where n is the length of the input string
 * Space complexity: O(n) for the StringBuilder
 *
 * Notes:
 * numRows == 1 must be considered first, otherwise the second for-loop will not exit (TLE).
 */


/*
 * Method2: 4ms, 79.77%
 * Iterate over the input string, for each character, append it to the StringBuilder of that row.
 */
class Solution {
  public String convert(String s, int numRows) {
    if (numRows == 1) {
      return s;
    }
    StringBuilder[] rows = new StringBuilder[numRows];
    for (int i = 0; i < numRows; ++i) {
      rows[i] = new StringBuilder();
    }
    // Direction along the zig-zag: true for going down, and false for going up
    Boolean down = true;
    int row = 0;
    for (int i = 0; i < s.length(); ++i) {
      rows[row].append(s.charAt(i));
      row += down ? 1: -1;
      if (row == numRows - 1 || row == 0) {
        down = !down;
      }
    }
    for (int i = 1; i < rows.length; ++i) {
      rows[0].append(rows[i]);
    }
    return rows[0].toString();
  }
}

/*
 * Time complexity: O(n) where n is the length of the input string
 * Space complexity: O(n) for the StringBuilder
 *
 * Notes:
 */

