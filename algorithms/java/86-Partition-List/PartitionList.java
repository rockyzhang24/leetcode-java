// Link: https://leetcode.com/problems/partition-list/
// Difficulty: Medium

/*
 * Run Time: 0ms, 100%
 *
 * Explanation:
 *
 * - Create two separate linked lists. One is for all nodes less than x and the
 * other is for all nodes greater than or equal to x. Then we scan the input
 * linked list and connect the node to the linked list it should belong to. At
 * the end, connect these two linked list.
 *
 * - It will be handled automatically if one of them is empty.
 *
 * - NOTE: cut off the last node of second linked list to avoid a circle.
 *
 */
class Solution {
  public ListNode partition(ListNode head, int x) {
    ListNode dummy1 = new ListNode(0); // less than x
    ListNode dummy2 = new ListNode(0); // greater than or equal to x
    ListNode cur1 = dummy1;
    ListNode cur2 = dummy2;
    while (head != null) {
      if (head.val < x) {
        cur1.next = head;
        cur1 = cur1.next;
      } else {
        cur2.next = head;
        cur2 = cur2.next;
      }
      head = head.next;
    }
    // Connect these two linked lists.
    cur1.next = dummy2.next;
    cur2.next = null;
    return dummy1.next;
  }
}

/*
 * Time complexity: O(n)
 *
 * Space complexity: O(1)
 *
 */
