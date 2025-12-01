// QUESTION: 2141. Maximum Running Time of N Computers
// LINK: https://leetcode.com/problems/maximum-running-time-of-n-computers/description/?envType=daily-question&envId=2025-12-01

// SOLUTION: BINARY SEARCH + GREEDY 

class Solution {
    public long maxRunTime(int n, int[] batteries) {
        long low = 1;
        long high = 0;
        
        // Calculate maximum possible run time
        for (int battery : batteries) {
            high += battery;
        }
        high /= n;
        
        // Binary search
        while (low <= high) {
            long mid = low + (high - low) / 2;
            
            if (isPossible(n, batteries, mid)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return high;
    }
    
    private boolean isPossible(int n, int[] batteries, long target) {
        long available = 0;
        
        // For each battery, it can contribute at most 'target' minutes
        // because a computer can only use one battery at a time
        for (int battery : batteries) {
            available += Math.min(battery, target);
        }
        
        // Check if we have enough to run n computers for target minutes
        return available >= n * target;
    }
}