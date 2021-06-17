// Link: https://leetcode.com/problems/substring-with-concatenation-of-all-words/

// 95ms, 52.48%
class Solution {
  public List<Integer> findSubstring(String s, String[] words) {
    int sLen = s.length();
    int wLen = words[0].length();
    int totalCnt = words.length;
    int totalLen = totalCnt * wLen; // the total length of all strings in words combined together
    int end = sLen - totalLen; // the ending index for our searching
    List<Integer> ans = new ArrayList<>();
    // Use a hashmap to count the occurrance of each string in words.
    Map<String, Integer> map = new HashMap<>();
    for (String w : words) {
      map.put(w, map.getOrDefault(w, 0) + 1);
    }
    for (int i = 0; i <= end; ++i) {
      // In each iteration, we use a hashmap to record the times we have seen for each string
      Map<String, Integer> seen = new HashMap<>();
      int j = i;
      for (; j < i + totalLen; j += wLen) {
        String cur = s.substring(j, j + wLen);
        if (!map.containsKey(cur) || seen.get(cur) != null && seen.get(cur) == map.get(cur)) {
          break;
        } else {
          seen.put(cur, seen.getOrDefault(cur, 0) + 1);
        }
      }
      if (j == i + totalLen) {
        ans.add(i);
      }
    }
    return ans;
  }
}

/*
 * Time complexity:
 * O(n*m*l), n is the length of s, m is the length of words and l is the length of the string in words.
 * O(n) outer loops, O(m) inner loops and in each inner loop, O(l) for the substring() method.
 *
 * Space complexity: O(m)
 *
 * Notes:
 */
