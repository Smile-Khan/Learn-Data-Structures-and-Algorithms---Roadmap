// QUESTION: 3578. Count Partitions With Max-Min Difference at Most K
// LINK: https://leetcode.com/problems/count-partitions-with-max-min-difference-at-most-k/description/?envType=daily-question&envId=2025-12-06


// SOLUTION: 

class Solution {
    public int countPartitions(int[] nums, int k) {
        int MOD = 1000000007;
        int n = nums.length;
        
        // dp[i] = number of valid partitions starting at i
        int[] dp = new int[n + 1];
        dp[n] = 1;
        
        // Deques to track min and max in current window
        Deque<Integer> minDeque = new ArrayDeque<>();
        Deque<Integer> maxDeque = new ArrayDeque<>();
        
        // prefix sum of dp values in current valid window
        int prefixSum = 0;
        int left = n;
        
        for (int i = n - 1; i >= 0; i--) {
            // Update deques with current element
            while (!minDeque.isEmpty() && nums[minDeque.peekLast()] >= nums[i]) {
                minDeque.pollLast();
            }
            minDeque.addLast(i);
            
            while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= nums[i]) {
                maxDeque.pollLast();
            }
            maxDeque.addLast(i);
            
            // Add current dp value to window sum
            prefixSum = (prefixSum + dp[i + 1]) % MOD;
            
            // Shrink window from left while condition violated
            while (!minDeque.isEmpty() && !maxDeque.isEmpty() && 
                   nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > k) {
                // Remove leftmost dp value from window sum
                prefixSum = (prefixSum - dp[left] + MOD) % MOD;
                
                // Remove elements that are out of window
                if (minDeque.peekFirst() == left - 1) {
                    minDeque.pollFirst();
                }
                if (maxDeque.peekFirst() == left - 1) {
                    maxDeque.pollFirst();
                }
                
                left--;
            }
            
            dp[i] = prefixSum;
        }
        
        return dp[0];
    }
}