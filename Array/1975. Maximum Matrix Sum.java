// QUESTION: 1975. Maximum Matrix Sum
// LINK: https://leetcode.com/problems/maximum-matrix-sum/description/?envType=daily-question&envId=2026-01-05

// SOLUTION: 

class Solution {
    public long maxMatrixSum(int[][] matrix) {
        /*
        GRAPH THEORY INTERPRETATION:

        Think of matrix as a graph where:
        - Each cell is a node
        - Adjacent cells share an edge

        Operation: Flip signs of two connected nodes.

        This is equivalent to:
        - Start with some nodes having "-1" weight
        - We can move "-1" weights along edges
        - Two "-1" weights cancel when they meet (become "+1")

        CONNECTIVITY MATTERS:
        for n * n grid, the graph is connected.
        Therefore, we can move any negative sign to any cell

        CONCLUSION:
        - If even # of negatives: pair and cancel them all
        - If odd # of negatives: one remains, place it on smallest | value |
        - Zero is special: can "absorb" negatives without cost
        */

        int n = matrix.length;
        long sumAbs = 0;
        int minAbs = Integer.MAX_VALUE;
        int negCount = 0;
        boolean zeroExists = false;


        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                int val = matrix[i][j];
                int absVal = Math.abs(val);

                sumAbs += absVal;
                minAbs = Math.min(minAbs, absVal);

                if(val < 0) negCount++;
                if(val == 0) zeroExists = true;
            }
        }

        // If we can eleminate all negatives
        if(zeroExists || negCount % 2 == 0)
        {
            return sumAbs;
        }

        // Otherwise, smallest absolute value becomes negative
        return sumAbs - 2L * minAbs;
    }
}


// SOLUTION: GREEDY

class Solution {
    public long maxMatrixSum(int[][] matrix) {
        /*
        GREEDY APPROACH WITH PROOF:

        STEP - BY - STEP REASONING:
        1. Operation flips sign of two adjacent cells
        2. This means negative signs can be "moved" around
        3. Two negatives can be combined to make positives


        FORMAL PROOF:
        Let's consider moving a negative sign from cell A to cell B:
        - Find path A -> B (grid is connected)
        - Apply operation along the path to "move" the negative
        - This shows any negative can be moved anywhere

        GREEDY STRATEGY:
        1. Sum absolute values of all elements (maximum possible)
        2. Count negatives
        3. If odd negatives, we must sacrifice one element
        4. Sacrifice the one with smallest absolute value
        */

        int n = matrix.length;
        long total = 0;
        int negatives = 0;
        int smallest = Integer.MAX_VALUE;
        boolean hasZero = false;

        for(int[] row : matrix)
        {
            for(int val : row)
            {
                // Always take absolute value initially
                int absVal = Math.abs(val);
                total += absVal;

                // Track smallest absolute value
                if(absVal < smallest)
                {
                    smallest = absVal;
                }

                // Count negatives
                if(val < 0)
                {
                    negatives++;
                }

                // Check for zero
                if(val == 0)
                {
                    hasZero = true;
                }
            }
        }

        // DECISION LOGIC
        if(hasZero || negatives % 2 == 0)
        {
            return total;   // Can make all positive
        }
        else 
        {
            // Must have one negative, make it the smallest
            return total - 2L * smallest;
        }
    }
}

// SOLUTION: SIMULATION

class Solution {
    public long maxMatrixSum(int[][] matrix) {
        /*
        SIMULATION APPROACH (CONCEPTUAL):

        Actually try to perform operations greedily.

        This is NOT efficient but helps understand:

        ALGORITHM:
        1. Find pair of adjacent negatives, flip them
        2. If no adjacent negatives, move a negative to meet another
        3. Repeat until 0 or 1 negative remains


        WHY NOT EFFICIENT:
        - Moving negatives can tak O(n2) steps
        - Actual implementation complex
        - Mathematical solution is simpler

        INCLUDED FOR LEARNING: Shows process, not actual implementation
        */

        // This is just conceptual - actual implementation would be complex
        // The mathematical solution is better

        return maxMatrixSumMathematical(matrix);
    }
    private long maxMatrixSumMathematical(int[][] matrix)
    {
        // Same as Approach 2
        int n = matrix.length;
        long sumAbs = 0;
        int minAbs = Integer.MAX_VALUE;
        int negCount = 0;
        boolean hasZero = false;

        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                int val = matrix[i][j];
                int absVal = Math.abs(val);

                sumAbs += absVal;
                minAbs = Math.min(minAbs, absVal);

                if(val < 0) negCount++;
                if(val == 0) hasZero = true;
            }
        }

        if(hasZero || negCount % 2 == 0)
        {
            return sumAbs;
        }
        return sumAbs - 2L * minAbs;
    }
}  


// SOLUTION: 

class Solution {
    public long maxMatrixSum(int[][] matrix) {
        /*
        INTERVIEW EXPLANATION:
        
        OBSERVATION 1: Operation preserves product
        Multiplying two adjacent elements by -1:
        (-1)×(-1) = 1, so product stays same.
        
        OBSERVATION 2: We can move negative signs
        To move negative from A to B:
        - Find path A→B in grid
        - Apply operation along path
        - Negative "travels" to B
        
        OBSERVATION 3: Zero is special
        Negative on zero becomes zero (effectively eliminated)
        
        CONCLUSION:
        - If even # negatives OR zero exists: all can be positive
          Max sum = sum of absolute values
        
        - If odd # negatives AND no zero: one negative remains
          Put it on smallest absolute value to minimize loss
          Max sum = sum(absolute values) - 2×min|value|
        */
        
        int n = matrix.length;
        long sumAbsolute = 0;      // Sum of absolute values
        int minAbsolute = Integer.MAX_VALUE;  // Smallest absolute value
        int negativeCount = 0;     // Count of negative numbers
        boolean foundZero = false; // Whether zero exists
        
        // Single pass through matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int value = matrix[i][j];
                int absoluteValue = Math.abs(value);
                
                // Add absolute value to total
                sumAbsolute += absoluteValue;
                
                // Update minimum absolute value
                if (absoluteValue < minAbsolute) {
                    minAbsolute = absoluteValue;
                }
                
                // Count negatives
                if (value < 0) {
                    negativeCount++;
                }
                
                // Check for zero
                if (value == 0) {
                    foundZero = true;
                }
            }
        }
        
        // Apply decision logic
        if (foundZero || negativeCount % 2 == 0) {
            // All elements can be made positive
            return sumAbsolute;
        } else {
            // One negative must remain - put it on smallest value
            return sumAbsolute - 2L * minAbsolute;
        }
    }
} 



// SOLUTION: MOST OPTMIZED 

class Solution {
    public long maxMatrixSum(int[][] matrix) {
        long absSum = 0;
        int negCount = 0;
        int minAbs = Integer.MAX_VALUE;

        // One pass. One scan. Total domination.

        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix[i].length; j++)
            {
                int val = matrix[i][j];
                int absVal = Math.abs(val);

                absSum += absVal;
                if(val < 0) negCount++;
                if(absVal < minAbs) minAbs = absVal;
            }
        }

        // If the number of negatives is even, they all annihilate each other.
        // If odd, one must remain, We sacrifice the weakest element
        return (negCount % 2 == 0) ? absSum : absSum - (2L * minAbs);
    }
}