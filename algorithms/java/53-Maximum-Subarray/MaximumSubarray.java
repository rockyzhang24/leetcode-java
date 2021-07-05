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


/*
 * Method2: divide and conque
 * refer to Indroduction to Algorithm section 4.1
 * 266ms, 5.18%
 *
 * The max subarray sum of the array is the max value of:
 * 1. the max subarray sum of the left half, [left, mid]
 * 2. the max subarray sum of the right half, [mid+1, right]
 * 3. the max subarray sum goes across mid
 *
 * For 1 and 2, they are subproblem, i.e., the recursion call.
 * For 3, once we get the results returned from the left and right half, we calculate the max sum goes
 * across mid. From mid and mid+1, we go left and right respectively to get the max sum, and then add both
 * together.
 */
class Solution {
  public int maxSubArray(int[] nums) {
    return divideConque(0, nums.length - 1, nums);
  }

  private int divideConque(int left, int right,  int[] nums) {
    if (left == right) {
      return nums[left];
    }
    int mid = left + (right - left) / 2;
    // Maximum subarray sum of the left half part
    int leftMax = divideConque(left, mid,nums);
    // Maximum subarray sum of the right half part
    int rightMax = divideConque(mid + 1, right, nums);
    // Calculate the maximum subarray sum across the middile. From mid, go left to get a max sum and
    // from mid+1, go right to get a max sum, and then sum both together.
    int midMaxLeft = nums[mid];
    int sum = 0;
    for (int i = mid; i >= 0; --i) {
      sum += nums[i];
      midMaxLeft = Math.max(midMaxLeft, sum);
    }
    sum = 0;
    int midMaxRight = nums[mid + 1];
    for (int i = mid + 1; i < nums.length; ++i) {
      sum += nums[i];
      midMaxRight = Math.max(midMaxRight, sum);
    }
    int midMax = midMaxLeft + midMaxRight;
    // Return the maximum of these three
    return Math.max(midMax, Math.max(leftMax, rightMax));
  }
}

/*
 * Time complexity: O(nlogn), n is the length of nums.
 * The recursion tree has O(logn) levels. On each level, i.e., in all nodes in the level, we need totally
 * O(n) time to find the max sum across mid.
 *
 * Space complexity: O(logn) for the recursion stack
 *
 * Notes:
 * Improvement: The time-consuming step is in the recursion the two for-loops to calculate the max subarray
 * sum across mid. If we can get it throught the recursion, i.e., no need to calculate it in linear time, the
 * time complexity will become O(n).
 * Refer to: https://leetcode.com/problems/maximum-subarray/discuss/20200/Share-my-solutions-both-greedy-and-divide-and-conquer
 */


/*
 * Method3: prefix sum
 * 0ms, 100%
 *
 * We maintain a varialbe preSum to represent the prefix sum of index i (i.e., nums[0] + ... + nums[i]).
 * And meanwhile, we maintain another variable curMin (initialize it to 0) to record the minimum of
 * all prefix sums from prefix-sum(0) to prefix-sum(i-1). So preSum-curMin will be the maximum sum of
 * all the subarrays ending with nums[i].
 */
class Solution {
  public int maxSubArray(int[] nums) {
    int preSum = nums[0];
    int curMin = 0;
    int max = nums[0];
    for (int i = 1; i < nums.length; ++i) {
      curMin = Math.min(curMin, preSum);
      preSum = preSum + nums[i];
      max = Math.max(max, preSum - curMin);
    }
    return max;
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

