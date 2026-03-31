// QUESTION: 3474. Lexicographically Smallest Generated String
// LINK: https://leetcode.com/problems/lexicographically-smallest-generated-string/description/?envType=daily-question&envId=2026-03-31

// SOLUTION: GREEDY

class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int totalLen = n + m - 1;
        
        char[] word = new char[totalLen];
        Arrays.fill(word, '?');
        
        // First, handle all 'T' constraints (mandatory matches)
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    int idx = i + j;
                    if (word[idx] == '?') {
                        word[idx] = str2.charAt(j);
                    } else if (word[idx] != str2.charAt(j)) {
                        return ""; // Conflict
                    }
                }
            }
        }
        
        // Now handle 'F' constraints: ensure substring != str2
        // We'll fill from left to right, preferring 'a' when possible
        for (int i = 0; i < totalLen; i++) {
            if (word[i] == '?') {
                // Try 'a' first
                word[i] = 'a';
                
                // Check if this creates any invalid 'F' constraint
                boolean needChange = false;
                for (int start = Math.max(0, i - m + 1); start <= Math.min(n - 1, i); start++) {
                    if (str1.charAt(start) == 'F') {
                        // Check if substring starting at start now equals str2
                        boolean matches = true;
                        for (int j = 0; j < m; j++) {
                            int idx = start + j;
                            if (idx >= totalLen || word[idx] == '?') {
                                matches = false;
                                break;
                            }
                            if (word[idx] != str2.charAt(j)) {
                                matches = false;
                                break;
                            }
                        }
                        if (matches) {
                            needChange = true;
                            break;
                        }
                    }
                }
                
                if (needChange) {
                    // Try next smallest character
                    word[i] = 'b';
                    // Verify again
                    boolean stillInvalid = false;
                    for (int start = Math.max(0, i - m + 1); start <= Math.min(n - 1, i); start++) {
                        if (str1.charAt(start) == 'F') {
                            boolean matches = true;
                            for (int j = 0; j < m; j++) {
                                int idx = start + j;
                                if (idx >= totalLen || word[idx] == '?') {
                                    matches = false;
                                    break;
                                }
                                if (word[idx] != str2.charAt(j)) {
                                    matches = false;
                                    break;
                                }
                            }
                            if (matches) {
                                return ""; // No valid character
                            }
                        }
                    }
                }
            }
        }
        
        // Final verification
        for (int i = 0; i < n; i++) {
            boolean matches = true;
            for (int j = 0; j < m; j++) {
                if (word[i + j] != str2.charAt(j)) {
                    matches = false;
                    break;
                }
            }
            if (str1.charAt(i) == 'T' && !matches) return "";
            if (str1.charAt(i) == 'F' && matches) return "";
        }
        
        return new String(word);
    }
}