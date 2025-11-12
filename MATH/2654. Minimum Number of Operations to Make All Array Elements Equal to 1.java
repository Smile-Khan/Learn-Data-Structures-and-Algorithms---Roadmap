// QUESTION: 2654. Minimum Number of Operations to Make All Array Elements Equal to 1
// LINK: https://leetcode.com/problems/minimum-number-of-operations-to-make-all-array-elements-equal-to-1/description/?envType=daily-question&envId=2025-11-12

// Approach 1: Greedy - Find Shortest Subarray with GCD

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        
        // Count existing ones
        int onesCount = 0;
        for (int num : nums) {
            if (num == 1) onesCount++;
        }
        
        // If there's at least one 1, we can spread it
        if (onesCount > 0) {
            return n - onesCount;
        }
        
        // Find the shortest subarray with GCD = 1
        int minLen = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            int currentGcd = nums[i];
            for (int j = i; j < n; j++) {
                currentGcd = gcd(currentGcd, nums[j]);
                if (currentGcd == 1) {
                    minLen = Math.min(minLen, j - i + 1);
                    break;
                }
            }
        }
        
        if (minLen == Integer.MAX_VALUE) {
            return -1;
        }
        
        // Operations = (minLen - 1) to create first 1 + (n - 1) to spread it
        return (minLen - 1) + (n - 1);
    }
    
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}


// Approach 2: Optimized GCD Precomputation

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        
        // Check for existing ones
        int ones = 0;
        for (int num : nums) {
            if (num == 1) ones++;
        }
        if (ones > 0) return n - ones;
        
        // Find minimum length to get GCD = 1
        int minDistance = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            int g = nums[i];
            for (int j = i; j < n; j++) {
                g = gcd(g, nums[j]);
                if (g == 1) {
                    minDistance = Math.min(minDistance, j - i);
                    break;
                }
            }
        }
        
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance + n - 1;
    }
    
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}


//Approach 3: Most Efficient - Early Termination

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        
        // Count ones first
        int oneCount = 0;
        for (int num : nums) {
            if (num == 1) oneCount++;
        }
        
        if (oneCount > 0) {
            return n - oneCount;
        }
        
        // Find the minimum operations to create first 1
        int minOpsToCreateOne = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            int currentGcd = nums[i];
            for (int j = i; j < n; j++) {
                currentGcd = gcd(currentGcd, nums[j]);
                if (currentGcd == 1) {
                    minOpsToCreateOne = Math.min(minOpsToCreateOne, j - i);
                    break;
                }
            }
        }
        
        if (minOpsToCreateOne == Integer.MAX_VALUE) {
            return -1;
        }
        
        return minOpsToCreateOne + (n - 1);
    }
    
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}


// Approach 4: Using GCD Table

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        
        // Check if any element is 1
        for (int num : nums) {
            if (num == 1) {
                // Count non-ones
                int count = 0;
                for (int x : nums) {
                    if (x != 1) count++;
                }
                return count;
            }
        }
        
        // Precompute GCD for all subarrays
        int minLen = n + 1;
        
        for (int i = 0; i < n; i++) {
            int g = nums[i];
            for (int j = i; j < n; j++) {
                g = gcd(g, nums[j]);
                if (g == 1) {
                    minLen = Math.min(minLen, j - i + 1);
                    break;
                }
            }
        }
        
        if (minLen > n) return -1;
        
        // Operations: (minLen - 1) to create first 1 + (n - 1) to convert others
        return (minLen - 1) + (n - 1);
    }
    
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}