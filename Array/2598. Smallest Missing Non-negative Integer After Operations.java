// QUESTION: 2598. Smallest Missing Non-negative Integer After Operations
// LINK: https://leetcode.com/problems/smallest-missing-non-negative-integer-after-operations/description/?envType=daily-question&envId=2025-10-16

// SOLUTION: GREEDY 


class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        int n = nums.length;
        int[] counts = new int[value];

        int mex = 0;
        int rem = 0;

        for(int num : nums)
        {
            rem = ((num % value) + value) % value;
            counts[rem]++;
        }

        while(true)
        {
            // Which bucket do we need for the number 'mex'
            int bucket_needed = mex % value;

            // Do we have any numbers in that bucket ?
            if(counts[bucket_needed] > 0)
            {
                // Yes! Use one up
                counts[bucket_needed]--;
                // And now, try to build the 'next' number in the sequence.
                mex++;
            }
            else 
            {
                break;
            }
        }
        return mex;
    }
}