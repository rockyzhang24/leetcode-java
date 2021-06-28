// Link: https://leetcode.com/problems/wildcard-matching/

// DP
// 27ms, 41.58%
class Solution {
  public boolean isMatch(String s, String p) {
    int slen = s.length();
    int plen = p.length();
    // M[i][j] means whether s[0, i-1] and p [0, j-1] are matched or not
    // i=0 means s is empty string and j=0 means p is empty string
    boolean[][] M = new boolean[slen + 1][plen + 1];
    M[0][0] = true;
    // Induction rule:
    // - M[i][j] = M[i-1][j-1]             if p[j] == '?' || s[i] == p[j]
    // - M[i][j] = M[i][j-1] || M[i-1][j]  if p[j] == '*'
    //             match 0      match more than 0
    for (int i = 0; i < slen + 1; ++i) {
      for (int j = 1; j < plen + 1; ++j) {
        if (i > 0 && (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1))) {
          M[i][j] = M[i - 1][j - 1];
        } else if (p.charAt(j - 1) == '*') {
          M[i][j] = M[i][j - 1] || i > 0 && M[i - 1][j];
        }
      }
    }
    return M[slen][plen];
  }
}

/*
 * Time complexity: O(m*n), m and n are the lengths of s and p respectively
 *
 * Space complexity: O(m*n) for the 2D array
 *
 * Notes:
 *
 */
