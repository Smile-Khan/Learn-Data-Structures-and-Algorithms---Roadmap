// QUESTION: 1018. Binary Prefix Divisible By 5
// LINK: https://leetcode.com/problems/binary-prefix-divisible-by-5/description/?envType=daily-question&envId=2025-11-24

// SOLUTION: 

class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        int n = nums.length;
        List<Boolean> result = new ArrayList<>(n);
        int currentMod = 0;
        
        for (int num : nums) {
            // Key insight: (a * 2 + b) mod 5 = ((a mod 5) * 2 + b) mod 5
            currentMod = (currentMod * 2 + num) % 5;
            result.add(currentMod == 0);
        }
        
        return result;
    }
}