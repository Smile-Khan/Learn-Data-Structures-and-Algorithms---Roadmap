// QUESTION: 3577. Count the Number of Computer Unlocking Permutations
// LINK: https://leetcode.com/problems/count-the-number-of-computer-unlocking-permutations/description/?envType=daily-question&envId=2025-12-10

// SOLUTION: 

class Solution {
    public int countPermutations(int[] complexity) {
        int n = complexity.length;

        for(int i = 1; i < n; i++)
        {
            if(complexity[i] <= complexity[0])
            {
                return 0;
            }
        }
        int ans = 1;
        int MOD = 1_000_000_007;

        for(int i = 2; i < n; i++)
        {
            ans = (int) (((long) ans * i) % MOD);
        }
        return ans;
    }
}