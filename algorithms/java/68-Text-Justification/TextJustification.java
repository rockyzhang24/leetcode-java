// Link: https://leetcode.com/problems/text-justification/

/*
 * 0ms, 100%
 *
 * Format the text line by line. First, in each line, we calculate how many words the line can contain.
 * Then we calculate how many spaces between words. Next we build the line.
 */
class Solution {
  public List<String> fullJustify(String[] words, int maxWidth) {
    List<String> ans = new ArrayList<>();
    // We format the text line by line
    for (int i = 0; i < words.length;) {
      // 1. we check how many words one line can contain
      // After counting the first word, each word after it will bring a space
      int curWidth = 0;
      curWidth += words[i].length();
      int curCnt = 1;
      int j = i + 1;
      for (; j < words.length && curWidth + words[j].length() + 1 <= maxWidth; ++j) {
        curWidth += words[j].length() + 1;
        curCnt++;
      }
      // 2. build the line
      // We append the first word first, each word after it brings a space
      StringBuilder cur = new StringBuilder();
      cur.append(words[i]);
      if (curCnt > 1 && j < words.length) {
        // CASE-1: if more than one words in the line and this is not the last line, we calculate how many spaces
        // between words, and then we append the spaces followed by appending a word.
        StringBuilder space = new StringBuilder();
        // Get the number of characters excluding space
        int charsLen = curWidth - curCnt + 1;
        // Ater evenly distributing the spaces, get how many spaces between words and the how many spaces remaining
        int spaces = (maxWidth - charsLen) / (curCnt - 1);
        int extraCnt = (maxWidth - charsLen) % (curCnt - 1);
        for (int k = 0; k < spaces; ++k) {
          space.append(" ");
        }
        // Build the line: append spaces, append extra space, and append the word
        for (int k = 1; k < curCnt; ++k) {
          cur.append(space);
          if (extraCnt > 0) {
            cur.append(" ");
            extraCnt--;
          }
          cur.append(words[i + k]);
        }
      } else {
        // CASE-2: if the line can only contain a single word or this is the last line, after append words, we append spaces
        // at the end
        for (int k = 1; k < curCnt; ++k) {
          cur.append(" ");
          cur.append(words[i + k]);
        }
        for (int k = 0; k < maxWidth - curWidth; ++k) {
          cur.append(" ");
        }
      }
      ans.add(cur.toString());
      i = j;
    }
    return ans;
  }
}

/*
 * Time complexity: O(n), n is the number of words
 *
 * Space complexity: O(N), N is the total number of characters in all words
 *
 * NOTES:
 *
 */
