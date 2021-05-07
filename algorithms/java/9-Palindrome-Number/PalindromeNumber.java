// Link: https://leetcode.com/problems/palindrome-number/

// 6ms, 99.96%
class Solution {
  public boolean isPalindrome(int x) {
    // Notice this corner case: negative numbers, numbers ending with 0 but not including 0 are not
    // palindrome
    if (x < 0 || x != 0 && x % 10 == 0) {
      return false;
    }
    int reverse = 0;
    while (reverse < x) {
      reverse = reverse * 10 + x % 10;
      x /= 10;
    }
    return reverse == x || reverse / 10 == x;
  }
}

/*
 * Time complexity: O(n), n is the number of digits in x
 * Space complexity: O(1)
 *
 * Notes:
 * Corner case is tricky. Obviously negative numbers are not palindrome. All numbers ending with 0 are not
 * palindrome as well, but don't forget to exclude 0 because 0 is palindrome
 */
