// QUESTION: 2264. Largest 3-Same-Digit Number in String
// LINK: https://leetcode.com/problems/largest-3-same-digit-number-in-string/description/?envType=daily-question&envId=2025-08-14

// SOLUTION: 

// One-pass run-length (no substrings, a bit faster)


class Solution {
    public String largestGoodInteger(String num) {
        char best = 0;      // track best digit seen in a run of ≥3
        int run = 1;
        for (int i = 1; i < num.length(); i++) {
            if (num.charAt(i) == num.charAt(i-1)) run++;
            else run = 1;

            if (run >= 3) {
                char d = num.charAt(i);            // the repeated digit
                if (best == 0 || d > best) best = d;
            }
        }
        return best == 0 ? "" : ("" + best + best + best);
    }
}


// Simple scan with substrings

class Solution {
    public String largestGoodInteger(String num) {
        String ans = "";
        for (int i = 0; i + 2 < num.length(); i++) {
            char a = num.charAt(i), b = num.charAt(i+1), c = num.charAt(i+2);
            if (a == b && b == c) {                 // <-- enforce same digit
                String t = num.substring(i, i+3);
                if (ans.isEmpty() || t.compareTo(ans) > 0) ans = t;
            }
        }
        return ans;
    }
}
