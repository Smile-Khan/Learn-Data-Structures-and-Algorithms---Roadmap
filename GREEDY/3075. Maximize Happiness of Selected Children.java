// QUESTION: 3075. Maximize Happiness of Selected Children
// LINK: https://leetcode.com/problems/maximize-happiness-of-selected-children/description/?envType=daily-question&envId=2025-12-25

// SOLUTION: GREEDY + SORTING

class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        int n = happiness.length;
        long totalHappiness = 0;
        int penalty = 0;

        Arrays.sort(happiness);
        for(int i = n-1; i >= n-k; i--)
        {
            long effectiveHappiness = happiness[i] - penalty;

            if(effectiveHappiness > 0)
            {
                totalHappiness += effectiveHappiness;
            }
            penalty++;
        }
        return totalHappiness;
    }
}