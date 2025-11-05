QUESTION: Get Minimum Squares
LINK: https://www.geeksforgeeks.org/problems/get-minimum-squares0538/1

SOLUTION: class Solution {
    public int minSquares(int n) {
        // Code here
        int[] dp = new int[n+1];
        
        // Initialize with worst case (all 1's)
        for(int i = 0; i <= n; i++)
        {
            dp[i] = i;  // i can be represented as i times 1 ^ 2
        }
        
        // Fill the dp table
        for(int i = 1; i <= n; i++)
        {
            for(int j = 1; j * j <= i; j++)
            {
                dp[i] = Math.min(dp[i], 1 + dp[i - j * j]);
            }
        }
        return dp[n];
    }
}