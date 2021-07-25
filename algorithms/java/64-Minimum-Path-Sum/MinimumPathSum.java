// Link: https://leetcode.com/problems/minimum-path-sum/

// Idea is the same with 62-Unique-Paths (../62-Unique-Paths/UniquePaths.java)

/*
 * Method-1: DP using 2D array
 * 2ms, 81.95%
 */
class Solution {
  public int minPathSum(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    // M{i][j] represents the minimum sum along the path from grid[i][j] to bottom right
    int[][] M = new int[m][n];
    M[m - 1][n - 1] = grid[m - 1][n - 1];
    for (int i = m - 1; i >= 0; --i) {
      for (int j = n - 1; j >= 0; --j) {
        if (i + 1 < m && j + 1 < n) {
          M[i][j] = grid[i][j] + Math.min(M[i + 1][j], M[i][j + 1]);
        } else if (i + 1 < m) {
          M[i][j] = grid[i][j] + M[i + 1][j];
        } else if (j + 1 < n) {
          M[i][j] = grid[i][j] + M[i][j + 1];
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
 * NOTES:
 * To avoid too many if-else, when fill M, we can fill the rightmost column and the bottommost row
 *
 *   // Fill the rightmost column
 *   for (int i = m - 2; i >= 0; --i) {
 *     M[i][n - 1] = grid[i][n - 1] + M[i + 1][n - 1];
 *   }
 *   // Fill the bottommost row
 *   for (int j = n - 2; j >= 0; --j) {
 *     M[m - 1][j] = grid[m - 1][j] + M[m - 1][j + 1];
 *   }
 *   // Fill other cells
 *   for (int i = m - 2; i >= 0; --i) {
 *     for (int j = n - 2; j >= 0; --j) {
 *       M[i][j] = grid[i][j] + Math.min(M[i + 1][j], M[i][j + 1]);
 *     }
 *   }
 *
 */


/*
 * Method-2: DP - optimize the space to 1D array
 * 2ms, 81.95%
 */
class Solution {
  public int minPathSum(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    int[] M = new int[n];
    M[n - 1] = grid[m - 1][n - 1];
    for (int i = m - 1; i >= 0; --i) {
      for (int j = n - 1; j >= 0; --j) {
        if (i + 1 < m && j + 1 < n) {
          M[j] = grid[i][j] + Math.min(M[j], M[j + 1]);
        } else if (i + 1 < m) {
          M[j] += grid[i][j];
        } else if (j + 1 < n) {
          M[j] = grid[i][j] + M[j + 1];
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
