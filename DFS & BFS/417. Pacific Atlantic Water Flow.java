// QUESTION: 417. Pacific Atlantic Water Flow
// LINK: https://leetcode.com/problems/pacific-atlantic-water-flow/description/?envType=daily-question&envId=2025-10-05

// SOLUTION: DFS + BFS

class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;

        List<List<Integer>> result = new ArrayList<>();

        Queue<int[]> pacificQueue = new LinkedList<>();
        Queue<int[]> atlanticQueue = new LinkedList<>();
        boolean[][] canReachPacific = new boolean[n][m];
        boolean[][] canReachAtlantic = new boolean[n][m];
 
        for(int i = 0; i < n; i++)
        {
            pacificQueue.add(new int[]{i, 0});
            canReachPacific[i][0] = true;
        }
        for(int j = 1; j< m; j++)
        {
            pacificQueue.add(new int[]{0, j});
            canReachPacific[0][j] = true;
        }

        for(int i = 0; i < n; i++)
        {
            atlanticQueue.add(new int[]{i, m-1});
            canReachAtlantic[i][m-1] = true;
        }

        for(int j = 0; j < m-1; j++)
        {
            atlanticQueue.add(new int[] {n-1, j});
            canReachAtlantic[n-1][j] = true;
        }
        bfs(heights, pacificQueue, canReachPacific);
        bfs(heights, atlanticQueue, canReachAtlantic);

        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < m; j++)
            {
                if(canReachAtlantic[i][j] && canReachPacific[i][j])
                {
                    result.add(Arrays.asList(i,j));
                }
            }
        }
        return result;

    }
    private void bfs(int[][] heights, Queue<int[]> queue,boolean[][] canReach)
    {
        int n = heights.length;
        int m = heights[0].length;

        int[][] directions = {{0,1}, {0,-1}, {1, 0}, {-1,0}}; // right, left, down, up

        while(!queue.isEmpty())
        {
            int[] currentCell = queue.poll();
            int row = currentCell[0];
            int col = currentCell[1];

            // Now, loop through the 4 directions
            for(int[] dir : directions)
            {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if(newRow < 0 || newRow >= n || newCol < 0 || newCol >= m)
                {
                    continue;
                }

                if(canReach[newRow][newCol])
                {
                    continue;
                }

                // does it violate the uphill rule?
                if(heights[newRow][newCol] < heights[row][col])
                {
                    continue;
                }

                canReach[newRow][newCol] = true;
                queue.add(new int[] {newRow, newCol});
            }

        }
    }
}


// 


class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;

        List<List<Integer>> result = new ArrayList<>();

        Queue<int[]> pacificQueue = new LinkedList<>();
        Queue<int[]> atlanticQueue = new LinkedList<>();
        boolean[][] canReachPacific = new boolean[n][m];
        boolean[][] canReachAtlantic = new boolean[n][m];
 
        for(int i = 0; i < n; i++)
        {
            pacificQueue.add(new int[]{i, 0});
            canReachPacific[i][0] = true;
        }
        for(int j = 1; j< m; j++)
        {
            pacificQueue.add(new int[]{0, j});
            canReachPacific[0][j] = true;
        }

        for(int i = 0; i < n; i++)
        {
            atlanticQueue.add(new int[]{i, m-1});
            canReachAtlantic[i][m-1] = true;
        }

        for(int j = 0; j < m-1; j++)
        {
            atlanticQueue.add(new int[] {n-1, j});
            canReachAtlantic[n-1][j] = true;
        }
        bfs(heights, pacificQueue, canReachPacific);
        bfs(heights, atlanticQueue, canReachAtlantic);

        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < m; j++)
            {
                if(canReachAtlantic[i][j] && canReachPacific[i][j])
                {
                    result.add(Arrays.asList(i,j));
                }
            }
        }
        return result;

    }
    private void bfs(int[][] heights, Queue<int[]> queue,boolean[][] canReach)
    {
        int n = heights.length;
        int m = heights[0].length;

        int[][] directions = {{0,1}, {0,-1}, {1, 0}, {-1,0}}; // right, left, down, up

        while(!queue.isEmpty())
        {
            int[] currentCell = queue.poll();
            int row = currentCell[0];
            int col = currentCell[1];

            // Now, loop through the 4 directions
            for(int[] dir : directions)
            {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if(newRow < 0 || newRow >= n || newCol < 0 || newCol >= m)
                {
                    continue;
                }

                if(canReach[newRow][newCol])
                {
                    continue;
                }

                // does it violate the uphill rule?
                if(heights[newRow][newCol] < heights[row][col])
                {
                    continue;
                }

                canReach[newRow][newCol] = true;
                queue.add(new int[] {newRow, newCol});
            }

        }
    }
}