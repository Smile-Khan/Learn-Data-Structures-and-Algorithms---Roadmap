QUESTION: 407. Trapping Rain Water II
LINK: https://leetcode.com/problems/trapping-rain-water-ii/description/?envType=daily-question&envId=2025-10-03

SOLUTION: BFS + Min-Heap (Priority Queue)

class Solution {
    public int trapRainWater(int[][] heightMap) {
        int n = heightMap.length;
        int m = heightMap[0].length;

        // Base case
        if(n < 3 || m < 3)
        {
            return 0;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2] - b[2]);
        boolean[][] visited = new boolean[n][m];
        int totalWater = 0;

        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < m; j++)
            {
                if(i == 0 || i == n-1 || j == 0 || j == m - 1)
                {
                    pq.add(new int[]{i, j, heightMap[i][j]}); // Height
                    visited[i][j] = true;
                }
            }
        } 
        
        while(!pq.isEmpty())
        {
            int[] currentCell = pq.poll();

            int row = currentCell[0];
            int col = currentCell[1];
            int height = currentCell[2];

            int[][] directions = {{1, 0}, {-1, 0}, {0, 1},{0, -1}};

            for(int[] dir : directions)
            {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if(newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && !visited[newRow][newCol])
                {
                    visited[newRow][newCol] = true;

                    totalWater += Math.max(0, height - heightMap[newRow][newCol]);

                    pq.add(new int[] {newRow, newCol, Math.max(height, heightMap[newRow][newCol])});
                }
            }
        }
        return totalWater;
    }
}