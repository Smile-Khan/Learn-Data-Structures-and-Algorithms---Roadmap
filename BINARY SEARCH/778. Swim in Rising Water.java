// QUESTION: 778. Swim in Rising Water
// LINK: https://leetcode.com/problems/swim-in-rising-water/description/?envType=daily-question&envId=2025-10-06

// SOLUTION: BINARY SEARCH + DFS

class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int low = grid[0][0];
        int high = n * n - 1;
        int result = high;

        while(low <= high)
        {
            int mid = low + (high - low) / 2;

            // check if a pth is possible with time 'mid'
            if(canReach(grid, mid))
            {
                result = mid;
                high = mid - 1;
            }
            else 
            {
                low = mid + 1;
            }
        }
        return result;
    }
    private boolean canReach(int[][] grid, int time)
    {
        int n = grid.length;

        boolean[][] visited = new boolean[n][n];

        return dfs(grid, 0, 0, time, visited);
    }
    private boolean dfs(int[][] grid, int r, int c, int time, boolean[][]visited)
    {
        int n = grid.length;

        if(r < 0 || r >= n || c < 0 || c >= n)
        return false;

        if(visited[r][c])
        return false;

        if(grid[r][c] > time)
        return false;

        visited[r][c] = true;

        // base case;
        if(r == n -1 && c == n-1)
        {
            return true;
        }

        if(dfs(grid, r + 1, c, time, visited)) return true;
        if(dfs(grid, r - 1, c, time, visited)) return true;
        if(dfs(grid, r, c+1, time, visited)) return true;
        if(dfs(grid, r, c-1, time, visited)) return true;

        return false;
    }
}

