// Link: https://leetcode.com/problems/sort-colors/
// Difficulty: Medium

/*
 * 0ms, 100%
 *
 * Use three pointers i, j, k as the boundaries, and move j to explore
 * [0, i) are all 0s
 * [i, j) are all 1s
 * [j, k] are unexplored area
 * (k, nums.length-1] are all 2s
 */
class Solution {
  public void sortColors(int[] nums) {
    int i = 0;
    int j = 0;
    int k = nums.length - 1;
    while (j <= k) {
      if (nums[j] == 0) {
        swap(nums, i++, j++);
      } else if (nums[j] == 1) {
        j++;
      } else {
        swap(nums, j, k--);
      }
    }
  }

  private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
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
