// Link: https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
// Difficulty: Medium

/*
 * 0ms, 100%
 *
 * Binary search
 * Same logic with problem "33. Search In Rotated Sorted Array" (../33-Search-In-Rotated-Sorted-Array/SearchInRotatedSortedArray.java).
 *
 * The only difference is that we need to handle the duplicates. If the array has duplicates and is rotated at the index of one dupliate,
 * e.g., [1, 1, ..., 1, 2, 0, 1, 1, ..., 1], when nums[mid] is equal to nums[left] which is 1, we don't know which segment mid will be at,
 * that means mid can be at the segment "1, 1, ..., 1, 2", or "0, 1, 1, ..., 1", no way to tell. To handle this situation, in each iteration
 * firstly we skip the duplicates from both ends but maintain left and right not to go across mid, and afterwards, we can decide which
 * segment mid will be at, so we can apply the binary search. E.g., for [1, 2, 0, 1, 1, 1, 1, 1, 1], mid is 4 and nums[mid] is 1, after
 * skipping the duplicates, it becomes [2, 0, 1, 1], and at this time, nums[mid] < nums[left], so mid is at the second segment.
 */
class Solution {
  public boolean search(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] == target) {
        return true;
      }
      // Tricky part: Skip duplicates
      while (left < mid && nums[left] == nums[mid]) {
        left++;
      }
      while (right > mid && nums[right] == nums[mid]) {
        right--;
      }
      // Same as problem 26
      if (nums[mid] >= nums[left]) {
        if (target >= nums[left] && target <= nums[mid]) {
          right = mid - 1;
        } else {
          left = mid + 1;
        }
      } else {
        if (target >= nums[mid] && target <= nums[right]) {
          left = mid + 1;
        } else {
          right = mid - 1;
        }
      }
    }
    return false;
  }
}

/*
 * Time complexity: O(logn)
 *
 * Space complexity: O(1)
 *
 * NOTES:
 *
 */
