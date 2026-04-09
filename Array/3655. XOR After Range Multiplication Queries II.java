// QUESTION: 3655. XOR After Range Multiplication Queries II
// LINK: https://leetcode.com/problems/xor-after-range-multiplication-queries-ii/description/?envType=daily-question&envId=2026-04-09

// SOLUTION: Large step processing

import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;
    
    // Fast modular exponentiation
    private int modPow(long base, long exponent) {
        long result = 1;
        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD;
            exponent >>= 1;
        }
        return (int) result;
    }
    
    // Modular inverse using Fermat's Little Theorem
    private int modInverse(long value) {
        return modPow(value, MOD - 2);
    }
    
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int arraySize = nums.length;
        int threshold = (int) Math.sqrt(arraySize);
        
        // Group queries by step size for small k
        List<List<int[]>> smallStepQueries = new ArrayList<>(threshold);
        for (int i = 0; i < threshold; i++) {
            smallStepQueries.add(new ArrayList<>());
        }
        
        // Process each query
        for (int[] query : queries) {
            int left = query[0];
            int right = query[1];
            int step = query[2];
            int multiplier = query[3];
            
            if (step < threshold) {
                // Small step - store for batch processing
                smallStepQueries.get(step).add(new int[]{left, right, multiplier});
            } else {
                // Large step - process directly (few indices)
                for (int index = left; index <= right; index += step) {
                    nums[index] = (int)(((long)nums[index] * multiplier) % MOD);
                }
            }
        }
        
        // Process all small step sizes together
        long[] cumulativeMultiplier = new long[arraySize + threshold];
        
        for (int step = 1; step < threshold; step++) {
            if (smallStepQueries.get(step).isEmpty()) {
                continue;
            }
            
            // Initialize cumulative multipliers
            Arrays.fill(cumulativeMultiplier, 1);
            
            // Apply range updates using difference array technique
            for (int[] query : smallStepQueries.get(step)) {
                int left = query[0];
                int right = query[1];
                int multiplier = query[2];
                
                // Start of range
                cumulativeMultiplier[left] = (cumulativeMultiplier[left] * multiplier) % MOD;
                
                // End of range (exclusive)
                int rangeEnd = ((right - left) / step + 1) * step + left;
                cumulativeMultiplier[rangeEnd] = (cumulativeMultiplier[rangeEnd] * modInverse(multiplier)) % MOD;
            }
            
            // Propagate multipliers
            for (int index = step; index < arraySize; index++) {
                cumulativeMultiplier[index] = (cumulativeMultiplier[index] * cumulativeMultiplier[index - step]) % MOD;
            }
            
            // Apply to array
            for (int index = 0; index < arraySize; index++) {
                nums[index] = (int)(((long)nums[index] * cumulativeMultiplier[index]) % MOD);
            }
        }
        
        // Calculate final XOR
        int xorResult = 0;
        for (int value : nums) {
            xorResult ^= value;
        }
        return xorResult;
    }
}