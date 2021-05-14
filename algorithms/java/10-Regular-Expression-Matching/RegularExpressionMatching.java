// Link: https://leetcode.com/problems/regular-expression-matching/

// Method1: Recursion
// 25ms, 34.02%
class Solution {
  public boolean isMatch(String s, String p) {
    return helper(s, 0, s.length(), p, 0, p.length());
  }

  // Pointers sp and pp are pointing to the two characters that are being compared currently in s
  // and p respectively.
  private boolean helper(String s, int sp, int slen, String p, int pp, int plen) {
    // Base case
    if (pp == plen) {
      return sp == slen;
    }
    // Recursive rule
    // Case1:
    // The next character in p is not '*', and the current charatcters in s and p are matched
    // (i.e., they are the same or the current character in p is '.')
    if ((pp + 1 < plen && p.charAt(pp + 1) != '*' || pp == plen - 1)
        && sp < slen
        && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '.')) {
      return helper(s, sp + 1, slen, p, pp + 1, plen);
    }
    // Case2:
    // The next character in p is '*', it will match 0 or more of its preceding character
    if (pp + 1 < plen && p.charAt(pp + 1) == '*') {
      boolean matched = false;
      for (int i = 0; sp + i < slen && (s.charAt(sp + i) == p.charAt(pp) || p.charAt(pp) == '.'); ++i) {
        if (helper(s, sp + i + 1, slen, p, pp + 2, plen)) {
          matched = true;
        }
      }
      return matched || helper(s, sp, slen, p, pp + 2, plen);
    }
    return false;
  }
}

/*
 * Time complexity:
 *   The number of nodes in the recursion tree. Consisering the worse case, for example, s="aaaab", p="a*a*a*",
 *   the height of the tree is p.length() and there are at most s.length() nodes on each level. O(slen^plen)
 * Space complexity:
 *   The height of the tree, i.e., O(plen)
 *
 * Notes:
 * 1. Base case:
 * When string s has been finished (i.e., sp == slen), if pp < plen, they can still be matched, like s="abc", p="abca*",
 * so we shouldn't stop. Only if pattern p is finished, we should stop and check whether s is finished as well.
 * 2. Recompute:
 * Recursive method has many repeated computation. For example, s="aaaa", p="a*a*a*", matches of "aaa" and "a*"
 * will be computed many times. To avoid the recompute, we should use DP.
 */


// Method2: DP (bottom-up)
// 2ms, 86.27%
class Solution {
  public boolean isMatch(String s, String p) {
    int slen = s.length();
    int plen = p.length();
    // M[i][j] represents whether s[i...] and p[j...] are matched. So the result is M[0][0].
    boolean[][] M = new boolean[slen + 1][plen + 1];
    M[slen][plen] = true;
    for (int i = slen; i >= 0; --i) {
      for (int j = plen - 1; j >= 0; --j) {
        // Whether the s[i] and p[j] are matched
        boolean curMatched = (i < slen && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'));
        if (j + 1 < plen && p.charAt(j + 1) != '*' || j == plen - 1) {
          // Case1
          M[i][j] = curMatched && M[i + 1][j + 1];
        } else {
          // Case2
          M[i][j] = M[i][j + 2] || curMatched && M[i + 1][j];
        }
      }
    }
    return M[0][0];
  }
}

/*
 * Time complexity: O(m*n), m and n are the lenghs of s and p respectively.
 * Space complexity: O(m*n)
 *
 * Notes:
 * For case 2, we shouldn't use a loop to determine M[i][j] because it may have repeated computation and makes
 * the time complexity be O(m*n*m)
 */
