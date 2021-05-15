// Link: https://leetcode.com/problems/container-with-most-water/

// 3ms, 79.43%
class Solution {
  public int maxArea(int[] height) {
    // Two pointers
    int i = 0;
    int j = height.length - 1;
    int ans = 0;
    int maxHeight = 0;
    while (i < j) {
      int curHeight = height[i] <= height[j] ? height[i] : height[j];
      if (curHeight > maxHeight) {
        ans = Math.max(ans, curHeight * (j - i));
        maxHeight = curHeight;
      }
      // Move the pointer with the smaller height
      if (height[i] <= height[j]) {
        i++;
      } else {
        j--;
      }
    }
    return ans;
  }
}

/*
 * Time complexity: O(n), n is the length of the height
 * Space complexity: O(1)
 *
 * Notes:
 * This explanation is really impresive: https://leetcode.com/problems/container-with-most-water/discuss/6099/Yet-another-way-to-see-what-happens-in-the-O(n)-algorithm
 */
