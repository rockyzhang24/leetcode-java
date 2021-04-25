// Link: https://leetcode.com/problems/add-two-numbers/

class Solution {
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(-1);
    ListNode cur = dummy;
    int carry = 0;
    while (l1 != null || l2 != null) {
      int n1 = l1 == null ? 0 : l1.val;
      int n2 = l2 == null ? 0 : l2.val;
      int sum = n1 + n2 + carry;
      cur.next = new ListNode(sum % 10);
      carry = sum / 10;
      cur = cur.next;
      if (l1 != null) {
        l1 = l1.next;
      }
      if (l2 != null) {
        l2 = l2.next;
      }
    }
    if (carry != 0) {
      cur.next = new ListNode(carry);
    }
    return dummy.next;
  }
}

/*
 * Time complexity: O(n) where n is max(l1's length, l2's length)
 * Space complexity: O(n) where is the same above
 *
 * Notes:
 * After traversing both linked lists, don't forget the last digit should be the carry if it is not zero.
 */
