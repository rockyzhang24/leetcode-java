// Link: https://leetcode.com/problems/3sum/

// Method1: sort and two pointers
// 15ms, 99.70%
class Solution {
  public List<List<Integer>> threeSum(int[] nums) {
    // sanity check
    if (nums == null || nums.length < 3) {
      return new ArrayList<List<Integer>>();
    }
    int n = nums.length;
    // sort the array first for easy deduplicate
    Arrays.sort(nums);
    List<List<Integer>> ans = new ArrayList<>();
    for (int i = 0; i < n; ++i) {
      // Nice trick:
      // Because the array is sorted already, if the first number is larger than 0, the sum
      // cannot be 0. This improves the runtime from 97% to 99%.
      if (nums[i] > 0) {
        break;
      }
      // use two pointers to solve the 2sum problem in a sorted array
      int target = 0 - nums[i];
      int l = i + 1;
      int r = n - 1;
      while (l < r) {
        int twoSum = nums[l] + nums[r];
        if (twoSum > target) {
          r--;
        } else if (twoSum < target) {
          l++;
        } else {
          ans.add(Arrays.asList(nums[i], nums[l++], nums[r--]));
          // dedup: skip all the duplicates for the second and the third numbers
          while (l < r && nums[l] == nums[l - 1]) {
            l++;
          }
          while (l < r && nums[r] == nums[r + 1]) {
            r--;
          }
        }
      }
      // dedup: skip all duplicates of the first number
      while (i + 1 < n && nums[i] == nums[i + 1]) {
        i++;
      }
    }
    return ans;
  }
}

/*
 * Time complexity: O(n^2)
 * O(nlogn) for Arrays.sort() which uses quicksort internally, and O(n) for 2sum. We run 2sum n times. So the
 * time complexity is O(nlogn + n^2) which is O(n^2).
 *
 * Space complexity: O(logn)
 * It is the quicksort used by Arrays.sort() internally.
 *
 * Notes:
 * Trick1: The array is sorted, so if the first number, nums[i], is larger than 0, the final 3sum cannot be 0,
 * so we break.
 * Trick2: if the 2sum is larger or smaller than the target, we move the pointers, and after that, actually we
 * should dedup, i.e., keep moving l or r to skip all the duplicates. However, we don't need to do this using an
 * extra loop, the outer while loop will handle this.
 */


// Method2: sort and hashset
// 211ms, 29.36%
class Solution {
  public List<List<Integer>> threeSum(int[] nums) {
    if (nums == null || nums.length < 3) {
      return new ArrayList<List<Integer>>();
    }
    int n = nums.length;
    Arrays.sort(nums);
    List<List<Integer>> ans = new ArrayList<>();
    for (int i = 0; i < n; ++i) {
      if (nums[i] > 0) {
        break;
      }
      // 2sum probelm using hashset
      int target = 0 - nums[i];
      Set<Integer> set = new HashSet<>();
      for (int j = i + 1; j < n; ++j) {
        int another = target - nums[j];
        if (set.contains(another)) {
          ans.add(Arrays.asList(nums[i], another, nums[j]));
          // skip the duplicates
          while (j + 1 < n && nums[j] == nums[j + 1]) {
            j++;
          }
        } else {
          set.add(nums[j]);
        }
      }
      // skip the duplicates of the first number
      while (i + 1 < n && nums[i] == nums[i + 1]) {
        i++;
      }
    }
    return ans;
  }
}

/*
 * Time complexity: O(n^2)
 *
 * Space complexity: O(n) for the hashset
 *
 * Notes:
 *
 */


// Method3: no-sort
// https://leetcode.com/problems/3sum/solution/
