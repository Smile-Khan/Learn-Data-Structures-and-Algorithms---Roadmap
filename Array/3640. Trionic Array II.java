// QUESTION: 3640. Trionic Array II
// LINK: https://leetcode.com/problems/trionic-array-ii/description/?envType=daily-question&envId=2026-02-04

// SOLUTION: 

class Solution {
    public long maxSumTrionic(int[] nums) {
        int n = nums.length;
        long INF = (long) 2e15; // Greater than any possible sum

        long[] S = new long[n + 1];
        for(int i = 0; i < n; i++) S[i + 1] = S[i] + nums[i];

        long[] incEnd = new long[n];
        java.util.Arrays.fill(incEnd, -INF);
        for(int i = 1; i < n; i++)
        {
            if(nums[i] > nums[i - 1])
            {
                incEnd[i] = nums[i] + Math.max((long)nums[i - 1], incEnd[i - 1]);
            }
        }

        long[] incStart = new long[n];
        java.util.Arrays.fill(incStart, -INF);

        for(int i = n - 2; i >= 0; i--)
        {
            if(nums[i] < nums[i + 1])
            {
                incStart[i] = nums[i] + Math.max((long) nums[i + 1], incStart[i + 1]);
            }
        }

        long maxTrionic = -INF;

        // Scan for decreasing segments [p, q]
        int i = 0;
        while(i < n - 1)
        {
            int j = i;
            while(j + 1 < n && nums[j] > nums[j + 1])
            {
                j++;
            }

            // Current maximal decreasing segment is[i, j]
            // We need to find, p, q in [i, j] such that i <= p < q <= j
            // Maximize: (incEnd[p] - S[p + 1] + (incStart[q] + S[q]))

            long maxA = -INF;
            for(int k = i; k <= j; k++)
            {
                if(incStart[k] != -INF && maxA != -INF)
                {
                    maxTrionic = Math.max(maxTrionic, maxA + incStart[k] + S[k]);
                }
                if(incEnd[k] != -INF)
                {
                    maxA = Math.max(maxA, incEnd[k] - S[k + 1]);
                }
            }

            i = Math.max(i + 1, j);
        }
        return maxTrionic;
    }
}