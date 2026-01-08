QUESTION: 1458. Max Dot Product of Two Subsequences
LINK: https://leetcode.com/problems/max-dot-product-of-two-subsequences/description/?envType=daily-question&envId=2026-01-08

SOLUTION: 1

class Solution {
    /*
    BRUTE FORCE APPROACH (Recursive Exploration):

    HOW IT WORKS:
    1. Try all possible subsequences of nums1 and nums2
    2. For each valid pair (same length), compute dot product
    3. Track maximum dot product found

    WHY IT'S BRUTE FORCE
    - For each element in nums1, we have 2 choices: take or skip
    - For each element in nums2, we have 2 choices: take or skip
    - Need to ensure subsequences have same length
    - Total possibilities: O(2^(n + m)) where n, m <= 500

    COMPLEXITY:
    TIME: O(2 ^ (n + m)) - Exponentially large, impractical
    SPACE: O(N + M) - Recursion stack depth

    GOOD FOR: Understanding the problem structure only
    */

    public int maxDotProduct(int[] nums1, int[] nums2) {
        return bruteForceRecursion(nums1, nums2, 0, 0, 0, 0);
    }

    private int bruteForceRecursion(int[] nums1, int[] nums2, int i, int j, int length1, int length2)
    {
        // Base case: reached end of both arrays
        if(i == nums1.length && j == nums2.length)
        {
            // Only valid if we have non-empty subsequences of equal length
            return(length1 == length2 && length1 > 0) ? 0 : Integer.MIN_VALUE;
        }

        // Case 1: Skip nums1[i] if available
        int skip1 = Integer.MIN_VALUE;
        if(i < nums1.length)
        {
            skip1 = bruteForceRecursion(nums1, nums2, i + 1, j, length1, length2);
        }

        // Case 2: Skip nums2[j] if available
        int skip2 = Integer.MIN_VALUE;
        if(j < nums2.length)
        {
            skip2 = bruteForceRecursion(nums1, nums2, i, j + 1, length1, length2);
        }

        // Case 3: Take both nums1[i] and nums2[j] (if available)
        int takeBoth = Integer.MIN_VALUE;
        if(i < nums1.length && j < nums2.length )
        {
            int currentProduct = nums1[i] * nums2[j];

            int next = bruteForceRecursion(nums1, nums2, i + 1, j + 1, length1 + 1, length2 + 1);

            if(next != Integer.MIN_VALUE)
            {
                takeBoth = currentProduct + next;
            }
            else 
            {
                // Start new subsequence with just this pair
                takeBoth = currentProduct;
            }
        }
        return Math.max(skip1, Math.max(skip2, takeBoth));
    }
}


// APPROACH 2:

class Solution {
    /*
    APPROACH 2: RECURSION WITH MEMOIZATION (Top - Down DP):

    HOW IT WORKS:
    1. Define state: dp[i][j] = max dot product using nums1[i..] and nums2[j..]
    2. At each step, we have choices:

        a. Skip nums1[i]
        b. Skip nums2[j]
        c. Take both nums1[i] and nums2[j]
    3. Use memoization to avoid recomputation

    KEY INSIGHT:
    - We need to handle the case where we start fresh with just the current pair
    - This is different from standard LCS where we need continuous sequence

    COMPLEXITY:
    TIME: O(N * M) - Each state (i, j) computed once
    SPACE: O(N * M) - For memo table + recursion stack
    */

    private Integer[][] memo;

    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        memo = new Integer[n][m];

        return dfs(nums1, nums2, 0, 0);
    }

    private int dfs(int[] nums1, int[] nums2, int i, int j)
    {
        // Base case
        if(i == nums1.length || j == nums2.length)
        {
            // Can't form any more pairs
            return Integer.MIN_VALUE / 2;   // Avoid overflow
        }

        // Check memo
        if(memo[i][j] != null)
        {
            return memo[i][j];
        }

        // Option 1: Take current pair and continue
        int takeAndContinue = 
        nums1[i] * nums2[j] + Math.max(0, dfs(nums1, nums2, i + 1, j + 1));

        // Option 2: Skip nums1[i]
        int skip1 = dfs(nums1, nums2, i + 1, j);

        // Option 3: Skip nums2[j]
        int skip2 = dfs(nums1, nums2, i, j + 1);

        // The maximum of all options
        int result = Math.max(takeAndContinue, Math.max(skip1, skip2));

        memo[i][j] = result;
        return result;
    }
}


// APPROACH 3: BOTTOM-UP DP (Tabulation) 

class Solution {
    /*
    APPROACH 3: BOTTOM-UP DYNAMIC PROGRAMMING (2D DP):

    HOW IT WORKS:
    1. dp[i][j] = max dot product using first i elements of nums1 and first j elements of nums2
    2. Transition:
    dp[i][j] = max(nums1[i - 1] * nums2[j - 1] + max(0, dp[i - 1][j - 1]), // take both
    dp[i - 1][j], // Skip nums1[i - 1]
    dp[i][j - 1]  // Skip nums[j - 1])

    3. Initialize with -inf to handle negative products

    WHY max(0, dp[i - 1][j - 1]):
    - If previous subsequence has negative sum, better to start fresh
    - This allows us to take just the current pair if it's better

    COMPLEXITY:
    TIME: O(N * M)
    SPACE: O(M * N)
    */

    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        // dp[i][j] = max dot product using first i of nums1, first j of nums2
        int[][] dp = new int[n + 1][m + 1];

