// Link: https://leetcode.com/problems/set-matrix-zeroes/
// Difficulty: Medium

/*
 * 0ms, 100%
 *
 * Step-1: Use the first row to record the column status, i.e, which columns should be set to 0,
 * and use the first column to record the row status, i.e., which rows should be set to 0.
 * If matrix[i][j] is 0, mark matrix[0][j] and matrix[i][0] to 0.
 *
 * Step-2: Then we just need to traverse the first row and the first column, and set the
 * corresponding rows and columns to 0.
 *
 * However, matrix[0][0] is special. The statuses of the first row and first column is recorded
 * in this same cell. If it is 0, we need to know whether the whole first row should be set to 0,
 * or the wholo first column should be set to 0, or both.
 *
 * So we only use it to record the status of the first row, i.e., if there is a 0 in the first row,
 * we set it to 0. We use another boolean variable to record the status of the first column, i.e.,
 * if there is a 0 in the first column, we set the boolean variable to true.
 *
 * Furthermore, in step-2, matrix[0][0] should be processed at the end.
 */
class Solution {
  public void setZeroes(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    boolean isFirstColZero = false;
    for (int i = 0; i < m; ++i) {
      if (matrix[i][0] == 0) {
        isFirstColZero = true;
      }
      for (int j = 1; j < n; ++j) {
        if (matrix[i][j] == 0) {
          matrix[i][0] = 0;
          matrix[0][j] = 0;
        }
      }
    }
    // Traverse the first column and set the corresponding rows to 0
    for (int i = 1; i < m; ++i) {
      if (matrix[i][0] == 0) {
        for (int j = 1; j < n; ++j) {
          matrix[i][j] = 0;
        }
      }
    }
    // Traverse the first row and set the corresponding columns to 0
    for (int j = 1; j < n; ++j) {
      if (matrix[0][j] == 0) {
        for (int i = 1; i < m; ++i) {
          matrix[i][j] = 0;
        }
      }
    }
    // Process matrix[0][0]
    if (matrix[0][0] == 0) {
      for (int j = 0; j < n; ++j) {
        matrix[0][j] = 0;
      }
    }
    if (isFirstColZero) {
      for (int i = 0; i < m; ++i) {
        matrix[i][0] = 0;
      }
    }
  }
}

/*
 * Time complexity: O(m * n)
 *
 * Space complexity: O(1)
 *
 * NOTES:
 *
 */
