// QUESTION: 3637. Trionic Array I
// LINK: https://leetcode.com/problems/trionic-array-i/description/

// SOLUTION: 

class Solution {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        if(n < 4) return false; // Too small for the required p and q

        int i = 0;

        // Phase 1: Strictly Increasing (0 to p)
        int startP = i;
        while(i < n - 1 && nums[i] < nums[i + 1])
        {
            i++;
        }

        // If we didn't move, or we hit the end, it's not Trionic
        if(i == startP || i == n - 1) return false;

        // Phase 2: Strictly Decreasing (p to q)
        int startQ = i;
        while(i < n - 1 && nums[i] > nums[i + 1])
        {
            i++;
        }

        // If we didn't move, or we hit the end, it's not Trionic
        if(i == startQ || i == n - 1) return false;

        // Phase 3: Strictly Increasing (q to n-1)
        int startEnd = i;
        while(i < n - 1 && nums[i] < nums[i + 1])
        {
            i++;
        }

        // Final Condition: we must have moved in Phase 3 AND reached the very last index
        return i > startEnd && i == n - 1;
    }
}