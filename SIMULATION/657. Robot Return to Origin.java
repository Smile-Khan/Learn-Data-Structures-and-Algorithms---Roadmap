// QUESTION: 657. Robot Return to Origin
// LINK: https://leetcode.com/problems/robot-return-to-origin/description/?envType=daily-question&envId=2026-04-05

// SOLUTION: SIMULATION

class Solution {
    public boolean judgeCircle(String moves) {
        int x = 0;
        int y = 0;

        for(char ch : moves.toCharArray())
        {
            if(ch == 'U') y++;
            else if(ch == 'D') y--;
            else if(ch == 'L') x--;
            else if(ch == 'R') x++;
        }
        return x == 0 && y == 0;
    }
}