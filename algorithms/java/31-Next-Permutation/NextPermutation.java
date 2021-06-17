// Link: https://leetcode.com/problems/next-permutation/

// 0ms, 100%
class Solution {
  public void nextPermutation(int[] nums) {
    // From the end, find the first number that is not in the non-decreasing sequence
    int i = nums.length - 2;
    for (; i >= 0 && nums[i] >= nums[i + 1]; --i);
    // If the whole array is non-decreasing, reverse it and return.
    if (i < 0) {
      reverse(nums, 0, nums.length - 1);
      return;
    }
    // In the non-decreasing sequence, find the number that is just larger than this number
    int j = nums.length - 1;
    for (; j >= 0 && nums[j] <= nums[i]; --j);
    // Swap these two numbers
    swap(nums, i, j);
    // Reverse, i.e., order these numbers from small to large
    reverse(nums, i + 1, nums.length - 1);
  }

  private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  private void reverse(int[] nums, int i, int j) {
    while (i < j) {
      swap(nums, i++, j--);
    }
  }
}

/*
 * Time complexity: O(n)
 *
 * Space complexity: O(1)
 *
 * Notes:
 * 1. From the end of the string, we find the first number that is not in the non-dcreasing sequence, and
 * mark its index as i. If i < 0 which means the whole array is the largest, we reverse the array to make it
 * lowest and return.
 * 2. In this non-dreasing sequence (i.e., nums[i+1 ... len-1]), find the first number that is larger than
 * nums[i] and mark its index as j.
 * 3. Swap nums[i] and nums[j]
 * 4. At last, we reverse these numbers, i.e., nums[i+1, len-1], to make them order increasingly.
 */
