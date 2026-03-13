// QUESTION: 3296. Minimum Number of Seconds to Make Mountain Height Zero
// LINK: https://leetcode.com/problems/minimum-number-of-seconds-to-make-mountain-height-zero/description/?envType=daily-question&envId=2026-03-13

// SOLUTION: BINARY SEARCH ON ANSWER

class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        long left = 1;
        long right = Long.MAX_VALUE / 2; // Safe upper bound
        
        while (left < right) {
            long mid = left + (right - left) / 2;
            
            if (canReduce(mountainHeight, workerTimes, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    private boolean canReduce(int target, int[] workerTimes, long time) {
        long total = 0;
        
        for (int t : workerTimes) {
            // Binary search to find max x for this worker
            long low = 1;
            long high = target; // Can't reduce more than total height
            long maxUnits = 0;
            
            while (low <= high) {
                long mid = low + (high - low) / 2;
                long timeNeeded = t * mid * (mid + 1) / 2;
                
                if (timeNeeded <= time) {
                    maxUnits = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            
            total += maxUnits;
            if (total >= target) return true;
        }
        
        return total >= target;
    }
}