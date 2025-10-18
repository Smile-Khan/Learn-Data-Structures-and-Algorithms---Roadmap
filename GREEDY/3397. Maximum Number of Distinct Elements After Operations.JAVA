// QUESTION: 3397. Maximum Number of Distinct Elements After Operations
// LINK: https://leetcode.com/problems/maximum-number-of-distinct-elements-after-operations/editorial/?envType=daily-question&envId=2025-10-18

// SOLUTION: GREEDY 

class Solution {
    public int maxDistinctElements(int[] nums, int k) {
        Arrays.sort(nums);
        long prev = Long.MIN_VALUE;
        int result = 0;
        
        for (int num : nums) {
            long lower = (long) num - k;
            long upper = (long) num + k;
            
            // Try to assign the smallest possible value that's greater than prev
            long assign = Math.max(lower, prev + 1);
            
            if (assign <= upper) {
                result++;
                prev = assign;
            }
        }
        
        return result;
    }
}