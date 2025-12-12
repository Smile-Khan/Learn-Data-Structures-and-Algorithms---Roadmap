QUESTION: Transpose of Matrix
LINK: https://www.geeksforgeeks.org/problems/transpose-of-matrix-1587115621/1

SOLUTION: 

class Solution {
    public ArrayList<ArrayList<Integer>> transpose(int[][] mat) {
        // code here
        int n = mat.length;
        
        ArrayList<ArrayList<Integer>> transposed = new ArrayList<>();
        
        // for each column in original matrix
        for(int j = 0; j < n; j++)
        {
            ArrayList<Integer> newRow = new ArrayList<>();
            
            // for each row in original matrix
            for(int i = 0; i < n; i++)
            {
                newRow.add(mat[i][j]);  // Take element from column j
            }
            
            transposed.add(newRow);
        }
        return transposed;
    }
}