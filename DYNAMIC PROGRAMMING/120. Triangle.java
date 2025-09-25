// QUESTION: 120. Triangle
// LINK: https://leetcode.com/problems/triangle/description/?envType=daily-question&envId=2025-09-25

// SOLUTION: RECURSIVE O(2^N) space O(n)



class Solution {
    
    public int minimumTotal(List<List<Integer>> triangle) {
        return findMinPath(0, 0, triangle);
    }
    private int findMinPath(int row, int col, List<List<Integer>> triangle)
    {
        // Base case : If we are at the last row
        if(row == triangle.size() - 1)
        {
            // the path is just this single element;
            return triangle.get(row).get(col);
        }

        // Find the minimum path from the left child
        int path1 = findMinPath(row+1, col, triangle);

        // Find the minimum path from the right child
        int path2 = findMinPath(row+1, col+1, triangle);

        int myValue = triangle.get(row).get(col);
        return myValue + Math.min(path1, path2);
    }
}

// DP MEMOIZATION  O(N^2) SPACE: O(N^2)

class Solution {
    
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();

        Integer[][] memo = new Integer[n][n];

        // Start the memoized recursion from the top of the triangle
        return findMinPath(0, 0, triangle, memo);
    }
    private int findMinPath(int row, int col, List<List<Integer>> triangle, Integer[][] memo)
    {
        // Base case : If we are at the last row
        if(row == triangle.size() - 1)
        {
            // the path is just this single element;
            return triangle.get(row).get(col);
        }

        // Memoization check
        if(memo[row][col] != null)
        {
            return memo[row][col];
        }

        // Find the minimum path from the left child
        int pathDown = findMinPath(row+1, col, triangle, memo);

        // Find the minimum path from the right child
        int pathDownRight = findMinPath(row+1, col+1, triangle, memo);

        int result = triangle.get(row).get(col) + Math.min(pathDown, pathDownRight);

        memo[row][col] = result;

        return result;
    }
}


// OPTIMIZED ONE, TIME: O(N^2), SPACE: O(N)

class Solution {
    
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        List<Integer> lastRow = triangle.get(n - 1);

        for(int j = 0; j < n; j++)
        {
            dp[j] = lastRow.get(j);
        }

        for(int i = n-2; i >= 0; i--)
        {
            for(int j = 0; j <= i; j++)
            {
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j+1]);
            }
        }   
        return dp[0];
    }
}