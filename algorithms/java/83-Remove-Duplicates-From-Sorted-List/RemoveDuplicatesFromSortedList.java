// Link: https://leetcode.com/problems/remove-duplicates-from-sorted-list/
// Difficulty: Easy

/*
 * Run Time: 0ms, 100%
 *
 * Explanation:
 * Two pointers, same idea with problem 26 "Remove Duplicates from Sorted Array" (../26-Remove-Duplicates-From-Sorted-Array/RemoveDuplicatesFromSortedArray.java)
 *
 */
class Solution {
  public ListNode deleteDuplicates(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode slow = head;
    for (ListNode fast = head.next; fast != null; fast = fast.next) {
      if (fast.val != slow.val) {
        slow.next = fast;
        slow = slow.next;
      }
    }
    // Don't forget to cut off slow.next
    slow.next = null;
    return head;
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
