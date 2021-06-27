// Link: https://leetcode.com/problems/trapping-rain-water/

// 1ms, 84.79%
class Solution {
  public int trap(int[] height) {
    int n = height.length;
    // At least 3 bars are required to trap water
    if (n < 3) {
      return 0;
    }
    // leftMax[i] is the max height among all bars on its left hand side
    int[] leftMax = new int[n];
    // rightMax[i] is the max height among all bars on its right hand side
    int[] rightMax = new int[n];
    // Initialize leftMax
    leftMax[0] = height[0];
    for (int i = 1; i < n; ++i) {
      if (height[i] > leftMax[i - 1]) {
        leftMax[i] = height[i];
      } else {
        leftMax[i] = leftMax[i - 1];
      }
    }
    // Initialize rightMax
    rightMax[n - 1] = height[n - 1];
    for (int i = n - 2; i >= 0 ; --i) {
      if (height[i] > rightMax[i + 1]) {
        rightMax[i] = height[i];
      } else {
        rightMax[i] = rightMax[i + 1];
      }
    }
    // Calculate how many water trapped. For each bar i, we calculate the water above
    // it, (min(leftMax[i], rightMax[i]) - height[i]) * 1 (should be positive) and add
    // it to the answer.
    int ans = 0;
    for (int i = 0; i < n; ++i) {
      int minHeight = Math.min(leftMax[i], rightMax[i]);
      if (minHeight > height[i]) {
        ans += minHeight - height[i];
      }
    }
    return ans;
  }
}

/*
 * Time complexity: O(n)
 *
 * Space complexity: O(n)
 *
 * Notes:
 * For each bar i, we calculate the water above it and sum them. To calculate it, we need
 * to know the height of the longest bar among all bars on its left hand side, and the height
 * of the longest bar among all bars on its right hand side. We use the smaller one to
 * calculate the water.
 * So we use two extra arrays, leftMax[] and rightMax[].
 * - leftMax[i] is the height of the longest bar among all bars from bar[0] to bar[i]
 * - rightMax[i] is the height of the longest bar among all bars from bar[n-1] to bar[i]
 */


// Method2: using a stack
// 1ms, 84.79%
class Solution {
  public int trap(int[] height) {
    // stack contains the index of the non-increasing sequence of the heights
    Deque<Integer> stack = new ArrayDeque<>();
    int ans = 0;
    for (int i = 0; i < height.length; ++i) {
      if (stack.isEmpty() || height[i] <= height[stack.peekFirst()]) {
        stack.offerFirst(i);
      } else {
        while (!stack.isEmpty() && height[i] > height[stack.peekFirst()]) {
          int idx = stack.pollFirst();
          if (!stack.isEmpty()) {
            ans += (Math.min(height[i], height[stack.peekFirst()]) - height[idx]) * (i - stack.peekFirst() - 1);
          }
        }
        stack.offerFirst(i);
      }
    }
    return ans;
  }
}

/*
 * Time complexity: O(n)
 *
 * Space complexity: O(n)
 *
 * Notes:
 * In the stack, maintain a non-increasing sequence of the heights and store the indices in it.
 * When height[i] is larger than height[stack.top], we pop out stack.top (idx = stack.top), and
 * then we calculate the amount of water in the rectangle whose left and right edges are the current
 * top and i, thus the length of the rectange is i-stack.top-1, and the width of the rectangle
 * is min(height[i], height[stack.top])-height[idx].
 * We repeat this process until height[i] is not larger than stack.top, and then we push i into
 * the stack.
 * Then we continue and repeat the same precedure for height[i+1].
 */


// Method3: two pointers
class Solution {
  public int trap(int[] height) {
    int n = height.length;
    if (n < 3) {
      return 0;
    }
    int leftMax = height[0];
    int rightMax = height[n - 1];
    int left = 0;
    int right = n - 1;
    int ans = 0;
    while (left <= right) {
      if (leftMax <= rightMax) {
        ans += leftMax - height[left];
        left++;
        if (left < n) {
          leftMax = Math.max(leftMax, height[left]);
        }
      } else {
        ans += rightMax - height[right];
        right--;
        if (right >= 0) {
          rightMax = Math.max(rightMax, height[right]);
        }
      }
    }
    return ans;
  }
}

/*
 * Time complexity: O(n)
 *
 * Space complexity: O(1)
 *
 * Notes:
 * leftMax is the max height of [0, left]
 * rightMax is the max height of [right, n-1]
 * If leftMax is smaller than rightMax, we can calculate the water above left which is bounded by
 * leftMax. We can see that so far we don't know the heights of bars at (left, right), however,
 * even if there is a larger height which will update rightMax, leftMax is still the smaller one, so
 * water above left must be determined by leftMax.
 * The case where rigthMax is smaller than leftMax is the same.
 */
