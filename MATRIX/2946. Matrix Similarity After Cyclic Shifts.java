// QUESTION: 2946. Matrix Similarity After Cyclic Shifts
// LINK: https://leetcode.com/problems/matrix-similarity-after-cyclic-shifts/description/?envType=daily-question&envId=2026-03-27https://leetcode.com/problems/matrix-similarity-after-cyclic-shifts/description/?envType=daily-question&envId=2026-03-27

// SOLUTION: 

class Solution {
    public boolean areSimilar(int[][] mat, int k) {
        int rows = mat.length;
        int cols = mat[0].length;

        // k mod cols because shifting cols times brings back to original
        k = k % cols;

        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                int shiftedIndex;
                if(i % 2 == 0)
                {
                    // Even row - shift left
                    shiftedIndex = (j - k + cols) % cols; 
                }
                else 
                {
                    // Odd row - shift right
                    shiftedIndex = (j + k) % cols;
                }

                if(mat[i][j] != mat[i][shiftedIndex])
                {
                    return false;
                }
            }
        }
        return true;
    }
}

// APPROACH 2: Simulation / Iterative Approach

class Solution {
    public boolean areSimilar(int[][] mat, int k) {
        int rows = mat.length;
        int cols = mat[0].length;
        
        // Optimization: shifting cols times brings back to original
        int effectiveShift = k % cols;
        if (effectiveShift == 0) return true;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int shiftedIndex;
                if (i % 2 == 0) {
                    // Even row - left shift
                    shiftedIndex = (j - effectiveShift + cols) % cols;
                } else {
                    // Odd row - right shift
                    shiftedIndex = (j + effectiveShift) % cols;
                }
                
                if (mat[i][j] != mat[i][shiftedIndex]) {
                    return false;
                }
            }
        }
        return true;
    }
}