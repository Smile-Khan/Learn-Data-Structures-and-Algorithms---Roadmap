// QUESTION: 2154. Keep Multiplying Found Values by Two
// LINK: https://leetcode.com/problems/keep-multiplying-found-values-by-two/description/?envType=daily-question&envId=2025-11-19

// SOLUTION: Linear Search with While Loop

class Solution {
    public int findFinalValue(int[] nums, int original) {
        boolean found;
        
        do {
            found = false;
            for (int num : nums) {
                if (num == original) {
                    original *= 2;
                    found = true;
                    break;
                }
            }
        } while (found);
        
        return original;
    }
}


// Approach 2: HashSet for Fast Lookup

class Solution {
    public int findFinalValue(int[] nums, int original) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        
        while (set.contains(original)) {
            original *= 2;
        }
        
        return original;
    }
}


// APPROEACH 3: Boolean Array for Constant Time Lookup
class Solution {
    public int findFinalValue(int[] nums, int original) {
        boolean[] exists = new boolean[1001]; // Since nums[i] <= 1000
        
        for (int num : nums) {
            exists[num] = true;
        }
        
        while (original <= 1000 && exists[original]) {
            original *= 2;
        }
        
        return original;
    }
}

// APPROACH 4: Sorting and Binary Search
class Solution {
    public int findFinalValue(int[] nums, int original) {
        Arrays.sort(nums);
        
        while (binarySearch(nums, original)) {
            original *= 2;
        }
        
        return original;
    }
    
    private boolean binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return false;
    }
}


// APPROACH 5: MATHEMATICAL INSIGHT (Bit Manipulation)
class Solution {
    public int findFinalValue(int[] nums, int original) {
        // Keep doubling until original is not in nums
        while (contains(nums, original)) {
            original <<= 1; // Multiply by 2 using bit shift
        }
        return original;
    }
    
    private boolean contains(int[] nums, int target) {
        for (int num : nums) {
            if (num == target) {
                return true;
            }
        }
        return false;
    }
}