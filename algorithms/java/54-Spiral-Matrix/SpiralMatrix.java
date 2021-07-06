// Link: https://leetcode.com/problems/spiral-matrix/

/*
 * Recursion
 * 0ms, 100%
 *
 * Layer by layer from outer to inner.
 *
 * For example,
 *
 * 1 2 3
 * 4 5 6
 * 7 8 9
 *
 * We use four for-loop to traverse each layer. E.g., for the first layer, the first for-loop for 1, 2;
 * the second for-loop for 3, 6; the third for-loop for 9, 8 and the last for-loop for 7, 4.
 */
class Solution {
  public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> ans = new ArrayList<>();
    spiral(0, matrix, matrix.length, matrix[0].length, ans);
    return ans;
  }

  private void spiral(int layer, int[][] matrix, int m, int n, List<Integer> ans) {
    // Base case: three cases
    if (m == 0 || n == 0) {
      // No more row or column left
      return;
    }
    if (m == 1) {
      // Just one row (the middile row) left, including the case where only one cell (i.e., the center cell) left
      for (int i = layer; i < layer + n; ++i) {
        ans.add(matrix[layer][i]);
      }
      return;
    }
    if (n == 1) {
      // Just one column (the middle column) left
      for (int i = layer; i < layer + m; ++i) {
        ans.add(matrix[i][layer]);
      }
      return;
    }
    // Traverse four edges of the current layer in spiral order
    // Up
    for (int i = layer; i < layer + n - 1; ++i) {
      ans.add(matrix[layer][i]);
    }
    // Right
    for (int i = layer; i < layer + m - 1; ++i) {
      ans.add(matrix[i][layer + n - 1]);
    }
    // Down
    for (int i = layer + n - 1; i >= layer + 1; --i) {
      ans.add(matrix[layer + m - 1][i]);
    }
    // Left
    for (int i = layer + m - 1; i >= layer + 1; --i) {
      ans.add(matrix[i][layer]);
    }
    // Deal with the next layer
    spiral(layer + 1, matrix, m - 2, n - 2, ans);
  }
}

/*
 * Time complexity: O(m*n), m is the number of rows and n is the number of columns
 *
 * Space complexity: O(min(m, n)) for the recursion stack
 *
 * Notes:
 *
 */
