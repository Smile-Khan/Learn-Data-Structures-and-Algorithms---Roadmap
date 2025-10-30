// QUESTION: 1526. Minimum Number of Increments on Subarrays to Form a Target Array
// LINK: https://leetcode.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/description/?envType=daily-question&envId=2025-10-30

// SOLUTION: GREEDY 

class Solution {
    public int minNumberOperations(int[] target) {
        int operations = target[0]; // Start with first element
        
        for (int i = 1; i < target.length; i++) {
            if (target[i] > target[i - 1]) {
                operations += target[i] - target[i - 1];
            }
        }
        
        return operations;
    }
}