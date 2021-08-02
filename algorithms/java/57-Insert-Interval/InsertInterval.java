// Link: https://leetcode.com/problems/insert-interval/

/*
 * 1ms, 98.65%
 */
class Solution {
  public int[][] insert(int[][] intervals, int[] newInterval) {
    int n = intervals.length;
    List<int[]> ans = new ArrayList<>();
    int i = 0;
    // Before we find overlapping, add non-overlapping intervals into result
    for (; i < n && intervals[i][1] < newInterval[0]; ++i) {
      ans.add(intervals[i]);
    }
    // Merge the overlapping intervals and add it to result. We don't need an extra
    // array, and instead we can update newInterval.
    if (i < n) {
      newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
    }
    for (; i < n && intervals[i][0] <= newInterval[1]; ++i) {
      newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
    }
    ans.add(newInterval);
    // Add the remaining non-overlapping intervals
    for (; i < n; ++i) {
      ans.add(intervals[i]);
    }
    return ans.toArray(new int[ans.size()][]);
  }
}

/*
 * Time complexity: O(n)
 *
 * Space complexity: O(1)
 *
 * Notes:
 * Pay attention to the corner cases:
 * 1) intervals is empty
 * 2) newInterval doesn't overlap any interval and it is at the end, e.g., [[1,3]], [5,8]
 *
 * We we update the start-time and end-time, we should handle these two corner cases. The
 * end-time is handled automatically in the for-loop (line 21). For the start-time, we update
 * it only if i<n (line 17).
 *
 */
