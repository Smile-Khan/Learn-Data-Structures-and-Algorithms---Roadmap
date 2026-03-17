// QUESTION: 1727. Largest Submatrix With Rearrangements
// LINK: https://leetcode.com/problems/largest-submatrix-with-rearrangements/description/?envType=daily-question&envId=2026-03-17

// SOLUTION: 

class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int maxArea = 0;

        for(int row = 1; row < rows; row++)
        {
            for(int col = 0; col < cols; col++)
            {
                if(matrix[row][col] == 1)
                {
                    matrix[row][col] = matrix[row-1][col] + 1;
                }
            }
        }


        for(int row = 0; row < rows; row++)
        {
            int[] heights = matrix[row].clone();

            Arrays.sort(heights);

            for(int i = 0; i < cols/ 2; i++)
            {
                int temp = heights[i];
                heights[i] = heights[cols - 1 - i];
                heights[cols - 1 - i] = temp;
            }

            for(int i = 0; i < cols; i++)
            {
                int height = heights[i];
                int width = i + 1;
                maxArea = Math.max(maxArea, height * width);
            }
        }
        return maxArea;
    }
}