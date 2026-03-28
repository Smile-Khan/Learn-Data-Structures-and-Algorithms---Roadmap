// QUESTION: 2573. Find the String with LCP
// LINK: https://leetcode.com/problems/find-the-string-with-lcp/description/?envType=daily-question&envId=2026-03-28


// SOLUTION: 

class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] result = new char[n];
        
        // Build the string
        char currentChar = 'a';
        for (int i = 0; i < n; i++) {
            if (result[i] != 0) continue;
            if (currentChar > 'z') return "";
            
            for (int j = i; j < n; j++) {
                if (lcp[i][j] > 0) {
                    result[j] = currentChar;
                }
            }
            currentChar++;
        }
        
        // Verify the string satisfies all LCP conditions
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int expected = lcp[i][j];
                int actual = 0;
                while (i + actual < n && j + actual < n && 
                       result[i + actual] == result[j + actual]) {
                    actual++;
                }
                if (actual != expected) {
                    return "";
                }
            }
        }
        
        return new String(result);
    }
}