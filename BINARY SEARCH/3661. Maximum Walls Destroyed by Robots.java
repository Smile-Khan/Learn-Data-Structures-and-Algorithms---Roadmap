// QUESTION: 3661. Maximum Walls Destroyed by Robots
// LINK: https://leetcode.com/problems/maximum-walls-destroyed-by-robots/?envType=daily-question&envId=2026-04-03

// SOLUTION: BINARY + DP

class Solution {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        int m = walls.length;
        
        // Pair robots with their distances and sort by position
        int[][] robotInfo = new int[n][2];
        for (int i = 0; i < n; i++) {
            robotInfo[i][0] = robots[i];
            robotInfo[i][1] = distance[i];
        }
        Arrays.sort(robotInfo, (a, b) -> a[0] - b[0]);
        Arrays.sort(walls);
        
        // Extract sorted robot positions and distances
        int[] sortedRobots = new int[n];
        int[] sortedDist = new int[n];
        for (int i = 0; i < n; i++) {
            sortedRobots[i] = robotInfo[i][0];
            sortedDist[i] = robotInfo[i][1];
        }
        
        // Precompute wall indices for each robot's left/right ranges
        int[] leftDestroyable = new int[n];
        int[] rightDestroyable = new int[n];
        int[] betweenWalls = new int[n];
        
        for (int i = 0; i < n; i++) {
            int pos = sortedRobots[i];
            int dist = sortedDist[i];
            
            // Left range (considering previous robot)
            int leftStart = pos - dist;
            if (i > 0) {
                leftStart = Math.max(leftStart, sortedRobots[i-1] + 1);
            }
            int leftEnd = pos;
            leftDestroyable[i] = countWallsInRange(walls, leftStart, leftEnd);
            
            // Right range (considering next robot)
            int rightStart = pos;
            int rightEnd = pos + dist;
            if (i < n - 1) {
                rightEnd = Math.min(rightEnd, sortedRobots[i+1] - 1);
            }
            rightDestroyable[i] = countWallsInRange(walls, rightStart, rightEnd);
            
            // Walls between current and previous robot
            if (i > 0) {
                betweenWalls[i] = countWallsInRange(walls, sortedRobots[i-1], sortedRobots[i]);
            }
        }
        
        // DP
        int dpLeft = leftDestroyable[0];
        int dpRight = rightDestroyable[0];
        
        for (int i = 1; i < n; i++) {
            int newLeft = Math.max(
                dpLeft + leftDestroyable[i],
                dpRight - rightDestroyable[i-1] + Math.min(leftDestroyable[i] + rightDestroyable[i-1], betweenWalls[i])
            );
            
            int newRight = Math.max(
                dpLeft + rightDestroyable[i],
                dpRight + rightDestroyable[i]
            );
            
            dpLeft = newLeft;
            dpRight = newRight;
        }
        
        return Math.max(dpLeft, dpRight);
    }
    
    private int countWallsInRange(int[] walls, int start, int end) {
        int leftIdx = lowerBound(walls, start);
        int rightIdx = upperBound(walls, end) - 1;
        if (leftIdx > rightIdx) return 0;
        return rightIdx - leftIdx + 1;
    }
    
    private int lowerBound(int[] arr, int target) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] < target) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
    
    private int upperBound(int[] arr, int target) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] <= target) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}

