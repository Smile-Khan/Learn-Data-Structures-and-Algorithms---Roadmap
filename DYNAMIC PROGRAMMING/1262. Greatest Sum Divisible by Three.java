// QUESTION: 1262. Greatest Sum Divisible by Three
// LINK: https://leetcode.com/problems/greatest-sum-divisible-by-three/description/?envType=daily-question&envId=2025-11-23

// SOLUTION: DP

class Solution {
    public int maxSumDivThree(int[] nums) {
        // Initialize DP: max sum for remainders 0, 1, 2
        int[] maxSum = new int[3];
        
        for (int num : nums) {
            // Create a copy for this iteration
            int[] current = maxSum.clone();
            
            for (int rem = 0; rem < 3; rem++) {
                if (maxSum[rem] != 0 || rem == 0) {
                    int newRem = (rem + num) % 3;
                    current[newRem] = Math.max(current[newRem], maxSum[rem] + num);
                }
            }
            
            // Also consider the number itself
            int numRem = num % 3;
            current[numRem] = Math.max(current[numRem], num);
            
            maxSum = current;
        }
        
        return maxSum[0];
    }
}