// Link: https://leetcode.com/problems/powx-n/

/*
 * 0ms, 100%
 *
 * Like 29-Divide-Two-Integer
 * If we multiply x each time, we may get LTE, e.g, x=1.0000, n=2^31-1, we need 2^31-1 times multiplication.
 * So, for example, in order to compute x^n, instead of x^2 -> x^3 -> ... -> x^20, we compute x^2, x^4, x^8, ...,
 * until x^m where m is right smaller than n, then we let n = n-m and repeat the process.
 */
class Solution {
  public double myPow(double x, int n) {
    int power = n;
    if (n < 0) {
      // If n is -2^31, we set it to 2^31 - 1 due to the overflow of 2^31 and at the end we just need to
      // multiply x one more time.
      power = (n == Integer.MIN_VALUE ? Integer.MAX_VALUE : -n);
    }
    double ans = 1;
    while (power > 0) {
      int curPow = 1;
      double curAns = x;
      while (curPow < power >> 1) {
        curAns *= curAns;
        curPow <<= 1;
      }
      ans *= curAns;
      power -= curPow;
    }
    if (n < 0) {
      return n == Integer.MIN_VALUE ? 1 / (ans * x) : 1 / ans;
    }
    return ans;
  }
}

/*
 * Time complexity: O((logn)^2)
 * We need O(logn) time to double m to n. After subtraction (i.e., n-m), the worse case is the leftover is half of n.
 * E.g., n=31, at the first time, m=16, the leftover is 15. So we need O(logn) times. So the time complexity is O((logn)^2).
 *
 * Space complexity: O(1)
 *
 * Notes:
 *
 */


/*
 * Method2: Recursion
 * 0ms, 100%
 *
 * Basic idea: to calculate x^n, we just need to calculate x^(n/2), and x^n = x^(n/2) * x^(n/2)
 */
class Solution {
  public double myPow(double x, int n) {
    if (n < 0) {
      return n == Integer.MIN_VALUE ? 1 / (x * pow(x, Integer.MAX_VALUE)) : 1 / pow(x, -n);
    }
    return pow(x, n);
  }

  private double pow(double x, int n) {
    if (n == 0) {
      return 1.0;
    }
    double half = pow(x, n / 2);
    if (n % 2 == 0) {
      return half * half;
    } else {
      return half * half * x;
    }
  }
}

/*
 * Time complexity: O(logn)
 *
 * Space complexity: O(logn) for the recursion stack
 *
 * Notes:
 *
 */


/*
 * Method3: Iteration
 * 0ms, 100%
 *
 */
class Solution {
  public double myPow(double x, int n) {
    int remaining = n;
    if (n < 0) {
      remaining = n == Integer.MIN_VALUE ? Integer.MAX_VALUE : -n;
    }
    double ans = 1;
    double base = x;
    while (remaining > 0) {
      if (remaining % 2 == 1) {
        ans *= base;
      }
      base *= base;
      remaining /= 2;
    }
    if (n < 0) {
      return n == Integer.MIN_VALUE ? 1 / (ans * x) : 1 / ans;
    }
    return ans;
  }
}
/*
 * Time complexity: O(logn)
 *
 * Space complexity: O(1)
 *
 * Notes:
 *
 */
