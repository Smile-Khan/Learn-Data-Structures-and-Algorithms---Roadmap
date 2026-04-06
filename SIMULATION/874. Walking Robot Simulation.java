// QUESTION: 874. Walking Robot Simulation
// LINK: https://leetcode.com/problems/walking-robot-simulation/description/?envType=daily-question&envId=2026-04-06


// SOLUTION: SIMULATION


class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        // Direction arrays: North, East, South, West
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int dir = 0; // Start facing North
        int x = 0, y = 0;
        int maxDist = 0;
        
        // Store obstacles in HashSet for O(1) lookup
        Set<String> obstacleSet = new HashSet<>();
        for (int[] obs : obstacles) {
            obstacleSet.add(obs[0] + "," + obs[1]);
        }
        
        for (int cmd : commands) {
            if (cmd == -1) {
                // Turn right
                dir = (dir + 1) % 4;
            } else if (cmd == -2) {
                // Turn left
                dir = (dir + 3) % 4;
            } else {
                // Move forward
                for (int step = 0; step < cmd; step++) {
                    int nextX = x + dirs[dir][0];
                    int nextY = y + dirs[dir][1];
                    
                    if (obstacleSet.contains(nextX + "," + nextY)) {
                        break; // Obstacle encountered, stop moving
                    }
                    x = nextX;
                    y = nextY;
                    
                    // Update maximum distance
                    maxDist = Math.max(maxDist, x*x + y*y);
                }
            }
        }
        
        return maxDist;
    }
}