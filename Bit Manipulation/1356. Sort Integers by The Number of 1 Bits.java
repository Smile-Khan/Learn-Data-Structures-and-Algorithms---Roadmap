// QUESTION: 1356. Sort Integers by The Number of 1 Bits
// LINK: https://leetcode.com/problems/sort-integers-by-the-number-of-1-bits/description/?envType=daily-question&envId=2026-02-25

// SOLUTION: Approach 1: Convert to Integer[] (Simplest)

class Solution {
    public int[] sortByBits(int[] arr) {
        // Convert to Integer array for custome sorting
        Integer[] nums = new Integer[arr.length];
        for(int i = 0; i < arr.length; i++)
        {
            nums[i] = arr[i];
        }

        // Sort with custom comparator
        Arrays.sort(nums, (a, b) -> {
            int bitA = Integer.bitCount(a);
            int bitB = Integer.bitCount(b);

            if(bitA == bitB)
            {
                return a - b;   // sort by value
            }
            return bitA - bitB; // sort by bit count
        });

        // Convert back to int[]
        for(int i = 0; i < arr.length; i++)
        {
            arr[i] = nums[i];
        }
        return arr;
    }
}


// Approach 2: Using 2D Array

import java.util.*;

class Solution {
    public int[] sortByBits(int[] arr) {
        int n = arr.length;
        int[][] pairs = new int[n][2];
        
        // Store [number, bitCount] pairs
        for (int i = 0; i < n; i++) {
            pairs[i][0] = arr[i];
            pairs[i][1] = Integer.bitCount(arr[i]);
        }
        
        // Sort by bitCount, then by number
        Arrays.sort(pairs, (a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        
        // Extract sorted numbers
        for (int i = 0; i < n; i++) {
            arr[i] = pairs[i][0];
        }
        
        return arr;
    }
}


// Approach 3: Using List (Most Flexible)

import java.util.*;

class Solution {
    public int[] sortByBits(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int num : arr) {
            list.add(num);
        }
        
        Collections.sort(list, (a, b) -> {
            int bitA = Integer.bitCount(a);
            int bitB = Integer.bitCount(b);
            
            if (bitA == bitB) {
                return a - b;
            }
            return bitA - bitB;
        });
        
        // Convert back to array
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = list.get(i);
        }
        
        return result;
    }
}

// Approach 4: Bucket Sort (Most Efficient)

import java.util.*;

class Solution {
    public int[] sortByBits(int[] arr) {
        // Since numbers ≤ 10⁴, max bits = 14
        List<Integer>[] buckets = new ArrayList[15];
        for (int i = 0; i < 15; i++) {
            buckets[i] = new ArrayList<>();
        }
        
        // Group numbers by bit count
        for (int num : arr) {
            int bits = Integer.bitCount(num);
            buckets[bits].add(num);
        }
        
        // Sort each bucket and combine
        int[] result = new int[arr.length];
        int index = 0;
        for (int i = 0; i < 15; i++) {
            Collections.sort(buckets[i]);
            for (int num : buckets[i]) {
                result[index++] = num;
            }
        }
        
        return result;
    }
}