// QUESTION:- 643. Maximum Average Subarray I
// LINK:- https://leetcode.com/problems/maximum-average-subarray-i/description/

//SOLUTION: SLIDING WINDOW
//
//⏱ Time & Space Complexity
//Time: O(n)
//
//Space: O(1) (no extra space except variables)

class Solution {
    public double findMaxAverage(int[] nums, int k) {
        double currentSum = 0;

        // Sum of the first window
        for (int i = 0; i < k; i++) {
            currentSum += nums[i];
        }

        double maxSub = currentSum;  // Initialize max with the first window sum

        // Slide the window
        for (int i = k; i < nums.length; i++) {
            currentSum += nums[i];         // Add new element
            currentSum -= nums[i - k];     // Remove leftmost element of previous window
            maxSub = Math.max(maxSub, currentSum); // Update max
        }

        return maxSub / k;
    }
}
