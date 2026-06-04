// QUESTION: 3751. Total Waviness of Numbers in Range I
// LINK: https://leetcode.com/problems/total-waviness-of-numbers-in-range-i/description/?envType=daily-question&envId=2026-06-04

// SOLUTION: Direct Loop (Best for Single Range)

class Solution {
    public int totalWaviness(int num1, int num2) {
        int total = 0;

        for(int num = num1; num <= num2; num++)
        {
            total += getWaviness(num);
        }
        return total;
    }
    private int getWaviness(int num)

    {
        String s = String.valueOf(num);
        int len = s.length();
        if(len < 3) return 0;

        int count = 0;
        for(int i = 1; i < len-1; i++)
        {
            int left = s.charAt(i - 1) - '0';
            int mid = s.charAt(i) - '0';
            int right = s.charAt(i + 1) - '0';

            // Peak: greater than both neighbors
            if(mid > left && mid > right)
            {
                count++;
            }

            // Valley: less than both neighbors
            else if(mid < left && mid < right)
            {
                count++;
            }
        }
        return count;
    }
}