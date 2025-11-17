// QUESTION: 1437. Check If All 1's Are at Least Length K Places Away
// LINK: https://leetcode.com/problems/check-if-all-1s-are-at-least-length-k-places-away/description/?envType=daily-question&envId=2025-11-17

// SOLUTION:  Count Zeros Between Ones

class Solution {
    public boolean kLengthApart(int[] nums, int k) {
        int zeroCount = k; // Initialize with k to handle first 1
        
        for (int num : nums) {
            if (num == 1) {
                if (zeroCount < k) {
                    return false;
                }
                zeroCount = 0;
            } else {
                zeroCount++;
            }
        }
        return true;
    }
}