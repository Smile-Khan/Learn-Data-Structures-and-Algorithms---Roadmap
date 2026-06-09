// QUESTION: 3689. Maximum Total Subarray Value I
// LINK: https://leetcode.com/problems/maximum-total-subarray-value-i/description/?envType=daily-question&envId=2026-06-09

// SOLUTION: Greedy + Mathematical Observation


class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int min = nums[0];
        int max = nums[0];

        for(int num : nums)
        {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        long diff = max - min;
        return diff * k;
    }
}