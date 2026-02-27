// QUESTION: Number of submatrix have sum X
// LINK: https://www.geeksforgeeks.org/problems/number-of-submatrix-have-sum-k/1

// SOLUTION: Early Termination

class Solution {
    public int countSquare(int[][] mat, int x) {
        int n = mat.length;
        int m = mat[0].length;
        
        // Build prefix sum
        int[][] prefix = new int[n + 1][m + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                prefix[i][j] = mat[i-1][j-1] + prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1];
            }
        }
        
        int count = 0;
        int maxSize = Math.min(n, m);
        
        // Optimization: If x is extremely large or small, we can skip sizes
        // But with negatives, we can't really skip
        
        // However, we can compute min/max possible sum for each size
        // and skip if x is outside that range
        int[] minSumForSize = new int[maxSize + 1];
        int[] maxSumForSize = new int[maxSize + 1];
        
        // Initialize with extreme values
        for(int size = 1; size <= maxSize; size++) {
            minSumForSize[size] = Integer.MAX_VALUE;
            maxSumForSize[size] = Integer.MIN_VALUE;
        }
        
        // First pass: find min and max possible sums for each size
        for(int size = 1; size <= maxSize; size++) {
            for(int i = size; i <= n; i++) {
                for(int j = size; j <= m; j++) {
                    int sum = prefix[i][j] 
                            - prefix[i - size][j] 
                            - prefix[i][j - size] 
                            + prefix[i - size][j - size];
                    
                    minSumForSize[size] = Math.min(minSumForSize[size], sum);
                    maxSumForSize[size] = Math.max(maxSumForSize[size], sum);
                }
            }
        }
        
        // Second pass: only check sizes where x is within possible range
        for(int size = 1; size <= maxSize; size++) {
            // Skip if x is outside the possible range for this size
            if(x < minSumForSize[size] || x > maxSumForSize[size]) {
                continue;
            }
            
            for(int i = size; i <= n; i++) {
                for(int j = size; j <= m; j++) {
                    int sum = prefix[i][j] 
                            - prefix[i - size][j] 
                            - prefix[i][j - size] 
                            + prefix[i - size][j - size];
                    
                    if(sum == x) count++;
                }
            }
        }
        
        return count;
    }
}