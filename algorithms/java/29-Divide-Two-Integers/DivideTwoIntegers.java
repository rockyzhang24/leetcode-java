// Link: https://leetcode.com/problems/divide-two-integers/

// 2ms, 32.60%
class Solution {
  public int divide(int dividend, int divisor) {
    // Some corner cases
    if (dividend == 0) {
      return 0;
    }
    if (divisor == 1) {
      return dividend;
    }
    if (divisor == -1) {
      return dividend == Integer.MIN_VALUE ? Integer.MAX_VALUE : -dividend;
    }

    // The result is positive or negative
    boolean pos = (dividend >= 0 && divisor > 0 || dividend <= 0 && divisor < 0);
    // We use negative numbers to solve this problem instead of positive number to avoid
    // overflow (i.e., changing Integer.MIN_VALUE to positive will cause overflow)
    if (dividend > 0) {
      dividend = -dividend;
    }
    if (divisor > 0) {
      divisor = -divisor;
    }
    int ans = 0;
    while (divisor >= dividend) {
      int subtractor = divisor;
      int count = 1;
      while (Integer.MIN_VALUE >> 1 <= subtractor && subtractor << 1 >= dividend) {
        subtractor <<= 1;
        count <<= 1;
      }
      dividend -= subtractor;
      ans += count;
    }
    return pos ? ans : -ans;
  }
}

/*
 * Time complexity: O((logn)^2), n is the absolute value of dividend.
 * Each iteration, we need O(logn) to get the number we should substract from the dividend by
 * doubling the divisor repeatedly. In the worst case, the leftover after subtraction is
 * the half of dividend, so totally we need O(logn) iterations.
 *
 * Space complexity: O(1)
 *
 * Notes:
 * Key idea: for two positive integer, A / B means how many times we can subtract B from A.
 *
 * If each time we subtract B from A and then count the total times, calculating
 * Integer.MAX_VALUE / 1 will be time-consuming.
 *
 * So each time we cannot subtract B directly. Instead we double B repeatedly only if it is
 * still smaller than or equal to A. And meanwhile, we count how many Bs here. Then we
 * subtract this number from A. Next time, the leftover will act as the dividend and we follow
 * the same procedure. Finally, we sum up the counts which is the result.
 *
 * We use left-shift by 1 to implement the "double" operation and the counting.
 */
