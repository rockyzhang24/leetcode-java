// Link: https://leetcode.com/problems/unique-paths-ii/

// This problem is the follow up of 62-Unique-Paths (../62-Unique-Paths/UniquePaths.java)

/*
 * DP
 * 0ms, 100%
 */
class Solution {
  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int m = obstacleGrid.length;
    int n = obstacleGrid[0].length;
    // Corner case: if the goal cell has obstacle, no valid paths in this grid, return 0 directly
    if (obstacleGrid[m - 1][n - 1] == 1) {
      return 0;
    }
    // M[i][j] represents how many unique paths from grid[i][j] to the goal
    int[][] M = new int[m][n];
    M[m - 1][n - 1] = 1;
    // Induction rule:
    // M[i][j] = 0 if grid[i][j] has obstacle
    //         = M[i+1][j] + M[i][j+1] otherwise
    for (int i = m - 1; i >= 0; --i) {
      for (int j = n - 1; j >= 0; --j) {
        if (obstacleGrid[i][j] == 1) {
          continue;
        }
        if (i + 1 < m) {
          M[i][j] += M[i + 1][j];
        }
        if (j + 1 < n) {
          M[i][j] += M[i][j + 1];
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
 * The 'Final' cell may have obstacle as well. If so, there will be no paths for this grid, 0 should be
 * returned.
 */


/*
 * Method-2: DP - optimize the space complexity to 1D array
 * 0ms, 100%
 */
class Solution {
  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int m = obstacleGrid.length;
    int n = obstacleGrid[0].length;
    if (obstacleGrid[m - 1][n - 1] == 1) {
      return 0;
    }
    int[] M = new int[n];
    M[n - 1] = 1;
    for (int i = m - 1; i >= 0; --i) {
      for (int j = n - 1; j >= 0; --j) {
        if (obstacleGrid[i][j] == 1) {
          M[j] = 0;
        } else if (j + 1 < n) {
          M[j] += M[j + 1];
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
