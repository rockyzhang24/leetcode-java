// Link: https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
// Difficulty: Medium

/*
 * Two pointers
 * Similar to problem "26. Remove Duplicates From Sorted Array" (../26-Remove-Duplicates-From-Sorted-Array/RemoveDuplicatesFromSortedArray.java)
 * but we should use an extra flag to record whether we find more thant two duplicates or not.
 *
 * 0ms, 100%
 */
class Solution {
  public int removeDuplicates(int[] nums) {
    int flag = 0;
    int slow = 0;
    for (int fast = 1; fast < nums.length; ++fast) {
      if (nums[fast] != nums[slow]) {
        nums[++slow] = nums[fast];
        flag = 0;
      } else if (flag == 0) {
        nums[++slow] = nums[fast];
        flag = 1;
      }
    }
    return slow + 1;
  }
}

/*
 * Time complexity: O(n)
 *
 * Space complexity: O(1)
 *
 * NOTES:
 * Without using flag, see https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/discuss/27976/3-6-easy-lines-C%2B%2B-Java-Python-Ruby
 *
 */
