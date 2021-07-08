// Link: https://leetcode.com/problems/jump-game/

// Same idea with 45-Jump-Game-II (../45-Jump-Game-Ii/JumpGameIi.java)

/*
 * DP
 * 108ms, 26.09%
 */
class Solution {
  public boolean canJump(int[] nums) {
    int n  = nums.length;
    // M[i] represents whetehr we can reach the last index from nums[i]
    boolean[] M = new boolean[n];
    M[n - 1] = true;
    for (int i = n - 2; i >= 0; --i) {
      for (int j = 1; j <= nums[i] && i + j < n; ++j) {
        if (M[i + j]) {
          M[i] = true;
          break;
        }
      }
    }
    return M[0];
  }
}

/*
 * Time complexity: O(n^2)
 * For example, [5,4,3,2,1,0,0]
 *
 * Space complexity: O(n)
 *
 * Notes:
 *
 */


/*
 * Method2: BFS
 * 2ms, 67.82%
 *
 * This problem can be seen as a graph so it can be solved using BFS. nusm[0] is the origin node and
 * nums[n-1] is the target node. We want to know whether nums[n-1] can be reached from nums[0].
 *
 * From one node, say nums[0], the indices of nodes connected to it are continuous. So we let the queue
 * contain index and instead of using an actual queue, we use two variables, start and end, to simulate
 * a queue. They are the indices of the first and the last element in the queue.
 *
 * Each iteartion, we pop the indices of the node of the current level, and push the indices of nodes of the
 * next level.
 * - Pop: move start, until start > end
 * - Push: once popping (start++), push the indices of elements to which we can jump from nums[start].
 * E.g., nums[start]=3, the indices are start+1, start+2 and start+3, because they are continus, we just
 * need to move end to start+3.
 *
 */
class Solution {
  public boolean canJump(int[] nums) {
    // Equivalent to push the first node into the queue
    int start = 0;
    int end = 0;
    // Equivalent to !queue.isEmpty()
    while (start <= end) {
      int nextEnd = end;
      // Pop and push
      for (; start <= end; ++start) {
        nextEnd = Math.max(nextEnd, start + nums[start]);
        if (nextEnd >= nums.length - 1) {
          return true;
        }
      }
      end = nextEnd;
    }
    return false;
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
