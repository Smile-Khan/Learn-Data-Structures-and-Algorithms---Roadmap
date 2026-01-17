// QUESTION: 3047. Find the Largest Area of Square Inside Two Rectangles
// LINK: https://leetcode.com/problems/find-the-largest-area-of-square-inside-two-rectangles/description/?envType=daily-question&envId=2026-01-17

// SOLUTION: 

class Solution {
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int n = bottomLeft.length;
        long maxSide = 0;

        // Iterate through all unique pairs (i, j) where i < j
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Determine the boundaries of the intersection
                long xLow = Math.max(bottomLeft[i][0], bottomLeft[j][0]);
                long xHigh = Math.min(topRight[i][0], topRight[j][0]);
                long yLow = Math.max(bottomLeft[i][1], bottomLeft[j][1]);
                long yHigh = Math.min(topRight[i][1], topRight[j][1]);

                // Check if an actual intersection exists
                if (xHigh > xLow && yHigh > yLow) {
                    // The side of the largest square is the smaller dimension of the overlap
                    long side = Math.min(xHigh - xLow, yHigh - yLow);
                    if (side > maxSide) {
                        maxSide = side;
                    }
                }
            }
        }

        // Return the area. A square's power is its side multiplied by itself!
        return maxSide * maxSide;
    }
}