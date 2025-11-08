// QUESTION: 1611. Minimum One Bit Operations to Make Integers Zero
// LINK: https://leetcode.com/problems/minimum-one-bit-operations-to-make-integers-zero/description/?envType=daily-question&envId=2025-11-08

// SOLUTION: Approach 1: Iterative with XOR Pattern


class Solution {
    public int minimumOneBitOperations(int n) {
        int result = 0;
        int powerOfTwo = 1;

        while (n > 0) {
            if ((n & 1) == 1) {
                result ^= powerOfTwo - 1;
            }
            n >>= 1;
            powerOfTwo <<= 1;
        }

        return result;
    }
}


//Approach 2: Recursive with MSB  


class Solution {
    public int minimumOneBitOperations(int n) {
        if(n == 0) return 0;
        
        // Find position of MSB
        int k = 0;
        int temp = n;
        while(temp > 0) {
            temp >>= 1;
            k++;
        }
        k--; // Adjust for 0-indexing
        
        // Formula: 2^(k+1) - 1 - f(n ^ 2^k)
        int power = (1 << (k + 1)) - 1;
        int remaining = n ^ (1 << k);
        
        return power - minimumOneBitOperations(remaining);
    }
}