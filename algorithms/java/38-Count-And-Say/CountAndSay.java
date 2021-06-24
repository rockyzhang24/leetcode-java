// Link: https://leetcode.com/problems/count-and-say/

// 5ms, 51.98%
class Solution {
  public String countAndSay(int n) {
    String ans = "1";
    for (; n > 1; --n) {
      StringBuilder sb = new StringBuilder();
      int i = 0;
      for (int j = 0; j <= ans.length(); ++j) {
        if (j == ans.length() || ans.charAt(j) != ans.charAt(i)) {
          sb.append(j - i);
          sb.append(ans.charAt(i));
          i = j;
        }
      }
      ans = sb.toString();
    }
    return ans;
  }
}

/*
 * Time complexity: O(n^2)
 *
 * Space complexity: O(n)
 *
 * Notes:
 * Just brute force.
 * We start from the 1st term, i.e., "1", to compute the 2nd term, then using the 2nd term
 * to compute the 3rd term, and so on until we finish the nth term.
 */
