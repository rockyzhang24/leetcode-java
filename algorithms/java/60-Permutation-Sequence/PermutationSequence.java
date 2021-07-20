// Link: https://leetcode.com/problems/permutation-sequence/

/*
 * Method-1: DFS
 * 623ms, 9.38%
 *
 * Solve it like method-1 of 46-Permutations (../46-Permutations/Permutations.java).
 * Observed that the generated permutations are in order, so once we get the kth permutation, we stop the recursion.
 */
class Solution {
  private boolean solved;
  int k;

  public String getPermutation(int n, int k) {
    this.k = k;
    StringBuilder ans = new StringBuilder();
    dfs(n, 0, new boolean[n + 1], ans);
    return ans.toString();
  }

  private void dfs(int n, int pos, boolean[] visited, StringBuilder sb) {
    if (pos == n) {
      k--;
      if (k == 0) {
        solved = true;
      }
      return;
    }
    for (int i = 1; i <= n; ++i) {
      if (visited[i]) {
        continue;
      }
      visited[i] = true;
      sb.append(i);
      dfs(n, pos + 1, visited, sb);
      if (solved) {
        return;
      }
      sb.deleteCharAt(sb.length() - 1);
      visited[i] = false;
    }
  }
}

/*
 * Time complexity: O(n*n!)
 *
 * Space complexity: O(n)
 *
 * Notes:
 *
 */


/*
 * Method-2: DFS - use swap
 * 326ms, 11.39%
 *
 * Idea is like method-2 of 46-Permutations (../46-Permutations/Permutations.java).
 *
 * Furthermore, before swap, we need to sort numbers so that the generated permutations will be in order.
 * Then when kth permutation is generated, we stop. When the recursion goes back to the upper level, we
 * need to restore the partially sorted array. To implement it, before sorting we backup the array and
 * restore it before going back.
 */
class Solution {
  int k;
  boolean solved;

  public String getPermutation(int n, int k) {
    this.k = k;
    // Initialize an array containing number from 1 to n
    int[] nums = new int[n];
    int num = 1;
    for (int i = 0; i < nums.length; ++i) {
      nums[i] = num++;
    }
    dfs(nums, 0);
    // Now nums contains the kth permuation. We convert it to a string
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < nums.length; ++i) {
      sb.append(nums[i]);
    }
    return sb.toString();
  }

  private void dfs(int[] nums, int pos) {
    if (pos == nums.length) {
      k--;
      if (k == 0) {
        solved = true;
      }
      return;
    }
    // Backup the array
    int[] backup = Arrays.copyOf(nums, nums.length);
    // Sort
    Arrays.sort(nums, pos, nums.length);
    for (int i = pos; i < nums.length; ++i) {
      swap(nums, pos, i);
      dfs(nums, pos + 1);
      if (solved) {
        return;
      }
      swap(nums, pos, i);
    }
    // Restore the array
    for (int i = 0; i < nums.length; ++i) {
      nums[i] = backup[i];
    }
  }

  private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }
}

/*
 * Time complexity: O(n*n!)
 * The recursion tree has totally n! nodes. In each node, it takes O(n), e.g., array copy and array retore.
 *
 * Space complexity: O(n^2)
 * The recursion stack needs O(n) space. Along the recursion path, each node needs extra O(n) for the backup array
 *
 * Notes:
 *
 */


/*
 * Method-3: factorial and count
 * 1ms, 98.25%
 *
 * For example, n=4, k=14, nums will be [1, 2, 3, 4], all the permutation can be listed as four groups based on the first number
 * 1, permutation(2, 3, 4)
 * 2, permutation(1, 3, 4)
 * 3, permutation(1, 2, 4)
 * 4, permutation(1, 2, 3)
 * Each has 3!, i.e., (n-1)! permutations. k/(n-1)! gives us which group the result resides. The count should start from 0, so k=14
 * we should k-- first. In this example, 3!=6, if we want the 14th one, 13/6=2, so the result resides in the 3rd group, i.e., 3, permutation(1, 2, 4).
 * Next, we adjust k to k=k%6, and then repeat the process above, i.e.,
 * 1, permutation(2, 4)
 * 2, permutation(1, 4)
 * 4, permutation(1, 2)
 * k/(n-2)!=1/1!=1, so the result resides in the second group.
 * So far, the first two number has been decided, they are 3 and 2. Next we continue it until all n positions are decided.
 */
class Solution {
  public String getPermutation(int n, int k) {
    List<Integer> nums = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    int factorial = 1;
    // Calculate n! and build an array containing [1, 2, ..., n]
    for (int i = 1; i <= n; ++i) {
      factorial *= i;  // n!
      nums.add(i);
    }
    // The sequence starts from 0
    k--;
    for (int i = 0; i < n; ++i) {
      factorial /= (n - i);
      int index = k / factorial;
      sb.append(nums.get(index));
      nums.remove(index);
      k = k % factorial;
    }
    return sb.toString();
  }
}

/*
 * Time complexity: O(n^2)
 * remove() takes O(n) time
 *
 * Space complexity: O(n)
 *
 * Notes:
 *
 */
