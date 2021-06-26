// Link: https://leetcode.com/problems/first-missing-positive/

class Solution {
  public int firstMissingPositive(int[] nums) {
    for (int i = 0; i < nums.length; ++i) {
      // Place nums[i] to the position it should be, i.e., nums[i] should be at index
      // nums[i]-1.
      while (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
        swap(nums, i, nums[i] - 1);
      }
    }
    for (int i = 0; i < nums.length; ++i) {
      if (nums[i] - 1 != i) {
        return i + 1;
      }
    }
    return nums.length + 1;
  }

  private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }
}

/*
 * Time complexity: O(n), n is the length of nums
 * For each number, it is swapped at most once.
 *
 * Space complexity: O(1)
 *
 * Notes:
 * The missing number must be in the range of 1 ~ n+1, n is the length of nums.
 * For nums with length n, i.e., index 0, 1, 2, ..., n-1, we put 1 at index 0, 2 at index 1, ...,
 * n at index n-1, i.e., nums[i] at index nums[i-1]. Ignore the number that is less than or equal
 * to 0 and greater than n.
 * After this rearranging, we scan nums from the beginning, if at index i, we find the number is not
 * equal to i+1, i+1 will be the answer. If we can't find it after scanning the whole array, it means
 * the array contains number 1 ~ n, then n+1 will be the answer.
 */
