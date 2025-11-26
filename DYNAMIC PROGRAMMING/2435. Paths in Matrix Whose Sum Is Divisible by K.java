// QUESTION: 2435. Paths in Matrix Whose Sum Is Divisible by K
// LINK: https://leetcode.com/problems/paths-in-matrix-whose-sum-is-divisible-by-k/description/?envType=daily-question&envId=2025-11-26

// SOLUTION: Brute Force DFS (Recursive)

class Solution {
    private int mod = 1000000007;
    
    public int numberOfPaths(int[][] grid, int k) {
        return dfs(0, 0, 0, grid, k);
    }
    
    private int dfs(int i, int j, int sum, int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        
        // Base case: out of bounds
        if (i >= m || j >= n) return 0;
        
        // Update current sum
        sum = (sum + grid[i][j]) % k;
        
        // Base case: reached destination
        if (i == m - 1 && j == n - 1) {
            return (sum % k == 0) ? 1 : 0;
        }
        
        // Move down and right
        int down = dfs(i + 1, j, sum, grid, k);
        int right = dfs(i, j + 1, sum, grid, k);
        
        return (down + right) % mod;
    }
}


// Approach 2: 3D Dynamic Programming (Top-Down with Memo)

class Solution {
    private int mod = 1000000007;
    
    public int numberOfPaths(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        Integer[][][] memo = new Integer[m][n][k];
        return dfs(0, 0, 0, grid, k, memo);
    }
    
    private int dfs(int i, int j, int remainder, int[][] grid, int k, Integer[][][] memo) {
        int m = grid.length, n = grid[0].length;
        
        if (i >= m || j >= n) return 0;
        
        // Update remainder
        remainder = (remainder + grid[i][j]) % k;
        
        if (i == m - 1 && j == n - 1) {
            return (remainder == 0) ? 1 : 0;
        }
        
        if (memo[i][j][remainder] != null) {
            return memo[i][j][remainder];
        }
        
        int down = dfs(i + 1, j, remainder, grid, k, memo);
        int right = dfs(i, j + 1, remainder, grid, k, memo);
        
        return memo[i][j][remainder] = (down + right) % mod;
    }
}


// Approach 3: Bottom-Up DP (Most Optimal)


class Solution {
    public int numberOfPaths(int[][] grid, int k) {
        int mod = 1000000007;
        int m = grid.length, n = grid[0].length;
        
        // dp[i][j][r] = number of ways to reach (i,j) with sum % k = r
        int[][][] dp = new int[m][n][k];
        
        // Initialize starting position
        int startRem = grid[0][0] % k;
        dp[0][0][startRem] = 1;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int r = 0; r < k; r++) {
                    if (dp[i][j][r] == 0) continue;
                    
                    // Move down
                    if (i + 1 < m) {
                        int newRem = (r + grid[i + 1][j]) % k;
                        dp[i + 1][j][newRem] = (dp[i + 1][j][newRem] + dp[i][j][r]) % mod;
                    }
                    
                    // Move right
                    if (j + 1 < n) {
                        int newRem = (r + grid[i][j + 1]) % k;
                        dp[i][j + 1][newRem] = (dp[i][j + 1][newRem] + dp[i][j][r]) % mod;
                    }
                }
            }
        }
        
        return dp[m - 1][n - 1][0];
    }
}

// Approach 4: Space Optimized DP

class Solution {
    public int numberOfPaths(int[][] grid, int k) {
        int mod = 1000000007;
        int m = grid.length, n = grid[0].length;
        
        // Use two rows to optimize space
        int[][][] dp = new int[2][n][k];
        
        // Initialize starting position
        int startRem = grid[0][0] % k;
        dp[0][0][startRem] = 1;
        
        for (int i = 0; i < m; i++) {
            int curr = i % 2;
            int next = (i + 1) % 2;
            
            // Clear next row for reuse
            if (i < m - 1) {
                for (int j = 0; j < n; j++) {
                    Arrays.fill(dp[next][j], 0);
                }
            }
            
            for (int j = 0; j < n; j++) {
                for (int r = 0; r < k; r++) {
                    if (dp[curr][j][r] == 0) continue;
                    
                    // Move down
                    if (i + 1 < m) {
                        int newRem = (r + grid[i + 1][j]) % k;
                        dp[next][j][newRem] = (dp[next][j][newRem] + dp[curr][j][r]) % mod;
                    }
                    
                    // Move right
                    if (j + 1 < n) {
                        int newRem = (r + grid[i][j + 1]) % k;
                        dp[curr][j + 1][newRem] = (dp[curr][j + 1][newRem] + dp[curr][j][r]) % mod;
                    }
                }
            }
        }
        
        return dp[(m - 1) % 2][n - 1][0];
    }
}