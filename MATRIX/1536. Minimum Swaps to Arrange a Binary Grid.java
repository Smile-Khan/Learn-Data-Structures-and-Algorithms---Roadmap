// QUESTION: 1536. Minimum Swaps to Arrange a Binary Grid
// LINK: https://leetcode.com/problems/minimum-swaps-to-arrange-a-binary-grid/description/?envType=daily-question&envId=2026-03-02

// SOLUTION: 


class Solution {
    public int minSwaps(int[][] grid) {
        int n = grid.length;

        // Calculate trailing zeros for each row
        int[] trailingZeros = new int[n];
        for(int i = 0; i < n; i++)
        {
            int count = 0;

            for(int j = n - 1; j >= 0; j--)
            {
                if(grid[i][j] == 1) break;
                count++;
            }
            trailingZeros[i] = count;
        }

        int swaps = 0;

        // For each position from top to bottom
        for(int i = 0; i < n; i++)
        {
            int required = n - i - 1;

            // Find a row below (including i) with enough trailing zeros
            int j = i;
            while(j < n && trailingZeros[j] < required)
            {
                j++;
            }

            if(j == n) return -1;   // No suitable row found

            // Bubble up the found row
            while(j > i)
            {
                // Swap with row above
                int temp = trailingZeros[j];
                trailingZeros[j] = trailingZeros[j- 1];
                trailingZeros[j - 1] = temp;
                swaps++;
                j--;
            }
        }
        return swaps;
    }
}