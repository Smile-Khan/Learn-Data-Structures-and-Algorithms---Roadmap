// QUESTION: 85. Maximal Rectangle
// LINK: https://leetcode.com/problems/maximal-rectangle/?envType=daily-question&envId=2026-01-11

// SOLUTION: BRUTE FORCE APPROACH (Theoretical - Not Feasible):

class Solution {
    /*
    BRUTE FORCE APPROACH (Theoretical - Not Feasible):

    HOW IT WORKS:
    1. Check all possible rectangles in the matrix
    2. For each rectangle (top - left to bottom - right), verify if all cells are '1'
    3. Track maximum area found

    WHY IT'S BRUTE FORCE:
    - O((n * m) ^ 3) time complexity
    - For each pair of rows (N^2) and each pair of columns (m^2), check all cells (n * m)
    - Total: O(n^3 * m3) = O((n * m)^3)

    COMPLEXITY:
    TIME: O((n*m)^3) where n = rows, m = cols
    Space: O(1) - No extra space

    GOOD FOR: Understanding problem only (not runnable for 200 x 200)
    */

    public int maximalRectangle(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int maxArea = 0;

        // Try all possible top-left corners
        for(int top = 0; top < rows; top++)
        {
            for(int left = 0; left < cols; left++)
            {
                // Try all possible bottom-right corners
                for(int bottom = top; bottom < rows; bottom++)
                {
                    for(int right = left; right < cols; right++)
                    {
                        // Check if current rectangle contains only '1's
                        boolean allOnes = true;
                        for(int i = top; i <= bottom && allOnes; i++)
                        {
                            for(int j = left; j <= right && allOnes; j++)
                            {
                                if(matrix[i][j] == '0')
                                {
                                    allOnes = false;
                                }
                            }
                        }
                        // Calculate area if rectangle is valid
                        if(allOnes)
                        {
                            int area = (bottom - top + 1) * (right - left + 1);
                            maxArea = Math.max(maxArea, area);
                        }
                    }
                }
            }
        }
        return maxArea;
    }
}


// APPROACH 2: BRUTE FORCE WITH OPTIMIZATION (Better but Still Slow):

class Solution {
    /*
    APPROACH 2: BRUTE FORCE WITH OPTIMIZATION (BETTER BUT STILL SLOW):

    HOW IT WORKS:
    1. FOR EACH CELL AS TOP-LEFT CORNER
    2. EXPAND RIGHTWORDS TO FIND MAXIMUM WIDTH
    3. EXPAND DOWNWARDS WHILE MAINTAINING WIDTH
    4. TRACK MAXIMUM AREA

    OPTIMIZATION OVER BRUTE FORCE:
    - Stops early when encountering '0'
    - O(n^2 * m^2) worst case but often faster

    COMPLEXITY:
    TIME: O(n^2 * m^2) where n = rows, m = cols
    SPACE: O(1)

    STILL TOO SLOW: 200*200 = 1.6 billion operations worst case
    */

    public int maximalRectangle(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int maxArea = 0;

        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                if(matrix[i][j] == '1')
                {
                    // Initialize max width for this starting point
                    int maxWidth = cols;

                    // Expand downwards
                    for(int height = 1; i + height - 1 < rows; height++)
                    {
                        int row = i + height - 1;

                        // Find maximum width for this row
                        int width = 0;
                        while(width < maxWidth && j + width < cols && matrix[row][j + width] == '1')
                        {
                            width++;
                        }

                        // Update max width (can't be larger than previous rows)
                        
                        maxWidth = Math.min(maxWidth, width);

                        // Calculate areea
                        int area = height * maxWidth;
                        maxArea = Math.max(maxArea, area);

                        // Early stop if width becomes 0
                        if(maxWidth == 0) break;
                    }
                }
            }
        }
        return maxArea;
    }
}

// APPROACH 3: HISTOGRAM METHOD (Largest Rectangle in Histogram):


