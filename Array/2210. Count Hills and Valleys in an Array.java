QUESTION: 2210. Count Hills and Valleys in an Array
LINK: https://leetcode.com/problems/count-hills-and-valleys-in-an-array/description/?envType=daily-question&envId=2025-07-27

SOLUTION:

class Solution {
    public int countHillValley(int[] nums) {
        int count = 0;
        int n = nums.length;

        for (int i = 1; i < n - 1; i++) {
            // Skip duplicate neighbors (for same hill/valley group)
            if (nums[i] == nums[i - 1]) continue;

            // Find closest non-equal left neighbor
            int left = i - 1;
            while (left >= 0 && nums[left] == nums[i]) {
                left--;
            }

            // Find closest non-equal right neighbor
            int right = i + 1;
            while (right < n && nums[right] == nums[i]) {
                right++;
            }

            if (left >= 0 && right < n) {
                if (nums[i] > nums[left] && nums[i] > nums[right]) {
                    count++; // hill
                } else if (nums[i] < nums[left] && nums[i] < nums[right]) {
                    count++; // valley
                }
            }
        }

        return count;
    }
}