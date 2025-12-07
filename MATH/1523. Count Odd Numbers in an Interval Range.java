// QUESTION: 1523. Count Odd Numbers in an Interval Range
// LINK: https://leetcode.com/problems/count-odd-numbers-in-an-interval-range/description/?envType=daily-question&envId=2025-12-07

// SOLUTION: 

class Solution {
    public int countOdds(int low, int high) {
        // Using bitwise operations
        return ((high & 1) == 1 ? (high + 1) / 2 : high / 2)
        -((low & 1) == 1 ? low / 2 : (low + 1) / 2);
    }
}