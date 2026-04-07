// QUESTION: 2069. Walking Robot Simulation II
// LINK: https://leetcode.com/problems/walking-robot-simulation-ii/description/?envType=daily-question&envId=2026-04-07

// SOLUTION: SIMULATION

class Robot {
    private int width;
    private int height;
    private int x;
    private int y;
    private int dirIndex;
    private String[] dirs = {"East", "North", "West", "South"};
    private int[] dx = {1, 0, -1, 0};
    private int[] dy = {0, 1, 0, -1};
    private int stepsPerCycle;
    
    public Robot(int width, int height) {
        this.width = width;
        this.height = height;
        this.x = 0;
        this.y = 0;
        this.dirIndex = 0;
        stepsPerCycle = 2 * (width + height) - 4;
        if (stepsPerCycle == 0) stepsPerCycle = 1;
    }
    
    public void step(int num) {
        // Reduce num modulo the cycle length
        num %= stepsPerCycle;
        if (num == 0) num = stepsPerCycle;
        
        // Simulate each step (at most perimeter steps, which is ≤ 400)
        for (int i = 0; i < num; i++) {
            int nextX = x + dx[dirIndex];
            int nextY = y + dy[dirIndex];
            
            if (nextX < 0 || nextX >= width || nextY < 0 || nextY >= height) {
                dirIndex = (dirIndex + 1) % 4;
                nextX = x + dx[dirIndex];
                nextY = y + dy[dirIndex];
            }
            
            x = nextX;
            y = nextY;
        }
    }
    
    public int[] getPos() {
        return new int[]{x, y};
    }
    
    public String getDir() {
        return dirs[dirIndex];
    }
}