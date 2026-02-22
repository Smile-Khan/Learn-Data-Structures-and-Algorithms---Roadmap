// QUESTION: 868. Binary Gap
// LINK: https://leetcode.com/problems/binary-gap/description/?envType=daily-question&envId=2026-02-22

// SOLUTION: TRACK LAST

class Solution {
    public int binaryGap(int n) {
        int maxDistance = 0;
        int lastPos = -1;
        int currentPos = 0;
        
        while (n > 0) {
            if ((n & 1) == 1) {  // Current bit is 1
                if (lastPos != -1) {
                    maxDistance = Math.max(maxDistance, currentPos - lastPos);
                }
                lastPos = currentPos;
            }
            n >>= 1;  // Right shift to check next bit
            currentPos++;
        }
        
        return maxDistance;
    }
}