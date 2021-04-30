// Link: https://leetcode.com/problems/median-of-two-sorted-arrays/

class Solution {
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int len = nums1.length + nums2.length;
    if (len % 2 == 0) {
      // len is even
      return ((double) findKthSmallest(nums1, nums2, 0, 0, len / 2) + findKthSmallest(nums1, nums2, 0, 0, len / 2 + 1)) / 2;
    } else {
      // len is odd
      return (double) findKthSmallest(nums1, nums2, 0, 0, len / 2 + 1);
    }
  }

  // Find the kth smallest element in two sorted array
  private int findKthSmallest(int[] arr1, int[] arr2, int s1, int s2, int k) {
    if (k == 1) {
      if (s1 < arr1.length && s2 < arr2.length) {
        return arr1[s1] <= arr2[s2] ? arr1[s1] : arr2[s2];
      } else {
        return s1 >= arr1.length ? arr2[s2] : arr1[s1];
      }
    }
    // Get the (k/2)th elements of each array
    int idx1 = s1 + k / 2 - 1;
    int idx2 = s2 + k / 2 - 1;
    int num1 = idx1 >= arr1.length ? Integer.MAX_VALUE : arr1[idx1];
    int num2 = idx2 >= arr2.length ? Integer.MAX_VALUE : arr2[idx2];
    if (num1 <= num2) {
      return findKthSmallest(arr1, arr2, idx1 + 1, s2, k - k / 2);
    } else {
      return findKthSmallest(arr1, arr2, s1, idx2 + 1, k - k / 2);
    }
  }
}

/*
 * Time complexity:
 *    O(logk) = O(log (m+n)/2) = O(log (m+n)) where m and n are the lengths of nums1 and nums2 respectilvely.
 *    Each time, we rule out k/2 from k until k becomes 1, so it's O(logk).
 * Space complexity: O(logk) = O(log (m+n)), i.e., the height of the recursion tree
 *
 * Notes:
 * This problem is the special case of "Find the Kth Smallest Element in Two Sorted Array".
 * We compare the (k/2)th elements in each array and rule out the first k/2 elements with the smaller one, because
 * the kth smallest element won't be in these k/2 elements. Then recursively we try to find the (k - k/2)th smallest
 * element from the remaining elements of the two arrays.
 */
