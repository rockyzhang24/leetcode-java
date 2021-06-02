// Link: https://leetcode.com/problems/remove-element/

// 0ms, 100%
class Solution {
  public int removeElement(int[] nums, int val) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int slow = 0;
    for (int fast = 0; fast < nums.length; ++fast) {
      if (nums[fast] != val) {
        nums[slow++] = nums[fast];
      }
    }
    return slow;
  }
}

/*
 * Time complexity: O(n)
 *
 * Space complexity: O(1)
 *
 * Notes:
 * Use two pointers, slow and fast:
 * - [0, slow) are the result so far
 * - [slow, fast) are the elements discarded
 * - [fast, n-1] are the elements that has not been explored so far
 */
