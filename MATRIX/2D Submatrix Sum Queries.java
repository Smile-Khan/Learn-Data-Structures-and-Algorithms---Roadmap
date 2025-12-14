// QUESTION: 2D Submatrix Sum Queries
// LINK: https://www.geeksforgeeks.org/problems/2d-submatrix-sum-queries/1

// SOLUTION: 2D Prefix Sum

import java.util.ArrayList;

class Solution {
    public ArrayList<Integer> prefixSum2D(int[][] mat, int[][] queries) {
        int n = mat.length, m = mat[0].length;
        ArrayList<Integer> result = new ArrayList<>();
        
        // Create prefix sum matrix (modify input if allowed, otherwise create new)
        int[][] prefix = new int[n][m];
        
        // First row
        prefix[0][0] = mat[0][0];
        for (int j = 1; j < m; j++) {
            prefix[0][j] = prefix[0][j - 1] + mat[0][j];
        }
        
        // First column
        for (int i = 1; i < n; i++) {
            prefix[i][0] = prefix[i - 1][0] + mat[i][0];
        }
        
        // Rest of the matrix
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                prefix[i][j] = mat[i][j] 
                             + prefix[i - 1][j] 
                             + prefix[i][j - 1] 
                             - prefix[i - 1][j - 1];
            }
        }
        
        // Answer queries
        for (int[] query : queries) {
            int r1 = query[0], c1 = query[1];
            int r2 = query[2], c2 = query[3];
            
            int sum = prefix[r2][c2];
            
            if (r1 > 0) {
                sum -= prefix[r1 - 1][c2];
            }
            if (c1 > 0) {
                sum -= prefix[r2][c1 - 1];
            }
            if (r1 > 0 && c1 > 0) {
                sum += prefix[r1 - 1][c1 - 1];
            }
            
            result.add(sum);
        }
        
        return result;
    }
}