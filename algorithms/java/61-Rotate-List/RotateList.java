// Link: https://leetcode.com/problems/rotate-list/

/*
 * Method-1: two pointers
 * 0ms, 100%
 */
class Solution {
  public ListNode rotateRight(ListNode head, int k) {
    if (head == null || head.next == null || k == 0) {
      return head;
    }
    // Count the length first
    int len = 0;
    ListNode temp = head;
    while (temp != null) {
      temp = temp.next;
      len++;
    }
    // Two pointers
    ListNode left = head;
    ListNode right = head;
    // Move right forward, making the distance between left and right be k%len
    for (int i = 0; i < k % len; ++i) {
      right = right.next;
    }
    // NOTICE: if k is equal to the multiple of the length of the linked list, the new
    // linked list after rotate is identical to the original list, so we return it directly
    if (left == right) {
      return head;
    }
    // Move left and right together to the end. Now the sub-list between left and right is the
    // sub-list that will be rotated
    while (right.next != null) {
      right = right.next;
      left = left.next;
    }
    // Rotate: first cut off the sub-list, then connect the tail the sub-list to the original head
    ListNode newHead = left.next;
    left.next = null;
    right.next = head;
    return newHead;
  }
}

/*
 * Time complexity: O(n), n is the length of the linked list
 *
 * Space complexity: O(1)
 *
 * NOTES:
 *
 */


/*
 * Method-2: circle the list
 * 0ms, 100%
 */
class Solution {
  public ListNode rotateRight(ListNode head, int k) {
    if (head == null || head.next == null || k == 0) {
      return head;
    }
    // Count the length
    int len = 1;
    ListNode tail = head;
    while (tail.next != null) {
      tail = tail.next;
      len++;
    }
    // Connect the tail to the head to make it circle
    tail.next = head;
    k %= len;
    // Set the new tail
    ListNode newTail = head;
    for (int i = 0; i < len - k - 1; ++i) {
      newTail = newTail.next;
    }
    // Cut off
    ListNode newHead = newTail.next;
    newTail.next = null;
    return newHead;
  }
}

/*
 * Time complexity: O(n)
 *
 * Space complexity: O(1)
 *
 * NOTES:
 * In this method, we can avoid the corner case in method-1, i.e., when k is the multiple of the length,
 * we should return the original list directly.
 *
 */
