// QUESTION: 3432. Count Partitions with Even Sum Difference
// LINK: https://leetcode.com/problems/count-partitions-with-even-sum-difference/description/?envType=daily-question&envId=2025-12-05

// SOLUTION: 

class Solution {
    public int countPartitions(int[] nums) {
        int n = nums.length;
        // Using long just to be safe
        long totalSum = 0;

        // One pathetic loop to get the sum.
        for(int num : nums)
        {
            totalSum += num;
        }

        // The grand, complex, multi-layered decision-making process
        if(totalSum % 2 == 0)
        {
            // The total sum is even, so every partition is a winner
            // There are n-1 possible places to make a cut
            return n - 1;
        }
        else 
        {
            // The total sum is odd. It's impossible. Give up
            return 0;
        }
    }
}