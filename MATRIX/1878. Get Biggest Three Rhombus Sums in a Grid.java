// QUESTION: 1878. Get Biggest Three Rhombus Sums in a Grid
// link: https://leetcode.com/problems/get-biggest-three-rhombus-sums-in-a-grid/editorial/?envType=daily-question&envId=2026-03-16



class Solution {
    public int[] getBiggestThree(int[][] grid) {

        TreeSet<Integer> sums = new TreeSet<>(Collections.reverseOrder());
        
        int m = grid.length;
        int n = grid[0].length;

        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                // Add the single cell rhombus (k = 0)
                sums.add(grid[i][j]);

                int maxK = 0;

                while(i - maxK >= 0 & i + maxK < m && j - maxK >= 0 && j + maxK < n)
                {
                    maxK++;
                }
                maxK--;

                for(int k = 1; k <= maxK; k++)
                {
                    int rhombusSum = 0;

                    // Top-Right Edge
                    for(int d = 0; d < k; d++)
                    {
                        rhombusSum += grid[i - k + d][j + d];
                    }

                    // Bottom-Right Edge
                    for(int d = 0; d < k; d++)
                    {
                        rhombusSum += grid[i + d][j + k - d];
                    }
                    // Bottom-Left Edge
                    for(int d = 0; d < k; d++)
                    {
                        rhombusSum += grid[i + k - d][j - d];
                    }
                    // top - left edge
                    for(int d = 0; d < k; d++)
                    {
                        rhombusSum += grid[i - d][j - k + d];
                    }
                    sums.add(rhombusSum);
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        int count = 0;

        for(int sum : sums)
        {
            if(count++ == 3) break;
            result.add(sum);
        }
        return result.stream().mapToInt(i -> i).toArray();
    }
}