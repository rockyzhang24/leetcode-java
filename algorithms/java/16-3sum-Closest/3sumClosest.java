// Link: https://leetcode.com/problems/3sum-closest/

// 3ms, 98.42%
class Solution {
  public int threeSumClosest(int[] nums, int target) {
    // Assume nums.length >= 3sum
    int n = nums.length;
    // Sort the array so that we can use two pointers to solve the two sum problem and easy to dedup.
    Arrays.sort(nums);
    int ans = 0;
    int dist = Integer.MAX_VALUE;
    for (int i = 0; i < n; ++i) {
      int l = i + 1;
      int r = n - 1;
      while (l < r) {
        int threeSum = nums[i] + nums[l] + nums[r];
        if (threeSum < target) {
          l++;
          // dedup
          while (l < r && nums[l] == nums[l - 1]) {
            l++;
          }
        } else if (threeSum > target) {
          r--;
          // dedup
          while (r > l && nums[r] == nums[r + 1]) {
            r--;
          }
        } else {
          return target;
        }
        int curDist = Math.abs(threeSum - target);
        if (curDist < dist) {
          dist = curDist;
          ans = threeSum;
        }
      }
      // dedup
      while (i + 1 < n && nums[i] == nums[i + 1]) {
        i++;
      }
    }
    return ans;
  }
}

/*
 * Time complexity: O(n^2)
 * Arrays.sort() has O(nlogn) time. Two Sum problem has O(n) times. We run two sums problem n times. So the
 * total time complexity is O(nlogn + n^2) = O(n^2)
 *
 * Space complexity: O(logn)
 * Arrays.sort() uses quicksort which needs O(logn) space
 *
 * Notes:
 * - Same idea with the normal three sum. Besides that, we keep track of the distance between the current
 * three sum and the target, and update the answer when we find a three sum with smaller distance.
 * - It works fine without the dedup, so if doesn't matter if we delete those three while-loops.
 */
