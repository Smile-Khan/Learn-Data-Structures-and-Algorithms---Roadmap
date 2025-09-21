// QUESTION: Max rectangle
// LINK: https://www.geeksforgeeks.org/problems/max-rectangle/1

// SOLUTION: 

class Solution {
    static int maxArea(int mat[][]) {
        // code here
        if(mat == null || mat.length == 0 || mat[0].length == 0)
        {
            return 0; // Edge case
        }
        
        int numRows = mat.length;
        int numCols = mat[0].length;
        
        int maxRectangleArea = 0;
        
        // Histogram heights, gets updated for each row
        int[] currentHistogramHeights = new int[numCols];
        
        // Loop through each row of the matrix
        for(int i= 0; i < numRows; i++)
        {
            // update
            for(int j = 0; j < numCols; j++)
            {
                if(mat[i][j] == 1)
                {
                    currentHistogramHeights[j] += 1;
                }
                else 
                {
                    currentHistogramHeights[j] = 0; // Reset height if a 0 is found
                }
            }
            // Calculate maxArea
            int areaFromCurrentHistogram = calculateLargestHistogramArea(currentHistogramHeights);
            
            // Update the overal max area
            maxRectangleArea = Math.max(maxRectangleArea, areaFromCurrentHistogram);
        }
        return maxRectangleArea;
    }
    private static int calculateLargestHistogramArea(int[] heights)
    {
        int numBars = heights.length;
        int[] stack = new int[numBars + 1]; // Use a simple array as a stack
        int stackTop = -1; // Pointer to the top of our manual stack
        // --- End of Manual Stack Implementation ---
        
        int maxHistogramArea = 0;

        for (int i = 0; i < numBars; i++) {
            while (stackTop != -1 && heights[stack[stackTop]] >= heights[i]) {
                int poppedBarHeight = heights[stack[stackTop--]]; // Pop from manual stack
                int leftBoundaryIndex = (stackTop == -1) ? -1 : stack[stackTop];
                int width = i - leftBoundaryIndex - 1;
                maxHistogramArea = Math.max(maxHistogramArea, poppedBarHeight * width);
            }
            stack[++stackTop] = i; // Push to manual stack
        }

        // After the loop, clear out any remaining bars in the stack
        while (stackTop != -1) {
            int poppedBarHeight = heights[stack[stackTop--]]; // Pop
            int leftBoundaryIndex = (stackTop == -1) ? -1 : stack[stackTop];
            int width = numBars - leftBoundaryIndex - 1; // Width extends to the end
            maxHistogramArea = Math.max(maxHistogramArea, poppedBarHeight * width);
        }
        
        return maxHistogramArea;
    }
}