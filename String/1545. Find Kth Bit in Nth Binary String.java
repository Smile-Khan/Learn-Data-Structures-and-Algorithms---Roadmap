// QUESTION: 1545. Find Kth Bit in Nth Binary String
// LINK: https://leetcode.com/problems/find-kth-bit-in-nth-binary-string/description/?envType=daily-question&envId=2026-03-03

// SOLUTION: 

class Solution {
    public char findKthBit(int n, int k) {
        if(n == 1) return '0';

        int len = (1 << n) - 1; // 2 ^ n - 1
        int mid = len / 2 + 1;  // middle position (1-indexed)

        if(k == mid)
        {
            return '1';
        }
        else if(k < mid)
        {
            return findKthBit(n - 1, k);
        }
        else
        {
            // k > mid: find in right half
            int newK = len - k + 1; // mirror position
            char bit = findKthBit(n - 1, newK);
            return bit == '0' ? '1' : '0';
        }
    }
}