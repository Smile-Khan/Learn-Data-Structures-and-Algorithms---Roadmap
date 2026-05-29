QUESTION: 3300. Minimum Element After Replacement With Digit Sum
LINK: https://leetcode.com/problems/minimum-element-after-replacement-with-digit-sum/description/?envType=daily-question&envId=2026-05-29

SOLUTION: digit sum 

class Solution {
    public int minElement(int[] nums) {
        int min = Integer.MAX_VALUE;

        for(int num : nums)
        {
            int digitSum = 0;
            int temp = num;

            while(temp > 0)
            {
                digitSum += temp % 10;
                temp /= 10;
            }
            min = Math.min(min, digitSum);
        }
        return min;
    }
}