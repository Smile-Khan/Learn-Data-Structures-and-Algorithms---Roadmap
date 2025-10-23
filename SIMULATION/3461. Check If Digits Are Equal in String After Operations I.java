// QUESTION: 3461. Check If Digits Are Equal in String After Operations I
// LINK: https://leetcode.com/problems/check-if-digits-are-equal-in-string-after-operations-i/description/?envType=daily-question&envId=2025-10-23

// SOLUTION: Most Practical Solution

class Solution {
    public boolean hasSameDigits(String s) {
        // Convert to integer array for easier manipulation
        int n = s.length();
        int[] digits = new int[n];
        for (int i = 0; i < n; i++) {
            digits[i] = s.charAt(i) - '0';
        }
        
        // Simulate the process
        while (n > 2) {
            for (int i = 0; i < n - 1; i++) {
                digits[i] = (digits[i] + digits[i + 1]) % 10;
            }
            n--;
        }
        
        return digits[0] == digits[1];
    }
}

// Alternative Solution:Efficient Simulation with Early Exit

class Solution {
    public boolean hasSameDigits(String s) {
        while (s.length() > 2) {
            // Early exit if all characters become same
            boolean allSame = true;
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) != s.charAt(0)) {
                    allSame = false;
                    break;
                }
            }
            if (allSame) return true;
            
            StringBuilder next = new StringBuilder();
            for (int i = 0; i < s.length() - 1; i++) {
                int sum = (s.charAt(i) - '0' + s.charAt(i + 1) - '0') % 10;
                next.append(sum);
            }
            s = next.toString();
        }
        return s.charAt(0) == s.charAt(1);
    }
}

//Approach 3: Simulation with Char Array

class Solution {
    public boolean hasSameDigits(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        
        while (n > 2) {
            for (int i = 0; i < n - 1; i++) {
                int digit1 = arr[i] - '0';
                int digit2 = arr[i + 1] - '0';
                arr[i] = (char) ((digit1 + digit2) % 10 + '0');
            }
            n--;
        }
        
        return arr[0] == arr[1];
    }
}

// Approach 4: Direct Simulation (Most Intuitive)
class Solution {
    public boolean hasSameDigits(String s) {
        while (s.length() > 2) {
            StringBuilder next = new StringBuilder();
            for (int i = 0; i < s.length() - 1; i++) {
                int digit1 = s.charAt(i) - '0';
                int digit2 = s.charAt(i + 1) - '0';
                int sum = (digit1 + digit2) % 10;
                next.append(sum);
            }
            s = next.toString();
        }
        return s.charAt(0) == s.charAt(1);
    }
}