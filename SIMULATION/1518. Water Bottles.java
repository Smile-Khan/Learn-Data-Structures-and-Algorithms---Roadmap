// QUESTION: 1518. Water Bottles
// LINK: https://leetcode.com/problems/water-bottles/description/?envType=daily-question&envId=2025-10-01

// SOLUTION: simulation

class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int totalDrunk = numBottles;
        int emptyBottles = numBottles;

        while(emptyBottles >= numExchange)
        {
            int newFullBottles = emptyBottles / numExchange;
            totalDrunk += newFullBottles;

            emptyBottles = (emptyBottles % numExchange) + newFullBottles;
        }
        return totalDrunk;
    }
}


// SOLUTION: MATH

class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int totalDrunk = numBottles;
        int emptyBottles = numBottles;

        while(emptyBottles >= numExchange)
        {
            int newFullBottles = emptyBottles / numExchange;
            totalDrunk += newFullBottles;

            emptyBottles = (emptyBottles % numExchange) + newFullBottles;
        }
        return totalDrunk;
    }
}