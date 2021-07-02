// Link: https://leetcode.com/problems/rotate-image/solution/

/*
 * Method1: rotate in group of four
 * 0ms, 100%
 * Circle by circle for outside to inside, for each circle, rotate in group of four cells
 * For example:
 *
 * 1   2   3   4
 * 5   6   7   8
 * 9   10  11  12
 * 13  14  15  16
 *
 * For the first circle:
 * 1 -> temp, 13 -> 1, 16 -> 13, 4 -> 16, temp -> 4
 * 2 -> temp, 9 -> 2, 15 -> 9, 8 -> 15, temp -> 8
 * 3 -> temp, 5 -> 3, 14 -> 5, 12 -> 14, temp -> 12
 * The first circle is finished, then we move to the inner circle, i.e., 6,7,11,10
 *
 */
class Solution {
  public void rotate(int[][] matrix) {
    int n = matrix.length;
    for (int i = 0; i < n / 2; ++i) {
      for (int j = i; j < n - i - 1; ++j) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[n - 1 - j][i];
        matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
        matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
        matrix[j][n - 1 - i] = temp;
      }
    }
  }
}

/*
 * Time complexity: O(n^2), n is the edge length of the matrix and thus n^2 the total number of cells in the matrix
 *
 * Space complexity: O(1)
 *
 * Notes:
 *
 */

/*
 * Method2: Two flips, first flip it diagonally and then flip it horizontally.
 * 0ms, 100%
 * For example,
 * 1 2 3      1 4 7      7 4 1
 * 4 5 6  ->  2 5 8  ->  8 5 2
 * 7 8 9      3 6 9      9 6 3
 */
class Solution {
  public void rotate(int[][] matrix) {
    int n = matrix.length;
    // Flip it diagonally
    for (int i = 0; i < n; ++i) {
      for (int j = i; j < n; ++j) {
        swap(matrix, i, j, j, i);
      }
    }
    // Flip it horizontally
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n / 2; ++j) {
        swap(matrix, i, j, i, n - 1 - j);
      }
    }
  }

  private void swap(int[][] matrix, int i, int j, int m, int n) {
    int temp = matrix[i][j];
    matrix[i][j] = matrix[m][n];
    matrix[m][n] = temp;
  }
}

/*
 * Time complexity: O(n^2), n is the edge length of the matrix and thus n^2 is the total number of cells the matrix has
 *
 * Space complexity: O(1)
 *
 * Notes:
 *
 */
