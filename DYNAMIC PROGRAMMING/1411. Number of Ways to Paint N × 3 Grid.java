// QUESTION: 1411. Number of Ways to Paint N × 3 Grid
// LINK: https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/description/?envType=daily-question&envId=2026-01-03

// SOLUTION: DYNAMIC PROGRAMMING

class Solution {
    public int numOfWays(int n) {
        long mod = 1_000_000_007;
        long aba = 6;
        long abc = 6;

        // The battle intensifies as we climb the rows
        for(int i = 1; i < n; i++)
        {
            long prevAba = aba;
            long prevAbc = abc;

            // The transition matrix applied in real - time
            aba = (3 * prevAba + 2 * prevAbc) % mod;
            abc = (2 * prevAba + 2 * prevAbc) % mod;
        }
        return (int) ((aba + abc) % mod);
    }
}