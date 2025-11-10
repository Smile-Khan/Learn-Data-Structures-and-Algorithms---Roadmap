QUESTION: Stock Buy and Sell with Cooldown
LINK: https://www.geeksforgeeks.org/problems/buy-stock-with-cooldown/1

SOLUTION: DP
class Solution {
    public int maxProfit(int arr[]) {
        // Code here
         int n = arr.length;
        if (n <= 1) return 0;
        
        // We only need to track three states
        int holdingStock = -arr[0];    // Max profit when holding a stock
        int justSold = 0;                 // Max profit when just sold today
        int cooldownState = 0;            // Max profit when in cooldown
        
        for (int i = 1; i < n; i++) {
            int prevHolding = holdingStock;
            int prevSold = justSold;
            int prevCooldown = cooldownState;
            
            // Update states
            holdingStock = Math.max(prevHolding, prevCooldown - arr[i]);
            justSold = prevHolding + arr[i];
            cooldownState = Math.max(prevCooldown, prevSold);
        }
        
        return Math.max(justSold, cooldownState);
    }
}
