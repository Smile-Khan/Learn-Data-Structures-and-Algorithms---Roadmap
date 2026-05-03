// QUESTION: 796. Rotate String
// LINK: https://leetcode.com/problems/rotate-string/description/?envType=daily-question&envId=2026-05-03

// SOLUTION: Approach 1: String Concatenation (Simplest, O(n²) time)

class Solution {
    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) return false;
        // If goal is a rotation of s, it will be a substring of s+s
        return (s + s).contains(goal);
    }
}

// APPROACH 2: Using KMP (O(n) time, O(n) space)


class Solution {
    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) return false;
        if (s.equals(goal)) return true;
        
        String doubled = s + s;
        return kmpSearch(doubled, goal);
    }
    
    private boolean kmpSearch(String text, String pattern) {
        int[] lps = computeLPS(pattern);
        int i = 0, j = 0;
        
        while (i < text.length()) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (j == pattern.length()) return true;
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }
        return false;
    }
    
    private int[] computeLPS(String pattern) {
        int[] lps = new int[pattern.length()];
        int len = 0;
        int i = 1;
        
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else if (len > 0) {
                len = lps[len - 1];
            } else {
                lps[i] = 0;
                i++;
            }
        }
        return lps;
    }
}

// Approach 3: Character-by-Character Check (O(n²) time, O(1) space)

class Solution {
    public boolean rotateString(String s, String goal) {
        int n = s.length();
        if(n != goal.length()) return false;
        if(n == 0) return true;

        // Try each possible rotation start
        for(int start = 0; start < n; start++)
        {
            boolean isMatch = true;
            for(int i = 0; i < n; i++)
            {
                if(s.charAt((start + i) % n) != goal.charAt(i))
                {
                    isMatch = false;
                    break;
                }
            }
            if(isMatch) return true;
        }
        return false;
    }
}