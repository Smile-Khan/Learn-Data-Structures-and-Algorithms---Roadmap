// QUESTION: 1758. Minimum Changes To Make Alternating Binary String
// LINK: https://leetcode.com/problems/minimum-changes-to-make-alternating-binary-string/description/?envType=daily-question&envId=2026-03-05

// SOLUTION: 


class Solution {
    public int minOperations(String s) {
        int n = s.length();
        int mismatchA = 0; // for pattern starting with '0'
        int mismatchB = 0; // for pattern starting with '1'
        
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            
            // For pattern starting with '0': even index should be '0', odd index should be '1'
            if (i % 2 == 0) {
                if (c != '0') mismatchA++;
                if (c != '1') mismatchB++;
            } else {
                if (c != '1') mismatchA++;
                if (c != '0') mismatchB++;
            }
        }
        
        return Math.min(mismatchA, mismatchB);
    }
}