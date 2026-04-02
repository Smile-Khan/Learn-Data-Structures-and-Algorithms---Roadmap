// QUESTION: 1895. Largest Magic Square
// LINK: https://leetcode.com/problems/largest-magic-square/description/?envType=daily-question&envId=2026-01-18

// SOLUTION: PREFIX SUM

class Solution {
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Step 1: Pre-calculate Row and Column Prefix Sums
        // rowPref[i][j+1] is the sum of row i from 0 to j
        int[][] rowPref = new int[m][n + 1];
        int[][] colPref = new int[m + 1][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowPref[i][j + 1] = rowPref[i][j] + grid[i][j];
                colPref[i + 1][j] = colPref[i][j] + grid[i][j];
            }
        }

        // Step 2: Search for the largest K from min(m, n) down to 2
        for (int k = Math.min(m, n); k > 1; k--) {
            for (int r = 0; r <= m - k; r++) {
                for (int c = 0; c <= n - k; c++) {
                    if (isMagic(grid, r, c, k, rowPref, colPref)) {
                        return k;
                    }
                }
            }
        }

        return 1;
    }

    private boolean isMagic(int[][] grid, int r, int c, int k, int[][] rowPref, int[][] colPref) {
        // Target is the sum of the first row (calculated in O(1))
        int target = rowPref[r][c + k] - rowPref[r][c];

        // Check all other rows in O(1) each
        for (int i = 1; i < k; i++) {
            if (rowPref[r + i][c + k] - rowPref[r + i][c] != target) return false;
        }

        // Check all columns in O(1) each
        for (int j = 0; j < k; j++) {
            if (colPref[r + k][c + j] - colPref[r][c + j] != target) return false;
        }

        // Only the diagonals require a loop - O(k)
        int diag1 = 0;
        int diag2 = 0;
        for (int i = 0; i < k; i++) {
            diag1 += grid[r + i][c + i];
            diag2 += grid[r + i][c + k - 1 - i];
        }

        return diag1 == target && diag2 == target;
    }
}

