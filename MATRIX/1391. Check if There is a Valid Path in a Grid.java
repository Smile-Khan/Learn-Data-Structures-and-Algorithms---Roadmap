// QUESTION: 1391. Check if There is a Valid Path in a Grid
// LINK: https://leetcode.com/problems/check-if-there-is-a-valid-path-in-a-grid/description/?envType=daily-question&envId=2026-04-27

// SOLUTION: DFS

class Solution {
    int m, n;
    private boolean[][] visited;

    // Directions: up, down, left, right
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // For each street type, which directions are possible?
    // 0: up, 1: down, 2: left, 3: right
    private int[][] streetDirs = {
        {},     // 0 (not used)
        {2, 3}, // 1: Left, right
        {0, 1}, // 2: up, down
        {1, 2}, // 3: down, left
        {1, 3}, // 4: down, right
        {0, 2}, // 5: up, left
        {0, 3}, // 6: up, right
    };

    // For each direction, what street types can connect?
    // From current cell's direction, what neighbor street types allow coming back?
    private int[][] validNeighbors = {
        {2, 3, 4},  // from up, neighbor must have: 2, 3, 4 (down connections)
        {2, 5, 6},  // from down, neighbor must have: 2, 5, 6 (up connections)
        {1, 3, 5},  // from left, neighbor must have: 1, 3, 5 (right connections)
        {1, 4, 6}   // from right, neighbor must have: 1, 4, 6 (left connections)
    };
    public boolean hasValidPath(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        visited = new boolean[m][n];

        return dfs(grid, 0, 0);
    }
    private boolean dfs(int[][] grid, int i, int j)
    {
        if(i == m - 1 && j == n - 1)
        {
            return true;
        }

        visited[i][j] = true;
        int street = grid[i][j];

        // Try all possible directions for this street
        for(int dir : streetDirs[street])
        {
            int new_i = i + dirs[dir][0];
            int new_j = j + dirs[dir][1];

            // Check bounds
            if(new_i < 0 || new_i >= m || new_j < 0 || new_j >= n) continue;

            if(visited[new_i][new_j]) continue;

            // Check if neighbor's street connects back
            int neighborStreet = grid[new_i][new_j];
            boolean valid = false;

            // Check if neighbor's street has the opposite direction
            for(int neighborDir : streetDirs[neighborStreet])
            {
                // If neighbor can connect back to current cell
                if(new_i + dirs[neighborDir][0] == i && new_j + dirs[neighborDir][1] == j)
                {
                    valid = true;
                    break;
                }
            }
            if(valid && dfs(grid, new_i, new_j))
            {
                return true;
            }
        }
        return false;
    }
}