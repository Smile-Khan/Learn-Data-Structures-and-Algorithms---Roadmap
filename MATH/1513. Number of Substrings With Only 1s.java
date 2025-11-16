// QUESTION: 1513. Number of Substrings With Only 1s 
// LINK: https://leetcode.com/problems/number-of-substrings-with-only-1s/description/?envType=daily-question&envId=2025-11-16

// SOLUTION: Approach 1: Brute Force (Check All Substrings)


class Solution {
    public int numSub(String s) {
        int n = s.length();
        int count = 0;
        int mod = 1000000007;
        
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                for (int j = i; j < n; j++) {
                    if (s.charAt(j) == '1') {
                        count = (count + 1) % mod;
                    } else {
                        break;
                    }
                }
            }
        }
        
        return count;
    }
}

// Approach 2: Count Consecutive 1's Groups

class Solution {
    public int numSub(String s) {
        int mod = 1000000007;
        long total = 0;
        int currentCount = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                currentCount++;
            } else {
                if (currentCount > 0) {
                    total = (total + (long) currentCount * (currentCount + 1) / 2) % mod;
                    currentCount = 0;
                }
            }
        }
        
        // Don't forget the last group
        if (currentCount > 0) {
            total = (total + (long) currentCount * (currentCount + 1) / 2) % mod;
        }
        
        return (int) total;
    }
}


// Approach 3: Single Pass with Running Count

class Solution {
    public int numSub(String s) {
        int mod = 1000000007;
        long result = 0;
        int consecutiveOnes = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '1') {
                consecutiveOnes++;
                result = (result + consecutiveOnes) % mod;
            } else {
                consecutiveOnes = 0;
            }
        }
        
        return (int) result;
    }
}


// Approach 4: Mathematical Formula for Each Group


class Solution {
    public int numSub(String s) {
        int mod = 1000000007;
        long count = 0;
        int start = 0;
        
        while (start < s.length()) {
            if (s.charAt(start) == '1') {
                int end = start;
                while (end < s.length() && s.charAt(end) == '1') {
                    end++;
                }
                int length = end - start;
                count = (count + (long) length * (length + 1) / 2) % mod;
                start = end;
            } else {
                start++;
            }
        }
        
        return (int) count;
    }
}


// Approach 5: Using While Loop with Pointer

class Solution {
    public int numSub(String s) {
        int mod = 1000000007;
        long result = 0;
        int i = 0;
        int n = s.length();
        
        while (i < n) {
            if (s.charAt(i) == '1') {
                int j = i;
                while (j < n && s.charAt(j) == '1') {
                    j++;
                }
                int len = j - i;
                result = (result + (long) len * (len + 1) / 2) % mod;
                i = j;
            } else {
                i++;
            }
        }
        
        return (int) result;
    }
}