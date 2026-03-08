// QUESTION: 1980. Find Unique Binary String
// LINK: https://leetcode.com/problems/find-unique-binary-string/description/?envType=daily-question&envId=2026-03-08

// SOLUTION: Diagonal Approach

class Solution {
    public String findDifferentBinaryString(String[] nums) {
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < nums.length; i++)
        {
            char ch = nums[i].charAt(i);
            result.append(ch == '0' ? '1' : '0');
        }       
        return result.toString();
    }
}