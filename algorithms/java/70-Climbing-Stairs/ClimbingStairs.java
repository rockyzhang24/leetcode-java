// Link: https://leetcode.com/problems/climbing-stairs/
// Difficulty: Easy

/*
 * DP
 * 0ms, 100%
 */
class Solution {
  public int climbStairs(int n) {
    // M[i] represents how many distinct ways for a stair with i steps, i starts from 0.
    int[] M = new int[n + 1];
    M[0] = 1;
    M[1] = 1;
    for (int i = 2; i < n + 1; ++i) {
      M[i] = M[i - 1] + M[i - 2];
    }
    return M[n];
  }
}

/*
 * Time complexity: O(n)
 *
 * Space complexity: O(n)
 *
 * NOTES:
 *
 */


/*
 * Method-2: DP - optimize the space complexity to O(1)
 * 0ms, 100%
 */
class Solution {
  public int climbStairs(int n) {
    if (n == 1) {
      return 1;
    }
    int one = 1;
    int two = 1;
    int cur = 0;
    for (int i = 2; i < n + 1; ++i) {
      cur = one + two;
      two = one;
      one = cur;
    }
    return cur;
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
