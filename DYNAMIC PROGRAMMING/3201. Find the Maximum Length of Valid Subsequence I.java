QUESTION LINK: https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-i/description/?envType=daily-question&envId=2025-07-16



SOLUTION:

class Solution {
    public int maximumLength(int[] nums) {
        int result = 0;
        int[][] pattern = { { 0, 0}, {0, 1}, {1, 0}, {1, 1}};

        for(int[] patterns : pattern)
        {
            int count = 0;
            for(int num : nums)
            {
                if(num % 2 == patterns[count % 2])
                {
                    count++;
                }
            }
            result = Math.max(result, count);
        }
        return result;
    }
}