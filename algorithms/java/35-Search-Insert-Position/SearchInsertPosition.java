// Link: https://leetcode.com/problems/search-insert-position/

// Binary search
// 0ms, 100%
class Solution {
  public int searchInsert(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] == target) {
        return mid;
      }
      if (target > nums[mid]) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    // After the binary search, if target doesn't exist, left and right will be
    // the smallest larger and largest smaller respectively.
    return left;
  }
}

/*
 * Time complexity: O(logn)
 *
 * Space complexity: O(1)
 *
 * Notes:
 * We use binary search. The binary search has one property:
 * If the target doesn'target exist, after the search
 * - left will be the smallest larger (i.e., the smallest of the elements larger than target)
 * - right will be the largest smaller (i.e., the largest of the elements smaller than target).
 *
 * For example, nums = [1, 2, 3, 4, 6, 7], target = 5
 * After searching, left and right will point to 6 and 4 respectively, i.e.,
 * nums = [1, 2, 3, 4, 6, 7]
 *                  r  l
 *
 * So we just need to return left directly.
 */
