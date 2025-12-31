// QUESTION: 1970. Last Day Where You Can Still Cross
// LINK: https: https://leetcode.com/problems/last-day-where-you-can-still-cross/description/?envType=daily-question&envId=2025-12-31

// SOLUTION: BINARY SEARCH + BFS 

class Solution {
    public int latestDayToCross(int row, int col, int[][] cells) {
        int low = 1;
        int high = cells.length;
        int ans = 0;

        while(low <= high)
        {
            int mid = low + (high - low) / 2;

            if(isPossible(row, col, cells, mid))
            {
                ans = mid;
                low = mid + 1;
            }
            else
            {
                high = mid - 1;
            }
        }
        return ans;
    }
    private boolean isPossible(int row, int col, int[][] cells, int day)
    {
        int[][] grid = new int[row][col];
        

        for(int i = 0; i < day; i++)
        {
            int r = cells[i][0] - 1;
            int c = cells[i][1] - 1;
            grid[r][c] = 1; // Blocked
        }

        return bfs(grid, row, col);
    }

    private boolean bfs(int[][] grid, int row, int col)
    {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[row][col];

        for(int c = 0; c < col; c++)
        {
            if(grid[0][c] == 0)
            {
                queue.offer(new int[]{0, c});
                visited[0][c] = true;
            }
        }

        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        while(!queue.isEmpty())
        {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];

            if(r == row - 1) return true;

            for(int[] d : directions)
            {
                int nr = r + d[0];
                int nc = c + d[1];

                if(nr >= 0 && nr < row && nc >= 0 && nc < col
                && grid[nr][nc] == 0 && !visited[nr][nc])
                {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        return false;
    }
}