// QUESTION: 717. 1-bit and 2-bit Characters
// LINK: https://leetcode.com/problems/1-bit-and-2-bit-characters/description/?envType=daily-question&envId=2025-11-18

// SOLUTION: LINEAR SOLUTION 

class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int i = 0;
        int n = bits.length;
        
        while (i < n - 1) {
            if (bits[i] == 0) {
                i++; // Move 1 step for '0'
            } else {
                i += 2; // Move 2 steps for '1' (10 or 11)
            }
        }
        
        // If we land exactly on the last character, it's a one-bit character
        return i == n - 1;
    }
}

// Approach 2: Backward Traversal

class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int i = bits.length - 2; // Start from second last element
        
        // Count consecutive 1's before the last 0
        int onesCount = 0;
        while (i >= 0 && bits[i] == 1) {
            onesCount++;
            i--;
        }
        
        // If even number of consecutive 1's, last character is one-bit
        // If odd number, last character is part of two-bit character
        return onesCount % 2 == 0;
    }
}


// Approach 3: Greedy Decoding


class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length;
        if (n == 1) return true;
        
        int i = 0;
        boolean lastIsOneBit = false;
        
        while (i < n) {
            if (bits[i] == 0) {
                i++;
                lastIsOneBit = true;
            } else {
                i += 2;
                lastIsOneBit = false;
            }
        }
        
        return lastIsOneBit;
    }
}


//Approach 5: Most Optimal (Mathematical Insight)

class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length;
        int i = 0;
        
        while (i < n - 1) {
            i += bits[i] + 1; // 0 → +1, 1 → +2
        }
        
        return i == n - 1;
    }
}