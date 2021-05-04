// Link: https://leetcode.com/problems/reverse-integer/

// 1ms, 100%
class Solution {
  public int reverse(int x) {
    int ans = 0;
    while (x != 0) {
      int mod = x % 10;
      if (x > 0 && ans <= (Integer.MAX_VALUE - mod) / 10
          || x < 0 && ans >= (Integer.MIN_VALUE - mod) / 10) {
        ans = ans * 10 + mod;
      } else {
        return 0;
      }
      x /= 10;
    }
    return ans;
  }
}

/*
 * Time complexity: O(n) where n the number of digits in x
 * Space complexity: O(1)
 *
 * Notes:
 * In the if condition, x>0 and x<0 should be checked separately.
 */
