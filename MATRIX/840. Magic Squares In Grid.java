// QUESTION: 840. Magic Squares In Grid
// LINK: https://leetcode.com/problems/magic-squares-in-grid/description/?envType=daily-question&envId=2025-12-30

// SOLUTION: 

class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // If the grid is too small to even hold a 3*3, it's impossible
        if(rows < 3 || cols < 3)
        {
            return 0;
        }

        int count = 0;

        for(int r = 0; r <= rows - 3; r++)
        {
            for(int c = 0; c <= cols - 3; c++)
            {
                if(isMagic(grid, r, c))
                {
                    count++;
                }
            }
        }
        return count;
    }
    private boolean isMagic(int[][] grid, int r, int c)

    {
        boolean[] seen = new boolean[10];
        for(int i = r; i < r + 3; i++)
        {
            for(int j = c; j < c + 3; j++)
            {
                int val = grid[i][j];

                // Is the number outside the required 1-9 range? FAILURE
                if(val < 1 || val > 9 || seen[val])
                {
                    return false;
                }
                seen[val] = true;
            }
        }

            // Row and column sum check
            for(int i = 0; i < 3; i++)
            {
                // Cheeck row sum
                if(grid[r + i][c] + grid[r + i][c + 1] + grid[r + i][c + 2] != 15)
                {
                    return false;   // Failure
                }

                if(grid[r][c + i] + grid[r + 1][c + i] + grid[r + 2][c + i] != 15)
                {
                return false;   // Failure
                }
            }

            if(grid[r][c] + grid[r + 1][c + 1] + grid[r + 2][c + 2] != 15)
            {
            return false;
            }

            if(grid[r][c + 2] + grid[r + 1][c + 1] + grid[r + 2][c] != 15)
            {
            return false;
            }

            return true;
        
    }
}