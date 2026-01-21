// QUESTION: 3315. Construct the Minimum Bitwise Array II
// LINK: https://leetcode.com/problems/construct-the-minimum-bitwise-array-ii/description/?envType=daily-question&envId=2026-01-21

// SOLUTION: 

class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];

        for(int i = 0; i < n; i++)
        {
            int target = nums.get(i);

            if((target & 1) == 0)
            {
                ans[i] = -1;
            }
            else 
            {
                // (target + 1) & ~target isolates the lowest 0-bit as a power of 2.
                // Example: target = 11 (1011), target + 1 = 12 (1100)
                // ~target = ...0100. Result = 0100 (4).
                int lowestZeroBit = (target + 1) & ~target;
                
                // Shift right to find the highest bit of the trailing 1s.
                // 0100 >> 1 = 0010 (2).
                // Flip it using XOR to minimize the value.
                // 1011 ^ 0010 = 1001 (9).
                ans[i] = target ^ (lowestZeroBit >> 1);
            }
        }
        return ans;
    }
}