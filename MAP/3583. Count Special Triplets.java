// QUESTION: 3583. Count Special Triplets
// LINK: https://leetcode.com/problems/count-special-triplets/description/?envType=daily-question&envId=2025-12-09

// SOLUTION: Approach 1: Brute Force (Triple Loop)

class Solution {
    public int specialTriplets(int[] nums) {
        int n = nums.length;
        int count = 0;
        int MOD = 1000000007;
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] == nums[j] * 2) {
                    for (int k = j + 1; k < n; k++) {
                        if (nums[k] == nums[j] * 2) {
                            count = (count + 1) % MOD;
                        }
                    }
                }
            }
        }
        
        return count;
    }
}


// Approach 2: Count for Each j (Optimized)

class Solution {
    public int specialTriplets(int[] nums) {
        int MOD = 1000000007;
        int n = nums.length;
        long count = 0;
        
        for (int j = 1; j < n - 1; j++) {
            int target = nums[j] * 2;
            
            // Count how many i < j have nums[i] == target
            int leftCount = 0;
            for (int i = 0; i < j; i++) {
                if (nums[i] == target) {
                    leftCount++;
                }
            }
            
            // Count how many k > j have nums[k] == target
            int rightCount = 0;
            for (int k = j + 1; k < n; k++) {
                if (nums[k] == target) {
                    rightCount++;
                }
            }
            
            count = (count + (long) leftCount * rightCount) % MOD;
        }
        
        return (int) count;
    }
}

// Approach 3: Frequency Array (Optimal)

class Solution {
    public int specialTriplets(int[] nums) {
        int MOD = 1000000007;
        int n = nums.length;
        
        // Maximum possible value in nums
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }
        
        // Frequency array for counting numbers
        int[] freq = new int[maxVal + 1];
        int[] leftFreq = new int[maxVal + 1];
        
        // First pass: count total frequency of each number
        for (int num : nums) {
            if (num <= maxVal) {
                freq[num]++;
            }
        }
        
        long total = 0;
        
        // Process from left to right
        for (int j = 0; j < n; j++) {
            int current = nums[j];
            
            // Remove current from right frequency
            freq[current]--;
            
            // Check if current * 2 is within bounds
            int target = current * 2;
            if (target <= maxVal) {
                // Count valid triplets: (i, j, k) where i<j and k>j
                long leftCount = leftFreq[target]; // i < j with nums[i] == target
                long rightCount = freq[target];    // k > j with nums[k] == target
                total = (total + leftCount * rightCount) % MOD;
            }
            
            // Add current to left frequency
            leftFreq[current]++;
        }
        
        return (int) total;
    }
}


// Approach 4: Most Efficient with HashMap

class Solution {
    public int specialTriplets(int[] nums) {
        int MOD = 1000000007;
        int n = nums.length;
        
        // leftCount[x] = number of times x appears before current position
        Map<Integer, Integer> leftCount = new HashMap<>();
        // rightCount[x] = number of times x appears after current position
        Map<Integer, Integer> rightCount = new HashMap<>();
        
        // Initialize rightCount with all numbers
        for (int num : nums) {
            rightCount.put(num, rightCount.getOrDefault(num, 0) + 1);
        }
        
        long total = 0;
        
        // Process each position as j
        for (int j = 0; j < n; j++) {
            int current = nums[j];
            
            // Remove current from rightCount
            rightCount.put(current, rightCount.get(current) - 1);
            if (rightCount.get(current) == 0) {
                rightCount.remove(current);
            }
            
            int target = current * 2;
            // Count triplets with nums[j] = current
            if (leftCount.containsKey(target) && rightCount.containsKey(target)) {
                long left = leftCount.get(target);
                long right = rightCount.get(target);
                total = (total + left * right) % MOD;
            }
            
            // Add current to leftCount
            leftCount.put(current, leftCount.getOrDefault(current, 0) + 1);
        }
        
        return (int) total;
    }
}

// Approach 5: Alternative with Two Passes
class Solution {
    public int specialTriplets(int[] nums) {
        int MOD = 1000000007;
        int n = nums.length;
        
        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();
        
        // Fill right map with all elements
        for (int num : nums) {
            right.put(num, right.getOrDefault(num, 0) + 1);
        }
        
        long result = 0;
        
        for (int j = 0; j < n; j++) {
            int val = nums[j];
            
            // Update right count (remove current element)
            right.put(val, right.get(val) - 1);
            if (right.get(val) == 0) {
                right.remove(val);
            }
            
            // Check for valid triplets
            int target = val * 2;
            if (left.containsKey(target) && right.containsKey(target)) {
                result = (result + (long) left.get(target) * right.get(target)) % MOD;
            }
            
            // Update left count (add current element)
            left.put(val, left.getOrDefault(val, 0) + 1);
        }
        
        return (int) result;
    }
}