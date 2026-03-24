// QUESTION: 2906. Construct Product Matrix
// LINK: https://leetcode.com/problems/construct-product-matrix/description/?envType=daily-question&envId=2026-03-24

// SOLUTION: PREFIX SUFFIX

class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int MOD = 12345;

        int total = n * m;
        int[] flat = new int[total];
        int index = 0;

        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < m; j++)
            {
                flat[index++] = grid[i][j] % MOD;
            }
        }

        // Calculate prefix products
        long[] prefix = new long[total];
        prefix[0] = 1;
        
        for(int i = 1; i < total; i++)
        {
            prefix[i] = (prefix[i-1] * flat[i - 1]) % MOD;
        }

        // Calculate suffix products
        long[] suffix = new long[total];
        suffix[total - 1] = 1;
        for(int i = total - 2; i >= 0; i--)
        {
            suffix[i] = (suffix[i + 1] * flat[i + 1]) % MOD;
        }

        // Build result matrix
        int[][] result = new int[n][m];
        index = 0;

        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < m; j++)
            {
                long product = (prefix[index] * suffix[index]) % MOD;
                result[i][j] = (int) product;
                index++;
            }
        }
        return result;
    }
}