// Link: https://leetcode.com/problems/sudoku-solver/

// DFS
// 3ms, 95.86%
class Solution {
  public void solveSudoku(char[][] board) {
    // bit vectors for each row, each col, and each box
    short[] rows = new short[9];
    short[] cols = new short[9];
    short[] boxes = new short[9];
    // a boolean variable to mark whether the puzzle has been solved
    boolean[] isSolved = new boolean[1];
    // preprocessing: scan the whole board, for each filled cell, set 1 at the corresponding
    // bit in the bit vectors
    for (int i = 0; i < board.length; ++i) {
      for (int j = 0; j < board[0].length; ++j) {
        char cur = board[i][j];
        if (cur != '.') {
          int temp = 1 << (cur - '1');
          rows[i] |= temp;
          cols[j] |= temp;
          boxes[i / 3 * 3 + j / 3] |= temp;
        }
      }
    }
    solve(board, rows, cols, boxes, 0, 0, isSolved);
  }

  private void solve(char[][] board, short[] rows, short[] cols, short[] boxes, int row, int col, boolean[] isSolved) {
    if (row == board.length - 1 && col == board[0].length) {
      isSolved[0] = true;
      return;
    }
    // adjust the row and column
    if (col == board[0].length) {
      row += 1;
      col = 0;
    }
    if (board[row][col] == '.') {
      // try 1 to 9 for each cell
      for (int i = 1; i <= 9; ++i) {
        // check whether this number is valid
        int temp = 1 << (i - 1);
        int boxNum = row / 3 * 3 + col / 3;
        if ((rows[row] & temp) == 0 && (cols[col] & temp) == 0 && (boxes[boxNum] & temp) == 0) {
          // fill this number in the cell
          board[row][col] = (char) (i + '0');
          // set the corresponding bit to 1 for marking this number is used
          rows[row] |= temp;
          cols[col] |= temp;
          boxes[boxNum] |= temp;
          // try next cell
          solve(board, rows, cols, boxes, row, col + 1, isSolved);
          // if the puzzle is solved, directly return
          if (isSolved[0]) {
            return;
          }
          // recover the corresponding bit to 0 in order to try the next number
          rows[row] ^= temp;
          cols[col] ^= temp;
          boxes[boxNum] ^= temp;
        }
      }
      // recover the cell back to empty
      board[row][col] = '.';
    } else {
      solve (board, rows, cols, boxes, row, col + 1, isSolved);
    }
  }
}

/*
 * Time complexity: O(9^(9*9)) = O(9^81)
 *
 * Space complexity: O(81)
 *
 * Notes:
 * - We have 81 position to try -> the height of the recursion tree is 81
 * - We have 9 choices on each position -> each node of the recursion tree has 9 branches
 */
