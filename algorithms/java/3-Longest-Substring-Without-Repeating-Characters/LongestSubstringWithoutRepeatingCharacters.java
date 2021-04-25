// Link: https://leetcode.com/problems/longest-substring-without-repeating-characters/

class Solution {
  public int lengthOfLongestSubstring(String s) {
    int len = s.length();
    if (len <= 1) {
      return len;
    }
    // Two pointers, two ends of the longest substring ending with s.charAt(r)
    int l = 0;
    int r = 0;
    // Hashmap, for each unique char we have traversed so far, it records the position (index) where
    // the char is last seen. So key is a char, value is the index.
    Map<Character, Integer> map = new HashMap<>();
    int ans = 0;
    while (r < s.length()) {
      char cur = s.charAt(r);
      Integer idx = map.get(cur);
      if (idx != null && idx >= l) {
        l = idx + 1;
      }
      map.put(cur, r);
      ans = Math.max(ans, r - l + 1);
      r++;
    }
    return ans;
  }
}
