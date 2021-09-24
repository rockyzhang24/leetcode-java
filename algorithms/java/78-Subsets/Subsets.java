// Link: https://leetcode.com/problems/subsets/
// Difficulty: Medium

/*
 * 0ms, 100%
 *
 * DFS
 * - How many layers the recursion tree has: n (which is the length of nums)
 * - How mamy branches each node has: 2
 * i.e., we have n positions to try, and in each position, we have two choices: whetehr this number is in
 * the subset or not.
 */
class Solution {
  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    dfs(nums, 0, new ArrayList<>(), ans);
    return ans;
  }

  private void dfs(int[] nums, int pos, List<Integer> cur, List<List<Integer>> ans) {
    if (pos == nums.length) {
      ans.add(new ArrayList<>(cur));
      return;
    }
    // Add the current number into the subset
    cur.add(nums[pos]);
    dfs(nums, pos + 1, cur, ans);
    cur.remove(cur.size() - 1);

    // Not add the current number into the subset
    dfs(nums, pos + 1, cur, ans);
  }
}

/*
 * Time complexity: O(2^n)
 *
 * Space complexity: O(n)
 *
 * NOTES:
 *
 */
