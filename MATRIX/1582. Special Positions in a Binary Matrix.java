QUESTION: 1582. Special Positions in a Binary Matrix
LINK: https://leetcode.com/problems/special-positions-in-a-binary-matrix/description/?envType=daily-question&envId=2026-03-04

SOLUTION: class Solution {
    public int numSpecial(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        // Step 1: Count 1's in each row and column
        int[] rowCount = new int[m];
        int[] colCount = new int[n];

        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if(mat[i][j] == 1)
                {
                    rowCount[i]++;
                    colCount[j]++;
                }
            }
        }

        // Step 2: Find special positions
        int specials = 0;

        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if(mat[i][j] == 1 && rowCount[i] == 1 && colCount[j] == 1)
                {
                    specials++;
                }
            }
        }
        return specials;
    }
}

