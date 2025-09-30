// QUESTION: 2221. Find Triangular Sum of an Array
// LINK : https://leetcode.com/problems/find-triangular-sum-of-an-array/?envType=daily-question&envId=2025-09-30


// SOLUTION: 

class Solution {
    public int triangularSum(int[] nums) {
        int n = nums.length;

        while(n > 1)
        {
            int[] newNums = new int[n-1];

            for(int i = 0; i < n-1; i++)
            {
                newNums[i] = (nums[i] + nums[i+1]) % 10;
            }

            nums = newNums;
            n--;
        }
        return nums[0];
    }
}