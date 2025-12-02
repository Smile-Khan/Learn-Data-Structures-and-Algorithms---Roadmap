// QUESTION: 3623. Count Number of Trapezoids I
// LINK: https://leetcode.com/problems/count-number-of-trapezoids-i/description/

// SOLUTION: MAP

class Solution {
    public int countTrapezoids(int[][] points) {
        int MOD = 1_000_000_007;

        // Step 1: Group points by their Y-cordinate
        Map<Integer, Integer> yCounts = new HashMap<>();
        for(int[] point : points)
        {
            int y = point[1];

            yCounts.put(y, yCounts.getOrDefault(y, 0) + 1);
        }

        long totalTrapeZoids = 0;
        long sumOfPairsSoFar = 0;

        // Step 2: Iterate through the GROUPS
        // We only care about the number of points on each horizontal line
        for(int count : yCounts.values())
        {
            // If we can't even form one pair on this line, it's useless
            if(count < 2)
            {
                continue;
            }

            // Calculate the number of pairs we can form on THIS line
            long pairsOnThisLine = (long) count * (count - 1) / 2;

            // The number of new trapezoids is the number of pairs on this line
            // Multiplied by the sum of all pairs we've seen on previous lines

            long newTrapezoid = (pairsOnThisLine % MOD * sumOfPairsSoFar % MOD) % MOD;

            totalTrapeZoids = (totalTrapeZoids + newTrapezoid) % MOD;

            // Update the running sum of pairs for the next iterations
            sumOfPairsSoFar = (sumOfPairsSoFar + pairsOnThisLine) % MOD;
        }

        return (int) totalTrapeZoids;
    }
}