// Link: https://leetcode.com/problems/reverse-nodes-in-k-group/

// Method1: Recursion
// 0ms, 100%
class Solution {
  public ListNode reverseKGroup(ListNode head, int k) {
    if (head == null || head.next == null || k == 1) {
      return head;
    }
    // Count k nodes to find the tail of the current k-nodes list and the head of the
    // next k-nodes list
    ListNode tail = head;
    int i = k;
    for (; i > 1 && tail.next != null; --i) {
      tail = tail.next;
    }
    if (i != 1) {
      return head;
    }
    // Cut off the connection between the current list and the next list
    ListNode nextHead = tail.next;
    tail.next = null;

    // Reverse the current list and connect it to the following reversed list
    ListNode newHead = reverseLinkedList(head);
    head.next = reverseKGroup(nextHead, k);
    return newHead;
  }

  // Recursive way to reverse a linked list
  private ListNode reverseLinkedList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode newHead = reverseLinkedList(head.next);
    head.next.next = head;
    head.next = null;
    return newHead;
  }
}

/*
 * Time complexity: O(n), n is the number of nodes in the list
 * Each recursion function takes O(k) to count k nodes and reverse
 *
 * Space complexity: O(n/k) for the recursion stack
 *
 * Notes:
 * Take advantage of the reversing of a linked list.
 */


// Method2: Iteration
// 0ms, 100%
class Solution {
  public ListNode reverseKGroup(ListNode head, int k) {
    if (head == null || head.next == null || k == 1) {
      return head;
    }
    ListNode dummy = new ListNode(0, head);
    ListNode prevTail = dummy;
    // ptr is used to count k nodes
    ListNode ptr = head;
    while(ptr != null) {
      int count = 0;
      for (; count < k - 1 && ptr.next != null; ++count) {
        ptr = ptr.next;
      }
      // If less than k nodes, we connect it to the previous tail and then terminate
      if (count != k - 1) {
        prevTail.next = head;
        break;
      }
      // Cut off the connection between the tail of the current k-nodes list and the next node,
      // and mark the next node as the head of the next k-nodes list.
      ListNode nextHead = ptr.next;
      ptr.next = null;
      // Reverse the current k-nodes list and connect it with the previous tail
      prevTail.next = reverseLinkedList(head);

      prevTail = head;
      ptr = nextHead;
      head = nextHead;
    }
    return dummy.next;
  }

  // Iterative way to reverse a linked list
  private ListNode reverseLinkedList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode prev = null;
    while (head != null) {
      ListNode next = head.next;
      head.next = prev;
      prev = head;
      head = next;
    }
    return prev;
  }
}

/*
 * Time complexity: O(n)
 *
 * Space complexity: O(1)
 *
 * Notes:
 *
 */

