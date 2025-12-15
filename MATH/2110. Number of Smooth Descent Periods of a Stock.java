// QUESTION: 2110. Number of Smooth Descent Periods of a Stock
// LINK: https://leetcode.com/problems/number-of-smooth-descent-periods-of-a-stock/description/?envType=daily-question&envId=2025-12-15

// SOLUTION: Approach 1: Brute Force (O(n²))

class Solution {
    public long getDescentPeriods(int[] prices) {
        int n = prices.length;
        long count = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (j == i) {
                    count++; // Single day always valid
                } else if (prices[j] == prices[j - 1] - 1) {
                    count++; // Valid if each day is exactly 1 less than previous
                } else {
                    break; // Not smooth descent, stop checking longer periods
                }
            }
        }
        
        return count;
    }
}


//Approach 2: Dynamic Programming (O(n))

class Solution {
    public long getDescentPeriods(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        
        long[] dp = new long[n]; // dp[i] = number of smooth periods ending at i
        dp[0] = 1; // Single day is always valid
        long total = 1;
        
        for (int i = 1; i < n; i++) {
            if (prices[i] == prices[i - 1] - 1) {
                // Can extend previous smooth descent
                dp[i] = dp[i - 1] + 1;
            } else {
                // Start new smooth descent
                dp[i] = 1;
            }
            total += dp[i];
        }
        
        return total;
    }
}


// Approach 3: Two Pointers/Sliding Window (O(n))

class Solution {
    public long getDescentPeriods(int[] prices) {
        int n = prices.length;
        long count = 0;
        
        int left = 0;
        for (int right = 0; right < n; right++) {
            // Check if we can extend the current window
            if (right > left && prices[right] != prices[right - 1] - 1) {
                // Not smooth, move left pointer
                left = right;
            }
            
            // Add all subarrays ending at 'right'
            // Number of subarrays ending at 'right' = (right - left + 1)
            count += (right - left + 1);
        }
        
        return count;
    }
}


// Approach 4: Count Consecutive Smooth Descents (Most Optimal)


class Solution {
    public long getDescentPeriods(int[] prices) {
        int n = prices.length;
        long count = 1; // First element always forms a period
        
        int consecutive = 1; // Length of current smooth descent
        
        for (int i = 1; i < n; i++) {
            if (prices[i] == prices[i - 1] - 1) {
                consecutive++;
            } else {
                consecutive = 1;
            }
            count += consecutive;
        }
        
        return count;
    }
}

// Approach 5: Mathematical Formula (Optimized)

class Solution {
    public long getDescentPeriods(int[] prices) {
        int n = prices.length;
        long total = 0;
        int currentLength = 1;
        
        for (int i = 0; i < n; i++) {
            if (i > 0 && prices[i] == prices[i - 1] - 1) {
                // Continue the smooth descent
                currentLength++;
            } else {
                // Start new smooth descent
                currentLength = 1;
            }
            total += currentLength;
        }
        
        return total;
    }
}