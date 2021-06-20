// Link: https://leetcode.com/problems/longest-valid-parentheses/

// Method1: using stack
// 6ms, 13.53%
class Solution {
  public int longestValidParentheses(String s) {
    Deque<Integer> stack = new ArrayDeque<>();
    int ans = 0;
    for (int i = 0; i < s.length(); ++i) {
      if (s.charAt(i) == '(' || (stack.isEmpty() || s.charAt(stack.peekFirst()) == ')')) {
        stack.offerFirst(i);
      } else {
        stack.pollFirst();
        ans = Math.max(ans, i - (stack.isEmpty() ? -1 : stack.peekFirst()));
      }
    }
    return ans;
  }
}

/*
 * Time complexity: O(n)
 *
 * Space complexity: O(n)
 *
 * Notes:
 * The stack can filter out all the matching parentheses and just keeps all the unmatching
 * parenthesis inside.
 * 1. push: when s[i] doesn't match with stack.top
 *    - s[i] is '('
 *    - stack is empty
 *    - s[i] is ')' and stack.top is also ')'
 * 2. pop: s[i] does match with stack.top, i.e., stack.top is '(' and s[i] is ')'.
 * After popping, we update the answer, i.e., the longest length of the parentheses ending at s[i].
 */


// Method2: using a counter to avoid extra space
// 1ms, 100%
class Solution {
  public int longestValidParentheses(String s) {
    int ans = 0;
    int count = 0;
    int start = -1;
    // Traverse from left to right
    for (int i = 0; i < s.length(); ++i) {
      count = (s.charAt(i) == '(' ? count + 1 : count - 1);
      if (count == 0) {
        ans = Math.max(ans, i - start);
      } else if (count < 0) {
        count = 0;
        start = i;
      }
    }
    // Traverse from right to left
    count = 0;
    int end = s.length();
    for (int i = s.length() - 1; i >= 0; --i) {
      count = (s.charAt(i) == ')' ? count + 1 : count - 1);
      if (count == 0) {
        ans = Math.max(ans, end - i);
      } else if (count < 0) {
        count = 0;
        end = i;
      }
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
 * We scan s from left to right. When we see '(', we increase the counter by 1 and when we see ')', we
 * decrease the counter by 1. When the counter becomes 0, we find a sequence of valid parentheses, so
 * we update the answer.
 * However, it can only handle the case like "())" but not "(()", i.e., the number of the left parenthesis
 * is larger than the number of the right parenthsis. We should scan it again from right to left.
 */
