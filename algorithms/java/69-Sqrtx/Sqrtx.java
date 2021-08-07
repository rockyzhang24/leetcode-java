// Link: https://leetcode.com/problems/sqrtx/
// Difficulty: Easy

/*
 * 72ms, 5%
 *
 * Keep doing x/i until x/i is smaller than i, then i-1 will be the result.
 */
class Solution {
  public int mySqrt(int x) {
    int i = 0;
    for (; x / (i + 1) >= i + 1; ++i);
    return i;
  }
}

/*
 * Time complexity: O(sqrt(x))
 *
 * Space complexity: O(1)
 *
 * NOTES:
 * Here we use i+1 to handle the case where x=0
 *
 */


/*
 * Method-2: binary search
 * 1ms, 99.99%
 *
 * To calculate sqrt(x), we need to find a number num, x/num >= num && x/(num+1) < num+1. E.g., x=8,
 * we know that 8/2=4, 8/3=2, so sqrt(8) is 2.
 */
class Solution {
  public int mySqrt(int x) {
    if (x == 0) {
      return 0;
    }
    int left = 1;
    int right = x;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (x / mid >= mid && x / (mid + 1) < mid + 1) {
        return mid;
      } else if (x / mid < mid) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return -1;
  }
}

/*
 * Time complexity: O(logx) = O(32) = O(1)
 *
 * Space complexity: O(1)
 *
 * NOTES:
 *
 */
