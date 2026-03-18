// QUESTION: 3070. Count Submatrices with Top-Left Element and Sum Less Than k
// LINK: https://leetcode.com/problems/count-submatrices-with-top-left-element-and-sum-less-than-k/description/?envType=daily-question&envId=2026-03-18

// SOLUTION: 2D PREFIX SUM

class Solution {
    public int countSubmatrices(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[][] prefix = new int[rows][cols];
        int count = 0;

        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                // Calculate prefix sum
                int top = (i > 0) ? prefix[i - 1][j] : 0;
                int left = (j > 0) ? prefix[i][j - 1] : 0;
                int topLeft = (i > 0 && j > 0 ) ? prefix[i-1][j-1] : 0;


                prefix[i][j] = grid[i][j] + top + left - topLeft;

                if(prefix[i][j] <= k)
                {
                    count++;
                }
            }
        }
        return count;
    }
}

// APPROACH 2: Cumulative Row Sum

class Solution {
    public int countSubmatrices(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;
        
        // For each row, we'll keep cumulative sum
        int[] rowSum = new int[rows];
        
        for (int j = 0; j < cols; j++) {
            // Add current column to row sums
            for (int i = 0; i < rows; i++) {
                rowSum[i] += grid[i][j];
            }
            
            // Check submatrices ending at column j
            int sum = 0;
            for (int i = 0; i < rows; i++) {
                sum += rowSum[i];
                if (sum <= k) {
                    count++;
                } else {
                    break; // Further rows will only increase sum
                }
            }
        }
        return count;
    }
}