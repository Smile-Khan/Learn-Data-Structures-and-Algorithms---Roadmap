// QUESTION: Swap diagonals
// LINK: https://www.geeksforgeeks.org/problems/swap-major-and-minor-diagonals-of-a-square-matrix/1

// SOLUTION: 


class Solution {
    public void swapDiagonal(int[][] mat) {
        // code here
        int n = mat.length;
        
        for(int i = 0; i < n; i++)
        {
            int j = n - 1 - i;
            
            // SWap mat[i][i] and mat[i][j]
            int temp = mat[i][i];
            mat[i][i] = mat[i][j];
            mat[i][j] = temp;
        }
    }
}