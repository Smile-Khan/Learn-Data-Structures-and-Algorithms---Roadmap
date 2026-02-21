// QUESTION: 762. Prime Number of Set Bits in Binary Representation
// LINK: https://leetcode.com/problems/prime-number-of-set-bits-in-binary-representation/description/?envType=daily-question&envId=2026-02-21

// SOLUTION: 

class Solution {
    public int countPrimeSetBits(int left, int right) {
        // The Magic Mask: 665770 in decimal.
        // Binary: 010100100100010101100
        // Bits set at indices: 2, 3, 5, 7, 11, 13, 17, 19
        // Why calculate it at runtime when I can just know it?
        final int PRIME_MASK = 0b10100010100010101100; 

        int totalCount = 0;
        for (int i = left; i <= right; i++) {
            // Integer.bitCount uses the POPCNT instruction on modern CPUs.
            // It is the absolute pinnacle of efficiency.
            if (((PRIME_MASK >> Integer.bitCount(i)) & 1) == 1) {
                totalCount++;
            }
        }

        return totalCount;
    }
}