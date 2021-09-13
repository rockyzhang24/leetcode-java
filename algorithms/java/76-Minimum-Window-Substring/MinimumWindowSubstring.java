// Link: https://leetcode.com/problems/minimum-window-substring/
// Difficulty: Hard

/*
 * 14ms, 40.28%
 *
 * Use a sliding window. Move the right end (r) to find a valid substring. For example,
 * s="xbaxxbaxxcab", t="abc", the sliding window when the first valid substring is found is like
 *
 * 0 1 2 3 4 5 6 7 8 9 ...
 * x b a x x b a x x c a b
 * |                 |
 * l-----------------r
 *
 * Then we keep moving the left end (l) to get smaller valid substrings and update the result. E.g.,
 *
 * 0 1 2 3 4 5 6 7 8 9 ...  and then 0 1 2 3 4 5 6 7 8 9 ...  and so on
 * x b a x x b a x x c a b           x b a x x b a x x c a b
 *   |               |                   |             |
 *   l---------------r                   l-------------r
 *
 * However, if we move left one by one, it is not effective. So we can use a queue to record the
 * indices of characters that are in t in order. When moving left, we keep popping out the indices
 * from the queue and move left there, until the substring becomes invalid.
 *
 * Then we move right again.
 */
class Solution {
  public String minWindow(String s, String t) {
    // Calculate the count of each character in t
    Map<Character, Integer> freq = new HashMap<>();
    for (char c : t.toCharArray()) {
      freq.put(c, freq.getOrDefault(c, 0) + 1);
    }
    // Use a queue to store the indices of the characters in t that are found in s
    Queue<Integer> queue = new ArrayDeque<>();
    // The length of the min substring so far, and its start index
    int minLen = Integer.MAX_VALUE;
    int minStart = 0;
    // How many characters in t we meet in s
    int cnt = 0;
    // Sliding window [l...r]
    int l = 0;
    for (int r = 0; r < s.length(); ++r) {
      char cur = s.charAt(r);
      Integer freqR = freq.get(cur);
      if (freqR == null) {
        continue;
      }
      if (freqR > 0) {
        cnt++;
      }
      freq.put(cur, freqR - 1);
      queue.offer(r);
      // Move left and update the result
      while (cnt == t.length()) {
        l = queue.poll();
        char c = s.charAt(l);
        int freqL = freq.get(c);
        if (freqL == 0) {
          // update
          if (r - l + 1 < minLen) {
            minLen = r - l + 1;
            minStart = l;
          }
          cnt--;
        }
        freq.put(c, freqL + 1);
      }
    }
    return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
  }
}

/*
 * Time complexity: O(m+n)
 * m is the length of s and n is the length of t
 *
 * Space complexity: O(m+n)
 * m is for the queue, and n is for the hashmap
 *
 * NOTES:
 *
 */
