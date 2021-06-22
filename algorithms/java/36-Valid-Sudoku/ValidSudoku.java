// Link: https://leetcode.com/problems/valid-sudoku/

// 1ms, 100%
class Solution {
  public boolean isValidSudoku(char[][] board) {
    for (int i = 0; i < 9; ++i) {
      int[] row = new int[9];
      int[] col = new int[9];
      int[] box = new int[9];
      for (int j = 0; j < 9; ++j) {
        char rowCur = board[i][j];
        char colCur = board[j][i];
        char boxCur = board[i / 3 * 3 + j / 3][i % 3 * 3 + j % 3];
        if (rowCur != '.' && row[rowCur - '1'] == 1
            || colCur != '.' && col[colCur - '1'] == 1
            || boxCur != '.' && box[boxCur - '1'] == 1) {
          return false;
        }
        if (rowCur != '.') {
          row[rowCur - '1'] = 1;
        }
        if (colCur != '.') {
          col[colCur - '1'] = 1;
        }
        if (boxCur != '.') {
          box[boxCur - '1'] = 1;
        }
      }
    }
    return true;
  }
}

/*
 * Time complexity: O(n^2), n is the edge length of the board
 *
 * Space complexity: O(n)
 *
 * Notes:
 * for i = 0 to n
 *   for j = 0 to n
 *     check each element j in row i
 *     check each element j in col i
 *     check each element j in box i
 *
 * For the boxes, we have 9 boxes and number them as below
 * 0 1 2
 * 3 4 5
 * 7 8 9
 *
 * In each box i, we number each element j as below
 * 0 1 2
 * 3 4 5
 * 7 8 9
 *
 * So how to correspond the element j in box i with the element in board?
 * board[i / 3 * 3 + j / 3][i % 3 * 3 + j % 3]
 *
 * For example:
 * For the element 4 in box 3, i.e., i = 3, j = 4, it is at row_4, the col_1 in the board.
 * i / 3 * 3 + j / 3 = 3 / 3 * 3 + 4 / 3 = 4
 * i % 3 * 3 + j % 3 = 3 % 3 * 3 + 4 % 3 = 1
 */
