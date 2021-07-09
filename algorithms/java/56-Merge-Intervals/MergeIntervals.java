// Link: https://leetcode.com/problems/merge-intervals/

/*
 * Method1:
 * Sort intervals by their start time and then iterate over the intervals to merge the overlapping ones
 * 5ms, 94.73%
 */
class Solution {
  public int[][] merge(int[][] intervals) {
    // Sort by the start time
    Arrays.sort(intervals, new Comparator<int[]>() {
      @Override
      public int compare(int[] arr1, int[] arr2) {
        if (arr1[0] == arr2[0]) {
          return 0;
        }
        return arr1[0] < arr2[0] ? -1 : 1;
      }
    });
    List<int[]> ans = new ArrayList<>();
    // Take the first interval and compare its end with the starts of the next intervals. As long as they overlap,
    // we update the end to be the max end of the overlapping inervals. Once we find a non-overlapping interval,
    // we add the previous extended interval to the result and start over.
    int[] newInterval = intervals[0];
    ans.add(newInterval);
    for (int[] interval : intervals) {
      if (interval[0] <= newInterval[1]) {
        // Overlapping interval, update the end
        newInterval[1] =  Math.max(newInterval[1], interval[1]);
      } else {
        // Non-overlapping interval, add it to the result and start over
        newInterval = interval;
        ans.add(newInterval);
      }
    }
    return ans.toArray(new int[ans.size()][]);
  }
}

/*
 * Time complexity: O(nlogn)
 *
 * Space complexity: O(logn) for sorting
 *
 * Notes:
 * On each start-over, we add the interval into the result first and then adjust its end time. If instead we
 * adjust the end time first, and add it into the result at the end, after iterating all the intervals, the
 * last interval is left and not been processed yet, and we have to add the last interval into the result after
 * the for-loop.
 *
 */


/*
 * Method2: sweep line
 * 16ms, 6.73%
 *
 * Sort the start time and end time of all the intervals together. Then we use a counter and scan all these time,
 * counter++ if we meet a start time and counter-- if an end time. Once the counter becomes 0, it means we have
 * scaned a group of overlapping intervals, so add it to the result.
 */
class Solution {
  public int[][] merge(int[][] intervals) {
    List<int[]> ans = new ArrayList<>();
    // Build the array holding all the times
    Time[] times = new Time[intervals.length * 2];
    for (int i = 0; i < intervals.length; ++i) {
      times[i * 2] = new Time(intervals[i][0], 0);
      times[i * 2 + 1] = new Time(intervals[i][1], 1);
    }
    // Sort the array
    // If a start time is equal to a end time, we order the put the start time in front
    Arrays.sort(times, new Comparator<Time>() {
      @Override
      public int compare(Time t1, Time t2) {
        if (t1.getTime() == t2.getTime()) {
          if (t1.getType() == t2.getType()) {
            return 0;
          }
          return t1.getType() < t2.getType() ? -1 : 1;
        }
        return t1.getTime() < t2.getTime() ? -1 : 1;
      }
    });
    // Sweep
    int count = 0;
    int start = times[0].getTime();
    for (int i = 0; i < times.length; ++i) {
      count = times[i].getType() == 0 ? count + 1 : count - 1;
      if (count == 0) {
        // Finish merging a group of overlapping intervals, add it to result and reset the start time
        // for merging the next group
        ans.add(new int[] {start, times[i].getTime()});
        if (i + 1 < times.length) {
          start = times[i + 1].getTime();
        }
      }
    }
    return ans.toArray(new int[ans.size()][]);
  }

  private class Time {
    int time;
    // 0 for start time and 1 for end time
    int type;

    Time(int time, int type) {
      this.time = time;
      this.type = type;
    }

    int getTime() {
      return time;
    }

    int getType() {
      return type;
    }
  }
}

/*
 * Time complexity: O(nlogn), n is intervals.length
 * O(n) for building the array, O(2n * log2n) for the sorting, O(2n) for sweep, and O(n) for toArray(). So
 * O(n + 2n * log2n + 2n + n) = O(6n + nlogn) = O(nlogn)
 *
 * Space complexity: O(n)
 * O(2n) for the times array, O(log2n) = O(logn) for the sort, and O(n) for the answer list. So
 * O(2n + logn + n) = O(3n + logn) = O(n)
 *
 * Notes:
 *
 */
