// QUESTION: 837. New 21 Game
// LINK: https://leetcode.com/problems/new-21-game/description/?envType=daily-question&envId=2025-08-17

// SOLUTION: 

class Solution {
    public double new21Game(int n, int k, int maxPts) {
        if(k == 0 || n >= k + maxPts)
        {
            return 1.0;
        }

        double[] dp = new double[n + 1];
        dp[0] = 1.0;

        double windowSum = 1.0;
        double result = 0.0;

        for(int i = 1; i <= n; i++)
        {
            dp[i] = windowSum / maxPts;
            if(i < k)
            {
                windowSum += dp[i];

            }
            else 
            {
                result += dp[i];
            }

            if(i >= maxPts)
            {
                windowSum -= dp[i - maxPts];
            }
        }
        return result;
    }
}