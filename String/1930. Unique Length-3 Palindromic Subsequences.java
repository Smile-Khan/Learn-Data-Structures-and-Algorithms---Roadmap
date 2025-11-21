// QUESTION: 1930. Unique Length-3 Palindromic Subsequences
// LINK: https://leetcode.com/problems/unique-length-3-palindromic-subsequences/description/?envType=daily-question&envId=2025-11-21

// SOLUTION: Precompute First and Last Occurrences

class Solution {
    public int countPalindromicSubsequence(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);
        
        // Precompute first and last occurrences of each character
        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            if (first[idx] == -1) {
                first[idx] = i;
            }
            last[idx] = i;
        }
        
        int count = 0;
        
        // For each possible outer character
        for (int i = 0; i < 26; i++) {
            if (first[i] != -1 && last[i] != -1 && first[i] < last[i]) {
                // Count unique characters between first and last occurrence
                boolean[] seen = new boolean[26];
                for (int j = first[i] + 1; j < last[i]; j++) {
                    int midChar = s.charAt(j) - 'a';
                    if (!seen[midChar]) {
                        seen[midChar] = true;
                        count++;
                    }
                }
            }
        }
        
        return count;
    }
}