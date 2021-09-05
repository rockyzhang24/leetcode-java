// Link: https://leetcode.com/problems/edit-distance/
// Difficulty: Hard

/*
 * Method-1: recursion
 * TLE if word1 and word2 are too long
 */
class Solution {

  private int minDist;

  public int minDistance(String word1, String word2) {
    int n1 = word1.length();
    int n2 = word2.length();
    minDist = Math.max(n1, n2);
    getMinDist(word1, word2, 0, 0, n1, n2, 0);
    return minDist;
  }

  private void getMinDist(String word1, String word2, int idx1, int idx2, int n1, int n2, int curDist) {
    // If word1 is finished and word2 has remainings, we insert each letter in the remainings
    if (idx1 == n1) {
      minDist = Math.min(minDist, curDist + n2 - idx2);
      return;
    }
    // If word2 is finished and word1 has remainings, we delete each letter in the remainings
    if (idx2 == n2) {
      minDist = Math.min(minDist, curDist + n1 - idx1);
      return;
    }
    if (word1.charAt(idx1) == word2.charAt(idx2)) {
      getMinDist(word1, word2, idx1 + 1, idx2 + 1, n1, n2, curDist);
    } else {
      // insert
      getMinDist(word1, word2, idx1, idx2 + 1, n1, n2, curDist + 1);
      // delete
      getMinDist(word1, word2, idx1 + 1, idx2, n1, n2, curDist + 1);
      // replace
      getMinDist(word1, word2, idx1 + 1, idx2 + 1, n1, n2, curDist + 1);
    }
  }
}

/*
 * Time complexity: O(n^3)
 * The worst case is both lengths of word1 and word2 are n and all the characters are not equal
 *
 * Space complexity: O(n)
 * It is for the recursion stack
 *
 * NOTES:
 *
 */


/*
 * Method-2: DP
 * 7ms, 25.02%
 */
class Solution {
  public int minDistance(String word1, String word2) {
    int n1 = word1.length();
    int n2 = word2.length();
    int[][] M = new int[n1 + 1][n2 + 2];
    // If both word1 and word2 are empty, do nothing, so the distance is 0
    M[n1][n2] = 0;
    // If word1 is empty, we insert letters
    for (int i = n2 - 1; i >= 0; --i) {
      M[n1][i] = n2 - i;
    }
    // If word2 is empty, we delete letters
    for (int i = n1 - 1; i >= 0; --i) {
      M[i][n2] = n1 - i;
    }
    // If both are non-empty:
    // M[i][j] = M[i + 1][j + 1] if word1[i] == word2[j]
    //         = min(M[i][j + 1], M[i + 1][j], M[i + 1][j + 1]) + 1 otherwise
    //               insert         delete       replace
    for (int i = n1 - 1; i >= 0; --i) {
      for (int j = n2 - 1; j >= 0; --j) {
        if (word1.charAt(i) == word2.charAt(j)) {
          M[i][j] = M[i + 1][j + 1];
        } else {
          M[i][j] = Math.min(Math.min(M[i][j + 1], M[i + 1][j]), M[i + 1][j + 1]) + 1;
        }
      }
    }
    return M[0][0];
  }
}

/*
 * Time complexity: O(n1 * n2), n1 and n2 are the lengths of word1 and word2 respectively
 *
 * Space complexity: O(n1 * n2)
 *
 * NOTES:
 *
 */


/*
 * Method-3: DP - optimize the space to O(n)
 * 3ms, 99.42%
 */
class Solution {
  public int minDistance(String word1, String word2) {
    int n1 = word1.length();
    int n2 = word2.length();
    int[] M = new int[n2 + 1];
    for (int i = n2; i >= 0; --i) {
      M[i] = n2 - i;
    }
    for (int i = n1 - 1; i >= 0; --i) {
      // The diagonal cell
      int diag = M[n2];
      M[n2] = n1 - i;
      for (int j = n2 - 1; j >= 0; --j) {
        int nextDiag = M[j];
        if (word1.charAt(i) == word2.charAt(j)) {
          M[j] = diag;
        } else {
          M[j] = Math.min(Math.min(M[j], M[j + 1]), diag) + 1;
        }
        diag = nextDiag;
      }
    }
    return M[0];
  }
}

/*
 * Time complexity: O(n1 * n2)
 *
 * Space complexity: O(n2)
 *
 * NOTES:
 *
 */
