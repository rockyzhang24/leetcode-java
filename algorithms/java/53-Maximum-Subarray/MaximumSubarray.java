// Link: https://leetcode.com/problems/maximum-subarray/

/*
 * DP
 * 0ms, 100%
 *
 * Originally, we use M[], M[i] represents the max sum of all subarrays ending with nums[i], but
 * in the induction rule, M[i] only depends on M[i - 1]. So we optimize the 1D array to a variable
 * curMax.
 */
class Solution {
  public int maxSubArray(int[] nums) {
    // When iterate over nums, curMax is the max sum of all subarrays ending with nums[i]
    int curMax = nums[0];
    int ans = nums[0];
    for (int i = 1; i < nums.length; ++i) {
      if (curMax < 0) {
        curMax = nums[i];
      } else {
        curMax = curMax + nums[i];
      }
      ans = Math.max(ans, curMax);
    }
    return ans;
  }
}

/*
 * Time complexity: O(n)
 *
 * Space complexity: O(1)
 *
 * Notes:
 *
 */
