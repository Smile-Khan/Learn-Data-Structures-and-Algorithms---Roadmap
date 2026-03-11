// QUESTION: 1009. Complement of Base 10 Integer
// LINK: https://leetcode.com/problems/complement-of-base-10-integer/description/?envType=daily-question&envId=2026-03-11

// SOLUTION: 

class Solution {
    public int bitwiseComplement(int n) {
        if (n == 0) return 1;

        int mask = 0;
        int temp = n;

        while(temp > 0)
        {
            mask = (mask << 1) | 1;
            temp >>= 1;
        }
        return n ^ mask;
    }
}


// APPROACH 2: 

class Solution {
    public int findComplement(int n) {
        // Special case: 0's complement is 1
        if (n == 0) return 1;
        
        // Find the highest set bit (e.g., for 5 (101), highest is 4 (100))
        int highestBit = Integer.highestOneBit(n);
        
        // Create mask with all 1's of same length (e.g., for 5, mask = 7 (111))
        int mask = (highestBit << 1) - 1;
        
        // XOR flips all bits
        return n ^ mask;
    }
}