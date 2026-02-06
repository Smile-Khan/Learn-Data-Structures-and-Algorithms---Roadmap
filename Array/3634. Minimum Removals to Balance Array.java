// QUESTION: 3634. Minimum Removals to Balance Array
// LINK: https://leetcode.com/problems/minimum-removals-to-balance-array/description/?envType=daily-question&envId=2026-02-06

// SOLUTION: SLIDING WINDOW

class Solution {
    public int minRemoval(int[] nums, int k) {
        Arrays.sort(nums);

        int n = nums.length;
        int maxHeap = 0;
        int right = 0;

        // Step 2: The Two-Pointer Siege. O(N)
        for(int left = 0; left < n; left++)
        {
            // Calculate the power threshold once
            long threshold = (long) nums[left] * k;

            // Expand the right flank as fas as the threshold allows
            while(right < n && nums[right] <= threshold)
            {
                right++;
            }

            // the size of our current balanced squad
            int currentSquad = right - left;
            if(currentSquad > maxHeap)
            {
                maxHeap = currentSquad;
            }

        }
        return n - maxHeap;
    }
}