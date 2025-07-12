//PROBLEM LINK: https://www.geeksforgeeks.org/problems/gold-mine-problem2608/1
//
//
//        🎯 Problem Summary
//Find maximum gold collectible from left to right with specific movement rules
//
//Start: Any row in first column
//Moves: Right, Diagonal-up-right, Diagonal-down-right
//Goal: Maximum gold collected when reaching rightmost column
//
//🧠 Key Insight
//"Work backwards from destination" - This is a RIGHT-TO-LEFT DP pattern!
//
//Instead of thinking "where can I go from here?"
//Think "where could I have come from to reach here?"
//
//        📊 Algorithm Breakdown
//Step 1: Initialize DP Table
//javaint[][] dp = new int[n][m];
//// dp[i][j] = max gold from position (i,j) to end
//Step 2: Base Case (Last Column)
//javafor(int i = 0; i < n; i++)
//dp[i][m-1] = mat[i][m-1];
//Step 3: Fill DP (Right to Left)
//javafor(int j = m-2; j >= 0; j--) {
//        for(int i = 0; i < n; i++) {
//int right = dp[i][j+1];
//int rightUp = (i > 0) ? dp[i-1][j+1] : 0;
//int rightDown = (i < n-1) ? dp[i+1][j+1] : 0;
//
//dp[i][j] = mat[i][j] + max(right, rightUp, rightDown);
//    }
//            }
//Step 4: Find Answer
//javaint maxGold = 0;
//for(int i = 0; i < n; i++)
//maxGold = Math.max(maxGold, dp[i][0]);
//🔥 Interview Memory Triggers
//1. Movement Pattern Recognition
//
//3 directions: Right, Diagonal-up-right, Diagonal-down-right
//Key insight: All moves go towards right → Process columns right-to-left
//
//2. Boundary Conditions
//java// Always check bounds for diagonal moves
//int rightUp = (i > 0) ? dp[i-1][j+1] : 0;
//int rightDown = (i < n-1) ? dp[i+1][j+1] : 0;
//3. DP State Definition
//
//dp[i][j] = Maximum gold from cell (i,j) to the end
//Not maximum gold to reach (i,j)
//
//⚡ Complexity Analysis
//
//Time: O(n × m) - Visit each cell once
//Space: O(n × m) - DP table
//
//🎪 Common Variations & Extensions
//1. Path Reconstruction
//java// Add parent tracking
//int[][][] parent = new int[n][m][2];
//// Store which direction gave maximum
//2. Minimum Path Sum Version
//
//        Same logic, use Math.min instead of Math.max
//
//3. 4-Direction Movement
//
//Add left movement → Need different approach (DFS + Memoization)
//
//🚨 Interview Gotchas
//1. Why Right-to-Left?
//
//Answer: Because all valid moves go right, we know the "future" states
//Moving left-to-right would require knowing unknown future states
//
//2. Alternative Approaches
//
//Recursion + Memoization: Possible but less efficient
//BFS/DFS: Overkill for this problem
//
//3. Edge Cases
//
//Single column matrix
//Single row matrix
//All zeros in matrix
//
//🎯 Quick Revision Checklist
//
//Remember: RIGHT-TO-LEFT processing
//Base case: Initialize last column
//Boundary checks for diagonal moves
//Final answer: Max value in first column
//Time: O(n×m), Space: O(n×m)
//
//💡 Pattern Recognition
//This pattern appears in:
//
//Cherry Pickup problems
//Path sum problems with restricted movements
//Any grid DP where movement has preferred direction
//
//🔄 5-Minute Revision Format
//
//Problem: Max gold, left-to-right, 3 moves
//Approach: DP, right-to-left filling
//Key: dp[i][j] = mat[i][j] + max(3 possible next positions)
//Answer: Max in first column
//Complexity: O(n×m) time and space


class Solution {
    public int maxGold(int[][] mat) {
        // code here
        int n = mat.length;
        int m = mat[0].length;

        int[][] dp = new int[n][m];


        // Fill last column
        for(int i = 0; i < n; i++)
        {
            dp[i][m-1] = mat[i][m-1];
        }


        // fill the rest of DP from RIGHT TO LEFT
        for(int j = m - 2; j >= 0; j--)
        {
            for(int i = 0; i < n; i++)
            {
                int right = dp[i][j+1];
                int rightUp = (i > 0) ? dp[i-1][j+1] : 0;
                int rightDown = (i < n-1) ? dp[i+1][j+1] : 0;

                dp[i][j] = mat[i][j] + Math.max(right, Math.max(rightUp, rightDown));
            }
        }

        // Find max in first column
        int maxGold = 0;
        for(int i = 0; i < n; i++)
        {
            maxGold = Math.max(maxGold, dp[i][0]);
        }
        return maxGold;
    }
}