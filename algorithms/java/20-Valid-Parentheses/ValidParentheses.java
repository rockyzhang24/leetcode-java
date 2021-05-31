// Link: https://leetcode.com/problems/valid-parentheses/

class Solution {
  public boolean isValid(String s) {
    Deque<Character> stack = new ArrayDeque<>();
    for (char c : s.toCharArray()) {
      if (c == '(' || c == '[' || c == '{') {
        stack.offerFirst(c);
      } else if (!stack.isEmpty() && (c == ')' && stack.peekFirst() == '('
            || c == ']' && stack.peekFirst() == '['
            || c == '}' && stack.peekFirst() == '{')) {
        stack.pollFirst();
      } else {
        return false;
      }
    }
    return stack.isEmpty();
  }
}

/*
 * Time complexity: O(n), n is the length of s
 *
 * Space complexity: O(n) for the stack.
 *
 * Notes:
 *
 */
