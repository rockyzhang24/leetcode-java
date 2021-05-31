// Link: https://leetcode.com/problems/generate-parentheses/

// 1ms, 68.70%
class Solution {
  public List<String> generateParenthesis(int n) {
    List<String> ans = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    helper(n, 0, 0, sb, ans);
    return ans;
  }

  private void helper(int n, int lnum, int rnum, StringBuilder cur, List<String> ans) {
    if (lnum + rnum == 2 * n) {
      ans.add(cur.toString());
      return;
    }
    // try left parenthesis
    if (lnum < n) {
      cur.append("(");
      helper(n, lnum + 1, rnum, cur, ans);
      cur.deleteCharAt(cur.length() - 1);
    }
    // try right parenthesis
    if (rnum < lnum) {
      cur.append(")");
      helper(n, lnum, rnum + 1, cur, ans);
      cur.deleteCharAt(cur.length() - 1);
    }
  }
}

/*
 * Time complexity: O(4^n)
 * The recursion tree height is 2*n and each node has at most 2 branches. So the number
 * of nodes is 2^(2n) = 4^n.
 * This time complexity is not that tight, because it is impossible that every node has
 * two branches (i.e., we can only try left or right parenthesis on some nodes). However
 * I don't know how to get a tighter one.
 *
 * Space complexity: O(n)
 *
 * Notes:
 * Combination question. We use DFS to solve it.
 */
