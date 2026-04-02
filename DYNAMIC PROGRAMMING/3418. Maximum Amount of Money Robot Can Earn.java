// QUESTION: 3418. Maximum Amount of Money Robot Can Earn
// LINK: https://leetcode.com/problems/maximum-amount-of-money-robot-can-earn/submissions/1966789190/?envType=daily-question&envId=2026-04-02

// SOLUTION: DP

class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;

        // DP array: dp[i][j][k] = max coins at (i, j) with k neutralizations used 
        int[][][] dp = new int[m][n][3];

        // Initialize with very small values (negative infinity)
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                Arrays.fill(dp[i][j], Integer.MIN_VALUE);
            }
        }

        // Starting position
        if(coins[0][0] >= 0)
        {
            dp[0][0][0] = coins[0][0];
            dp[0][0][1] = coins[0][0];
            dp[0][0][2] = coins[0][0];
        }
        else 
        {
            // If starting cell has robber, we can neutralize it
            dp[0][0][0] = coins[0][0];  // No neutralization, lose coins
            dp[0][0][1] = 0;
            dp[0][0][2] = 0;    // Use 2 neutralizations (overkill but valid)
        }

        // Fill the DP table
        for(int i = 0; i < m; i++)
        {
            for(int j =  0; j < n; j++)
            {
                if(i == 0 && j == 0) continue;

                for(int k = 0; k <= 2; k++)
                {
                    // Try coming from top
                    if(i > 0)
                    {
                        // Option 1: Don't neutralize current cell
                        if(dp[i-1][j][k] != Integer.MIN_VALUE)
                        {
                            dp[i][j][k] = Math.max(dp[i][j][k], dp[i-1][j][k] + coins[i][j]);
                        }

                        // Option 2: Neutralize current cell (if robber and k > 0)
                        if(k > 0 && coins[i][j] < 0 && dp[i-1][j][k-1] != Integer.MIN_VALUE)
                        {
                            dp[i][j][k] = Math.max(dp[i][j][k], dp[i-1][j][k-1]);
                        }
                    }

                    // Try coming from left
                    if(j > 0)
                    {
                        // Option 1: Don't neutralize current cell
                        if(dp[i][j-1][k] != Integer.MIN_VALUE)
                        {
                            dp[i][j][k] = Math.max(dp[i][j][k], dp[i][j-1][k] + coins[i][j]);
                        }

                        // Option 2: Neutralize current cell
                        if(k > 0 && coins[i][j] < 0 && dp[i][j-1][k-1] != Integer.MIN_VALUE)
                        {
                            dp[i][j][k] = Math.max(dp[i][j][k], dp[i][j-1][k-1]);
                        }
                    }
                }
            }
        }

        // Return the best value at bottom-right with up to 2 neatralizations
        return Math.max(dp[m-1][n-1][0], 
               Math.max(dp[m-1][n-1][1], dp[m-1][n-1][2]));
    }
}

