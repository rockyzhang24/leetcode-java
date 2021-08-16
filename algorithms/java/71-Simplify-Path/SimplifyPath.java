// Link: https://leetcode.com/problems/simplify-path/
// Difficulty: Medium

/*
 * Method-1: Use a deque, and not use the builtin split() method of String
 * 2ms, 99.81%
 *
 * Treat the deque as stack. It contains the file and directory names of the canonical path so far.
 * After processing the path, we pop the names from the deque from the other end (i.e., the bottomo
 * of the stack) to build the canonical path.
 *
 */
class Solution {
  public String simplifyPath(String path) {
    int n = path.length();
    Deque<String> deque = new ArrayDeque<>();
    for (int i = 0; i < n;) {
      // Four cases:
      // "/"
      if (path.charAt(i) == '/') {
        i++;
      } else if (path.charAt(i) == '.' && (i + 1 == n || path.charAt(i + 1) == '/')) {
        // "./"
        i += 2;
      } else if (path.charAt(i) == '.' && i + 1 < n && path.charAt(i + 1) == '.' && (i + 2 == n || path.charAt(i + 2) == '/')) {
        // for "../", we pop out the top of the stack
        deque.pollFirst();
        i += 3;
      } else {
        // others, i.e., normal file or directory name
        int j = i;
        // find the ending position of the fileo or directory name
        for (; j < n && path.charAt(j) != '/'; ++j);
        // push it into the stack
        deque.offerFirst(path.substring(i, j));
        i = j;
      }
    }
    // Now, all file and directory names are contained in the stack. We build the canonical path
    StringBuilder sb = new StringBuilder();
    while (!deque.isEmpty()) {
      sb.append("/");
      sb.append(deque.pollLast());
    }
    return sb.length() == 0 ? "/" : sb.toString();
  }
}

/*
 * Time complexity: O(n), n is the length of path
 *
 * Space complexity: O(n) for the deque
 *
 * NOTES:
 * When the deque is empty, it means the path doesn't contain explicit file or directory name, so we should return "/"
 */


/*
 * Method-2: Take advantage of String's builtin split() method
 * 4ms, 75.98%
 */
class Solution {
  public String simplifyPath(String path) {
    Deque<String> deque = new ArrayDeque<>();
    for (String name : path.split("/")) {
      if (!name.equals("..") && !name.equals(".") && !name.equals("")) {
        deque.offerFirst(name);
      } else if (name.equals("..") && !deque.isEmpty()) {
        deque.pollFirst();
      }
    }
    StringBuilder sb = new StringBuilder();
    while (!deque.isEmpty()) {
      sb.append("/");
      sb.append(deque.pollLast());
    }
    return sb.length() == 0 ? "/" : sb.toString();
  }
}

/*
 * Time complexity: O(n)
 *
 * Space complexity: O(n)
 *
 * NOTES:
 * This method is slower. split() takes O(n) to break the path into pieces. For example, if path is "/a/b/c/d/",
 * the first method takes exactly O(n) but the second method takes O(n) for split() and O(n/2) for processing.
 *
 */
