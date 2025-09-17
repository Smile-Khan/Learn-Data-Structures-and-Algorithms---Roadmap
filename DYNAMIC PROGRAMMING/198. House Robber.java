// QUESTION: 198. House Robber
// LINK: https://leetcode.com/problems/house-robber/description/

// SOLUTION: 

class Solution {
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        
        // Only need previous two values
        int prev2 = 0;        // dp[i-2]
        int prev1 = nums[0];  // dp[i-1]
        
        for (int i = 1; i < nums.length; i++) {
            int current = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
}