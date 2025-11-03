QUESTION: 1578. Minimum Time to Make Rope Colorful
LINK: https://leetcode.com/problems/minimum-time-to-make-rope-colorful/description/?envType=daily-question&envId=2025-11-03

SOLUTION: GREEDY APPROACH

class Solution {
    public int minCost(String colors, int[] neededTime) {
        int totalCost = 0;
        int i = 0;  // Main pointer

        while(i < colors.length())
        {
            int groupEnd = i;
            while(groupEnd + 1 < colors.length() && colors.charAt(groupEnd) == colors.charAt(groupEnd + 1))
            {
                groupEnd++;
            }

            if(i < groupEnd)
            {
                int sumOfGroup = 0;
                int maxInGroup = 0;

                for(int j = i; j <= groupEnd; j++)
                {
                    sumOfGroup += neededTime[j];
                    maxInGroup = Math.max(maxInGroup, neededTime[j]);
                }
                totalCost += (sumOfGroup - maxInGroup);
            }
            i = groupEnd + 1;
        } 
        return totalCost;
    }
}