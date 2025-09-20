// QUESTION: 300. Longest Increasing Subsequence
// LINK: https://leetcode.com/problems/longest-increasing-subsequence/description/

// SOLUTION:

class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n+1];

        for(int i = 0; i < n; i++)
        {
            dp[i] = 1;
        }

        for(int i = 1; i < n; i++)
        {
            for(int j = 0; j < i; j++)
            {
                if(nums[j] < nums[i])
                {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                
            }
        }
        int maxLength = 0;
        for(int i = 0; i < n;i++)
        {
            maxLength = Math.max(maxLength, dp[i]);
        }
        return maxLength;
    }
}