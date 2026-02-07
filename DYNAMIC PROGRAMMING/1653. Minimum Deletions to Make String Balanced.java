QUESTION: 1653. Minimum Deletions to Make String Balanced
LINK: https://leetcode.com/problems/minimum-deletions-to-make-string-balanced/description/?envType=daily-question&envId=2026-02-07

SOLUTION: class Solution {
    public int minimumDeletions(String s) {
        int n = s.length();
        
        // Count total 'a's
        int totalA = 0;
        for (char c : s.toCharArray()) {
            if (c == 'a') totalA++;
        }
        
        int minDeletions = n;  // Worst case: delete all
        int countB = 0;  // 'b's seen so far (left side)
        
        // For each possible split point (before index i)
        for (int i = 0; i <= n; i++) {
            if (i > 0 && s.charAt(i-1) == 'b') {
                countB++;
            }
            
            // 'a's remaining on right side
            int aRight = totalA - (i - countB);
            
            // Deletions needed = remove 'b's on left + remove 'a's on right
            int deletions = countB + aRight;
            minDeletions = Math.min(minDeletions, deletions);
        }
        
        return minDeletions;
    }
}