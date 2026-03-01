// QUESTION: 1689. Partitioning Into Minimum Number Of Deci-Binary Numbers
// LINK: https://leetcode.com/problems/partitioning-into-minimum-number-of-deci-binary-numbers/description/?envType=daily-question&envId=2026-03-01

// SOLUTION: GREEDY


class Solution {
    public int minPartitions(String n) {
        // Convert to char array for manipulation
        char[] digits = n.toCharArray();
        int operations = 0;
        
        while (!isAllZero(digits)) {
            // In each operation, subtract 1 from each non-zero digit
            for (int i = 0; i < digits.length; i++) {
                if (digits[i] > '0') {
                    digits[i]--;
                }
            }
            operations++;
        }
        
        return operations;
    }
    
    private boolean isAllZero(char[] digits) {
        for (char c : digits) {
            if (c != '0') return false;
        }
        return true;
    }
}


// APPROACH 2: MATHEMATICAL 

class Solution {
    public int minPartitions(String n) {
        int max = 0;
        
        // For each position, the digit tells us how many numbers
        // need to have '1' in that position
        for (int i = 0; i < n.length(); i++) {
            int digit = n.charAt(i) - '0';
            
            // This is like: to get digit d, we need at least d numbers
            // So the answer is the maximum across all positions
            max = Math.max(max, digit);
        }
        
        return max;
    }
}


// Binary Search on Answer

class Solution {
    public int minPartitions(String n) {
        int left = 1;
        int right = 9; // Maximum possible digit
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (canForm(n, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    private boolean canForm(String n, int k) {
        // With k deci-binary numbers, can we form n?
        // Each digit must be <= k (since max contribution from k numbers is k)
        for (int i = 0; i < n.length(); i++) {
            if (n.charAt(i) - '0' > k) {
                return false;
            }
        }
        return true;
    }
}