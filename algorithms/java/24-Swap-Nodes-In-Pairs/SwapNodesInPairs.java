// Link: https://leetcode.com/problems/swap-nodes-in-pairs/

// Method1: Iteration
// 0ms, 100%
class Solution {
  public ListNode swapPairs(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode dummy = new ListNode(0, head);
    ListNode prev = dummy;
    // cur is pointing to the start node of the pair currently being processed
    ListNode cur = head;
    // next is pointing to the start node of the next pair
    ListNode next = head;
    while (next != null && next.next != null) {
      next = next.next.next;
      prev.next = cur.next;
      cur.next.next = cur;
      cur.next = next;
      prev = cur;
      cur = next;
    }
    return dummy.next;
  }
}

/*
 * Time complexity: O(n)
 *
 * Space complexity: O(1)
 *
 * Notes:
 *
 *  1 -> 2 -> 3 -> 4 -> 5
 * cur       next
 *
 * After swap nodes of the first pair,
 *  2 -> 1 -> 3 -> 4 -> 5
 *     cur   next
 *
 * Then we find two problems:
 * 1. We have to make node-2 to be the new head. This make the swaping nodes of the first pair
 * different from swaping nodes of other pair.
 * 2. Next we will swap 3 -> 4, so we move cur and next
 * 2 -> 1 -> 3 -> 4 -> 5
 *          cur       next
 * when swaping 3 -> 4, we have one extra operation, i.e., make node-1 point to node-4. So we
 * observe that swapping nodes of any non-first pair needs this operation. We need a prev pointer
 * to point to the the previous node, i.e., node-1.
 *
 * To make the swaping of the first pair the same as the swaping of any other pair, and easily
 * handle the new head, we use a dummy node.
 *
 * dummy -> 1 -> 2 -> 3 -> 4 -> 5
 * prev    cur       next
 *
 * After the first swaping, we have
 * dummy -> 2 -> 1 -> 3 -> 4 -> 5
 * prev         cur  next
 *
 * Then we set the prev, cur, and next for the next swaping,
 * dummy -> 2 -> 1 -> 3 -> 4 -> 5
 *             prev  cur       next
 *
 */


// Method2: recursion
// 0ms, 100%
class Solution {
  public ListNode swapPairs(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode newHead = head.next;
    head.next = swapPairs(head.next.next);
    newHead.next = head;
    return newHead;
  }
}

/*
 * Time complexity: O(n)
 *
 * Space complexity: O(n) for the recursion stack
 *
 * Notes:
 *
 */

