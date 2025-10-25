// QUESTION: 1716. Calculate Money in Leetcode Bank
// LINK: https://leetcode.com/problems/calculate-money-in-leetcode-bank/description/?envType=daily-question&envId=2025-10-25


// SOLUTION: 

class Solution {
    public int totalMoney(int n) {
        int weeks = n / 7;
        int days = n % 7;

        // Calculate total for complete weeks
        int total = 0;

        // Each week i contributes : (i + i + 1 +....+ i + 6) = 7 * i + 21
        for(int i = 0; i < weeks; i++)
        {
            total += 7 * (i + 1) + 21;
        }

        // Add remaining days
        for(int i = 0; i < days; i++)
        {
            total += (weeks + 1) + i;
        }
        return total; 
    }
}