// QUESTION: 3471. Find the Largest Almost Missing Integer
// LINK: https://leetcode.com/problems/find-the-largest-almost-missing-integer/description/?envType=daily-question&envId=2026-08-18

// SOLUTION: SLIDING WINDOW

class Solution {
    public int largestInteger(int[] nums, int k) {
        // Map: number -> number of subarrays it appears in
        Map<Integer, Integer> subarrayCount = new HashMap<>();
        
        // Map: number -> frequency in current window
        Map<Integer, Integer> windowFreq = new HashMap<>();
        
        // Process first window
        for (int i = 0; i < k; i++) {
            windowFreq.put(nums[i], windowFreq.getOrDefault(nums[i], 0) + 1);
        }
        
        // Count unique numbers in first window
        for (int num : windowFreq.keySet()) {
            subarrayCount.put(num, 1);
        }
        
        // Slide window
        for (int i = k; i < nums.length; i++) {
            // Remove left
            int left = nums[i - k];
            windowFreq.put(left, windowFreq.get(left) - 1);
            if (windowFreq.get(left) == 0) {
                windowFreq.remove(left);
            }
            
            // Add right
            int right = nums[i];
            windowFreq.put(right, windowFreq.getOrDefault(right, 0) + 1);
            
            // Count unique numbers in this new window
            for (int num : windowFreq.keySet()) {
                subarrayCount.put(num, subarrayCount.getOrDefault(num, 0) + 1);
            }
        }
        
        // Find largest with count = 1
        int result = -1;
        for (Map.Entry<Integer, Integer> entry : subarrayCount.entrySet()) {
            if (entry.getValue() == 1) {
                result = Math.max(result, entry.getKey());
            }
        }
        
        return result;
    }
}