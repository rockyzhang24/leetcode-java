// Link: https://leetcode.com/problems/4sum/

// 3ms, 99.28%
class Solution {
  public List<List<Integer>> fourSum(int[] nums, int target) {
    Arrays.sort(nums);
    return kSum(nums, target, 0, 4);
  }

  // General kSum solution. Assume k >= 2 and the input array is sorted already.
  private List<List<Integer>> kSum(int[] nums, int target, int start, int k) {
    List<List<Integer>> res = new ArrayList<>();
    // Early termination
    if (nums == null || k > nums.length - start || nums[start] * k > target || nums[nums.length - 1] * k < target) {
      return new ArrayList<>();
    }
    if (k == 2) {
      // Two sum
      int l = start;
      int r = nums.length - 1;
      while (l < r) {
        int twoSum = nums[l] + nums[r];
        if (twoSum < target) {
          l++;
        } else if (twoSum > target) {
          r--;
        } else {
          res.add(new ArrayList<>(Arrays.asList(nums[l++], nums[r--])));
          // skip duplicates
          for (; l < r && nums[l] == nums[l - 1]; ++l);
          for (; r > l && nums[r] == nums[r + 1]; --r);
        }
      }
      return res;
    }
    for (int i = start; i < nums.length; ++i) {
      List<List<Integer>> lists = kSum(nums, target - nums[i], i + 1, k - 1);
      if (lists != null) {
        for (List<Integer> l : lists) {
          l.add(nums[i]);
          res.add(l);
        }
      }
      // skip duplicates
      for (; i + 1 < nums.length && nums[i] == nums[i + 1]; ++i);
    }
    return res;
  }
}

/*
 * Time complexity: O(n^(k-1))
 * The height of the recursion tree is k-2, at most it has n-2 branches, so totally it has
 * (n-2)^(k-2) nodes, i.e., O(n^(k-2)). And at each node, at most it takes O(n) for two-sum.
 * So the time complexity is O(n^(k-2)*n) = O(n^(k-1)).
 *
 * Space complexity: O(k) for the recursion call stack. In the worst case, k can be n.
 *
 * Notes:
 * We still take advantage of two sum problem like three sum.
 * For three sum, we use an outer loop to enumerate the first number and in the loop, we run two-sum.
 * So for four sum, we need to use two nested loops to enumerate the first two numbers, and run two-sum
 * inside the loops.
 * Now, for k sum, we need k-2 nested loops to enumerate the first k-2 numbers, and run two-sum inside
 * the loop. We can implement the k-2 nested loops using recursion.
 *
 * We sort the array first so that we can solve two-sum using two pointers, and it will be easier to
 * deduplicate if the array is sorted. (We can also use hashset to solve the two-sum problem, but it
 * needs O(n) space for the hashset)
 *
 * When we find a answer of the two-sum (i.e., two numbers in a list), combine it with the number on
 * each outer loop to build a answer of k-sum. In the recursion, these numbers are along the current
 * path. To do this, we have two ways:
 * 1. Return lists. On each node, we add nums[i] into each list and return all these lists.
 * 2. Maintain a list from the root containing all nums[i] on each node.
 *
 * Trick:
 * Pay attention to the eary termination check. It reduces the runtime largely.
 */
