// QUESTION: 3653. XOR After Range Multiplication Queries I
// LINK: https://leetcode.com/problems/xor-after-range-multiplication-queries-i/description/?envType=daily-question&envId=2026-04-08

// SOLUTION: SIMULATION 

class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int MOD = 1_000_000_007;

        for(int[] query : queries)
        {
            int li = query[0];
            int ri = query[1];
            int ki = query[2];
            int vi = query[3];

            for(int idx = li; idx <= ri; idx += ki)
            {
                nums[idx] = (int)(((long) nums[idx] * vi) % MOD);
            }
        }

        int result = 0;
        for(int num : nums)
        {
            result ^= num;
        }
        return result;
    }
}