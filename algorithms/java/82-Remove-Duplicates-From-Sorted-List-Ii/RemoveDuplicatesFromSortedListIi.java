// Link: https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
// Difficulty: Medium

/*
 * Run Time: 0ms, 100%
 *
 * Explanation:
 *
 */
class Solution {
  public ListNode deleteDuplicates(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode prev = dummy;
    int cnt = 1;
    while (head != null) {
      // Skip the duplicates
      while (head.next != null && head.next.val == head.val) {
        head = head.next;
        cnt++;
      }
      // If finding duplicates, delete all
      if (cnt > 1) {
        prev.next = head.next;
      } else {
        prev = head;
      }
      head = head.next;
      cnt = 1;
    }
    return dummy.next;
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
