// QUESTION: 3652. Best Time to Buy and Sell Stock using Strategy
// LINK: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-using-strategy/description/?envType=daily-question&envId=2025-12-18

// SOLUTION: SLIDING WINDOW 


class Solution {
    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        long initialProfit = 0;

        for(int i = 0; i < n; i++)
        {
            initialProfit += (long) strategy[i] * prices[i];
        }

        long[] holdDelta = new long[n];
        long[] sellDelta = new long[n];

        for(int i = 0; i < n; i++)
        {
            holdDelta[i] = (long) (0 - strategy[i]) * prices[i];
            sellDelta[i] = (long) (1 - strategy[i]) * prices[i];
        }

        long currentDelta = 0;
        int halfK = k / 2;

        // Sum the hold part
        for(int i = 0; i < halfK; i++)
        {
            currentDelta += holdDelta[i];
        }

        // Sum the sell part
        for(int i = halfK; i < k; i++)
        {
            currentDelta += sellDelta[i];
        }

        long maxDelta = currentDelta;

        for(int i = 1; i <= n - k; i++)
        {

            // The element leaving the window at position i - 1
            // It was in the HOLD section of the previous window
            currentDelta -= holdDelta[i - 1];

            //The element at position i - 1 + halfK was in the SELL section
            // of the previous window, but now it's leaving the window
            int leavingSellIndex = i - 1 + halfK;
            currentDelta -= sellDelta[leavingSellIndex];

            // The element entering at position i + halfK - 1
            // is now in the HOLD section of the current window
            int enteringHoldIndex = i + halfK - 1;
            currentDelta += holdDelta[enteringHoldIndex];

            // The element entering at position i + k - 1
            // is now in the SELL section of the current window
            int enteringSellIndex = i + k - 1;
            currentDelta += sellDelta[enteringSellIndex];

            maxDelta = Math.max(maxDelta, currentDelta);
        }
        return initialProfit + Math.max(0L, maxDelta);
    }
}