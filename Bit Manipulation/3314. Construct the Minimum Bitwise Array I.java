// QUESTION: 3314. Construct the Minimum Bitwise Array I
// LINK: https://leetcode.com/problems/construct-the-minimum-bitwise-array-i/?envType=daily-question&envId=2026-01-20

// SOLUTION: 

class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];

        for(int i = 0; i < n; i++)
        {
            int t = nums.get(i);
            if(t == 2)
            {
                ans[i] = -1;
            }
            else 
            {
                for(int b = 0; b < 31; b++)
                {
                    if(((t >> b) & 1) == 0)
                    {
                        ans[i] = t ^ (1 << (b - 1));
                        break;
                    }
                }
            }
        }
        return ans;
    }
}