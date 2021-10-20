// Link: https://leetcode.com/problems/largest-rectangle-in-histogram/
// Difficulty: Hard

/*
 * Run Time: 7ms, 98.61%
 *
 * Explanation:
 * For each bar i, we calculate the area of the max rectangle whose height is heights[i]. To
 * calculate this, we need to know the index of the first bar that is lower than bar i on its
 * left side, and the counterpart on its right side. Use two extra arrays to record. We can get
 * each array by O(n) time.
 *
 */
class Solution {
  public int largestRectangleArea(int[] heights) {
    int n = heights.length;
    // leftFirstLower[i] represents the index of the first lower bar (compared to bar i)
    // on its left side, i.e., from bar i to bar 0
    int[] leftFirstLower = new int[n];
    // rightFirstLower[i] represents the index of the first lower bar (compared to bar i)
    // on its right side, i.e., from bar i to bar
    int[] rightFirstLower = new int[n];
    leftFirstLower[0] = -1;
    rightFirstLower[n - 1] = n;
    // Calculate these two arrays. O(n) for each.
    for (int i = 1; i < n; ++i) {
      int idx = i - 1;
      while (idx >= 0 && heights[idx] >= heights[i]) {
        idx = leftFirstLower[idx];
      }
      leftFirstLower[i] = idx;
    }
    for (int i = n - 2; i >= 0; --i) {
      int idx = i + 1;
      while (idx < n && heights[idx] >= heights[i]) {
        idx = rightFirstLower[idx];
      }
      rightFirstLower[i] = idx;
    }
    // Calculate the max area
    int maxArea = 0;
    for (int i = 0; i < n; ++i) {
      maxArea = Math.max(maxArea, heights[i] * (rightFirstLower[i] - leftFirstLower[i] - 1));
    }
    return maxArea;
  }
}

/*
 * Time complexity: O(n)
 * Both calculating arrays leftFirstLower and rightFirstLower are O(n). When calculating leftFirstLower[i],
 * see the while-loop, we jump to some indices until we find a lower bar. These indices only participate in
 * the calculation of leftFirstLower[i], i.e., when we calculate the subsequent element of leftFirstLower
 * in the future, these indices won't be used. That is, each element in heights will be used only once. So
 * calculating leftFirstLower is O(n). Calculating rightFirstLower has the same story.
 *
 * Another explanation: https://leetcode.com/problems/largest-rectangle-in-histogram/discuss/28902/5ms-O(n)-Java-solution-explained-(beats-96)/112469
 *
 * Space complexity: O(n)
 *
 * NOTES:
 *
 */


/*
 * Method-2: use a stack
 *
 * Run Time: 17ms, 83.84%
 *
 * Explanation:
 * Use a stack to maintain a non-decreasing sequence (it stores indices). Once we find a bar lower than the top,
 * say bar i, we can compute the rectangle area with the top as its height. We pop out top, and for the top, the
 * first lower bar on its left side is the current top element and the first lower bar on its right side is bar i,
 * so area = heights[top] * (i - stack.top - 1).
 *
 */
class Solution {
  public int largestRectangleArea(int[] heights) {
    int n = heights.length;
    Deque<Integer> stack = new ArrayDeque<>();
    int maxArea = 0;
    for (int i = 0; i <= n; ++i) {
      while (!stack.isEmpty() && (i == n || heights[i] < heights[stack.peekFirst()])) {
        int top = stack.pollFirst();
        int left = stack.isEmpty() ? -1 : stack.peekFirst();
        maxArea = Math.max(maxArea, heights[top] * (i - left - 1));
      }
      stack.offerFirst(i);
    }
    return maxArea;
  }
}

/*
 * Time complexity: O(n)
 *
 * Space complexity: O(n)
 *
 * NOTES:
 *
 * 1. why i <= n?
 * When we go over all the bars but some bars are still in the stack, at time time i == n, we should continue to process the bars
 * in the stack, so we cannot terminate for-loop when i == n.
 *
 * 2. After we pop out the top and ready to calculate the area with top as its height, the stack becomes empty, so in
 * heights[top] * (i - stack.top - 1), stack.top will be unavailable, how to deal with this case?
 * Set it to -1. It means this rectangle can extend to the leftmost, i.e., bar 0. So heights[top] * (i - (-1) - 1) will get its area.
 *
 */
