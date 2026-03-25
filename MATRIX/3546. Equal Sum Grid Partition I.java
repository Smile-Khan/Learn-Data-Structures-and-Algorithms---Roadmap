// QUESTION: 3546. Equal Sum Grid Partition I
// LINK: https://leetcode.com/problems/equal-sum-grid-partition-i/description/?envType=daily-question&envId=2026-03-25

// SOLUTION: Prefix Sum with Cumulative Summation

class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        long totalSum = 0;

        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < m; j++)
            {
                totalSum += grid[i][j];
            }
        }


        // HORIZONTAL cuts
        long topSum = 0;
        for(int i = 0; i < n - 1; i++)
        {
            for(int j = 0; j < m; j++)
            {
                topSum += grid[i][j];
            }

            if(topSum == totalSum - topSum)
            {
                return true;
            }
        }

        // VERTICAL cuts
        long leftSum = 0;
        for(int j = 0; j < m - 1; j++)
        {
            // Add current column to left sum
            for(int i = 0; i < n; i++)
            {
                leftSum += grid[i][j];
            }

            if(leftSum == totalSum - leftSum)
            {
                return true;
            }
        }
        return false;
    }
}