// QUESTION: 1680. Concatenation of Consecutive Binary Numbers
// LINK: https://leetcode.com/problems/concatenation-of-consecutive-binary-numbers/description/?envType=daily-question&envId=2026-02-28

// SOLUTION: 

class Solution {
    public int concatenatedBinary(int n) {
        long result = 0;  
        int MOD = 1_000_000_007;

        int length = 1;
        int powerOfTwo = 2;

        for(int i = 1; i <= n; i++)
        {
            // Update length when we reach a power of two
            if(i == powerOfTwo)
            {
                length++;
                powerOfTwo *= 2;
            }

            // Shift result left by 'length' bits and add current number
            result = ((result << length) % MOD + i) % MOD;
        }
        return (int) result;
    }
}