// QUESTION: 1292. Maximum Side Length of a Square with Sum Less than or Equal to Threshold
// LINK: https://leetcode.com/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold/description/?envType=daily-question&envId=2026-01-19

// SOLUTION: BINARY SEARCH

class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;

        // prefix sum array
        int[][] prefixSum = new int[m + 1][n + 1];

        // Build prefix sum
        for(int i = 1; i <= m; i++)
        {
            for(int j = 1; j <= n; j++)
            {
                prefixSum[i][j] = mat[i - 1][j - 1] + prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1];
            }
        }

        int low = 0;
        int high = Math.min(m, n);
        int ans = 0;

        while(low <= high)
        {
            int mid = low + (high - low) / 2;

            if(existSquare(prefixSum, mid, threshold))
            {
                ans = mid;
                low = mid + 1;
            }
            else 
            {
                high = mid - 1;
            }
        }
        return ans;
    }
    private boolean existSquare(int[][] ps, int k, int threshold)
    {
        if(k == 0) return true;

        int m = ps.length - 1;
        int n = ps[0].length - 1;

        for(int i = k; i <= m; i++)
        {
            for(int j = k; j <= n; j++)
            {
                int sum = ps[i][j] - ps[i - k][j] - ps[i][j - k] + ps[i - k][j - k];

                if(sum <= threshold)
                {
                    return true;
                }
            }
        }
        return false;
    }
}


// Most Intuitive O(M⋅N)

class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;

        int[][] ps = new int[m + 1][n + 1];

        for(int i = 1; i <= m; i++)
        {
            for(int j = 1; j <= n; j++)
            {
                ps[i][j] = mat[i - 1][j - 1] + ps[i - 1][j] + ps[i][j - 1] - ps[i - 1][j - 1];
            }
        }
        int maxSide = 0;

        // Step 2: The Greedy One-pass Expansion
        // We only try to grow the square. This is O(M * N)
        for(int i = 1; i <= m; i++)
        {
            for(int j = 1; j <= n; j++)
            {
                // Can we fit a square of size (maxSide + 1) ending at (i, j) ?
                int k = maxSide + 1;
                if(i >= k && j >= k)
                {
                    int currentSum = ps[i][j] - ps[i - k][j] - ps[i][j - k] + ps[i - k][j - k];

                    if(currentSum <= threshold)
                    {
                        // Our power grows! We don't need to Binary search.
                        // We simply keep this new limit.
                        maxSide++;
                    }
                }
            }
        }
        return maxSide;
    }
}