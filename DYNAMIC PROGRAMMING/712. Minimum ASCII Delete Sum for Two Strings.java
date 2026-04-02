// QUESTION: 712. Minimum ASCII Delete Sum for Two Strings
// LINK: https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/description/?envType=daily-question&envId=2026-01-10

// SOLUTION 1: BRUTE FORCE

class Solution {
    /*
    BRUTE FORCE RECURSIVE APPROACH (Theoretical):

    HOW IT WORKS:
    1. Try all possible deletions from both strings
    2. For each deletion combination, check if resulting strings are equal
    3. Track minimum ASCII sum of deletions

    WHY IT's BRUTE FORCE
    - For each character in s1, we have 2 choices: delete or keep
    - For each character in s2, we have 2 choices: delete or keep
    - Total possibilities: O(2 ^ (n + m)) where n, m <= 1000

    COMPLEXITY:
    TIME: O(2 ^(n + m)) - Exponentially large, impossible for constraints
    SPACE: O(n + m) - Recursion stack depth

    GOOD FOR: Understanding the problem structure only
    */

    public int minimumDeleteSum(String s1, String s2) {
        return bruteForce(s1, s2, 0, 0, 0);    
    }

    private int bruteForce(String s1, String s2, int i, int j, int currentSum)
    {
        // Base case 1: Both strings fully processed
        if(i == s1.length() && j == s2.length())
        {
            return currentSum;
        }

        // Base case 2: s1 done, need to delete remaining s2
        if(i == s1.length())
        {
            int sum = currentSum;
            for(int k = j; k < s2.length(); k++)
            {
                sum += s2.charAt(k);
            }
            return sum;
        }

        // Base case 3: s2 done, need to delete remaining s1
        if(j == s2.length())
        {
            int sum = currentSum;
            for(int k = i; k < s1.length(); k++)
            {
                sum += s1.charAt(k);
            }
            return sum;
        }

        // If characters match, no need to delete, move both pointers
        if(s1.charAt(i) == s2.charAt(j))
        {
            return bruteForce(s1, s2, i + 1, j + 1, currentSum);
        }

        // Option 1: Delete s1[i] (add its ASCII to sum)
        int deleteS1 = bruteForce(s1, s2, i + 1, j, currentSum + s1.charAt(i));

        // Option 2: Delete s2[j] (add its ASCII to sum)
        int deleteS2 = bruteForce(s1, s2, i, j + 1, currentSum + s2.charAt(j));

        // Option 3: Delete both (add both ASCII to sum)
        int deleteBoth = bruteForce(s1, s2, i + 1, j + 1, currentSum + s1.charAt(i) + s2.charAt(j));

        return Math.min(deleteS1, Math.min(deleteS2, deleteBoth));
    }
}


// APPROACH 2: RECURSION WITH MEMOIZATION (Top-Down DP):

class Solution {
    /*
    APPROACH 2: RECURSION WITH MEMOIZATION (TOP + DOWN DP)

    HOW IT WORKS
    1. Define state: dp[i][j] = min ASCII sum to make s1[i..] and s2[j...] equal
    2. At each step:

        - If chars match: dp[i][j] = dp[i + 1][j + 1]
        - If chars don't match: min of:
           1. Delete s1[i]: s1.charAt(i) + dp[i + 1][j]
           2. Delete s2[j]: s2.charAt(j) + dp[i][j + 1]
    
    3. Use memoization to avoid recursion

    COMPLEXITY:
    TIME: O(N * M) - FOR STATE (i, j) computed once
    SPACE: O(n * m) - For memo table + recursion stack
    */

    private Integer[][] memo;

    public int minimumDeleteSum(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        memo = new Integer[n + 1][m + 1];

        return dfs(s1, s2, 0, 0);
    }
    private int dfs(String s1, String s2, int i, int j)
    {
        // Check memo
        if(memo[i][j] != null)
        {
            return memo[i][j];
        }

        // Base cases
        if(i == s1.length())
        {
            // Delete all remaining characters from s2
            int sum = 0;
            for(int k = j; k < s2.length(); k++)
            {
                sum += s2.charAt(k);
            }

            memo[i][j] = sum;
            return sum;
        }

        if(j == s2.length())
        {
            // Delete all remaining characters from s1
            int sum = 0;
            for(int k = i; k < s1.length(); k++)
            {
                sum += s1.charAt(k);
            }
            memo[i][j] = sum;
            return sum;
        }

        // If characters match, no deletion needed
        if(s1.charAt(i) == s2.charAt(j))
        {
            memo[i][j] = dfs(s1, s2, i + 1, j + 1);
            return memo[i][j];
        }

        // Try deleting from s1 or s2
        int deleteS1 = s1.charAt(i) + dfs(s1, s2, i + 1, j);
        int deleteS2 = s2.charAt(j) + dfs(s1, s2, i, j + 1);

        // We don't need to delete both - deleting from one string is enough
        // because deleting from both would be deletingS1 + deletingS2
        memo[i][j] = Math.min(deleteS1, deleteS2);
        return memo[i][j];
    }
}


