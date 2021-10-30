// Link: https://leetcode.com/problems/maximal-rectangle/discuss/29054/Share-my-DP-solution
// Difficulty: Hard

/*
 * Run Time: 19ms, 22.35%
 *
 * Explanation:
 * Based on the problem "85. Largest Rectangle in Histogram" and its method-2 using a stack.
 * Each row can be converted as the histogram problem. This row is the bottom of the histogram,
 * and the height of the bar is the number of continuous "1"s above the bottom. Using an array,
 * say height[], to record the height of each bar, and update it when scaning row by row.
 *
 */
class Solution {
  public int maximalRectangle(char[][] matrix) {
    if (matrix.length == 0 || matrix[0].length == 0) {
      return 0;
    }
    int rows = matrix.length;
    int cols = matrix[0].length;
    int[] heights = new int[cols];
    int maxArea = 0;
    for (int i = 0; i < rows; ++i) {
      Deque<Integer> stack = new ArrayDeque<>();
      for (int j = 0; j <= cols; ++j) {
        // Update the heights array
        if (j < cols) {
          heights[j] = matrix[i][j] == '1' ? heights[j] + 1 : 0;
        }

        // Solve the histogram problem
        while (!stack.isEmpty() && (j == cols || heights[j] < heights[stack.peekFirst()])) {
          int top = stack.pollFirst();
          int left = stack.isEmpty() ? -1 : stack.peekFirst();
          maxArea = Math.max(maxArea, heights[top] * (j - left - 1));
        }
        stack.offerFirst(j);
      }
    }
    return maxArea;
  }
}

/*
 * Time complexity: O(rows * cols)
 *
 * Space complexity: O(cols)
 *
 * NOTES:
 *
 */


/*
 * Method-2: DP
 *
 */
class Solution {
  public int maximalRectangle(char[][] matrix) {
    if (matrix.length == 0 || matrix[0].length == 0) {
      return 0;
    }
    int rows = matrix.length;
    int cols = matrix[0].length;
    int[] heights = new int[cols];
    int[] left = new int[cols];
    int[] right = new int[cols];
    for (int i = 0; i < rows; ++i) {
      // TODO
    }
  }
}

/*
 * Time complexity:
 *
 * Space complexity:
 *
 * NOTES:
 *
 */

