// QUESTION: 3379. Transformed Array
// LINK: https://leetcode.com/problems/transformed-array/description/?envType=daily-question&envId=2026-02-05

// SOLUTION: 

class Solution {
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        for(int i = 0; i < n; i++)
        {
            int steps = nums[i];

            if(steps == 0)
            {
                result[i] = nums[i];
            }
            else if(steps > 0)
            {
                // Move right
                int newIndex = (i + steps) % n;
                result[i] = nums[newIndex];
            }
            else
            {
                // Move left (steps is negative)
                int absSteps = Math.abs(steps);
                // Handle circular left move
                int newIndex = (i - (absSteps % n) + n) % n;
                result[i] = nums[newIndex];
            }
        }
        return result;
    }
}