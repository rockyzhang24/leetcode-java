// Link: https://leetcode.com/problems/permutations/

/*
 * DFS - use an extra boolean array for visited-checking
 * 1ms, 92.55%
 * For each position pos in nums, i.e., pos is [0, len-1], we try each number that has not been
 * used yet.
 * For example, nums=[1,2,3,4], at index 0, we can try 1, 2, 3, 4, say this time we try
 * 2 (1,3,4 remaining), then at index 1, we can try 1, 3, 4, and so on.
 */
class Solution {
  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> cur = new ArrayList<>();
    // The last parameter, a boolean array, is to record which elements has been used
    dfs(nums, 0, ans, cur, new boolean[nums.length]);
    return ans;
  }

  private void dfs(int[] nums, int pos, List<List<Integer>> ans, List<Integer> cur, boolean[] visited) {
    if (pos == nums.length) {
      ans.add(new ArrayList<>(cur));
      return;
    }
    // Try all the elements that are not used yet in this position
    for (int i = 0; i < nums.length; ++i) {
      if (!visited[i]) {
        visited[i] = true;
        cur.add(nums[i]);
        dfs(nums, pos + 1, ans, cur, visited);
        cur.remove(cur.size() - 1);
        visited[i] = false;
      }
    }
  }
}

/*
 * Time complexity: O(n*n!), n is the length of nums
 * First, we need to know how many nodes the recursion tree has. The recursion tree has n levels.
 * The root node has 1 node. The 1st level has n nodes (trying n numbers at index 1), the
 * 2nd level has n*(n-1) nodes (trying n-1 numbers at index 2), the 3rd level has n*(n-1)*(n-2)
 * nodes (trying n-2 numbers at index 3), and so on. The nth level has n*(n-1)*(n-2)*...*1 nodes.
 * So the total number of the nodes in the recursion tree is:
 *   1 + n + n*(n-1) + n*(n-1)*(n-2) + ... + n*(n-1)*(n-2)*..*2 + n*(n-1)*(n-2)*...*1
 * = n!/n! + n!/(n-1)! + ... +n!/2! + n!/1! + n!
 * = n!*(1/n! + 1/(n-1)! + ... + 1/2! + 1/1! + 1)
 *
 * Based on Taylor's theorem, 1 + 1/1! + 1/2! + ... + 1/n! = e
 * So the formula above is equal to n!*e, i.e., O(n!)
 *
 * In each non-leaf node, we have a for-loop which iterate over all the n numbers. Its time is O(n).
 * In each leaf node, we add the current result to the final answer by ans.add(new ArrayList<>(cur));
 * whose time is O(n) as well. So in each node, it take O(n).
 *
 * So the time complexity is O(n*n!).
 *
 * I also posted a detailed explanation here:
 * https://leetcode.com/problems/permutations/discuss/1308076/detailed-explanation-about-why-the-time-complexity-is-onn
 *
 * Space complexity: O(n)
 * The space for the recursion stack is O(n). The space for the visited array is O(n) as well. So
 * totally O(2n)=O(n)
 *
 * Notes:
 *
 */


/*
 * Method2: DFS - use swap to avoid visited-checking
 * 0ms, 100%
 * swap(pos, i) where i is [pos, n-1], it means we try different numbers at position pos.
 * For example, nums=[1, 2, 3]
 * When pos=0, we swap(0,0), swap(0,1), swap(0,2), i.e., we try 1, 2, 3 at position 0.
 * After this, we have finished position 0, next we will try position 1. After swap(0,0),
 * 2,3 are remaining for position 1, so swap(1,1) and swap(1,2) will fulfill this.
 */
class Solution {
  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    dfs(nums, 0, ans);
    return ans;
  }

  private void dfs(int[] nums, int pos, List<List<Integer>> ans) {
    if (pos == nums.length) {
      ans.add(arrayToList(nums));
      return;
    }
    // For each position pos in nums, i.e., pos is [0, len-1], we swap nums[pos] with each
    // number after it.
    for (int i = pos; i < nums.length; ++i) {
      swap(nums, pos, i);
      dfs(nums, pos + 1, ans);
      swap(nums, i, pos);
    }
  }

  private List<Integer> arrayToList(int[] array) {
    List<Integer> l = new ArrayList<>();
    for (int i : array) {
      l.add(i);
    }
    return l;
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
 * Time complexity: O(n!), n is the length of nums.
 * See https://leetcode.com/problems/permutations/discuss/1308076/detailed-explanation-about-why-the-time-complexity-is-onn
 * The recursion tree is the same with method-1. However, in each node, the number of execution of the for-loop is not
 * contant n, but n-pos which is decreasing level by level.
 *
 * Space complexity: O(n) for the recursion stack
 *
 * Notes:
 *
 */
