// Link: https://leetcode.com/problems/n-queens/

/*
 * DFS
 * 1ms, 99.58%
 *
 * At each row we put a queen, we have n choices. When we try each choice, we should perform
 * three checking: whether the column, 45 degree diagonal or 135 diagonal has a queen.
 *
 * So the recursion tree has n levels (one level is for one row), and each node has n branches
 * (i.e., at each row, we have n choices to place the queen).
 *
 * For the checking, we need three arrays.
 * 1. col array, size n, col[i] is whether column i has a queen or not.
 * 2. diagonal array, size 2*n-1.
 *    For the cells (x,y) at the same 45-degree diagonal, x+y are the same. The range of x+y is [0, n-1].
 *    So diagonal[i] is whether the cells whose x+y=i have a queen.
 * 3. antiDiagonal array, size 2*n-1.
 *    For the cells (x,y) at the same 135-degree diagonal, x-y are the same, The ranger of x-y is [-(n-1), n-1].
 *    To make it start from 0, we use x-y+(n-1) whose range becomes to [0, 2n-2].
 *    So antiDiagonal[i] is whether the cells whose x-y+(n-1)=i have a queen.
 */
class Solution {
  public List<List<String>> solveNQueens(int n) {
    // Three arrays for checking the three directions respectively
    // column
    int[] col = new int[n];
    // 45 degree
    int[] diagonal = new int[2 * n - 1];
    // 135 degree
    int[] antiDiagonal = new int[2 * n - 1];
    List<List<String>> ans = new ArrayList<>();
    // Store the position (index) of the queeen in each row
    List<Integer> pos = new ArrayList<>();
    dfs(col, diagonal, antiDiagonal, n, 0, pos, ans);
    return ans;
  }

  private void dfs(int[] col, int[] diagonal, int[] antiDiagonal, int n, int row, List<Integer> pos, List<List<String>> ans) {
    if (row == n) {
      // Find a solution, build the result and add it to the answer.
      // pos[i] stores the index where the queen should be at row i. For each row, we create a char array and fill it by '.' and
      // put 'Q' at index pos[i]. Then convert the char array to a string which is the solution for the row.
      List<String> solu = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        char[] temp = new char[n];
        Arrays.fill(temp, '.');
        temp[pos.get(i)] = 'Q';
        solu.add(new String(temp));
      }
      ans.add(solu);
    }
    // Try each choice in the row
    for (int i = 0; i < n; ++i) {
      if (col[i] == 1 || diagonal[row + i] == 1 || antiDiagonal[row - i + n - 1] == 1) {
        continue;
      }
      // When find a valid position, mark the three arrays and dfs for the next row.
      col[i] = 1;
      diagonal[row + i] = 1;
      antiDiagonal[row - i + n - 1] = 1;
      pos.add(i);
      dfs(col, diagonal, antiDiagonal, n, row + 1, pos, ans);
      // Restore
      pos.remove(pos.size() - 1);
      col[i] = 0;
      diagonal[row + i] = 0;
      antiDiagonal[row - i + n - 1] = 0;
    }
  }
}

/*
 * Time complexity:
 *
 * Space complexity:
 *
 * Notes:
 *
 */
