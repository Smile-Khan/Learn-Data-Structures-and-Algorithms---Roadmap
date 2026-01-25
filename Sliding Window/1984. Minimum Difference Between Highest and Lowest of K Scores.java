// QUESTION: 1984. Minimum Difference Between Highest and Lowest of K Scores
// LINK: https://leetcode.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores/?envType=daily-question&envId=2026-01-25

// SOLUTION: SLIDING WINDOW + SORTING 

class Solution {
    public int minimumDifference(int[] nums, int k) {
        // If k is 1, the difference is always 0. Even if you got that right.
        if(k == 1) return 0;

        // Step 1: Sort the battleField. O(N log N)
        Arrays.sort(nums);

        // Step 2: Initialize with the maximum possible value;
        int minDiff = Integer.MAX_VALUE;

        // Step 3: Slide the winodw of size k across the sorted scores
        // We must stop at n - k to avoid falling off the edge of the world
        for(int i = 0; i <= nums.length - k; i++)
        {
            int currentDiff = nums[i + k - 1] - nums[i];

            if(currentDiff < minDiff)
            {
                minDiff = currentDiff;
            }
        }
        return minDiff;
    }
}