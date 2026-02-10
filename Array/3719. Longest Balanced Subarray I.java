// QUESTION: 3719. Longest Balanced Subarray I
// LINK: https://leetcode.com/problems/longest-balanced-subarray-i/description/?envType=daily-question&envId=2026-02-10

// SOLUTION: 

class Solution {
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        int maxLen = 0;

        for(int start = 0; start < n; start++)
        {
            Set<Integer> evens = new HashSet<>();
            Set<Integer> odds = new HashSet<>();

            for(int end = start; end < n; end++)
            {
                if(nums[end] % 2 == 0)
                {
                    evens.add(nums[end]);
                }
                else
                {
                    odds.add(nums[end]);
                }

                if(evens.size() == odds.size())
                {
                    maxLen = Math.max(maxLen, end - start + 1);
                }
            }
        }
        return maxLen;
    }
}