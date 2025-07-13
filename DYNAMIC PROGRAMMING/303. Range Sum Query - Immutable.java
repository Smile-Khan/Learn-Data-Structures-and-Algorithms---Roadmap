//PROBLEM LINK: https://leetcode.com/problems/range-sum-query-immutable/description/
//
//
//Problem Statement
//Given an integer array nums, handle multiple queries to calculate the sum of elements between indices left and right (inclusive). The solution should efficiently answer these queries after an initial setup.
//
//        Key Insights
//Prefix Sum Array: Precompute cumulative sums to answer range queries in constant time.
//
//Efficiency: Avoid recalculating sums for each query by storing prefix sums.
//
//Edge Handling: Correctly handle the case where left is 0 to avoid index errors.
//
//Optimal Approach
//Prefix Sum Initialization:
//
//Compute a prefix sum array where prefix[i] is the sum of elements from nums[0] to nums[i].
//
//Query Processing:
//
//For a query [left, right], the sum is prefix[right] - prefix[left - 1] (if left > 0).
//
//If left == 0, the sum is simply prefix[right].
//
//Time Complexity
//Initialization: O(n) (computing prefix sums).
//
//Query: O(1) per query.
//
//Space Complexity
//O(n): For storing the prefix sum array.
//
//Edge Cases
//Single Element:
//
//nums = [5], sumRange(0, 0) → 5.
//
//Full Range:
//
//nums = [1, 2, 3], sumRange(0, 2) → 6.
//
//Negative Numbers:
//
//nums = [-1, -2, -3], sumRange(1, 2) → -5.
//
//Solution Code

class NumArray {
    private int[] prefix;

    public NumArray(int[] nums) {
        int n = nums.length;
        prefix = new int[n];
        prefix[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        if (left == 0) {
            return prefix[right];
        } else {
            return prefix[right] - prefix[left - 1];
        }
    }
}