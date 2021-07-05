// Link: https://leetcode.com/problems/n-queens-ii/

/*
 * DFS
 * 0ms, 100%
 *
 * The same idea of 51-N-Queens and this problem is easier. We don't need to store
 * the solutions and instead just return the number of solutions
 */
class Solution {
  public int totalNQueens(int n) {
    // Three arrays used to check the three directions: column, 45-degree diagonal, and 135-degree diagonal
    int[] col = new int[n];
    int[] diagonal = new int[2 * n - 1];
    int[] antiDiagonal = new int[2 * n - 1];
    int[] ans = new int[1];
    dfs(col, diagonal, antiDiagonal, ans, 0, n);
    return ans[0];
  }

  private void dfs(int[] col, int[] diagonal, int[] antiDiagonal, int[] ans, int row, int n) {
    if (row == n) {
      ans[0]++;
      return;
    }
    for (int i = 0; i < n; ++i) {
      if (col[i] == 1 || diagonal[row + i] == 1 || antiDiagonal[row - i + n - 1] == 1) {
        continue;
      }
      // Mark
      col[i] = 1;
      diagonal[row + i] = 1;
      antiDiagonal[row - i + n - 1] = 1;
      dfs(col, diagonal, antiDiagonal, ans, row + 1, n);
      // Restore
      col[i] = 0;
      diagonal[row + i] = 0;
      antiDiagonal[row - i + n - 1] = 0;
    }
  }
}

/*
 * Time complexity:
 *
 * Space complexity: O(n)
 *
 * Notes:
 *
 */
