// QUESTION: 1015. Smallest Integer Divisible by K
// LINK: https://leetcode.com/problems/smallest-integer-divisible-by-k/description/?envType=daily-question&envId=2025-11-25

// SOLUTION: 


class Solution {
    public int smallestRepunitDivByK(int k) {
        // Quick check: if k has factors 2 or 5, impossible

        if(k % 2 == 0 || k % 5 == 0)
        {
            return -1;
        }

        int remainder = 0;

        for(int n = 1; n <= k; n++)
        {
            remainder = (remainder * 10 + 1) % k;

            if(remainder == 0)
            {
                return n;
            }
        }
        return -1;
    }
}