/// APPROACH 3: BOTTOM-UP DP (2D Table):

class Solution {
    /*
    APPROACH 3: BOTTOM-UP DP (2D TABLE):

    HOW IT WORKS:
    1. DP[i][j] = min ASCII sum to make s1[0... i - 1] and s2[0... j - 1] equal
    2. DP[0][0] = 0 (empty strings)
    3. Fill table from top-left to bottom-right
    4. Transition:
        - If chars match: dp[i][j] = dp[i - 1][j - 1]
        - If chars don't match: min of:
          1. Delete s1[i - 1]: s1.charAt(i - 1) + dp[i - 1][j]
          2. Delete s2[j - 1]: s2.charAt(j - 1) + dp[i][j - 1];

    COMPLEXITY:
    TIME: O(N * M)
    SPACE: O(N * M)
    */

    public int minimumDeleteSum(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        // dp[i][j] = min ASCII sum for s1[0 ... i - 1] and s2[0.... j - 1]
        int[][] dp = new int[n + 1][m + 1];

        // Initialize base cases
        // First row: deleting all characters from s1 to match empty s2
        for(int i = 1; i <= n; i++)
        {
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        }    

        // First column : Deleting all characters from s2 to match empty s1
        for(int j = 1; j <= m; j++)
        {
            dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);
        }

        // Fill the DP table
        for(int i = 1; i <= n; i++)
        {
            for(int j = 1; j <= m; j++)
            {
                if(s1.charAt(i - 1) == s2.charAt(j- 1))
                {
                    // Characters match, no deletion needed
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else 
                {
                    // Take minimum of deleting from s1 to s2
                    int deleteFromS1 = s1.charAt(i - 1) + dp[i - 1][j];
                    int deleteFromS2 = s2.charAt(j - 1) + dp[i][j - 1];
                    dp[i][j] = Math.min(deleteFromS1, deleteFromS2);
                }
            }
        }
        return dp[n][m];
    }
}


// APPROACH 4: SPACE-OPTIMIZED DP (1D Array):

class Solution {
    /*
    APPROACH 4: SPACE-OPTIMIZED DP (1D Array):

    HOW IT WORKS:
    1. Observe that dp[i][j] only depends on:
        - dp[i - 1][j] (previous row, same column)
        - dp[i][j - 1] (same row, previous column)
        - dp[i - 1][j - 1] (previous row, previous column)

    2. We can use 1D array to store current row
    3. Keep track of previous diagonal value

    SPACE OPTIMIZATION TRICK:
    - Use dp[j] for current row
    - Use prevDiagonal to store dp[i - 1][j - 1]
    - prevRow[j] stores dp[i - 1][j]

    COMPLEXITY:
    TIME: O(N * M)
    SPACE: O(M) - ONLY NEED TO STORE ONE ROW
    */

    public int minimumDeleteSum(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        // dp[j] = min ASCII sum for current i
        int[] dp = new int[m + 1];
        int[] prevRow = new int[m + 1];

        // Initalize first row (i = 0, empty s1)
        for(int j = 1; j <= m; j++)
        {
            dp[j] = dp[j - 1] + s2.charAt(j - 1);
        }

        for(int i = 1; i <= n; i++)
        {
            // Save current dp as previous row for next iteration
            System.arraycopy(dp, 0, prevRow, 0, m + 1);

            // Initialize first column (j = 0, emtpy s2)
            dp[0] = prevRow[0] + s1.charAt(i - 1);

            for(int j = 1; j <= m; j++)
            {
                if(s1.charAt(i - 1) == s2.charAt(j - 1))
                {
                    // Characters match, no deletion needed
                    dp[j] = prevRow[j - 1];
                }
                else 
                {
                    // Take minimum of deleting from s1 or s2
                    int deleteFromS1 = s1.charAt(i - 1) + prevRow[j];
                    int deleteFromS2 = s2.charAt(j - 1) + dp[j - 1];
                    dp[j] = Math.min(deleteFromS1, deleteFromS2);
                }
            }
        }
        return dp[m];
    }
}


// APPROACH 5: DP WITH LONGEST COMMON SUBSEQUENCE VARIANT:


