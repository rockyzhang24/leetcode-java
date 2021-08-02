// Link: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

// Binary search
// 0ms, 100%
class Solution {
  public int[] searchRange(int[] nums, int target) {
    int[] ans = new int[] {-1, -1};
    if (nums == null || nums.length == 0) {
      return ans;
    }
    // Find the first occurrence, i.e., the start of the range
    int left = 0;
    int right = nums.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (target > nums[mid]) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    // Check whether the target is in nums
    if (left < nums.length && nums[left] == target) {
      ans[0] = left;
    } else {
      return ans;
    }
    // Find the last occurrence, i.e., the end of the range
    // TRICK: we don't have to reset the left back to 0.
    right = nums.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (target >= nums[mid]) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    // No need to check the target existence again
    ans[1] = right;
    return ans;
  }
}

/*
 * Time complexity: O(logn)
 * Binary search.
 *
 * Space complexity: O(1)
 *
 * Notes:
 * We use binary search to find the first occurrence and the last occurrence of the target.
 * These two cases share the same code template. The difference is how to move left or right
 * when nums[mid] is equal to target.
 *
 * - First occurrence: when nums[mid] == target, we move right, i.e., right = mid - 1. After
 * iteration, nums[left] will be the first occurrence of the target. If nums[left] is not equal
 * to target, it means target is not in the array.
 *
 * - Last occurrence: when nums[mid] == target, we move left, i.e., left = mid + 1. After
 * iteration, nums[right] will be the last occurrence of the target. If nums[right] is not equal
 * to target, it means target is not in the array.
 */
