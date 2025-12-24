// QUESTION: 3074. Apple Redistribution into Boxes
// LINK: https://leetcode.com/problems/apple-redistribution-into-boxes/description/?envType=daily-question&envId=2025-12-24

// SOLUTION: GREEDY + SORTING

class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {
        // Calculate total apples
        int totalApples = 0;
        for(int a : apple)
        {
            totalApples += a;
        }

        // Sort capacities in descending order
        Arrays.sort(capacity);

        // Take boxes from largest to smallest until we have enough capacity
        int boxesUsed = 0;
        int currentCapacity = 0;
        
        for(int i = capacity.length - 1; i >= 0; i--)
        {
            currentCapacity += capacity[i];
            boxesUsed++;

            if(currentCapacity >= totalApples)
            {
                return boxesUsed;
            }
        }
        return boxesUsed;   // should never reach here due to constraints;
    }
}