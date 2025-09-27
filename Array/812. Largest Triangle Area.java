// QUESTION: 812. Largest Triangle Area
// LINK: https://leetcode.com/problems/largest-triangle-area/description/?envType=daily-question&envId=2025-09-27

// SOLUTION: 

class Solution {
    public double largestTriangleArea(int[][] points) {
        double maxArea = 0.0;

        for(int i = 0; i < points.length; i++)
        {
            for(int j = i+1; j < points.length; j++)
            {
                for(int k = j+1; k < points.length; k++)
                {
                    int[] p1 = points[i];

                    // Extract
                    int x1 = p1[0];
                    int y1 = p1[1];

                    int[] p2 = points[j];

                    // Extract
                    int x2 = p2[0];
                    int y2 = p2[1];

                    int[] p3 = points[k];

                    // Extract
                    int x3 = p3[0];
                    int y3 = p3[1];

                    double currentArea = 0.5 * Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2));

                    maxArea = Math.max(maxArea, currentArea);
                }
            }
        }
        return maxArea;
    }
}