// QUESTION: 3010. Divide an Array Into Subarrays With Minimum Cost I
// LINK: https://leetcode.com/problems/divide-an-array-into-subarrays-with-minimum-cost-i/description/?envType=daily-question&envId=2026-02-01

// SOLUTION: 
class Solution {
    public int minimumCost(int[] nums) {
        int totalCost = nums[0];

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for(int i = 1; i < nums.length; i++)
        {
            if(nums[i] < min1)
            {
                min2 = min1;
                min1 = nums[i];
            }
            else if(nums[i] < min2)
            {
                min2 = nums[i];
            }
        }
        return totalCost + min1 + min2;
    }
}