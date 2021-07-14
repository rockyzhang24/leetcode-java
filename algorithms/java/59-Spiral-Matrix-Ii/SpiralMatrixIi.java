// Link: https://leetcode.com/problems/spiral-matrix-ii/

// The idea to solve this problem is the same with 54-Spiral-Matrix

/*
 * Recursion
 * 0ms, 100%
 *
 * Generate it layer by layer, from outside to inside.
 */
class Solution {
  public int[][] generateMatrix(int n) {
    int[][] ans = new int[n][n];
    generate(0, n, 1, ans);
    return ans;
  }

  private void generate(int layer, int n, int num, int[][] ans) {
    // Base case: if n is even, at the end there will be nothing left, return
    // directly; if n is odd, one item that is at the center will be left.
    if (n == 0) {
      return;
    }
    if (n == 1) {
      ans[layer][layer] = num;
      return;
    }
    // Generate each layer in the order of top edge, right edge, bottom edge, left edge
    for (int i = layer; i < layer + n - 1; ++i) {
      ans[layer][i] = num++;
    }
    for (int i = layer; i < layer + n - 1; ++i) {
      ans[i][layer + n - 1] = num++;
    }
    for (int i = layer + n - 1; i >= layer + 1; --i) {
      ans[layer + n - 1][i] = num++;
    }
    for (int i = layer + n - 1; i >= layer + 1; --i) {
      ans[i][layer] = num++;
    }
    generate(layer + 1, n - 2, num, ans);
  }
}

/*
 * Time complexity: O(n^2)
 *
 * Space complexity: O(n) for the recursion stack
 *
 * Notes:
 *
 */
