// QUESTION: 3573. Best Time to Buy and Sell Stock V
// LINK: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-v/description/?envType=daily-question&envId=2025-12-17

// SOLUTION: 

class Solution {
    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        // dp[i][j]: max profit with j transactions ending by day i
        long[][] dp = new long[n + 1][k + 1];
        
        for (int j = 1; j <= k; j++) {
            long bestBuy = Long.MIN_VALUE / 2; // max(dp[t][j-1] - prices[t]) for t < i
            long bestSell = Long.MIN_VALUE / 2; // max(dp[t][j-1] + prices[t]) for t < i
            
            for (int i = 1; i <= n; i++) {
                // Don't do any transaction on day i
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                
                // Complete a normal transaction (buy earlier, sell today)
                dp[i][j] = Math.max(dp[i][j], bestBuy + prices[i - 1]);
                
                // Complete a short transaction (sell earlier, buy today)
                dp[i][j] = Math.max(dp[i][j], bestSell - prices[i - 1]);
                
                // Update bestBuy and bestSell for next iteration
                bestBuy = Math.max(bestBuy, dp[i][j - 1] - prices[i - 1]);
                bestSell = Math.max(bestSell, dp[i][j - 1] + prices[i - 1]);
            }
        }
        
        return dp[n][k];
    }
}

// Optimized DP (Space Optimized)

class Solution {
    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        if (n < 2 || k == 0) return 0;
        
        // We can think of this as at most 2k operations (buy/sell pairs)
        // Each transaction can be either: buy then sell OR sell then buy
        long[] dp = new long[2 * k + 1];
        Arrays.fill(dp, Long.MIN_VALUE / 2);
        dp[0] = 0; // 0 operations
        
        for (int price : prices) {
            // Process operations in reverse to avoid using same day multiple times
            for (int j = 2 * k; j > 0; j--) {
                if (j % 2 == 1) {
                    // Odd position: buy operation
                    // For normal transaction: buy now (decrease cash)
                    // For short transaction: this is buying back (after selling short)
                    dp[j] = Math.max(dp[j], dp[j - 1] - price);
                } else {
                    // Even position: sell operation  
                    // For normal transaction: sell now (increase cash)
                    // For short transaction: this is selling short (increase cash)
                    dp[j] = Math.max(dp[j], dp[j - 1] + price);
                }
            }
        }
        
        // Find max profit with at most 2k operations
        long maxProfit = 0;
        for (int j = 0; j <= 2 * k; j += 2) {
            maxProfit = Math.max(maxProfit, dp[j]);
        }
        
        return maxProfit;
    }
}