// QUESTION: 2943. Maximize Area of Square Hole in Grid
// LINK: https://leetcode.com/problems/maximize-area-of-square-hole-in-grid/description/?envType=daily-question&envId=2026-01-15

// SOLUTION: 

import java.util.Arrays;

class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        // Step 1: Sort the bars. Chaos is the enemy of efficiency!
        Arrays.sort(hBars);
        Arrays.sort(vBars);
        
        // Step 2: Find the maximum "stretch" in the horizontal direction
        int maxH = getMaxConsecutive(hBars);
        
        // Step 3: Find the maximum "stretch" in the vertical direction
        int maxV = getMaxConsecutive(vBars);
        
        // Step 4: The side of the square is limited by the shorter dimension
        int side = Math.min(maxH, maxV);
        
        // Return the area of our glorious square hole!
        return side * side;
    }

    private int getMaxConsecutive(int[] bars) {
        int maxStreak = 1;
        int currentStreak = 1;
        
        for (int i = 1; i < bars.length; i++) {
            // Check if the current bar is a direct continuation of the previous one
            if (bars[i] == bars[i - 1] + 1) {
                currentStreak++;
            } else {
                // The streak is broken! Record the peak power and reset.
                maxStreak = Math.max(maxStreak, currentStreak);
                currentStreak = 1;
            }
        }
        
        // Final update for the last streak
        maxStreak = Math.max(maxStreak, currentStreak);
        
        // A streak of K bars removed creates a hole of K + 1 units!
        return maxStreak + 1;
    }
}


// SOLUTION 2:
class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        Arrays.sort(hBars);
        Arrays.sort(vBars);

        int maxH = getMaxConsecutive(hBars);
        int maxV = getMaxConsecutive(vBars);

        int side = Math.min(maxH, maxV);

        return side * side;
    }
    private int getMaxConsecutive(int[] bars)
    {
        int maxConsecutive = 1;
        int current = 1;

        for(int i = 1; i < bars.length; i++)
        {
            if(bars[i] == bars[i - 1] + 1)
            {
                current++;
            }
            else
            {
                current = 1;
            }
            maxConsecutive = Math.max(maxConsecutive, current);
        }
        return maxConsecutive + 1;
    }
}