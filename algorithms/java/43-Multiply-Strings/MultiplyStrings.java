// Link: https://leetcode.com/problems/multiply-strings/

// 10ms, 26.75%
// Directly simulate the procedure of the multiplication of two numbers
// E.g., we want to compute 123 * 456
//              123
//              456
//          --------
//              738
//             6150
//            49200
//          --------
//            56088
class Solution {
  public String multiply(String num1, String num2) {
    if (num1.equals("0") || num2.equals("0")) {
      return "0";
    }
    // An array to store the production of each step
    String[] products = new String[num2.length()];
    for (int i = num2.length() - 1; i >= 0; --i) {
      // StringBuilder to build the production of each step (the digits are in reversed order)
      StringBuilder sb = new StringBuilder();
      // Fill 0s first
      for (int k = 0; k < num2.length() - i - 1; ++k) {
        sb.append(0);
      }
      // Compute the productions, 123*6, 123*5 and 123*4
      int carry = 0;
      for (int j = num1.length() - 1; j >= 0; --j) {
        int temp = (num1.charAt(j) - '0') * (num2.charAt(i) - '0') + carry;
        carry = temp / 10;
        sb.append(temp % 10);
      }
      if (carry != 0) {
        sb.append(carry);
      }
      // Put the production of each step into the array for further summation
      // products[0] is 837, products[1] is 0516, products[2] is 00294
      products[i] = sb.toString();
    }
    // Sum the productions together
    // 837
    // 0516
    // 00294
    // -------
    // 88065
    StringBuilder sb = new StringBuilder();
    int carry = 0;
    for (int i = 0; i < products[0].length(); ++i) {
      int sum = 0;
      for (int j = 0; j < products.length; ++j) {
        if (i < products[j].length()) {
          sum += (products[j].charAt(i) - '0');
        }
      }
      sb.append((sum + carry) % 10);
      carry = (sum + carry) / 10;
    }
    if (carry != 0) {
      sb.append(carry);
    }
    // Finally we reverse the StringBuilder to get the result
    return sb.reverse().toString();
  }
}

/*
 * Time complexity:
 *
 * Space complexity:
 *
 * Notes:
 *
 */


// Method2: based on the relationship of the indices
// 3ms, 90.07%
// Refer to the top post on Discuss (https://leetcode.com/problems/multiply-strings/discuss/17605/Easiest-JAVA-Solution-with-Graph-Explanation)
//                  index 0 1 2
//              -----------------------
//                        1 2 3
//                          4 5
//                      --------
//                          1 5
//                        1 0
//                      0 5
//                        1 2
//                      0 8
//                    0 4
//               ----------------------
//              index 0 1 2 3 4
// The digits of the result of num1[i] * num2[j] are placed at i+j and i+j+1. E.g., num1[1]=2, num2[0]=4, the result is 8. So
// 0 is at index 1 and 8 is at index 2
class Solution {
  public String multiply(String num1, String num2) {
    int n1 = num1.length();
    int n2 = num2.length();
    int[] pos = new int[n1 + n2];
    for (int i = n1 - 1; i >= 0; --i) {
      for (int j = n2 - 1; j >= 0; --j) {
        int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
        int p1 = i + j;
        int p2 = i + j + 1;
        int sum = mul + pos[p2];
        pos[p1] += sum / 10;
        pos[p2] = sum % 10;
      }
    }
    StringBuilder sb = new StringBuilder();
    for (int p : pos) {
      if (p != 0 || sb.length() != 0) {
        sb.append(p);
      }
    }
    return sb.length() == 0 ? "0" : sb.toString();
  }
}

/*
 * Time complexity:
 *
 * Space complexity:
 *
 * Notes:
 *
 */
