// QUESTION: Ways To Tile A Floor
// LINK: https://www.geeksforgeeks.org/problems/ways-to-tile-a-floor5836/1

// SOLUTION: RECURSIVE + DP

class Solution {
    public int numberOfWays(int n) {
        // code here
        // int result = 0;
        // if(n == 1)
        // {
        //     return 1;
        // }
        // if(n == 2)
        // {
        //     return 2;
        // }
        
        // return result = numberOfWays(n-1) + numberOfWays(n-2);
        
        // DP approach
        if(n <= 2)
        {
            return n;
        }
        
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        
        for(int i = 3; i <= n; i++)
        {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
};