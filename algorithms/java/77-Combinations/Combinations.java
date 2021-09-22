// Link: https://leetcode.com/problems/combinations/
// Difficulty: Medium

/*
 * 3ms, 90.26%
 *
 * DFS recursion
 * - How many layers the recursion tree has: k (we have k positions to try)
 * - How man branches each node has: at most n (in each position, we have n choices)
 */
class Solution {
  public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> ans = new ArrayList<>();
    dfs(n, k, 0, 1, new ArrayList<>(), ans);
    return ans;
  }

  private void dfs(int n, int k, int pos, int num, List<Integer> cur, List<List<Integer>> ans) {
    if (pos == k) {
      ans.add(new ArrayList<>(cur));
      return;
    }
    for (int i = num; i <= n - (k - pos) + 1; ++i) {
      cur.add(i);
      dfs(n, k, pos + 1, i + 1, cur, ans);
      cur.remove(cur.size() - 1);
    }
  }
}

/*
 * Time complexity:
 *
 * Space complexity:
 *
 * NOTES:
 * Pruning: In the for-loop, using i <= n - (k - pos) + 1 instead of i <= n to save lots of time.
 * That means the start of searching has the upper bound. E.g. n=7, k=4, when i starts from 5, the
 * search will become meaningless, because the remaining numbers are not enough to make up 4 numbers.
 */
