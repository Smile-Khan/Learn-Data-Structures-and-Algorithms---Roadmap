// QUESTION: 2125. Number of Laser Beams in a Bank
// LINK: https://leetcode.com/problems/number-of-laser-beams-in-a-bank/description/?envType=daily-question&envId=2025-10-27

// SOLUTION: Counting Beams by Tracking Previous Row 

class Solution {
    public int numberOfBeams(String[] bank) {
        if (bank.length <= 1) return 0;
        
        int total = 0;
        int prevCount = -1;
        
        for (String row : bank) {
            int currCount = 0;
            for (char c : row.toCharArray()) {
                if (c == '1') currCount++;
            }
            
            if (currCount > 0) {
                if (prevCount != -1) {
                    total += prevCount * currCount;
                }
                prevCount = currCount;
            }
        }
        
        return total;
    }
}