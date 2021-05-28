// Link: https://leetcode.com/problems/letter-combinations-of-a-phone-number/

// 0ms, 100%
class Solution {

  private final static String[] mappings = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

  public List<String> letterCombinations(String digits) {
    // sanity check
    if (digits == null || digits.length() ==0) {
      return new ArrayList<>();
    }
    List<String> ans = new ArrayList<>();
    StringBuilder cur = new StringBuilder();
    combinations(digits, 0, cur, ans);
    return ans;
  }

  private void combinations(String digits, int idx, StringBuilder cur, List<String> ans) {
    if (idx == digits.length()) {
      ans.add(cur.toString());
      return;
    }
    for (int i = 0; i < mappings[digits.charAt(idx) - '0'].length(); ++i) {
      cur.append(mappings[digits.charAt(idx) - '0'].charAt(i));
      combinations(digits, idx + 1, cur, ans);
      cur.deleteCharAt(cur.length() - 1);
    }
  }
}

/*
 * Time complexity: O(4^n)
 * This is the total number of nodes in the recursion tree. In the worst case, this tree has 4 branches
 * on each node and the tree height is n.
 *
 * Space complexity: O(n), n is the lenght of the input string
 * It is the height of the recursion tree.
 *
 * Notes:
 * Very classic combination question. We use DFS (recursion) to solve this.
 * - The height of the recursion tree (i.e., the number of positions to try): n
 * - How many branches of each node (i.e., how many choices at each position): the number of letters that number can represent.
 *   So it is 3 or 4
 */
