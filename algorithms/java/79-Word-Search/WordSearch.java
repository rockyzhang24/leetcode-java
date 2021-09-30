// Link: https://leetcode.com/problems/word-search/
// Difficulty: Medium

/*
 * Graph traversal - DFS
 * 142ms, 41.24%
 */
class Solution {

  // Directions:                            left    right     up      down
  final static int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

  public boolean exist(char[][] board, String word) {
    int m = board.length;
    int n = board[0].length;
    // For each cell that matches the first char of word, run dfs on it.
    for (int i = 0; i < m; ++i) {
      for (int j = 0; j < n; ++j) {
        if (board[i][j] == word.charAt(0) && dfs(board, m, n, new boolean[m][n], i, j, 0, word)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean dfs(char[][] board, int m, int n, boolean[][] visited, int row, int col, int pos, String word) {
    if (pos == word.length() - 1 && board[row][col] == word.charAt(pos)) {
      return true;
    }
    if (board[row][col] != word.charAt(pos)) {
      return false;
    }
    visited[row][col] = true;
    // Check its four neighbor cells
    for (int[] dir : dirs) {
      int neiR = row + dir[0];
      int neiC = col + dir[1];
      if (neiR >= 0 && neiR < m && neiC >= 0 && neiC < n
          && !visited[neiR][neiC]
          && dfs(board, m, n, visited, neiR, neiC, pos + 1, word)) {
        return true;
      }
    }
    visited[row][col] = false;
    return false;
  }
}

/*
 * Time complexity: O(m*n*(4^l)) where l is the length of word
 *
 * Space complexity: O(l + m*n)
 *
 * NOTES:
 *
 */
