// QUESTION: 1877. Minimize Maximum Pair Sum in Array
// LINK: https://leetcode.com/problems/minimize-maximum-pair-sum-in-array/description/?envType=daily-question&envId=2026-01-24

// SOLUTION: TWO POINTER

class Solution {
    public int minPairSum(int[] nums) {
        int n = nums.length;

        Arrays.sort(nums);

        int maxPairSum = 0;
        int left = 0;
        int right = n - 1;

        while(left < right)
        {
            int currentPair = nums[left] + nums[right];

            maxPairSum = Math.max(maxPairSum, currentPair);
            left++;
            right--;
        }
        return maxPairSum;
    }
}


// SOLUTION: GREEDY

class Solution {
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);

        int result = 0;
        int n = nums.length;

        for(int i = 0; i < n / 2; i++)
        {
            // No need for a separate left and right variable if you have a brain
            int currentSum = nums[i] + nums[n - 1 - i];
            if(currentSum > result)
            {
                result = currentSum;
            }
        }
        return result;
    }
}