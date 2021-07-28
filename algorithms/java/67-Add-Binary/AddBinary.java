// Link: https://leetcode.com/problems/add-binary/

/*
 * 2ms, 75.29%
 *
 * The idea is similar to problem 2-Add-Two-Numbers (../2-Add-Two-Numbers/AddTwoNumbers.java)
 */
class Solution {
  public String addBinary(String a, String b) {
    StringBuilder sb = new StringBuilder();
    int aLen = a.length();
    int bLen = b.length();
    int carry = 0;
    for (int i = 0; i < aLen || i < bLen; ++i) {
      int sum = carry;
      int aIdx = aLen - 1 - i;
      int bIdx = bLen - 1 - i;
      if (aIdx >= 0) {
        sum += a.charAt(aIdx) - '0';
      }
      if (bIdx >= 0) {
        sum += b.charAt(bIdx) - '0';
      }
      sb.append(sum % 2);
      carry = sum / 2;
    }
    if (carry != 0) {
      sb.append(carry);
    }
    return sb.reverse().toString();
  }
}

/*
 * Time complexity: O(max(alen, blen))
 *
 * Space complexity: O(1)
 *
 * NOTES:
 *
 */
