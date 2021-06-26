// Link: https://leetcode.com/problems/combination-sum-ii/

// DFS
// 1ms, 100%
class Solution {
  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    // Sort first for de-duplicating
    Arrays.sort(candidates);
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> cur = new ArrayList<>();
    dfs(candidates, target, 0, ans, cur);
    return ans;
  }

  private void dfs(int[] candidates, int target, int startIdx, List<List<Integer>> ans, List<Integer> cur) {
    if (target == 0) {
      ans.add(new ArrayList<>(cur));
      return;
    }
    for (int i = startIdx; i < candidates.length; ++i) {
      if (candidates[i] <= target) {
        cur.add(candidates[i]);
        dfs(candidates, target - candidates[i], i + 1, ans, cur);
        cur.remove(cur.size() - 1);
        // skip the duplicates
        for (; i + 1 < candidates.length && candidates[i] == candidates[i + 1]; ++i);
      } else {
        return;
      }
    }
  }
}

/*
 * Time complexity:
 *
 * Space complexity:
 *
 * Notes:
 * Sort the array so that we can skip the duplicates easily.
 */
