// QUESTION: 72. Edit Distance
// LINK: https://leetcode.com/problems/edit-distance/description/

// SOLUTION: 2D DP
 
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        // dp[i][j] = min operations to convert word1[0..i-1] to word2[0..j-1]
        int[][] dp = new int[m + 1][n + 1];
        
        // Base cases: converting to/from empty string
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i; // Delete all characters from word1
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j; // Insert all characters of word2
        }
        
        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // Characters match, no operation needed
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // Take minimum of three operations
                    dp[i][j] = 1 + Math.min(
                        dp[i - 1][j],      // Delete
                        Math.min(
                            dp[i][j - 1],  // Insert
                            dp[i - 1][j - 1] // Replace
                        )
                    );
                }
            }
        }
        
        return dp[m][n];
    }
}