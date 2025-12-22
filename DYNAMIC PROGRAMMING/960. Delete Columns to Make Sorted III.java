// QUESTION: 960. Delete Columns to Make Sorted III
// LINK: https://leetcode.com/problems/delete-columns-to-make-sorted-iii/description/?envType=daily-question&envId=2025-12-22

// SOLUTION: DYNAMIC PROGRAMMING 


class Solution {
    public int minDeletionSize(String[] strs) {
        int numStrings = strs.length;
        if(numStrings == 0)
        {
            return 0;
        }

        int lenString = strs[0].length();
        if(lenString == 0)
        {
            return 0;
        }

        // dp[i] = length of the longest sorted subsequence of columns ending at column i
        // The array MUST be big enough to hold a value for every column.
        int[] dp = new int[lenString];
        Arrays.fill(dp, 1); // Every column is a valid subsequence of length 1 by itself.

        int maxLen = 1; // The minimum possible answer for max length is 1;

        // Outer loop for the current column 'i'. It MUST go the end.
        for(int i = 1; i < lenString; i++)
        {
            // Inner loop for the potential previous column 'j'. It MUST check all previous clumns.
            for(int j = 0; j < i; j++)
            {
                // Check if column 'i' can validly follow column 'j'
                boolean compatible = true;

                // This loop MUST check ALL strings
                for(int k = 0; k < numStrings; k++)
                {
                    if(strs[k].charAt(j) > strs[k].charAt(i))
                    {
                        compatible = false;
                        break;  // No need to check other strings; this pair is invalid
                    }
                }

                // If it's a valid extension, update our DP value for column 'i'.
                if(compatible)
                {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            // After checking all possible predecessors 'j', we have the final dp[i].
            // Update the overall max length found so far. THIS happens inside the outer loop.
            maxLen = Math.max(maxLen, dp[i]);
        }

        // The number of deletions is the total number of columns minus the max we could keep.
        return lenString - maxLen;
    }
}