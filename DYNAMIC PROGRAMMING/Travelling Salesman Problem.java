// QUESTION:Travelling Salesman Problem
// LINK: https://www.geeksforgeeks.org/problems/travelling-salesman-problem2732/1

// SOLUTION: Space optimized DP 

class Solution {
    public int tsp(int[][] cost) {
        int n = cost.length;
        if (n == 1) return 0;
        
        int totalStates = 1 << n;
        int INF = Integer.MAX_VALUE / 2;
        
        // dp[mask][i] = minimum cost to reach city i with visited cities represented by mask
        int[][] dp = new int[totalStates][n];
        for (int[] row : dp) Arrays.fill(row, INF);
        
        // Start from city 0
        dp[1][0] = 0;
        
        // Iterate through all masks
        for (int mask = 0; mask < totalStates; mask++) {
            for (int i = 0; i < n; i++) {
                if (dp[mask][i] == INF) continue;
                
                // Try to go to all unvisited cities
                for (int j = 0; j < n; j++) {
                    if ((mask & (1 << j)) == 0) { // j not visited
                        int newMask = mask | (1 << j);
                        dp[newMask][j] = Math.min(dp[newMask][j], 
                                                 dp[mask][i] + cost[i][j]);
                    }
                }
            }
        }
        
        // Find minimum Hamiltonian cycle
        int minCost = INF;
        int fullMask = totalStates - 1;
        
        for (int i = 1; i < n; i++) {
            if (dp[fullMask][i] != INF) {
                minCost = Math.min(minCost, dp[fullMask][i] + cost[i][0]);
            }
        }
        
        return minCost;
    }
}