class Solution {
    /*
    APPROACH 3: HISTOGRAM METHOD (LARGEST RECTANGLE IN HISTOGRAM):

    HOW IT WORKS:
    1. Treat each row as ground level of histogram
    2. heights[j] = consecutive '1's above current cell
    3. For each row, find largest rectangle in histogram
    4. use montonic stack for histogram problem

    KEY INSIGHT:
    - This reduces 2D problem to multiple 1D problem
    - Build histogram row by row

    COMPLEXITY:
    TIME: O(n * m) where n = rows, m = cols
    SPACE: O(m) for heights array and stack

    THIS IS THE STANDARD OPTIMAL SOLUTION
    */

    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0) return 0;

        int rows = matrix.length;
        int cols = matrix[0].length;
        int maxArea = 0;

        // heights array : heights of histogram at each column
        int[] heights = new int[cols];

        for(int i = 0; i < rows; i++)
        {
            // Update heights for current row
            for(int j = 0; j < cols; j++)
            {
                if(matrix[i][j] == '1')
                {
                    heights[j] += 1;
                }
                else 
                {
                    heights[j] = 0; // Reset height if cell is '0'
                }
            }

            // Find largest rectangle in current histogram
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }    
        return maxArea;
    }
    private int largestRectangleArea(int[] heights)
    {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        for(int i = 0; i <= n; i++)
        {
            int currentHeight = (i == n) ? 0 : heights[i];

            while(!stack.isEmpty() && currentHeight < heights[stack.peek()])
            {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }
}


// APPROACH 4: DYNAMIC PROGRAMMING (Left, Right, Height Arrays):


class Solution {
    /*
    APPROACH 4: DYNAMIC PROGRAMMING (Left, Right, Height Arrays):

    HOW IT WORKS:
    1. For each cell, track:
        - height: consecutive '1's above
        - left: left boundary of rectangle ending here
        - right: right boundary of rectangle ending here
    2. Update these arrays row by row
    3. Calculate area = height * (right - left)

    DP TRANSITIONS:
    - height[j] = matrix[i][j] == '1' ? height[j] + 1 : 0
    - left[j] = max(previous left[j], current left boundary)
    - right[j] = min(previous right[j], current right boundary)

    COMPLEXITY:
    TIME: O(n * m)
    SPACE: O(m) for three arrays
    */

    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0) return 0;

        int rows = matrix.length;
        int cols = matrix[0].length;
        int maxArea = 0;

        // DP arrays
        int[] height = new int[cols];   // Height of rectangle
        int[] left = new int[cols];     // left boundary
        int[] right = new int[cols];    // right boundary

        // Initialize right with max possible value
        Arrays.fill(right, cols);

        for(int i = 0; i < rows; i++)
        {
            int currentLeft = 0;    // current left boundary
            int currentRight = cols;    // current right boundary

            // update height and left boundaries
            for(int j = 0; j < cols; j++)
            {
                if(matrix[i][j] == '1')
                {
                    height[j] += 1;
                    left[j] = Math.max(left[j], currentLeft);
                }
                else 
                {
                    height[j] = 0;
                    left[j] = 0;
                    currentLeft = j + 1;
                }
            }

            // Update right boundaries (traverse from right to left)
            for(int j = cols - 1; j >= 0; j--)
            {
                if(matrix[i][j] == '1')
                {
                    right[j] = Math.min(right[j], currentRight);
                }
                else 
                {
                    right[j] = cols;
                    currentRight = j;
                }
            }

            // Calculate max area for current row
            for(int j = 0; j < cols; j++)
            {
                int area = height[j] * (right[j] - left[j]);
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }
}


// APPROACH 5: USING HISTOGRAM WITH STACK (Simpler Version):

class Solution {
    /*
    APPROACH 5: USING HISTOGRAM WITH STACK (Simpler Version):

    HOW IT WORKS:
    1. Same as Approach 3 but with clearer stack implementation
    2. Process each row as histogram base
    3. Use stack to find next smaller element on both sides

    OPTIMIZATION:
    - Precompute heights array for each row
    - Use single pass with stack for histogram

    COMPLEXITY:
    TIME: O(n * m)
    SPACE: O(m)
    */

    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0) return 0;

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] heights = new int[cols];
        int maxArea = 0;

        for(int i = 0; i < rows; i++)
        {
            // Update heights for current row
            for(int j = 0; j < cols; j++)
            {
                heights[j] = (matrix[i][j] == '1') ? heights[j] + 1 : 0;
            }

            // Calculate max rectangle for current histogram
            maxArea = Math.max(maxArea, calculateMaxRectangle(heights));
        }
        return maxArea;
    }
    private int calculateMaxRectangle(int[] heights)
    {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        for(int i = 0; i <= heights.length; i++)
        {
            int h = (i == heights.length) ? 0 : heights[i];

            while(!stack.isEmpty() && h < heights[stack.peek()])
            {
                int height = heights[stack.pop()];
                int left = stack.isEmpty() ? -1 : stack.peek();
                int width = i - left - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }
}


//  APPROACH 6: USING NEXT SMALLER ELEMENT ARRAYS:

class Solution {
    /*
     APPROACH 6: USING NEXT SMALLER ELEMENT ARRAYS:

     HOW IT WORKS:

     1. FOR each row's histogram, precompute:
        - leftSmaller: index of next smaller element on left
        - rightSmaller: index of next smaller element on right
     2. Area for bar at i = height[i] * (rightSmaller[i] - leftSmaller[i] - 1)

     DIFFERENCE FROM STACK APPROACH:
     - Precomputes boundaries for all bars first
     - Then calculates area in separate pass

     COMPLEXITY:
     TIME: O(n * m)
     SPACE:O(m)
     */

    public int maximalRectangle(char[][] matrix) {
        
        if(matrix.length == 0) return 0;

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] heights = new int[cols];
        int maxArea = 0;

        for(int i = 0; i < rows; i++)
        {
            // Update heights 
            for(int j = 0; j < cols; j++)
            {
                heights[j] = (matrix[i][j] == '1') ? heights[j] + 1 : 0;
            }

            // Calculate left and right boundaries
            int[] leftSmaller = new int[cols];
            int[] rightSmaller = new int[cols];

            // Initialize
            Arrays.fill(rightSmaller, cols);

            // Find left boundaries
            for(int j = 0; j < cols; j++)
            {
                int k = j - 1;
                while(k >= 0 && heights[k] >= heights[j])
                {
                    k = leftSmaller[k];
                }
                leftSmaller[j] = k;
            }

            // Find right boundaries
            for(int j = cols - 1; j >= 0; j--)
            {
                int k = j + 1;
                while(k < cols && heights[k] >= heights[j])
                {
                    k = rightSmaller[k];
                }
                rightSmaller[j] = k;
            }

            // Calculate max area
            for(int j = 0; j < cols; j++)
            {
                int area = heights[j] * (rightSmaller[j] - leftSmaller[j] - 1);
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }
}


// APPROACH 7: MOST OPTIMAL (Histogram + Stack - Final Solution):


class Solution {
    /*
    APPROACH 7: MOST OPTIMAL (Histogram + Stack - Final Solution):

    COMBINES:
    1. Build histogram row by row
    2. Use monotonic stack for histogram problem
    3. Clear variable naming
    4. Efficient O(n * m) time, O(m) space

    WHY THIS IS BEST:
    - Simple and elegant
    - Well-known pattern (Largest Rectangle in Histogram)
    - Easy to explain in interview

    FINAL VERSION FOR INTERVIEWS:
    */


    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
        {
            return 0;
        } 

        int rows = matrix.length;
        int cols = matrix[0].length;
        int maxArea = 0;

        // Heights array represents histogram for current row
        int[] heights = new int[cols];


        for(int i = 0; i < rows; i++)
        {
            // Update heights for current row
            for(int j = 0; j < cols; j++)
            {
                if(matrix[i][j] == '1')
                {
                    heights[j] += 1;    // Extend height if current cell is '1'
                }
                else 
                {
                    heights[j] = 0; // Reset height if current cell is '0'
                }
            }
            // Find largest rectangle in current histogram
            maxArea = Math.max(maxArea, largestRectInHistogram(heights));
        }
        return maxArea;
    }
    private int largestRectInHistogram(int[] heights)
    {
        int n = heights.length;

        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        for(int i = 0; i <= n; i++)
        {
            // current height (0 for imaginary bar at the end)
            int currentHeight = (i == n) ? 0 : heights[i];

            // While stack not empty and current bar is shorter
            while(!stack.isEmpty() && currentHeight < heights[stack.peek()])
            {
                // Pop the bar from stack
                int height = heights[stack.pop()];

                // Calculate width
                // If stack is empty, width extends from 0 to i
                // Otherwise, width extends from (stack.peek() + 1) to i - 1
                int left = stack.isEmpty() ? -1 : stack.peek();
                int width = i - left - 1;

                // Update max area
                maxArea = Math.max(maxArea, height * width);
            }
            // Push current index to stack
            stack.push(i);
        }
        return maxArea;
    }
}

// BONUS: OPTIMIZED DP (Alternative to Histogram): 

class Solution {
    /*
    BONUS: OPTIMIZED DP (Alternative to Histogram):

    HOW IT WORKS:

    1. For each cell, precompute:
        - left[j]: consecutive '1's to the left (including current)
        - right[j]: consecutive '1's to the right (including current)
        - height[j]: consecutive '1's above
    2. Calculate area = height * (left + right - 1)

    ADVANTAGE: Single pass through each row
    DISADVANTAGE: Harder to understand than histogram method

    COMPLEXITY:
    TIME: O(n * m)
    SPACE: O(m)
    */


    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0) return 0;

        int rows = matrix.length;
        int cols = matrix[0].length;
        int maxArea = 0;
        
        // height[j]: number of consecutive '1's above including current
        // left[j]: left boundary of current row of '1's
        // right[j]: right boundary of current row of '1's
        int[] height = new int[cols];
        int[] left = new int[cols];
        int[] right = new int[cols];
        
        // Initialize right with max value
        Arrays.fill(right, cols);
        
        for (int i = 0; i < rows; i++) {
            int currentLeft = 0;
            int currentRight = cols;
            
            // Update left boundaries and height
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                    left[j] = Math.max(left[j], currentLeft);
                } else {
                    height[j] = 0;
                    left[j] = 0;
                    currentLeft = j + 1;
                }
            }
            
            // Update right boundaries
            for (int j = cols - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], currentRight);
                } else {
                    right[j] = cols;
                    currentRight = j;
                }
                
                // Calculate area
                maxArea = Math.max(maxArea, height[j] * (right[j] - left[j]));
            }
        }
        
        return maxArea;
    }
}