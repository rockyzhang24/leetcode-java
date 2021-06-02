// Link: https://leetcode.com/problems/remove-duplicates-from-sorted-array/

// 0ms, 100%
class Solution {
  public int removeDuplicates(int[] nums) {
    if (nums == null || nums.length <= 1) {
      return nums == null ? 0 : nums.length;
    }
    int slow = 0;
    for (int fast = 1; fast < nums.length; ++fast) {
      if (nums[slow] != nums[fast]) {
        nums[++slow] = nums[fast];
      }
    }
    return slow + 1;
  }
}

/*
 * Time complexity: O(n), n is the length of the input array
 *
 * Space complexity: O(1)
 *
 * Notes:
 * Use slow and fast pointers.
 * [0 ... slow] are the result so far
 * (slow ... fast) are the elements discarded
 * [fast ... n-1] are the elements that has not been checked
 */
