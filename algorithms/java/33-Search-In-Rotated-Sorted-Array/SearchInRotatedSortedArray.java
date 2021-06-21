// Link: https://leetcode.com/problems/search-in-rotated-sorted-array/

// Binary search idea
// 0ms, 100%
class Solution {
  public int search(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] == target) {
        return mid;
      }
      if (nums[mid] >= nums[left]) {
        if (target < nums[mid] && target >= nums[left]) {
          right = mid - 1;
        } else {
          left = mid + 1;
        }
      } else {
        if (target > nums[mid] && target <= nums[right]) {
          left = mid + 1;
        } else {
          right = mid - 1;
        }
      }
    }
    return -1;
  }
}

/*
 * Time complexity: O(logn)
 * Due to the property of the binary search, each iteration, we rule out the half of the array
 *
 * Space complexity: O(1)
 *
 * Notes:
 * Using the binary search idea, each time we rule out the half.
 * Take [4,5,6,7,0,1,2] as an example:
 * - When mid is at 4~7, if 4 <= target < mid, we can rule out the right half of mid; otherwise
 * the left half can be ruled out.
 * - When mid is at 0~2, if mid < target <= 2, we can rule out the left half of mid; otherwise
 * the right half can be ruled out.
 */
