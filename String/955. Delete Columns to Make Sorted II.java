// QUESTION: 955. Delete Columns to Make Sorted II
// LINK: https://leetcode.com/problems/delete-columns-to-make-sorted-ii/description/?envType=daily-question&envId=2025-12-21

// SOLUTION: Most Optimal (Greedy with Cutting)

class Solution {
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        
        // cuts[i] = true if we've already determined strs[i] > strs[i-1]
        boolean[] cuts = new boolean[n];
        int deletions = 0;
        
        for (int col = 0; col < m; col++) {
            // Check if we need to delete this column
            boolean deleteCol = false;
            
            for (int row = 1; row < n; row++) {
                // If this pair is not already cut (sorted)
                if (!cuts[row]) {
                    char current = strs[row].charAt(col);
                    char prev = strs[row - 1].charAt(col);
                    
                    if (current < prev) {
                        // Must delete this column
                        deleteCol = true;
                        break;
                    }
                }
            }
            
            if (deleteCol) {
                deletions++;
            } else {
                // Update cuts for next column
                for (int row = 1; row < n; row++) {
                    if (!cuts[row]) {
                        char current = strs[row].charAt(col);
                        char prev = strs[row - 1].charAt(col);
                        
                        if (current > prev) {
                            // Now strs[row] > strs[row-1] for sure
                            cuts[row] = true;
                        }
                    }
                }
            }
        }
        
        return deletions;
    }
}