QUESTION: 696. Count Binary Substrings
LINK: https://leetcode.com/problems/count-binary-substrings/description/?envType=daily-question&envId=2026-02-19

SOLUTION: class Solution {
    public int countBinarySubstrings(String s) {
        int n = s.length();
        int prevCount = 0;
        int currCount = 1;
        int result = 0;

        for(int i = 1; i < n; i++)
        {
            if(s.charAt(i) == s.charAt(i - 1))
            {
                currCount++;
            }
            else
            {
                // We've completed a group
                result += Math.min(prevCount, currCount);

                prevCount = currCount;
                currCount = 1;
            }
        }
        // Add the last group
        result += Math.min(prevCount, currCount);

        return result;
    }
}