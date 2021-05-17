// Link: https://leetcode.com/problems/roman-to-integer/

// 4ms, 72.89%
class Solution {
  public int romanToInt(String s) {
    int len = s.length();
    Map<Character, Integer> values = new HashMap<>();
    values.put('I', 1);
    values.put('V', 5);
    values.put('X', 10);
    values.put('L', 50);
    values.put('C', 100);
    values.put('D', 500);
    values.put('M', 1000);
    int ans = 0;
    for (int i = 0; i < len; ++i) {
      int curVal = values.get(s.charAt(i));
      if (i + 1 < len && curVal < values.get(s.charAt(i + 1))) {
        ans -= curVal;
      } else {
        ans += curVal;
      }
    }
    return ans;
  }
}

/*
 * Time complexity: O(1)
 * Space complexity: O(1)
 *
 * Notes:
 * By checking whether i is smaller than i + 1, we decide whether we should substract i or add i.
 * In this way, we can avoid many "if condition" (e.g., whether 'I' is before 'V' or 'X').
 */
