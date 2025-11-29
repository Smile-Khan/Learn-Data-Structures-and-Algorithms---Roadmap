    // QUESTION: 3512. Minimum Operations to Make Array Sum Divisible by K
    // LINK: https://leetcode.com/problems/minimum-operations-to-make-array-sum-divisible-by-k/description/?envType=daily-question&envId=2025-11-29

    // SOLUTION: Greedy 

    class Solution {
    public int minOperations(int[] nums, int k) {
        long sum = 0;
        for (int n : nums) sum += n;
        return (int)(sum % k);
    }
}