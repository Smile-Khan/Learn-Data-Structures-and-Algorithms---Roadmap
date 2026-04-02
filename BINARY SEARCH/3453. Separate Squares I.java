// QUESTION: 3453. Separate Squares I
// LINK: https://leetcode.com/problems/separate-squares-i/description/?envType=daily-question&envId=2026-01-13

// SOLUTION: BINARY SEARCH

class Solution {
    public double separateSquares(int[][] squares) {
        double totalArea = 0;
        double minY = 2e9;  // Higher than any possible y
        double maxY = -1;   // Lower than any possible y

        // Scout the battlefield
        for(int[] sq : squares)
        {
            double y = sq[1];
            double l = sq[2];
            totalArea += l * l;
            if(y < minY) minY = y;
            if(y + l > maxY) maxY = y + l;
        }

        double targetArea = totalArea / 2.0;
        double low = minY;
        double high = maxY;

        // 100 iteration to crush any precision errors
        for(int i = 0; i < 100; i++)
        {
            double mid = low + (high - low) / 2.0;
            if(calculateAreaBelow(squares, mid) < targetArea)
            {
                low = mid;
            }
            else 
            {
                high = mid;
            }
        }
        return low;
    }
    private double calculateAreaBelow(int[][] squares, double yLine)
    {
        double currentArea = 0;
        for(int[] sq : squares)
        {
            double bottom = sq[1];
            double side = sq[2];
            double top = bottom + side;

            if(yLine <= bottom)
            {
                // This square is entirely above our line of fire
                continue;
            }
            else if(yLine >= top)
            {
                // This square is completely conquered (below the line)
                currentArea += side * side;
            }
            else
            {
                // We are cutting through the enemy
                currentArea += side * (yLine - bottom);
            }
        }
        return currentArea;
    }
}