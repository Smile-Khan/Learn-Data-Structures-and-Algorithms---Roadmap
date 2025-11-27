// QUESTION: 3381. Maximum Subarray Sum With Length Divisible by K
// LINK: https://leetcode.com/problems/maximum-subarray-sum-with-length-divisible-by-k/description/?envType=daily-question&envId=2025-11-27

// SOLUTION: Most Efficient Prefix Sum

class Solution {
    public long maxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long maxSum = Long.MIN_VALUE;
        long currentSum = 0;
        
        // minPrefix[r] stores minimum prefix sum with index ≡ r (mod k)
        long[] minPrefix = new long[k];
        Arrays.fill(minPrefix, Long.MAX_VALUE);
        minPrefix[0] = 0; // prefix sum before index 0 is 0
        
        for (int i = 0; i < n; i++) {
            currentSum += nums[i];
            int remainder = (i + 1) % k; // prefixSum[i+1] % k
            
            if (minPrefix[remainder] != Long.MAX_VALUE) {
                maxSum = Math.max(maxSum, currentSum - minPrefix[remainder]);
            }
            
            // Update minPrefix for the NEXT iteration
            // We want min prefix sum with index ≡ (i+1) % k
            minPrefix[remainder] = Math.min(minPrefix[remainder], currentSum);
        }
        
        return maxSum;
    }
}