// QUESTION: 3212. Count Submatrices With Equal Frequency of X and Y
// LINK: https://leetcode.com/problems/count-submatrices-with-equal-frequency-of-x-and-y/description/?envType=daily-question&envId=2026-03-19

// SOLUTION: PRFIX SUM:

class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int count = 0;

        // Prefix sums for X and Y
        int[][] prefixX = new int[n + 1][m + 1];
        int[][] prefixY = new int[n + 1][m + 1];

        for(int i = 1; i <= n; i++)
        {
            for(int j = 1; j <= m; j++)
            {
                // Calculate prefix sums
                prefixX[i][j] = prefixX[i-1][j] + prefixX[i][j-1] - prefixX[i - 1][j - 1];

                prefixY[i][j] = prefixY[i - 1][j] + prefixY[i][j - 1] - prefixY[i - 1][j - 1];

                if(grid[i - 1][j - 1] == 'X')
                {
                    prefixX[i][j]++;
                }
                else if(grid[i - 1][j - 1] == 'Y')
                {
                    prefixY[i][j]++;
                }

                // Check if submatrix from (0, 0) to (i - 1, j - 1) is valid
                int totalX = prefixX[i][j];
                int totalY = prefixY[i][j];

                if(totalX > 0 && totalX == totalY)
                {
                    count++;
                }
            }
        }
        return count;
    }
}

// APPROACH 2: CUMULATIVE ROW SUM

class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int count = 0;

        // Track cumulative counts for each column
        int[] colX = new int[m];
        int[] colY = new int[m];

        for(int i = 0; i < n; i++)
        {
            int rowX = 0;
            int rowY = 0;

            for(int j = 0; j < m; j++)
            {
                // Update row counts
                if(grid[i][j] == 'X')
                {
                    rowX++;
                }
                else if(grid[i][j] == 'Y')
                {
                    rowY++;
                }

                // Update column cumulative counts
                colX[j] += rowX;
                colY[j] += rowY;

                // Check if submatrix from (0,0) to (i, j) is valid
                if(colX[j] > 0 && colX[j] == colY[j])
                {
                    count++;
                }
            }
        }
        return count;
    }
}