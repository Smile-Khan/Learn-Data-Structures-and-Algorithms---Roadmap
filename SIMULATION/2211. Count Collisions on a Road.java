// QUESTION: 2211. Count Collisions on a Road
// LINK: https://leetcode.com/problems/count-collisions-on-a-road/description/?envType=daily-question&envId=2025-12-04

// SOLUTION: Simulation

class Solution {
    public int countCollisions(String directions) {
        int n = directions.length();

        // Step 1: Find the boundaries of the battlefield

        int left = 0;
        // Skip all the cowardly 'L's at the start
        while(left < n && directions.charAt(left) == 'L')
        {
            left++;
        }

        int right = n - 1;

        // Skip all the cowardly 'R's at the end

        while(right >= 0 && directions.charAt(right) == 'R')
        {
            right--;
        }

        int collisions = 0;

        // Step 2: Count the doomed cars in the cauldron 

        // If left > right, it means all cars were cowards (e.g 'LLRR'), no cauldron.

        if(left > right)
        {
            return 0;
        }

        // Every car in this range that moves is a guaranteed collision
        for(int i = left; i <= right; i++)
        {
            if(directions.charAt(i) != 'S')
            {
                collisions++;
            }
        }
        return collisions;
    }
}