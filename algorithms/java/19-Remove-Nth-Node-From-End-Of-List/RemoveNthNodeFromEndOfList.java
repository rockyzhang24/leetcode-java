// Link: https://leetcode.com/problems/remove-nth-node-from-end-of-list/

// 0ms, 100%
class Solution {
  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode first = dummy;
    ListNode second = dummy;
    for (; n > 0; --n) {
      first = first.next;
    }
    while (first.next != null) {
      first = first.next;
      second = second.next;
    }
    second.next = second.next.next;
    return dummy.next;
  }
}

/*
 * Time complexity: O(N), N is the length of the linked list
 *
 * Space complexity: O(1)
 *
 * Notes:
 * Use two pointers. We first make the distance of the two pointers be n. Then we move both
 * together until the first pointer pointing the last node. Now the second pointer is pointing
 * to the node just before the target node. Then we can easily delete the target node.
 *
 * Trick:
 * We use a dummy node so that removing the head will be the same as removing any other node.
 */
