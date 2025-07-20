QUESTION LINK:- https://leetcode.com/problems/subarray-sums-divisible-by-k/description/


SOLUTION:

class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> remainderCount = new HashMap<>();
        remainderCount.put(0, 1); // Handle subarrays from start

        int prefixSum = 0;
        int count = 0;

        for(int num : nums) {
            prefixSum += num;
            int remainder = prefixSum % k;

            // Handle negative remainders
            if(remainder < 0) remainder += k;

            // Count subarrays ending at current position
            count += remainderCount.getOrDefault(remainder, 0);

            // Update frequency map
            remainderCount.put(remainder, remainderCount.getOrDefault(remainder, 0) + 1);
        }

        return count;
    }
}