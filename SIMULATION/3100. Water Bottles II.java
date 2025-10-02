// QUESTION: 3100. Water Bottles II

// LINK: https://leetcode.com/problems/water-bottles-ii/?envType=daily-question&envId=2025-10-02

// SOLUTION: SIMULATION


class Solution {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int bottlesDrunk = numBottles;
        int emptyBottles = numBottles;

        while (emptyBottles >= numExchange) {
            // Trade empty bottles for one full bottle
            emptyBottles -= numExchange;
            
            // The full bottle becomes an empty one after drinking
            emptyBottles++;
            
            // Drink the new bottle
            bottlesDrunk++;
            
            // Increase the exchange rate for the next trade
            numExchange++;
        }
        
        return bottlesDrunk;
    }
}