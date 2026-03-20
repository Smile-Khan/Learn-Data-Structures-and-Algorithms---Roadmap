QUESTION: 3567. Minimum Absolute Difference in Sliding Submatrix
LINK: https://leetcode.com/problems/minimum-absolute-difference-in-sliding-submatrix/description/?envType=daily-question&envId=2026-03-20

// SOLUTION: SLIDING WINDOW 

class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Result size: (m - k + 1) × (n - k + 1)
        int[][] result = new int[m - k + 1][n - k + 1];
        
        // Slide the k×k window
        for (int i = 0; i <= m - k; i++) {
            for (int j = 0; j <= n - k; j++) {
                // Collect all values in this k×k submatrix
                List<Integer> values = new ArrayList<>();
                for (int x = i; x < i + k; x++) {
                    for (int y = j; y < j + k; y++) {
                        values.add(grid[x][y]);
                    }
                }
                
                // Get distinct values using TreeSet (automatically sorted)
                TreeSet<Integer> distinct = new TreeSet<>(values);
                
                // If all values are same, minDiff = 0
                if (distinct.size() == 1) {
                    result[i][j] = 0;
                    continue;
                }
                
                // Find minimum adjacent difference
                int minDiff = Integer.MAX_VALUE;
                Integer prev = null;
                for (int val : distinct) {
                    if (prev != null) {
                        minDiff = Math.min(minDiff, val - prev);
                    }
                    prev = val;
                }
                
                result[i][j] = minDiff;
            }
        }
        
        return result;
    }
}