// Link: https://leetcode.com/problems/longest-substring-without-repeating-characters/

class Solution {
  public int lengthOfLongestSubstring(String s) {
    int len = s.length();
    if (len <= 1) {
      return len;
    }
    // Two pointers, left and right ends of the longest substring ending with s.charAt(r)
    int l = 0;
    int r = 0;
    // Hashmap, for each unique char we have traversed so far, it records the position (index) where
    // the char is last seen. So key is a char, value is the index.
    Map<Character, Integer> map = new HashMap<>();
    int ans = 0;
    while (r < len) {
      Integer idx = map.put(s.charAt(r), r);
      if (idx != null && idx >= l) {
        l = idx + 1;
      }
      ans = Math.max(ans, r - l + 1);
      r++;
    }
    return ans;
  }
}

// Method2: use an int array instead of a hashmap (runtime drops: 4ms --> 2ms)
class Solution {
  public int lengthOfLongestSubstring(String s) {
    int len = s.length();
    if (len <= 1) {
      return len;
    }
    int l = 0;
    int r = 0;
    int ans = 0;
    // Assume the number of characters is 256 (i.e., ASCII)
    int[] map = new int[256];
    Arrays.fill(map, -1); // -1 as the default value
    while (r < len) {
      char cur = s.charAt(r);
      int idx = map[cur];
      if (idx != -1 && idx >= l) {
        l = idx + 1;
      }
      map[cur] = r;
      ans = Math.max(ans, r - l + 1);
      r++;
    }
    return ans;
  }
}

/*
 * Time complexity: O(n) wehre n is s.length()
 * Space complexity: O(m) where m is the size of the charset
 *
 * Notes:
 * Pay attention to the condition when we update l: idx >= l. Use "abba" as a test case and the answer should be 2,
 * if we omit idx >= l, the answer will be 3 which is incorrect.
 */
