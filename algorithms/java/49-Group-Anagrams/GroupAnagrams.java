// Link: https://leetcode.com/problems/group-anagrams/

/*
 * Method1: sort each string and group them
 * 5ms, 99.02%
 *
 * One property of anagram is that after sorted, they are the same.
 * We use a hashmap, key is a sorted string, value is a list of anagrams. We iterate over all
 * the strings and for each string s, we sorted it and put it into the list it belongs to.
 * Finally, all values are the group of anagrams.
 */
class Solution {
  public List<List<String>> groupAnagrams(String[] strs) {
    int n = strs.length;
    Map<String, List<String>> map = new HashMap<>();
    for (String s : strs) {
      char[] arr = s.toCharArray();
      Arrays.sort(arr);
      String sorted = new String(arr);
      List<String> curList = map.get(sorted);
      if (curList == null) {
        curList = new ArrayList<>();
        map.put(sorted, curList);
      }
      curList.add(s);
    }
    return new ArrayList<>(map.values());
  }
}

/*
 * Time complexity: O(n*(2k+klogk) + n), n is the length of strs and k is the average length of each string.
 * Both toCharArray() and new String() are O(k), sort() is O(klogk), map.values() are O(n)
 *
 * Space complexity: O(N), N is the total characters in all strings.
 *
 * Notes:
 *
 */


/*
 * Method2: prime number - will cause overflow if the length of a string is larger than 32!!
 * (In the problem description, strs[i].length <= 100, so even long type doesn't work neither)
 *
 * Map each character to a prime number, and then for a string we multiply the prime number of each
 * character together. Anagrams have the same production.
 * We use a hashmap as well, key is the production of anagram, value is a list of anagrams.
 *
 */
class Solution {
  public List<List<String>> groupAnagrams(String[] strs) {
    // 26 primes
    int[] primes = new int[] {2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
                              31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
                              73, 79, 83, 89, 97, 101};
    Map<Integer, List<String>> map = new HashMap<>();
    for (String s : strs) {
      // Production of a anagram
      int prod = 1;
      for (char c : s.toCharArray()) {
        prod *= primes[c - 'a'];
      }
      List<String> curList = map.get(prod);
      if (curList == null) {
        curList = new ArrayList<>();
        map.put(prod, curList);
      }
      curList.add(s);
    }
    return new ArrayList<>(map.values());
  }
}

/*
 * Time complexity: O(N), N is the number of characters in all strings
 *
 * Space complexity: O(N)
 *
 * Notes:
 *
 */


/*
 * Method3: count the number of each character in a anagram
 * 19ms, 19.20%
 *
 * Because of only lower-case letter, we use an array with 26 cells, and each cell corresponds
 * to a letter. The cell records the number of that letters in a anagram. Then we convert the
 * array to a string (encode) to represent the anagrams.
 * The converting has many ways. If we use int array (say int[] arr) and take "babc" as an example,
 * arr will count the frequency of each letter. After that, the converted string can be "1a2b1c", or
 * Arrays.toString().
 *
 * Trick: The problem description says strs[i].length <= 100, so char array is the most suitable,
 * i.e., 0~127 is enough. Then use String.valueOf(arr) or new String(arr) to convert it into a string.
 */
class Solution {
  public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();
    for (String s : strs) {
      char[] freq = new char[26];
      // Count the number of each letter in a string
      for (char c : s.toCharArray()) {
        freq[c - 'a']++;
      }
      String encode = new String(freq);
      List<String> curList = map.get(encode);
      if (curList == null) {
        curList = new ArrayList<>();
        map.put(encode, curList);
      }
      curList.add(s);
    }
    return new ArrayList<>(map.values());
  }
}

/*
 * Time complexity: O(N), N is the number of characters in all strings
 *
 * Space complexity: O(N)
 *
 * Notes:
 *
 */
