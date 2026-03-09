// QUESTION: 3129. Find All Possible Stable Binary Arrays I
// LINK: https://leetcode.com/problems/find-all-possible-stable-binary-arrays-i/description/?envType=daily-question&envId=2026-03-09

// SOLUTION: DFS (MEMO)

class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {
        int MOD = 1_000_000_007;
        Long[][][] memo = new Long[zero + 1][one + 1][2];
        return (int) ((dfs(zero, one, 0, limit, memo) + dfs(zero, one, 1, limit, memo)) % MOD);
    }
    
    private long dfs(int zeros, int ones, int last, int limit, Long[][][] memo) {
        if (zeros == 0 && ones == 0) return 1;
        if (zeros < 0 || ones < 0) return 0;
        if (memo[zeros][ones][last] != null) return memo[zeros][ones][last];
        
        long count = 0;
        int MOD = 1_000_000_007;
        
        if (last == 0) {
            // Last was 0, now we must add 1s (can't add more 0s consecutively)
            for (int k = 1; k <= Math.min(ones, limit); k++) {
                count = (count + dfs(zeros, ones - k, 1, limit, memo)) % MOD;
            }
        } else {
            // Last was 1, now we must add 0s (can't add more 1s consecutively)
            for (int k = 1; k <= Math.min(zeros, limit); k++) {
                count = (count + dfs(zeros - k, ones, 0, limit, memo)) % MOD;
            }
        }
        
        return memo[zeros][ones][last] = count;
    }
}


// APPROACH 2: DYNAMIC PROGRAMMING (TABULATION)

class Solution {
    public int numberOfStableArrays(int requiredZeros, int requiredOnes, int streakLimit) {
        long MODULUS = 1_000_000_007L;
        
        // dpZeros[i][j] = ways to form array with i zeros and j ones ending with 0
        // dpOnes[i][j] = ways to form array with i zeros and j ones ending with 1
        long[][] dpZeros = new long[requiredZeros + 1][requiredOnes + 1];
        long[][] dpOnes = new long[requiredZeros + 1][requiredOnes + 1];
        
        // Initialize base cases
        for (int zeroCnt = 1; zeroCnt <= Math.min(requiredZeros, streakLimit); zeroCnt++) {
            dpZeros[zeroCnt][0] = 1;
        }
        for (int oneCnt = 1; oneCnt <= Math.min(requiredOnes, streakLimit); oneCnt++) {
            dpOnes[0][oneCnt] = 1;
        }
        
        // Build solution bottom-up
        for (int zeroUsed = 1; zeroUsed <= requiredZeros; zeroUsed++) {
            for (int oneUsed = 1; oneUsed <= requiredOnes; oneUsed++) {
                // Case 1: Current sequence ends with 0
                long sequencesEndingZero;
                if (zeroUsed > streakLimit) {
                    // Need to subtract invalid sequences with too many consecutive zeros
                    sequencesEndingZero = dpZeros[zeroUsed - 1][oneUsed] + 
                                         dpOnes[zeroUsed - 1][oneUsed] - 
                                         dpOnes[zeroUsed - streakLimit - 1][oneUsed];
                } else {
                    sequencesEndingZero = dpZeros[zeroUsed - 1][oneUsed] + 
                                         dpOnes[zeroUsed - 1][oneUsed];
                }
                
                // Case 2: Current sequence ends with 1
                long sequencesEndingOne;
                if (oneUsed > streakLimit) {
                    // Need to subtract invalid sequences with too many consecutive ones
                    sequencesEndingOne = dpOnes[zeroUsed][oneUsed - 1] + 
                                        dpZeros[zeroUsed][oneUsed - 1] - 
                                        dpZeros[zeroUsed][oneUsed - streakLimit - 1];
                } else {
                    sequencesEndingOne = dpOnes[zeroUsed][oneUsed - 1] + 
                                        dpZeros[zeroUsed][oneUsed - 1];
                }
                
                // Store results with proper modulo handling
                dpZeros[zeroUsed][oneUsed] = (sequencesEndingZero % MODULUS + MODULUS) % MODULUS;
                dpOnes[zeroUsed][oneUsed] = (sequencesEndingOne % MODULUS + MODULUS) % MODULUS;
            }
        }
        
        // Final answer
        long totalValidArrays = (dpZeros[requiredZeros][requiredOnes] + 
                                dpOnes[requiredZeros][requiredOnes]) % MODULUS;
        
        return (int) totalValidArrays;
    }
}