        // Initialize with very small values (not 0 because negative product possible)

        for(int i = 0; i <= n; i++)
        {
            for(int j = 0; j <= m; j++)
            {
                dp[i][j] = Integer.MIN_VALUE / 2;
            }
        }
        // Base cases : empty subsequnces
        for(int i = 0; i <= n; i++) dp[i][0] = Integer.MIN_VALUE / 2;
        for(int j = 0; j <= m; j++) dp[0][j] = Integer.MIN_VALUE / 2;

        // Fill DP table
        for(int i = 1; i <= n; i++)
        {
            for(int j = 1; j <= m; j++)
            {
                int currentProduct = nums1[i - 1] * nums2[j - 1];


                // Option 1: Take current pair, optionally extend previous
                int takeOption = currentProduct + Math.max(0, dp[i - 1][j - 1]);

                // Option 2: Skip nums1[i - 1]
                int skip1 = dp[i - 1][j];

                // Option 3: Skip nums2[j - 1]
                int skip2 = dp[i][j - 1];

                // Also consider just the current pair (starting fresh)
                int startFresh = currentProduct;

                dp[i][j] = Math.max(takeOption, Math.max(skip1, Math.max(skip2, startFresh)));
            }
        }
        return dp[n][m];
    }
}

// APPROACH 4: SPACE OPTIMIZED BOTTOM-UP DP

class Solution {
    /*
    APPROACH 4: BOTTOM-UP DP WITH SPACE OPTIMIZATION (1D DP):
    
    HOW IT WORKS:
    1. Observe that dp[i][j] only depends on:
       - dp[i-1][j] (previous row, same column)
       - dp[i][j-1] (same row, previous column)
       - dp[i-1][j-1] (previous row, previous column)
    2. We can use 1D array to store current row
    3. Keep track of previous diagonal value
    
    SPACE OPTIMIZATION TRICK:
    - Use dp[j] for current row
    - Use prevRow[j] for previous row
    - Track diagonal using a variable
    
    COMPLEXITY:
    Time: O(n*m)
    Space: O(m) - only need to store one row
    */
    
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        
        // dp[j] = max dot product for current i
        int[] dp = new int[m];
        int[] prevRow = new int[m];
        
        // Initialize with very small values
        for (int j = 0; j < m; j++) {
            dp[j] = Integer.MIN_VALUE / 2;
            prevRow[j] = Integer.MIN_VALUE / 2;
        }
        
        for (int i = 0; i < n; i++) {
            // prevDiagonal stores dp[i-1][j-1]
            int prevDiagonal = Integer.MIN_VALUE / 2;
            
            for (int j = 0; j < m; j++) {
                int currentProduct = nums1[i] * nums2[j];
                
                // Option 1: Take current pair, optionally extend
                int takeOption = currentProduct;
                if (prevDiagonal != Integer.MIN_VALUE / 2) {
                    takeOption = currentProduct + Math.max(0, prevDiagonal);
                }
                
                // Option 2: Skip nums1[i] (from above)
                int skip1 = (j > 0) ? dp[j - 1] : Integer.MIN_VALUE / 2;
                if (i > 0) {
                    skip1 = Math.max(skip1, prevRow[j]);
                }
                
                // Option 3: Skip nums2[j] (from left)
                int skip2 = (j > 0) ? dp[j - 1] : Integer.MIN_VALUE / 2;
                
                // Store old diagonal before updating dp[j]
                int oldDiagonal = (j > 0) ? dp[j - 1] : Integer.MIN_VALUE / 2;
                
                // Update current cell
                dp[j] = Math.max(takeOption, Math.max(skip1, Math.max(skip2, currentProduct)));
                
                // Update diagonal for next iteration
                prevDiagonal = oldDiagonal;
            }
            
            // Copy current row to previous row for next iteration
            System.arraycopy(dp, 0, prevRow, 0, m);
        }
        
        return dp[m - 1];
    }
}

// APPROACH 5: CLEANER SPACE OPTIMIZED BOTTOM-UP DP

class Solution {
    /*
    APPROACH 5: CLEANER 1D DP (Easier to Understand):
    
    HOW IT WORKS:
    1. Similar to Approach 4 but with cleaner initialization
    2. Handle edge cases explicitly
    3. More readable variable names
    
    KEY DIFFERENCE:
    - We initialize dp with -infinity
    - We always consider starting fresh with current pair
    
    COMPLEXITY:
    Time: O(n*m)
    Space: O(m)
    */
    
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        
        // dp[j] stores max dot product for current i
        int[] dp = new int[m];
        int[] prev = new int[m];
        
        // Initialize with smallest possible value
        for (int j = 0; j < m; j++) {
            dp[j] = Integer.MIN_VALUE;
            prev[j] = Integer.MIN_VALUE;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int product = nums1[i] * nums2[j];
                
                // Start with the product itself (fresh start)
                dp[j] = product;
                
                // Option: extend previous subsequence if it was positive
                if (i > 0 && j > 0 && prev[j - 1] > 0) {
                    dp[j] = Math.max(dp[j], product + prev[j - 1]);
                }
                
                // Option: skip current element from nums1
                if (i > 0) {
                    dp[j] = Math.max(dp[j], prev[j]);
                }
                
                // Option: skip current element from nums2
                if (j > 0) {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
            }
            
            // Copy dp to prev for next iteration
            int[] temp = prev;
            prev = dp;
            dp = temp;
        }
        
        return prev[m - 1];
    }
}

