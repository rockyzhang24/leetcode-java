// Link: https://leetcode.com/problems/combination-sum/

// DFS
// 4ms, 52.65%
class Solution {
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> cur = new ArrayList<>();
    dfs(candidates, target, 0, ans,cur);
    return ans;
  }

  private void dfs (int[] candidates, int target, int startIdx, List<List<Integer>> ans, List<Integer> cur) {
    if (target == 0) {
      ans.add(new ArrayList<>(cur));
      return;
    }
    if (target < 0) {
      return;
    }
    for (int i = startIdx; i < candidates.length; ++i) {
      cur.add(candidates[i]);
      dfs(candidates, target - candidates[i], i, ans, cur);
      cur.remove(cur.size() - 1);
    }
  }
}

/*
 * Time complexity: O(N^target), N is the length of candidates
 * This is a very loose upper bound. In the worst case, the smallest number in candidates is 1, so the height
 * of the recursion tree is the value of target. The fan-out of each node is upper bounded by the length of
 * candidates, say N (actually, the fan-out of the nodes are decreasing from left to right). So the total
 * number of nodes in the recursion tree is O(N^target).
 *
 * Space complexity: O(target) for the recursion stack
 *
 * Notes:
 * How to avoid duplicate combinations?
 * a_1 + a_2 + a_3 + ... + a_n = target. If a_1 is candidates[0], a_2 is candidates[4], when enumerate a_3, we
 * start from candidates[4] instead of candidates[0].
 */


// Method2: Improved way
// 2ms, 98.4%
// We sort the candidates array first so that we can terminate the recursion earier. If
// candidates[i] is larger than the target remaining, no need to continue to try all
// the other elements after i.
class Solution {
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
        dfs(candidates, target - candidates[i], i, ans ,cur);
        cur.remove(cur.size() - 1);
      } else {
        return;
      }
    }
  }
}

/*
 * Time complexity: O(N^target)
 * The same with the first method. It's a very loose upper bound. But we have a good pruning in this method so the
 * actual runtime is less.
 *
 * Space complexity: O(target)
 *
 * Notes:
 * Sort the array for pruning.
 */
