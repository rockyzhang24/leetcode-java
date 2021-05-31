// Link: https://leetcode.com/problems/merge-two-sorted-lists/

// 0ms, 100%
class Solution {
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;
    while (l1 != null && l2 != null) {
      if (l1.val <= l2.val) {
        cur.next = l1;
        l1 = l1.next;
      } else {
        cur.next = l2;
        l2 = l2.next;
      }
      cur = cur.next;
    }
    cur.next = l1 == null ? l2 : l1;
    return dummy.next;
  }
}

/*
 * Time complexity: O(n1+n2), n1 an n2 are the lengths of l1 and l2 respectively.
 *
 * Space complexity: O(1)
 *
 * Notes:
 *
 */
