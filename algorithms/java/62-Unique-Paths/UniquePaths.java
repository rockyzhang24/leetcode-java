// Link: https://leetcode.com/problems/unique-paths/

/*
 * DP
 * 0ms, 100%
 */
class Solution {
  public int uniquePaths(int m, int n) {
    // M[i][j] represents how many unique paths from grid[i][j] to grid[m-1][n-1]
    int[][] M = new int[m][n];
    M[m - 1][n - 1] = 1;
    // Induction rule: M[i][j] = M[i][j] + M[i][j+1] + M[i+1][j]
    for (int i = m - 1; i >= 0; --i) {
      for (int j = n - 1; j >= 0; --j) {
        if (j + 1 < n) {
          M[i][j] += M[i][j + 1];
        }
        if (i + 1 < m) {
          M[i][j] += M[i + 1][j];
        }
      }
    }
    return M[0][0];
  }
}

/*
 * Time complexity: O(m*n)
 *
 * Space complexity: O(m*n)
 *
 * Notes:
 *
 */


/*
 * Method-2: DP - optimize the space complexity to O(n)
 * 0ms, 100%
 *
 * In method-1, we use a 2D array, and M[i][j] depends on the cell below it, i.e., M[i+1][j] and
 * the cell right to it, i.e., M[i][j+1].
 * We can just use one row. M[i] = M[i] + M[i+1]
 */
class Solution {
  public int uniquePaths(int m, int n) {
    int[] M = new int[n];
    M[n - 1] = 1;
    for (int i = 0; i < m; ++i) {
      for (int j = n - 1; j >= 0; --j) {
        if (j + 1 < n) {
          M[j] = M[j] + M[j + 1];
        }
      }
    }
    return M[0];
  }
}

/*
 * Time complexity: O(m*n)
 *
 * Space complexity: O(n)
 *
 * NOTES:
 *
 */
