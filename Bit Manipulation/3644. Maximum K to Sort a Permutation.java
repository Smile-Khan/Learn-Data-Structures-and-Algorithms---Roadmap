// QUESTION: 3644. Maximum K to Sort a Permutation
// LINK: https://leetcode.com/problems/maximum-k-to-sort-a-permutation/description/?envType=daily-question&envId=2026-10-23

// SOLUTION: 

class Solution {
    public int sortPermutation(int[] nums) {
        int ans = -1;   // Initialize with all bits set to 1

        for(int i = 0; i < nums.length; i++)
        {
            // If the element is not in its correct sorted position
            if(nums[i] != i)
            {
                ans &= nums[i]; //Accumulate the bitwise AND restriction
            }
        }
        // If no element were misplaced (already sorted), ans remains -1, so return 0
        return Math.max(ans, 0);
    }
}