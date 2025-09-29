// QUESTION: 1039. Minimum Score Triangulation of Polygon
// LINK: https://leetcode.com/problems/minimum-score-triangulation-of-polygon/description/?envType=daily-question&envId=2025-09-29

// SOLUTION: 

class Solution {
    public int minScoreTriangulation(int[] values) {
        int n = values.length;

        int[][] dp = new int[n][n];

        for(int len = 3; len <= n; len++)
        {
            for(int i = 0; i <= n - len; i++)
            {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for(int k = i+1; k <= j - 1; k++)
                {
                    int current = dp[i][k] + dp[k][j] + values[i] * values[k] * values[j];
                    dp[i][j] = Math.min(dp[i][j], current);
                }
            }
        }
        return dp[0][n-1];
    }
}