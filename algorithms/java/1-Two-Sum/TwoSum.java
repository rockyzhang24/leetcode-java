// Link: https://leetcode.com/problems/two-sum/

class Solution {
  public int[] twoSum(int[] nums, int target) {
    // Each input has exactly one solution --> sanity check is not necessary and nums.length >= 2
    // hashmap, <key: num, value: its index>
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      int another = target - nums[i];
      Integer idx = map.get(another);
      if (idx != null) {
        return new int[] {idx, i};
      } else {
        map.put(nums[i], i);
      }
    }
    return new int[] {-1, -1};
  }
}
