// QUESTION: 3370. Smallest Number With All Set Bits
// LINK: https://leetcode.com/problems/smallest-number-with-all-set-bits/description/?envType=daily-question&envId=2025-10-29

// SOLUTION: Approach 1: Find Next Power of 2 Minus 1



class Solution {
    public int smallestNumber(int n) {
        // Find the smallest number with all bits set that is >= n
        // These numbers are of the form 2^k - 1
        
        // If n is already all bits set, return n
        if ((n & (n + 1)) == 0) {
            return n;
        }
        
        // Find the next power of 2 greater than n
        int power = 1;
        while (power <= n) {
            power <<= 1;
        }
        
        // Return power - 1 (which has all bits set)
        return power - 1;
    }
}


//Approach 2: Using Bit Manipulation

class Solution {
    public int smallestNumber(int n) {
        // Numbers with all bits set: 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023...
        
        // If n is already all bits set, return n
        if ((n & (n + 1)) == 0) {
            return n;
        }
        
        // Set all bits after the highest set bit
        int mask = n;
        mask |= mask >> 1;
        mask |= mask >> 2;
        mask |= mask >> 4;
        mask |= mask >> 8;
        
        return mask;
    }
}


// Approach 3: Binary Search on Possible Values

class Solution {
    public int smallestNumber(int n) {
        // Precompute all numbers with all bits set up to 1023
        int[] allOnes = {1, 3, 7, 15, 31, 63, 127, 255, 511, 1023};
        
        // Binary search for the smallest number >= n
        int left = 0, right = allOnes.length - 1;
        int result = 1023;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (allOnes[mid] >= n) {
                result = allOnes[mid];
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return result;
    }
}


// Approach 4: Simple Iteration

class Solution {
    public int smallestNumber(int n) {
        // Since n <= 1000, we can simply check all possible numbers
        for (int x = n; x <= 1023; x++) {
            if ((x & (x + 1)) == 0) {
                return x;
            }
        }
        return 1023; // Should never reach here for n <= 1000
    }
}


// Approach 5: Mathematical Approach

class Solution {
    public int smallestNumber(int n) {
        // Numbers with all bits set: 2^1 - 1, 2^2 - 1, 2^3 - 1, ...
        
        if (n == 1) return 1;
        
        int k = (int)(Math.log(n) / Math.log(2)) + 1;
        int candidate = (1 << k) - 1;
        
        // If our candidate is less than n, we need the next one
        if (candidate < n) {
            candidate = (1 << (k + 1)) - 1;
        }
        
        return candidate;
    }
}


// Approach 6: Most Efficient Bit Manipulation


class Solution {
    public int smallestNumber(int n) {
        // If n is already all ones, return it
        if ((n & (n + 1)) == 0) {
            return n;
        }
        
        // Find the position of the most significant bit
        int msbPos = 0;
        int temp = n;
        while (temp > 0) {
            msbPos++;
            temp >>= 1;
        }
        
        // The smallest number with all bits set and >= n is (1 << msbPos) - 1
        return (1 << msbPos) - 1;
    }
}