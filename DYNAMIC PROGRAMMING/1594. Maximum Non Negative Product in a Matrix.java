// QUESTION: 1594. Maximum Non Negative Product in a Matrix
// LINK: https://leetcode.com/problems/maximum-non-negative-product-in-a-matrix/description/?envType=daily-question&envId=2026-03-23

// SOLUTION: DP

class Solution {
    public int maxProductPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;

        // Track max and min products
        long[][] maxProd = new long[m][n];
        long[][] minProd = new long[m][n];

        // Initial starting point
        maxProd[0][0] = grid[0][0];
        minProd[0][0] = grid[0][0];

        // Initializing first row (can only come from left)
        for(int j = 1; j < n; j++)
        {
            maxProd[0][j] = maxProd[0][j-1] * grid[0][j];
            minProd[0][j] = minProd[0][j-1] * grid[0][j];
        }

        // Initializing first column (can only come from above)
        for(int i = 1; i < m; i++)
        {
            maxProd[i][0] = maxProd[i-1][0] * grid[i][0];
            minProd[i][0] = minProd[i-1][0] * grid[i][0];
        }

        // Fill the DP table
        for(int i = 1; i < m; i++)
        {
            for(int j = 1; j < n; j++)
            {
                long current = grid[i][j];

                // Options from above and from left
                long fromAboveMax = maxProd[i-1][j] * current;
                long fromAboveMin = minProd[i-1][j] * current;

                long fromLeftMax = maxProd[i][j-1] * current;
                long fromLeftMin = minProd[i][j-1] * current;

                // For max product: take the maximum of all possiblities
                maxProd[i][j] = Math.max(Math.max(fromAboveMax, fromAboveMin),
                Math.max(fromLeftMax, fromLeftMin));

                // For min product: take the minimum of all possibilites
                minProd[i][j] = Math.min(Math.min(fromAboveMax, fromAboveMin),
                Math.min(fromLeftMax, fromLeftMin));
            }
        }

        // Check if result is negative
        if(maxProd[m-1][n-1] < 0)
        {
            return -1;
        }

        return (int)(maxProd[m-1][n-1] % MOD);
    }
}