QUESTION: 3228. Maximum Number of Operations to Move Ones to the End
LINK: https://leetcode.com/problems/maximum-number-of-operations-to-move-ones-to-the-end/description/?envType=daily-question&envId=2025-11-13

SOLUTION: 

class Solution {
    public int maxOperations(String s) {
        int totalOperations = 0;
        int onesCount = 0;

        for(int i = 0; i < s.length(); i++)
        {
            if(s.charAt(i) == '1')
            {
                onesCount++;
            }
            else 
            {
                if(i > 0 && s.charAt(i - 1) == '1')
                {
                    totalOperations += onesCount;
                }
            }
        }
        return totalOperations;
    }
}