// Link: https://leetcode.com/problems/longest-common-prefix

// Method1: vertical scanning
// Each iteation, we check whether the column i has the same character
// 0ms, 100%
class Solution {
  public String longestCommonPrefix(String[] strs) {
    // Assume strs is non-empty
    for (int i = 0; i < strs[0].length(); ++i) {
      char cur = strs[0].charAt(i);
      for (int j = 1; j < strs.length; ++j) {
        if (i == strs[j].length() || strs[j].charAt(i) != cur) {
          return strs[0].substring(0, i);
        }
      }
    }
    return strs[0];
  }
}

/*
 * Time complexity: O(m*n), m is the length of the input string array and n is the length of smallest string in the array
 * Space complexity: O(1)
 *
 * Notes:
 */

// Method2: horizontal scanning
// Initially set the longest common prefix (say curLCP) to the first string. Then in the other strings, for each string i, we
// update curLCP to the longest common prefix of i and curLCP.
// 0ms, 100%
class Solution {
  public String longestCommonPrefix(String[] strs) {
    String curLCP = strs[0];
    for (int i = 1; i < strs.length; ++i) {
      curLCP = getLCP(curLCP, strs[i]);
    }
    return curLCP;
  }

  // Get the longest common prefix of two strings given
  private String getLCP(String s1, String s2) {
    int i = 0;
    while (i < s1.length() && i < s2.length()) {
      if (s1.charAt(i) != s2.charAt(i)) {
        break;
      }
      ++i;
    }
    return s1.substring(0, i);
  }
}

/*
 * Time complexity:
 * worst case is all strings are identical, so it is O(m*n) where m is the length of strs and n is the length of each string.
 * The best case is that the first string itself is the longest common prefix, and of course, it is the shortest, so the time complexity of
 * the best case is O(m*n) where n is the length of the shortest string.
 * Only the base case has the same time complexity with method-1, so method-1 is better.
 *
 * Space complexity: O(1)
 *
 * Notes:
 */

