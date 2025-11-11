// QUESTION: 474. Ones and Zeroes
// LINK: https://leetcode.com/problems/ones-and-zeroes/description/?envType=daily-question&envId=2025-11-11

// SOLUTION: 3D DYNAMIC PROGRAMMING

class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][][] dp = new int[len + 1][m + 1][n + 1];
        
        for (int i = 1; i <= len; i++) {
            String str = strs[i - 1];
            int zeros = countZeros(str);
            int ones = str.length() - zeros;
            
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    // Don't take current string
                    dp[i][j][k] = dp[i - 1][j][k];
                    
                    // Take current string if we have enough capacity
                    if (j >= zeros && k >= ones) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - zeros][k - ones] + 1);
                    }
                }
            }
        }
        
        return dp[len][m][n];
    }
    
    private int countZeros(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == '0') count++;
        }
        return count;
    }
}


// Approach 2: Space Optimized 2D DP (Most Efficient)

class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        
        for (String str : strs) {
            int zeros = 0, ones = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') zeros++;
                else ones++;
            }
            
            // Process backwards to avoid reusing same string
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                }
            }
        }
        
        return dp[m][n];
    }
}


// Approach 3: Recursive with Memoization

class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][][] memo = new int[len][m + 1][n + 1];
        
        // Precompute zeros and ones for each string
        int[][] counts = new int[len][2];
        for (int i = 0; i < len; i++) {
            counts[i] = countZerosOnes(strs[i]);
        }
        
        return dfs(strs, counts, 0, m, n, memo);
    }
    
    private int dfs(String[] strs, int[][] counts, int index, int zerosLeft, int onesLeft, int[][][] memo) {
        if (index == strs.length) return 0;
        if (memo[index][zerosLeft][onesLeft] != 0) return memo[index][zerosLeft][onesLeft];
        
        int zeros = counts[index][0];
        int ones = counts[index][1];
        
        // Option 1: Skip current string
        int max = dfs(strs, counts, index + 1, zerosLeft, onesLeft, memo);
        
        // Option 2: Take current string if possible
        if (zeros <= zerosLeft && ones <= onesLeft) {
            max = Math.max(max, 1 + dfs(strs, counts, index + 1, zerosLeft - zeros, onesLeft - ones, memo));
        }
        
        memo[index][zerosLeft][onesLeft] = max;
        return max;
    }
    
    private int[] countZerosOnes(String str) {
        int zeros = 0, ones = 0;
        for (char c : str.toCharArray()) {
            if (c == '0') zeros++;
            else ones++;
        }
        return new int[]{zeros, ones};
    }
}


// Approach 4: Using Pair Class for Readability

import java.util.*;

class Solution {
    class StringInfo {
        int zeros, ones;
        StringInfo(int zeros, int ones) {
            this.zeros = zeros;
            this.ones = ones;
        }
    }
    
    public int findMaxForm(String[] strs, int m, int n) {
        List<StringInfo> infos = new ArrayList<>();
        for (String str : strs) {
            int zeros = 0, ones = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') zeros++;
                else ones++;
            }
            infos.add(new StringInfo(zeros, ones));
        }
        
        int[][] dp = new int[m + 1][n + 1];
        
        for (StringInfo info : infos) {
            for (int i = m; i >= info.zeros; i--) {
                for (int j = n; j >= info.ones; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - info.zeros][j - info.ones] + 1);
                }
            }
        }
        
        return dp[m][n];
    }
}

// Approach 5: Most Readable Optimized DP


class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        // dp[i][j] = maximum number of strings that can be formed with i zeros and j ones
        int[][] dp = new int[m + 1][n + 1];
        
        for (String str : strs) {
            // Count zeros and ones in current string
            int zeros = 0, ones = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') zeros++;
                else ones++;
            }
            
            // Update DP table from bottom-right to top-left
            // This ensures we don't use the same string multiple times
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                }
            }
        }
        
        return dp[m][n];
    }
}


// Approach 6: With Early Termination


class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        
        for (String str : strs) {
            int zeros = 0, ones = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') zeros++;
                else ones++;
            }
            
            // Early skip if string is too large
            if (zeros > m || ones > n) continue;
            
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    if (dp[i][j] < dp[i - zeros][j - ones] + 1) {
                        dp[i][j] = dp[i - zeros][j - ones] + 1;
                    }
                }
            }
        }
        
        return dp[m][n];
    }
}