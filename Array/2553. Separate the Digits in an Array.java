QUESTION: 2553. Separate the Digits in an Array
LINK: https://leetcode.com/problems/separate-the-digits-in-an-array/description/?envType=daily-question&envId=2026-05-11

SOLUTION: 
class Solution {
    public int[] separateDigits(int[] nums) {
        int totalDigits = 0;
        for(int num : nums)
        {
            totalDigits += String.valueOf(num).length();
        }

        int[] result = new int[totalDigits];
        int idx = 0;

        for(int num : nums)
        {
            String s = Integer.toString(num);
            for(char ch : s.toCharArray())
            {
                result[idx++] = ch - '0';
            }
        }
        return result;
    }
}