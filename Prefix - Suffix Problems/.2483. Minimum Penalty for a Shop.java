// QUESTION: 2483. Minimum Penalty for a Shop
// LINK: https://leetcode.com/problems/minimum-penalty-for-a-shop/description/?envType=daily-question&envId=2025-12-26

// SOLUTION: GREEDY + PREFIX SUM

class Solution {
    public int bestClosingTime(String customers) {
        int n = customers.length();

        // PrefixN[i] = count of 'N' from 0 to i - 1 (open hours penalty)
        // suffixY[i] = count of 'Y' from 0 to n - 1 (closed hours penalty)

        int[] prefixN = new int[n + 1];
        int[] suffixY = new int[n + 1];

        // Calculate prefix of "N"
        for(int i = 1; i <= n; i++)
        {
            prefixN[i] = prefixN[i - 1];
            if(customers.charAt(i - 1) == 'N')
            {
                prefixN[i]++;
            }
        }

        // Calculate suffix of 'Y'
        for(int i = n - 1; i >= 0; i--)
        {
            suffixY[i] = suffixY[i + 1];
            if(customers.charAt(i) == 'Y')
            {
                suffixY[i]++;
            }
        }


        // Find minimum penalty
        int minPenalty = Integer.MAX_VALUE;
        int bestHour = 0;

        for(int hour = 0; hour <= n; hour++)
        {
            int penalty = prefixN[hour] + suffixY[hour];
            if(penalty < minPenalty)
            {
                minPenalty = penalty;
                bestHour = hour;
            }
        }
        return bestHour;
    }
}

// Most Intuitive O(1) Space

class Solution {
    public int bestClosingTime(String customers) {
        int n = customers.length();

        // Start with penalty if close at hour 0
        int penalty = 0;
        for(char c : customers.toCharArray())
        {
            if(c == 'Y') penalty++;
        }


        int minPenalty = penalty;
        int bestHour = 0;

        // Try each closing hour
        for(int hour = 1; hour <= n; hour++)
        {
            if(customers.charAt(hour - 1) == 'Y')

            {
                penalty--;  // One less 'Y' after closing
            }
            else 
            {
                penalty++;  // One more 'N' before closing
            }

            if(penalty < minPenalty)
            {
                minPenalty = penalty;
                bestHour = hour;
            }
        }
        return bestHour;
    }
}