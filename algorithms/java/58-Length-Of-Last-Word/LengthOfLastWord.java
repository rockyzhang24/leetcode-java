// Link: https://leetcode.com/problems/length-of-last-word/

/*
 * 0ms, 100%
 */
class Solution {
  public int lengthOfLastWord(String s) {
    int ans = 0;
    int i = s.length() - 1;
    // Skip all trailing spaces
    for (; i >= 0 && s.charAt(i) == ' '; --i);
    // Count the length of the last word
    for (; i >= 0; --i) {
      if (s.charAt(i) == ' ') {
        break;
      }
      ans++;
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
 *
 */
