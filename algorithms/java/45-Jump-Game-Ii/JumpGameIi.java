// Link: https://leetcode.com/problems/jump-game-ii/

// DP
// 33ms, 12.69%
class Solution {
  public int jump(int[] nums) {
    int n = nums.length;
    // M[i] represents the minimum number of jumps from i to the end
    int[] M = new int[n];
    // Induction rule:
    // M[i] = min(M[i+1]+1, M[i+2]+1, ..., M[i+nums[i]]+1)
    // The trick here is to initialize M[i] to n to make it invalid first because
    // M[i] must be at [1, n-1]. In this way, the corner cases are handled.
    // - nums[i] is 0, M[i] will be n
    // - all M[i+j] are n, M[i] will be n as well
    for (int i = n - 2; i >= 0; --i) {
      M[i] = n;
      for (int j = 1; j <= nums[i] && i + j < n; ++j) {
        M[i] = Math.min(M[i], M[i + j] + 1);
      }
    }
    return M[0];
  }
}

/*
 * Time complexity: O(n^2)
 *
 * Space complexity: O(n) for the dp array
 *
 * Notes:
 *
 */


// Method2: BFS
// 1ms, 44.68%
//
// This question can be regarded as a BFS problem. Take [2, 3, 1, 1, 4] as an example,
// the root node is 2 (index 0), its level is 0. Next we iterate over the node at level 0
// to get the nodes at level 1. From 2,  we can reach 3 (index 1) and 1 (index 2), i.e.,
// jump 1 step and 2 steps, so 3 and 1 are at level 1. Next we iterate over nodes at level 1
// to get the nodes at level 2, and so on. We return the level until we the last node can be reached.
//
// We don't need a queue as we implement BFS in a graph, because in this problem, the nodes
// at each level are continuous and can be accessed via the index, thus we just need two
// variables to mark the start and end position (index) of a level. Once we iterate over the nodes
// at a level to get the nodes of the next level, we can find the end position of the next level.
class Solution {
  public int jump(int[] nums) {
    if (nums.length <= 1) {
      return 0;
    }
    int level = 0;
    // Equivalent to enqeue the first node
    int start = 0;
    int end = 0;
    // Equivalent to queue is not empty
    while (start <= end) {
      int nextEnd = end;
      // Moving start to traverse all nodes at the current level to get nodes of the next level and find
      // the end position of the next level. After traversing, the start will be right at the start position
      // of the next level.
      for(; start <= end; ++start) {
        nextEnd = Math.max(nextEnd, nums[start] + start);
        if (nextEnd >= nums.length - 1) {
          return level + 1;
        }
      }
      end = nextEnd;
      level++;
    }
    return -1;
  }
}

/*
 * Time complexity: O(n)
 *
 * Space complexity: O(1)
 *
 * Notes:
 *
 */
