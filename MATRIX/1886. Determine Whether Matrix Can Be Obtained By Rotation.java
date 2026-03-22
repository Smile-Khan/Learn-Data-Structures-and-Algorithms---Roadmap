// QUESTION: 1886. Determine Whether Matrix Can Be Obtained By Rotation
// LINK: https://leetcode.com/problems/determine-whether-matrix-can-be-obtained-by-rotation/description/?envType=daily-question&envId=2026-03-22

// SOLUTION: FORMULA 


class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;
        
        boolean r0 = true;  // 0° rotation
        boolean r90 = true; // 90° rotation
        boolean r180 = true; // 180° rotation
        boolean r270 = true; // 270° rotation
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Check 0°: mat[i][j] should equal target[i][j]
                if (mat[i][j] != target[i][j]) r0 = false;
                
                // Check 90°: mat[i][j] should equal target[j][n-1-i]
                if (mat[i][j] != target[j][n-1-i]) r90 = false;
                
                // Check 180°: mat[i][j] should equal target[n-1-i][n-1-j]
                if (mat[i][j] != target[n-1-i][n-1-j]) r180 = false;
                
                // Check 270°: mat[i][j] should equal target[n-1-j][i]
                if (mat[i][j] != target[n-1-j][i]) r270 = false;
            }
        }
        
        return r0 || r90 || r180 || r270;
    }
}