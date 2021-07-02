// Link: https://leetcode.com/problems/permutations-ii/

// This problem is the follow-up the last question 46-Permutations. The ideas to solve this
// problem are the same.

/*
 * DFS
 * 1ms, 99.35%
 * Sort the array first so that we can easily de-duplicate, i.e., all duplicates are
 * placed continuously so we can skip them.
 * At each position [0, n-1], we try each unique number, i.e., if duplicate is met,
 * we skip all the duplicates.
 */
class Solution {
  public List<List<Integer>> permuteUnique(int[] nums) {
    // Sort the array first for de-duplicating easily
    Arrays.sort(nums);
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> cur = new ArrayList<>();
    dfs(nums, 0, new boolean[nums.length], ans, cur);
    return ans;
  }

  private void dfs(int[] nums, int pos, boolean[] visited, List<List<Integer>> ans, List<Integer> cur) {
    if (pos == nums.length) {
      ans.add(new ArrayList<>(cur));
      return;
    }
    for (int i = 0; i < nums.length; ++i) {
      if (visited[i]) {
        continue;
      }
      cur.add(nums[i]);
      visited[i] = true;
      dfs(nums, pos + 1, visited, ans, cur);
      cur.remove(cur.size() - 1);
      visited[i] = false;
      // Skip the duplicates before we try the next number at the current position
      for (; i + 1 < nums.length && nums[i] == nums[i + 1]; ++i);
    }
  }
}

/*
 * Time complexity: O(n*n!), n is the length of nums.
 * The worst case is all numbers are unique. The time complexity is the same as the last question,
 * 46-Permutations.
 *
 * Space complexity: O(n) for the recursion stack and the visited array.
 *
 * Notes:
 *
 */


/*
 * Method2: in-place using swap
 * 1ms, 99.35%
 * In order to de-duplicate, we use a hashset at each node. When swap(pos, i), i.e., at positions pos
 * we try different numbers nums[i], make sure nums[i] is unique.
 *
 * In this method, we cannot sort the array for de-duplicating because swap operation will disrupt
 * the already arranged order.
 */
class Solution {
  public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    dfs(nums, 0, ans);
    return ans;
  }

  private void dfs(int[] nums, int pos, List<List<Integer>> ans) {
    if (pos == nums.length - 1) {
      ans.add(arrayToList(nums));
      return;
    }
    // Use a hashset for de-duplicating
    Set<Integer> set = new HashSet<>();
    for (int i = pos; i < nums.length; ++i) {
      if (set.add(nums[i])) {
        swap(nums, pos, i);
        dfs(nums, pos + 1, ans);
        swap(nums, i, pos);
      }
    }
  }

  private List<Integer> arrayToList(int[] arr) {
    List<Integer> list = new ArrayList<>();
    for (int i : arr) {
      list.add(i);
    }
    return list;
  }

  private void swap(int[] nums, int i, int j) {
    if (i == j) {
      return;
    }
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }
}

/*
 * Time complexity: O(n*n!)
 * The worst case is all numbers are unique. The time complexity is the same as 46-Permutations.
 *
 * Space complexity: O(n!)
 * At each node we use a hashset. At level 0 which only have 1 node, the size of the hashset is n;
 * at level 1 which has n nodes, the size of the hashset of each node is n-1, so totally n(n-1); at
 * level 2 which has n(n-1) nodes, the size of the hashset of each node is n-2, so totally n(n-1)(n-2),
 * and so on. At the last second level, there are n(n-1)(n-2)...3 nodes and the size of the hashset of
 * each node is 2, so total size is n(n-1)(n-2)...3*2. At the last level, we add results so no hashset
 * is needed. We sum them up and get O(n!).
 *
 * Notes:
 *
 */