class Solution {
    /*
    APPROACH 5: DP WITH LONGEST COMMON SUBSEQUENCE VARIENT:

    HOW IT WORKS:
    1. Find LCS with maximum ASCII sum (not length)
    2. Answer = total ASCII sum of both strings - 2 * LCS ASCII sum

    MATHEMATICAL INSIGHT:
    Let L = LCS with maximum ASCII sum
    Total deletions = delete from s1 + delete from s2
                    = (sum(s1) - sum(L)) + (sum(s2) - sum(L))
                    = sum(s1) + sum(s2) - 2 * sum(L)

    So we need to find LCS that maximizes ASCII sum

    COMPLEXITY:
    TIME: O(N * M)
    SPACE: O(N * M) or O(M) with optimization
    */


    public int minimumDeleteSum(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        // Calculate total ASCII sums
        int totalSum = 0;
        for(int i = 0; i < n; i++) totalSum += s1.charAt(i);
        for(int j = 0; j < m; j++) totalSum += s2.charAt(j);

        // Find LCS with maximum ASCII sum
        int[][] dp = new int[n + 1][m + 1];

        for(int i = 1; i <= n; i++)
        {
            for(int j = 1; j <= m; j++)
            {
                if(s1.charAt(i - 1) == s2.charAt(j - 1))
                {
                    dp[i][j] = dp[i - 1][j - 1] + s1.charAt(i - 1);
                }
                else 
                {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        int lcsSum = dp[n][m];
        return totalSum - 2 * lcsSum;
    }
}


// APPROACH 6: SPACE-OPTIMIZED LCS VARIANT:

class Solution {
    /*
    APPROACH 6: SPACE-OPTIMIZED LCS VARIENT:

    HOW IT WORKS:
    1. Same as Approach 5 but with space optimization
    2. Use 1D array for DP
    3. Track previous diagonal value

    COMPLEXITY:
    TIME: O(N * M)
    SPACE: O(M)
    */

    public int minimumDeleteSum(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        // calculate total ASCII value
        int totalSum = 0;

        for(int i = 0; i < n; i++) totalSum += s1.charAt(i);
        for(int j = 0; j < m; j++) totalSum += s2.charAt(j);

        // Find LCS with maximum ASCII sum using 1D DP
        int[] dp = new int[m + 1];
        int[] prevRow = new int[m + 1];

        for(int i = 1; i <= n; i++)
        {
            int prevDiagonal = 0;
            for(int j = 1; j <= m; j++)
            {
                int temp = dp[j]; // STORE CURRENT BEFORE UPDATE

                if(s1.charAt(i - 1) == s2.charAt(j - 1))
                {
                    
                    dp[j] = prevDiagonal + s1.charAt(i - 1);

                }
                else 
                {
                    dp[j] = Math.max(prevRow[j], dp[j - 1]);
                }

                prevDiagonal = temp;    // Update for next iteration
            }

            // Copy current row to previous row
            System.arraycopy(dp, 0, prevRow, 0, m + 1);
        }
        int lcsSum = dp[m];
        return totalSum - 2 * lcsSum;
    }
}

// APPROACH 7: DP WITH DIFFERENT PERSPECTIVE (Weighted Edit Distance):

class Solution {
    /*
    APPROACH 7: DP WITH DIFFERENT PERSPECTIVE (WEIGHTED EDIT DISTANCE)

    HOW IT WORKS:
    1. Think of this as edit distance problem
    2. Operations: Delete from s1 (cost = ASCII value), delete from s2 (cost = ASCII value)
    3. No insertion or replacement operations

    ALTERNATIVE FORMULATION:

    dp[i][j] = minimum cost to transform s1[0.. i - 1] to s2[0...j - 1]
    using only deletions

    COMPLEXITY:
    TIME: O(N * M)
    SPACE: O(N * M)
    */

    public int minimumDeleteSum(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        // dp[i][j] = min cost to make s1[0...i - 1] == s2[o...j-1]
        int[][] dp = new int[n + 1][m + 1];

        // initialize: cost to transform to empty string
        for(int i = 1; i <= n; i++)
        {
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        }

        for(int j = 1; j <= m; j++)
        {
            dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);
        }

        for(int i = 1; i <= n; i++)
        {
            for(int j = 1; j <= m; j++)
            {
                if(s1.charAt(i - 1) == s2.charAt(j - 1))
                {
                    // Characters match, no cost
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else 
                {
                    // Delete from s1 or delete from s2
                    dp[i][j] = Math.min(
                        dp[i - 1][j] + s1.charAt(i - 1),    // delete s1[i - 1]
                        dp[i][j - 1] + s2.charAt(j - 1)     // delete s2[j - 1]
                    );
                }
            }
        }
        return dp[n][m];
    }
}