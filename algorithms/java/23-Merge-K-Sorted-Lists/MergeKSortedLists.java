// Link: https://leetcode.com/problems/merge-k-sorted-lists/

// Method1: use min heap
// 4ms, 78.51%
class Solution {
  public ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0) {
      return null;
    }
    int n = lists.length;
    // Use a min heap
    PriorityQueue<ListNode> minHeap = new PriorityQueue<>(n, new Comparator<ListNode>() {
      @Override
      public int compare(ListNode l1, ListNode l2) {
        return l1.val - l2.val;
      }
    });
    // Put the first node of each list into the min heap
    for (ListNode l : lists) {
      if (l != null) {
        minHeap.offer(l);
      }
    }
    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;
    // Get the min node, append it to the result, and put its next node into the min heap
    while (!minHeap.isEmpty()) {
      ListNode curMin = minHeap.poll();
      cur.next = curMin;
      cur = cur.next;
      if (curMin.next != null) {
        minHeap.offer(curMin.next);
      }
    }
    return dummy.next;
  }
}

/*
 * Time complexity: O(Nlogn), n is the length of lists and N is the total number of nodes in all lists.
 * offer() of min heap costs O(logn) if the heap has n nodes.
 *
 * Space complexity: O(n) for the priority queue with capacity n.
 *
 * Notes:
 *
 */

// Method2: divide and conque (like merge sort)
// 3ms, 80.84%
class Solution {
  public ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0) {
      return null;
    }
    return divideAndConque(lists, 0, lists.length - 1);
  }

  private ListNode divideAndConque(ListNode[] lists, int start, int end) {
    if (start == end) {
      return lists[start];
    }
    int mid = start + (end - start) / 2;
    ListNode l1 = divideAndConque(lists, start, mid);
    ListNode l2 = divideAndConque(lists, mid + 1, end);
    return mergeTwoLists(l1, l2);
  }

  // Iterative way to merge two sorted lists
  private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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

  // Recursive way to merge two sorted lists
  private ListNode mergeTwoListsRecursion(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    }
    if (l2 == null) {
      return l1;
    }
    if (l1.val <= l2.val) {
      l1.next = mergeTwoListsRecursion(l1.next, l2);
      return l1;
    } else {
      l2.next = mergeTwoListsRecursion(l1, l2.next);
      return l2;
    }
  }
}

/*
 * Time complexity: O(Nlogn), n is the length of lists, and N is the total number of nodes in all lists.
 * The recursion tree has O(logn) levels. On each level, totally we need O(N) time to run merge-two-lists.
 *
 * Space complexity: O(logn) for the recursion stack
 *
 * Notes
 * Same idea as Merge Sort.
 */

