QUESTION: 1695. Maximum Erasure Value
LINK:  https://leetcode.com/problems/maximum-erasure-value/description/?envType=daily-question&envId=2025-07-22


SOLUTION:

class Solution {
    public int maximumUniqueSubarray(int[] nums) {

        Set<Integer> set = new HashSet<>();
        int left = 0;
        int right = 0;
        int currSum = 0;
        int maxSum = 0;

        while(right < nums.length)
        {
            while(set.contains(nums[right]))
            {
                set.remove(nums[left]);
                currSum -= nums[left];
                left++;
            }
            set.add(nums[right]);
            currSum += nums[right];
            maxSum = Math.max(maxSum, currSum);
            right++;
        }
        return maxSum;
    }
}